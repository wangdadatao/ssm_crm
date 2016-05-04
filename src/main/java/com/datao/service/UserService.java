package com.datao.service;

import com.datao.mapper.RoleMapper;
import com.datao.mapper.UserMapper;
import com.datao.pojo.User;
import com.datao.util.ConfigPro;
import com.datao.error.DataAccessException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by 海涛 on 2016/4/28.
 */
@Named
@Transactional
public class UserService {

    @Inject
    private UserMapper userMapper;

    @Inject
    private RoleMapper roleMapper;

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
            //将登陆用户放到Session中。
            Session session = subject.getSession();
            User user = (User) subject.getPrincipal();
            user.setRoles(roleMapper.findRoles(user.getId()));

            session.setAttribute("user", user);

        } catch (RuntimeException e) {
            throw new DataAccessException(e.getMessage());
        }

    }

    /**
     * 根据用户的手机号查找到用户
     *
     * @param tel
     * @return
     */
    public User findByUserTel(String tel) {
        return userMapper.findByTel(tel);
    }

    public void logOut() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }

    /**
     * 查询所有的用户
     * @return
     */
    public List<User> finAllUser() {
        return userMapper.findAll();
    }
}
