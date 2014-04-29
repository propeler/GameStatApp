package com.olukelom.gamestat.dao.interfaces;

import com.olukelom.gamestat.exceptions.DAOException;
import com.olukelom.gamestat.model.GameLevel;

import java.util.Map;

public interface GameLevelDAO {
    public GameLevel getLevel(int id) throws DAOException;
    public Map<Integer,GameLevel> getAll();
}
