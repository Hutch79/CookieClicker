package ch.hutch79.cookieclicker.util;

import ch.hutch79.cookieclicker.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {


    private final Main main;
    public DatabaseManager(Main main) {
        this.main = main;
    }
    private Connection connection;


    public void executeUpdate(String cmd) {
        try {
            this.connection.createStatement().executeUpdate(cmd);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    public void createTable() {
        this.executeUpdate("CREATE TABLE IF NOT EXISTS Cookies (UUID VARCHAR(100), Cookies DECIMAL(30, 2), CPC DECIMAL(20, 2), CPS DECIMAL(20, 2), Golden_Cookies DECIMAL(30, 2))");
    }

    public void dbConnect () throws SQLException {
        String HOST = main.getConfig().getString("database.address");
        int PORT = main.getConfig().getInt("database.port");
        String DATABASE = main.getConfig().getString("database.database");
        String USER = main.getConfig().getString("database.user");
        String PASSWORD = main.getConfig().getString("database.password");

        connection = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?useSSL=false", USER, PASSWORD);
    }

    public Connection getConnection() {return connection;}

    public boolean isConnected () {
        return connection != null;
    }

    public void dbDisconnect () {
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
