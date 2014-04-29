package com.olukelom.gamestat.servlets.auth;

import com.olukelom.gamestat.dao.DAOManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Login", value = "/login")

public class LoginServlet extends HttpServlet {

    private final static String LOGIN_FORM = "/WEB-INF/pages/Login.jsp";
    private final static String HOME = "/home";

    private final static String MESSAGE = "message";
    private final static String LOGIN = "login";
    private final static String PASSWORD = "password";

    private final static String LOGIN_MESSAGE = "Identify yourself.";
    private final static String LOGIN_ERROR_MESSAGE = "Wrong login/password!<br>Try again.";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(MESSAGE, LOGIN_MESSAGE);
        request.getRequestDispatcher(LOGIN_FORM).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        if (DAOManager.getUserDAO().isValidUser(login, password)) {
            doLoginSuccess(request, response, login);
        } else {
            doLoginFailure(request, response);
        }
    }

    private void doLoginSuccess(HttpServletRequest request, HttpServletResponse response, String login) throws ServletException, IOException {
        SessionManager.doUserLogin(request.getSession(true), DAOManager.getUserDAO().read(login));
        response.sendRedirect(HOME);
    }

    private void doLoginFailure(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(MESSAGE, LOGIN_ERROR_MESSAGE);
        request.getRequestDispatcher(LOGIN_FORM).forward(request, response);
    }
}
