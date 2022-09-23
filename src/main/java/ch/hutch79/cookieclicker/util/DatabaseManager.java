package ch.hutch79.cookieclicker.util;

import ch.hutch79.cookieclicker.Main;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.UUID;

public class DatabaseManager {


    private static Main main = null;
    public DatabaseManager(Main main) {
        DatabaseManager.main = main;
    }
    private static Connection connection;

    public void executeUpdate(String cmd) {
        try {
            connection.createStatement().executeUpdate(cmd);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createTable() {
        this.executeUpdate("CREATE TABLE IF NOT EXISTS cookies (UUID VARCHAR(100), Cookies DECIMAL(30, 2), CPC DECIMAL(20, 2), CPS DECIMAL(20, 2), Golden_Cookies DECIMAL(30, 0))");
    }

    public static void Connect() throws SQLException {
        String HOST = main.getConfig().getString("database.address");
        int PORT = main.getConfig().getInt("database.port");
        String DATABASE = main.getConfig().getString("database.database");
        String USER = main.getConfig().getString("database.user");
        String PASSWORD = main.getConfig().getString("database.password");

        connection = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?useSSL=false", USER, PASSWORD);
    }

    public static void updateUser(Player player, Integer cookies, Double cpc, Integer golden_cookies, Double cps) throws SQLException {
        Connect();
        DatabaseManager database = main.database;
        PreparedStatement ps = database.getConnection().prepareStatement("INSERT INTO cookies (UUID,Cookies,CPC,CPS,Golden_Cookies) VALUES (?,?,?,?,?)");
        UUID uuid = player.getUniqueId();
        ps.setString(1, String.valueOf(uuid));
        ps.setInt(2, cookies);
        ps.setDouble(3, cpc);
        ps.setDouble(4, cps);
        ps.setInt(5, golden_cookies);
        ps.execute();
        Disconnect();
    }

    public Connection getConnection() {return connection;}

    public static boolean isConnected() {
        return connection != null;
    }

    public static void Disconnect() {
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
