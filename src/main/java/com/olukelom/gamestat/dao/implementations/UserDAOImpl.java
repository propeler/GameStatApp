package com.olukelom.gamestat.dao.implementations;

import com.olukelom.gamestat.dao.DB;
import com.olukelom.gamestat.dao.interfaces.UserDAO;
import com.olukelom.gamestat.exceptions.DAOException;
import com.olukelom.gamestat.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {

    @Override
    public void create(User user) throws DAOException {
            try (Connection connection = DB.getConnection();
                 PreparedStatement statement = connection.prepareStatement(
                         "INSERT INTO users  " +
                                 "(user_login, user_password, user_is_admin)" +
                                 "VALUES (?,MD5(?),?)"
                 )) {
                statement.setString(1,user.getLogin());
                statement.setString(2,user.getPassword());
                statement.setBoolean(3,user.isAdmin());
                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    throw new DAOException("User creation failed");
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
    }

    @Override
    public User read(int id) throws DAOException {
        User user = new User();
        try (Connection connection = DB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT user_login,user_is_admin" +
                             " FROM users" +
                             " WHERE user_id = ? "
             )) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user.setId(id);
                    user.setLogin(resultSet.getString("user_login"));
                    user.setAdminRights(resultSet.getBoolean("user_is_admin"));
                    return user;
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return user;
    }

    @Override
    public User read(String login) throws DAOException {
        User user = new User();
        try (Connection connection = DB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT user_id,user_login,user_is_admin" +
                             " FROM users" +
                             " WHERE LOWER(user_login) = LOWER(?)"
             )) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user.setLogin(resultSet.getString("user_login"));
                    user.setId(resultSet.getInt("user_id"));
                    user.setAdminRights(resultSet.getBoolean("user_is_admin"));
                    return user;
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return user;
    }

    @Override
    public void update(User user) throws DAOException {
        if (user.isNotAnonymous()) {
            try (Connection connection = DB.getConnection();
                 PreparedStatement statement = connection.prepareStatement(
                         "UPDATE users SET " +
                                 "user_is_admin= ? " +
                                 "WHERE user_id = ? "
                 )) {
                statement.setBoolean(1, user.isAdmin());
                statement.setInt(2, user.getId());
                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    throw new DAOException("Updating user failed, no user updated");
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        } else {
            throw new DAOException("Updating user failed, user is not created yet");
        }
    }

    @Override
    public void resetPassword(User user) {
        if (user.isNotAnonymous()) {
            try (Connection connection = DB.getConnection();
                 PreparedStatement statement = connection.prepareStatement(
                         "UPDATE users SET " +
                                 "user_password= MD5(?) " +
                                 "WHERE user_id = ? "
                 )) {
                statement.setString(1, user.getPassword());
                statement.setInt(2, user.getId());
                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    throw new DAOException("Updating password failed, no user updated");
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        } else {
            throw new DAOException("Updating password failed, user is not created yet");
        }
    }

    @Override
    public void delete(User user) throws DAOException {
        try (Connection connection = DB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM users " +
                             "WHERE user_id = ? "
             )) {
            statement.setInt(1, user.getId());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Deleting user failed, no user deleted");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean isValidUser(String login, String password) {
        try (Connection connection = DB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT user_login" +
                             " FROM users" +
                             " WHERE LOWER(user_login) = LOWER(?) AND user_password = MD5(?) "
             )) {
            statement.setString(1, login);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
