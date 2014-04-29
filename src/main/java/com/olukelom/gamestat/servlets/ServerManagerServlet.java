package com.olukelom.gamestat.servlets;

import com.olukelom.gamestat.dao.DAOManager;
import com.olukelom.gamestat.dao.interfaces.GameLevelDAO;
import com.olukelom.gamestat.dao.interfaces.GameModeDAO;
import com.olukelom.gamestat.dao.interfaces.GameServerDAO;
import com.olukelom.gamestat.model.User;
import com.olukelom.gamestat.servlets.auth.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Servers", value = "/servers")

public class ServerManagerServlet extends HttpServlet {

    private final static String CONTENT = "content";
    private final static String SERVER = "server";
    private final static String SERVERS = "servers";
    private final static String MODES = "modes";
    private final static String LEVELS = "levels";
    private final static String ID = "id";
    private final static String MODE = "mode";
    private final static String EDIT_MODE = "edit";

    private final static String HOME_PAGE = "/home";
    private final static String LIST_PAGE = "/WEB-INF/pages/ServerList.jsp";
    private final static String INFO_PAGE = "/WEB-INF/pages/ServerInfo.jsp";
    private final static String EDIT_PAGE = "/WEB-INF/pages/ServerEdit.jsp";
    private final static String TEMPLATE_PAGE = "/WEB-INF/pages/Template.jsp";

    private GameServerDAO servers = DAOManager.getServerDAO();
    private GameLevelDAO levels = DAOManager.getLevelDAO();
    private GameModeDAO modes = DAOManager.getModeDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter(ID) != null) {
            int id = Integer.valueOf(request.getParameter(ID));
            request.setAttribute(SERVER, servers.getByID(id));
            String mode = request.getParameter(MODE);
            if (mode != null && mode.equals(EDIT_MODE)) {
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute(SessionManager.TARGET_ATTRIBUTE);
                if (user != null && user.isAdmin()) {
                    request.setAttribute(MODES, modes.getAll().values());
                    request.setAttribute(LEVELS, levels.getAll().values());
                    request.setAttribute(CONTENT, EDIT_PAGE);
                } else {
                    response.sendRedirect(HOME_PAGE);
                    return;
                }
            } else {
                request.setAttribute(CONTENT, INFO_PAGE);
            }
        } else {
            request.setAttribute(SERVERS, servers.getAll().values());
            request.setAttribute(CONTENT, LIST_PAGE);
        }
        request.getRequestDispatcher(TEMPLATE_PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO:implement method
    }
}
