package com.olukelom.gamestat.dao.implementations;

import com.olukelom.gamestat.dao.DB;
import com.olukelom.gamestat.dao.interfaces.GameModeDAO;
import com.olukelom.gamestat.exceptions.DAOException;
import com.olukelom.gamestat.model.GameMode;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

public class GameModeDAOImpl implements GameModeDAO {
    private Map<Integer, GameMode> modes = new TreeMap<>();

    public GameModeDAOImpl() {
        try (Connection connection = DB.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(
                    "SELECT * " +
                            "FROM game_modes"
            )) {
                while (resultSet.next()) {
                    GameMode mode = new GameMode(
                            resultSet.getInt("mode_id"),
                            resultSet.getString("mode_name"),
                            resultSet.getString("mode_description"));
                    modes.put(mode.getId(), mode);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public GameMode getMode(int id) {
        return modes.get(id);
    }

    @Override
    public Map<Integer, GameMode> getAll() {
        return modes;
    }
}
