package ch.hutch79.cookieclicker.util;


import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CookieManager {
    //=================================================================================================================
    // Cookies
    //=================================================================================================================
    public static void modifyCookie (double keks, Player player) {
        try {
            DatabaseManager.updateUser(player, keks, 0.0, 0, 0.0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0); // Add/Sett values in Database
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
    public static void modifyCPC(double cpc, Player player) {
        try {
            DatabaseManager.updateUser(player, 0.0, cpc, 0, 0.0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0); // Add/Sett values in Database
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
    public static void modifyCPS(double cps, Player player) {
        try {
            DatabaseManager.updateUser(player, 0.0, 0.0, 0, cps, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0); // Add/Sett values in Database
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
    public static void modifyGoldenCookies(double cps, Player player) {
        try {
            DatabaseManager.updateUser(player, 0.0, 0.0, 0, cps, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0); // Add/Sett values in Database
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

    //=================================================================================================================
    // Upgrades - 1 bis 10
    //=================================================================================================================
    public static void modifyUpgrade(int upgrade, double value, Player player) {
        try {
            switch (upgrade) {
                case 1:
                    DatabaseManager.updateUser(player, 0.0, 0.0, 0, 0.0, value, 0, 0, 0, 0, 0, 0, 0, 0, 0); // Add/Sett values in Database
                    break;
                case 2:
                    DatabaseManager.updateUser(player, 0.0, 0.0, 0, 0.0, 0, value, 0, 0, 0, 0, 0, 0, 0, 0); // Add/Sett values in Database
                    break;
                case 3:
                    DatabaseManager.updateUser(player, 0.0, 0.0, 0, 0.0, 0, 0, value, 0, 0, 0, 0, 0, 0, 0); // Add/Sett values in Database
                    break;
                case 4:
                    DatabaseManager.updateUser(player, 0.0, 0.0, 0, 0.0, 0, 0, 0, value, 0, 0, 0, 0, 0, 0); // Add/Sett values in Database
                    break;
                case 5:
                    DatabaseManager.updateUser(player, 0.0, 0.0, 0, 0.0, 0, 0, 0, 0, value, 0, 0, 0, 0, 0); // Add/Sett values in Database
                    break;
                case 6:
                    DatabaseManager.updateUser(player, 0.0, 0.0, 0, 0.0, 0, 0, 0, 0, 0, value, 0, 0, 0, 0); // Add/Sett values in Database
                    break;
                case 7:
                    DatabaseManager.updateUser(player, 0.0, 0.0, 0, 0.0, 0, 0, 0, 0, 0, 0, value, 0, 0, 0); // Add/Sett values in Database
                    break;
                case 8:
                    DatabaseManager.updateUser(player, 0.0, 0.0, 0, 0.0, 0, 0, 0, 0, 0, 0, 0, value, 0, 0); // Add/Sett values in Database
                    break;
                case 9:
                    DatabaseManager.updateUser(player, 0.0, 0.0, 0, 0.0, 0, 0, 0, 0, 0, 0, 0, 0, value, 0); // Add/Sett values in Database
                    break;
                case 10:
                    DatabaseManager.updateUser(player, 0.0, 0.0, 0, 0.0, 0, 0, 0, 0, 0, 0, 0, 0, 0, value); // Add/Sett values in Database
                    break;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public static double getUpgrade(int upgrade, Player player) throws SQLException {
        DatabaseManager.Connect();
        ResultSet resultSet = DatabaseManager.getConnection().createStatement().executeQuery("SELECT * FROM Cookies WHERE UUID = '" + player.getUniqueId() + "'"); // Get every result with given UUID
        resultSet.next(); // Jump to next entry. First one is always null
        double value = Double.parseDouble(String.format("%.2f", resultSet.getDouble("upgrade"+upgrade)).replace(",", ".")); // Filter out wanted element
        DatabaseManager.Disconnect();
        return value;
    }
}
