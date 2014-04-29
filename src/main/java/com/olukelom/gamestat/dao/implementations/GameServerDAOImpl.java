package com.olukelom.gamestat.dao.implementations;

import com.olukelom.gamestat.dao.DAOManager;
import com.olukelom.gamestat.dao.DB;
import com.olukelom.gamestat.dao.interfaces.GameServerDAO;
import com.olukelom.gamestat.exceptions.DAOException;
import com.olukelom.gamestat.model.GameServer;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

public class GameServerDAOImpl implements GameServerDAO {

    @Override
    public Map<Integer, GameServer> getAll() {
        Map<Integer, GameServer> servers = new TreeMap<>();
        try (Connection connection = DB.getConnection();
             Statement statement = connection.createStatement()
        ) {
            try (ResultSet resultSet = statement.executeQuery(
                    "SELECT * " +
                            "FROM servers"
            )) {
                while (resultSet.next()) {
                    GameServer server = new GameServer(resultSet.getString("server_password") != null);
                    server.setId(resultSet.getInt("server_id"));
                    server.setName(resultSet.getString("server_name"));
                    server.setDescription(resultSet.getString("server_description"));
                    server.setLevel(DAOManager.getLevelDAO().getLevel(resultSet.getInt("server_level")));
                    server.setMode(DAOManager.getModeDAO().getMode(resultSet.getInt("server_mode")));
                    server.setStatus(resultSet.getBoolean("server_is_online"));
                    servers.put(server.getId(), server);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return servers;
    }

    @Override
    public GameServer getByID(int id) throws DAOException {
        GameServer server = null;
        try (Connection connection = DB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * " +
                             "FROM servers " +
                             "WHERE server_id=?"
             )
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    server = new GameServer(resultSet.getString("server_password") != null);
                    server.setId(resultSet.getInt("server_id"));
                    server.setName(resultSet.getString("server_name"));
                    server.setDescription(resultSet.getString("server_description"));
                    server.setLevel(DAOManager.getLevelDAO().getLevel(resultSet.getInt("server_level")));
                    server.setMode(DAOManager.getModeDAO().getMode(resultSet.getInt("server_mode")));
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return server;
    }
}
