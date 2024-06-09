package com.study.easyboard.springmvc.util;

import com.study.easyboard.springmvc.chap05.dto.response.LoginUserInfoDto;

import javax.servlet.http.HttpSession;

public class LoginUtil {

    public static final String LOGIN = "login";

    public static boolean isLoggedIn(HttpSession session) {
        return session.getAttribute(LOGIN) != null;
    }

    public static String getLoggedInUserAccount(HttpSession session) {
        LoginUserInfoDto currentUser = (LoginUserInfoDto) session.getAttribute(LOGIN);
        return (currentUser != null) ? currentUser.getAccount() : null;
    }
}
