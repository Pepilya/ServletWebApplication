package DB;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UtilDb {
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "********";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/Users?serverTimezone=Europe/Moscow&useSSL=false";
    public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

    public Connection getConnection() throws Exception {
        Connection connection = null;
        Driver driver;
        try {
            driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException ex) {
            System.out.println("Driver is not activated");
            ex.getStackTrace();
        }
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            return connection;
        } catch (SQLException ex) {
            System.out.println("Data base is not activated");
            ex.getStackTrace();
        }
        return connection;
    }
}
