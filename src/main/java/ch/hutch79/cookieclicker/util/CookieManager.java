package ch.hutch79.cookieclicker.util;

import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ch.hutch79.cookieclicker.Main;

public class CookieManager {

    private final Main main;
    public CookieManager(Main main) {
        this.main = main;
    }
    private Connection connection;



    public void dbConnect () throws SQLException {
        String HOST = main.getConfig().getString("database.address");
        int PORT = main.getConfig().getInt("database.port");
        String DATABASE = main.getConfig().getString("database.database");
        String USER = main.getConfig().getString("database.user");
        String PASSWORD = main.getConfig().getString("database.password");

        connection = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?useSSL=false", USER, PASSWORD);
    }

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

    public static void addCookie (int keks, Player player) {

    }

    public static int getCookie(Player player){
        return 10;
    }

    public static void setCPC(int cpc, Player player) {

    }

    public static int getCPC(Player player) {
        return 10;
    }
}
