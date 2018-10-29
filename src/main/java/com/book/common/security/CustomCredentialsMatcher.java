package com.book.common.security;

import com.book.common.util.MD5Utils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class CustomCredentialsMatcher  extends SimpleCredentialsMatcher {

    /**
    *@Date 2018/8/28 11:40
    *@Description 自定义密码检验
    **/
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
//          账号密码 先提交到match进行校验
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String password = String.valueOf(usernamePasswordToken.getPassword());
        Object tokenCredentials = MD5Utils.enctpty(password);
        Object accountCredentials = getCredentials(info);
        if (tokenCredentials.equals(accountCredentials)){
            return true;
        }

        return false;
    }
}

