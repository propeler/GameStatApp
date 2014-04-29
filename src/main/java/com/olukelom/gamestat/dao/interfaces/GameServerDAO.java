package com.olukelom.gamestat.dao.interfaces;

import com.olukelom.gamestat.exceptions.DAOException;
import com.olukelom.gamestat.model.GameServer;

import java.util.Map;

public interface GameServerDAO {
    public Map<Integer,GameServer> getAll() throws DAOException;
    public GameServer getByID(int id) throws DAOException;
}
