package com.olukelom.gamestat.dao.interfaces;

import com.olukelom.gamestat.exceptions.DAOException;
import com.olukelom.gamestat.model.User;

public interface UserDAO {

    public void create(User user) throws DAOException;

    public User read(int id) throws DAOException;

    public User read(String login) throws DAOException;

    public void update(User user) throws DAOException;

    public void resetPassword(User user);

    public void delete(User user) throws DAOException;

    public boolean isValidUser(String login, String password) throws DAOException;
}
