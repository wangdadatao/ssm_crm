package com.datao.service;

import com.datao.mapper.UserMapper;
import com.datao.pojo.User;
import com.datao.util.ConfigPro;
import com.datao.util.DataAccessException;
import org.apache.commons.codec.digest.DigestUtils;
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
     *
     * @param user
     */
    public void userLog(User user, HttpServletRequest request) {

        User user1 = userMapper.findByTel(user.getTel());

        String password = user.getPassword();
        password = DigestUtils.md5Hex(password + ConfigPro.get("user.password.salt"));

        if (user1 != null) {

            if (user1.getPassword().equals(password)) {
                request.getSession().setAttribute("user", user1);
            } else {
                throw new DataAccessException("帐号或密码错误！");
            }

        } else {
            throw new DataAccessException("帐号或密码错误！");
        }
    }
}
