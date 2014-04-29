package com.olukelom.gamestat.dao.interfaces;

import com.olukelom.gamestat.exceptions.DAOException;
import com.olukelom.gamestat.model.GameMode;

import java.util.Map;

public interface GameModeDAO {
    public GameMode getMode(int id) throws DAOException;
    public Map<Integer,GameMode> getAll();
}
