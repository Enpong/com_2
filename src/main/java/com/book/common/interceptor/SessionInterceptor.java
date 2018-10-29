package com.book.common.interceptor;

import com.book.user.entity.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionInterceptor implements HandlerInterceptor {
    
    /**
    *@Date 2018/8/31 16:48
    *@Description 检验
    **/
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String uri = httpServletRequest.getRequestURI();
        if (uri.contains("login")){
            return true;
        }

        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("userinfo");
        if (user != null){
            return true;
        }

        httpServletRequest.getRequestDispatcher("/login").forward(httpServletRequest,httpServletResponse);

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
