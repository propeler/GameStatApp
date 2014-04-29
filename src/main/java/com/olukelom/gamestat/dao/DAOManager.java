package com.olukelom.gamestat.dao;

import com.olukelom.gamestat.dao.implementations.GameLevelDAOImpl;
import com.olukelom.gamestat.dao.implementations.GameModeDAOImpl;
import com.olukelom.gamestat.dao.implementations.GameServerDAOImpl;
import com.olukelom.gamestat.dao.implementations.UserDAOImpl;
import com.olukelom.gamestat.dao.interfaces.GameLevelDAO;
import com.olukelom.gamestat.dao.interfaces.GameModeDAO;
import com.olukelom.gamestat.dao.interfaces.GameServerDAO;
import com.olukelom.gamestat.dao.interfaces.UserDAO;

public class DAOManager {
    private static UserDAO userDAO = new UserDAOImpl();
    private static GameLevelDAO levelDAO = new GameLevelDAOImpl();
    private static GameModeDAO modeDAO = new GameModeDAOImpl();
    private static GameServerDAO serverDAO = new GameServerDAOImpl();

    public static UserDAO getUserDAO() {
        return userDAO;
    }

    public static GameLevelDAO getLevelDAO() {
        return levelDAO;
    }

    public static GameModeDAO getModeDAO() {
        return modeDAO;
    }

    public static GameServerDAO getServerDAO() {
        return serverDAO;
    }
}
