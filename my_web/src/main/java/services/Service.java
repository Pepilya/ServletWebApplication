package services;

import roles.User;
import dao.UserDAO;
import DB.Util;

import java.sql.*;
import java.util.Map;

public class Service extends Util implements UserDAO {
    private static Service instance;
    Util util = new Util();

    private Service() throws Exception {
    }

    public static Service getInstance() throws Exception {
        if (instance == null)
            instance = new Service();
        return instance;
    }

    @Override
    public void addUser(User user) throws SQLException {
        PreparedStatement statement = null;
        String sql = "INSERT INTO Profile (login, password, email, info) Values (?, ?, ?, ?)";
        Connection connection = null;
        try {
            connection = util.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getInfo());

            int rows = statement.executeUpdate();
            System.out.printf("%d rows added", rows);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (connection != null){
                connection.close();
            }
        }
    }

    @Override
    public Map<String, User> getAllUsers() {
        return null;
    }

    @Override
    public User getByLogin(String login) {

        String sql = "SELECT * FROM Profile";
        Connection connection = null;
        try {
            connection = util.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            int i = 1;
            while (result.next()){
                String loginCompare = result.getString("login");
                if (login.equalsIgnoreCase(loginCompare)) {
                    String passwordCompare = result.getString("password");
                    String emailCompare = result.getString("email");
                    String infoComare = result.getString("Info");
                    User user = new User();
                    user.setLogin(loginCompare);
                    user.setPassword(passwordCompare);
                    user.setEmail(emailCompare);
                    user.setInfo(infoComare);
                    return (user);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateUser(User user) throws SQLException {
        PreparedStatement statement = null;
        String sql = "UPDATE Profile SET password=?, email=?, Info=? WHERE login=?";
        Connection connection = null;
        try {
            connection = util.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getInfo());
            statement.setString(4, user.getLogin());
            int rows = statement.executeUpdate();
            System.out.printf("%d rows added", rows);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (connection != null){
                connection.close();
            }
        }
    }

    @Override
    public void deleteUser(User user) {

    }
}
