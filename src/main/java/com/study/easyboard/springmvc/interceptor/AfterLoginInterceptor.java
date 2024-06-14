package com.study.easyboard.springmvc.interceptor;

import com.study.easyboard.springmvc.util.LoginUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class AfterLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.debug("AfterLoginInterceptor: request URI is {}", requestURI);

        // Board 경로에 대해서는 실행하지 않도록 함
        if (requestURI.startsWith("/board")) {
            log.debug("Request to /board/**, not applying AfterLoginInterceptor");
            return true;
        }

        if (LoginUtil.isLoggedIn(request.getSession())) {
            log.debug("User is logged in, redirecting to home");
            response.sendRedirect("/");
            return false;
        }
        return true;
    }
}
