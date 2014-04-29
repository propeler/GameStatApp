package com.olukelom.gamestat.dao.implementations;

import com.olukelom.gamestat.dao.DB;
import com.olukelom.gamestat.dao.interfaces.GameLevelDAO;
import com.olukelom.gamestat.exceptions.DAOException;
import com.olukelom.gamestat.model.GameLevel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

public class GameLevelDAOImpl implements GameLevelDAO {
    private Map<Integer, GameLevel> levels = new TreeMap<>();

    public GameLevelDAOImpl() {
        try (Connection connection = DB.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(
                    "SELECT * " +
                            "FROM game_levels"
            )) {
                while (resultSet.next()) {
                    GameLevel level = new GameLevel(
                            resultSet.getInt("level_id"),
                            resultSet.getString("level_name"),
                            resultSet.getString("level_description"));
                    levels.put(level.getId(), level);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public GameLevel getLevel(int id) {
        return levels.get(id);
    }

    @Override
    public Map<Integer, GameLevel> getAll() {
        return levels;
    }
}
