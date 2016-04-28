package com.datao.service;

import com.datao.mapper.UserMapper;
import com.datao.pojo.User;
import com.datao.util.ConfigPro;
import com.datao.util.DataAccessException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by 海涛 on 2016/4/28.
 */
@Named
@Transactional
public class UserService {

    @Inject
    private UserMapper userMapper;

    /**
     * 用户登录
     */
    public void userLog(String tel, String password) {

        //获取认证主体，如果主体已存在，则退出主体
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }

        password = DigestUtils.md5Hex(password + ConfigPro.get("user.password.salt"));

        try {
            //登录，调用Shiro中的登录认证方法
            subject.login(new UsernamePasswordToken(tel, password));
        } catch (AuthenticationException e) {
            throw new DataAccessException("帐号或密码错误！");
        }

    }

    /**
     * 根据用户的手机号查找到用户
     * @param tel
     * @return
     */
    public User findByUserTel(String tel) {
        return userMapper.findByTel(tel);
    }
}
