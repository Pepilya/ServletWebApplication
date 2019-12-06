package dao;

import roles.User;

import java.sql.SQLException;
import java.util.*;


import java.util.HashMap;

public interface UserDAO {

    //create
    void addUser (User user) throws SQLException;

    //read

    Map <String, User> getAllUsers() throws SQLException;

    User getByLogin(String login) throws SQLException;

    //update

    void updateUser(User user) throws SQLException;

    void deleteUser(User user) throws SQLException;
}
