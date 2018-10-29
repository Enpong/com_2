package com.book.common.security;

import com.book.user.entity.Permission;
import com.book.user.entity.Role;
import com.book.user.entity.User;
import com.book.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    /**
     *@Date 2018/8/28 11:40
     *@Description 授权
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//         添加数据表 开启功能
        String username = (String) principalCollection.getPrimaryPrincipal();
        User user = userService.findUserByRealName(username);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for (Role role : user.getRoleList()){
            info.addRole(role.getRole());
            for (Permission permission : role.getPermissionList()){
                info.addStringPermission(permission.getPermission());
            }
        }
        return info;
    }

    /**
    *@Date 2018/8/28 11:40
    *@Description 认证
    **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
//
        User user = userService.findUserByUsername(username);
        if(user == null){
            return null;
        }else {
            AuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername() , user.getPassword() , getName());
            SecurityUtils.getSubject().getSession().setAttribute("userinfo" , user);
            return info;
        }
    }

}
