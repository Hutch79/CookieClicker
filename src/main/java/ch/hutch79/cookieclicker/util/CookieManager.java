package ch.hutch79.cookieclicker.util;

import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CookieManager {
    //=================================================================================================================
    // Cookies
    //=================================================================================================================
    public static void addCookie (double keks, Player player) {
        try {
            DatabaseManager.updateUser(player, keks, 0.0, 0, 0.0); // Add/Sett values in Database
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public static double getCookie(Player player) throws SQLException {
        DatabaseManager.Connect();
        ResultSet resultSet = DatabaseManager.getConnection().createStatement().executeQuery("SELECT * FROM Cookies WHERE UUID = '" + player.getUniqueId() + "'"); // Get every result with given UUID
        resultSet.next(); // Jump to next entry. First one is always null
        double keksi = Double.parseDouble(String.format("%.2f", resultSet.getDouble("cookies")).replace(",", ".")); // Filter out wanted element
        DatabaseManager.Disconnect();
        return keksi;
    }

    //=================================================================================================================
    // CPC
    //=================================================================================================================
    public static void addCPC(double cpc, Player player) {
        try {
            DatabaseManager.updateUser(player, 0.0, cpc, 0, 0.0); // Add/Sett values in Database
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public static double getCPC(Player player) throws SQLException {
        DatabaseManager.Connect();
        ResultSet resultSet = DatabaseManager.getConnection().createStatement().executeQuery("SELECT * FROM Cookies WHERE UUID = '" + player.getUniqueId() + "'"); // Get every result with given UUID
        resultSet.next(); // Jump to next entry. First one is always null
        double cpc = Double.parseDouble(String.format("%.2f", resultSet.getDouble("cpc")).replace(",", ".")); // Filter out wanted element
        DatabaseManager.Disconnect();
        return cpc;
    }

    //=================================================================================================================
    // CPS
    //=================================================================================================================
    public static void addCPS(double cps, Player player) {
        try {
            DatabaseManager.updateUser(player, 0.0, 0.0, 0, cps); // Add/Sett values in Database
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public static double getCPS(Player player) throws SQLException {
        DatabaseManager.Connect();
        ResultSet resultSet = DatabaseManager.getConnection().createStatement().executeQuery("SELECT * FROM Cookies WHERE UUID = '" + player.getUniqueId() + "'"); // Get every result with given UUID
        resultSet.next(); // Jump to next entry. First one is always null
        double cps = Double.parseDouble(String.format("%.2f", resultSet.getDouble("cps")).replace(",", ".")); // Filter out wanted element
        DatabaseManager.Disconnect();
        return cps;
    }

    //=================================================================================================================
    // GoldenCookies
    //=================================================================================================================
    public static void addGoldenCookies(double cps, Player player) {
        try {
            DatabaseManager.updateUser(player, 0.0, 0.0, 0, cps); // Add/Sett values in Database
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public static double getGoldenCookies(Player player) throws SQLException {
        DatabaseManager.Connect();
        ResultSet resultSet = DatabaseManager.getConnection().createStatement().executeQuery("SELECT * FROM Cookies WHERE UUID = '" + player.getUniqueId() + "'"); // Get every result with given UUID
        resultSet.next(); // Jump to next entry. First one is always null
        double goldenCookies = Double.parseDouble(String.format("%.2f", resultSet.getDouble("goldenCookies")).replace(",", ".")); // Filter out wanted element
        DatabaseManager.Disconnect();
        return goldenCookies;
    }
}
