package dao;

import roles.User;

import java.sql.SQLException;
import java.util.*;


public interface UserDAO {

    void addUser (User user) throws SQLException;

    Map <String, User> getAllUsers() throws SQLException;

    User getByLogin(String login) throws SQLException;

    void updateUser(User user) throws SQLException;
}
