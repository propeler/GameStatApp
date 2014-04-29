package com.olukelom.gamestat.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Homepage", value = "/home")

public class HomeServlet extends HttpServlet {

    private final static String WELCOME_PAGE = "/servers";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(WELCOME_PAGE).forward(request, response);
    }
}
