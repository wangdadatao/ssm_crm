package com.datao.service;

import com.datao.mapper.RoleMapper;
import com.datao.pojo.Role;
import com.datao.pojo.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by 海涛 on 2016/4/28.
 */
@Named
public class ShiroDbRealm extends AuthorizingRealm {

    @Inject
    private UserService userService;

    @Inject
    private RoleMapper roleMapper;

    /**
     * 验证权限
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        User shiroUser = (User) principalCollection.getPrimaryPrincipal();

        User user = userService.findByUserTel(shiroUser.getTel());

        if (user != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            //回去用户对应的角色列表
            List<Role> roles = roleMapper.findRoles(user.getId());

            for (Role r : roles) {
                info.addRole(r.getRolename());
            }

            return info;

        }

        return null;
    }

    /**
     * 验证登录
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;

        String tel = usernamePasswordToken.getUsername();

        User user = userService.findByUserTel(tel);

        if (user != null) {
            return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        }

        return null;
    }
}
