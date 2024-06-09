package com.study.easyboard.springmvc.interceptor;

import com.study.easyboard.springmvc.util.LoginUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
@Slf4j
public class BoardInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!LoginUtil.isLoggedIn(request.getSession())) {
            String redirectUri = request.getRequestURI();
            log.info("origin: {}", redirectUri);
            response.sendRedirect("/members/sign-in?message=login-required&redirect=" + redirectUri);
            return false;
        }
        return true;
    }
}

