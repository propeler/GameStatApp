package com.olukelom.gamestat.servlets.auth;

import com.olukelom.gamestat.model.User;
import javax.servlet.http.HttpSession;

public class SessionManager {

    public static final String TARGET_ATTRIBUTE = "user";

    public static void doUserLogin(HttpSession session, User user) {
        session.setAttribute(TARGET_ATTRIBUTE, user);
    }

    public static void doUserLogout(HttpSession session) {
        session.removeAttribute(TARGET_ATTRIBUTE);
    }
}
