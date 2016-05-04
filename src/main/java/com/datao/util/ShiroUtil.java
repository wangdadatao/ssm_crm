package com.datao.util;

import com.datao.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Created by 海涛 on 2016/5/3.
 */
public class ShiroUtil {

    /**
     * 判断是否是登陆状态
     * @return
     */
    public static boolean isCurrentUser(){
        return getCurrentUser() != null;
    }

    /**
     * 返回Subject
     * @return
     */
    public static Subject getSubject(){
        return SecurityUtils.getSubject();
    }

    /**
     * 查询当前的登录的用户
     * @return
     */
    public static User getCurrentUser(){
        return (User) getSubject().getPrincipal();
    }

    /**
     * 判断当前用户是否是管理员
     * @return
     */
    public static boolean isAdmin(){
        return getSubject().hasRole("管理员");
    }

    /**
     * 判断当前用户是否是经理
     * @return
     */
    public static boolean isManager(){
        return getSubject().hasRole("经理");
    }

    /**
     * 判断当前用户是否是普通员工
     * @return
     */
    public static boolean isEmployee(){
        return getSubject().hasRole("员工");
    }

    /**
     * 查询当前登录者 用户名
     * @return
     */
    public static String getCurrentUserName() {
        return getCurrentUser().getUsername();
    }
    /**
     * 查询当前登录者 用户Id
     * @return
     */
    public static Integer getCurrentUserId() {
        return getCurrentUser().getId();
    }

}
