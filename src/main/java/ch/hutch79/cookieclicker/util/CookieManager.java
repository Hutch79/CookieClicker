package ch.hutch79.cookieclicker.util;

import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CookieManager {
    //=================================================================================================================
    // Cookies
    //=================================================================================================================
    public static void modifyCookie(double keks, Player player) {
        try {
            DatabaseManager.updateUser(player, keks, 0.0, 0, 0.0); // Add/Sett values in Database
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static double getCookie(Player player) throws SQLException {
        DatabaseManager.Connect();
        ResultSet resultSet = DatabaseManager.getConnection().createStatement().executeQuery("SELECT * FROM Cookies WHERE UUID = '" + player.getUniqueId() + "'"); // Get every result with given UUID
        if (!resultSet.next()) {
            //Keine Einträge vorhanden
            return 0.0;
        }
        double keksi = Double.parseDouble(String.format("%.2f", resultSet.getDouble("cookies")).replace(",", ".")); // Filter out wanted element
        //Halte die Connection warm. Sonst gibt's z.B. Lags
        //DatabaseManager.Disconnect();
        return keksi;
    }

    //=================================================================================================================
    // CPC
    //=================================================================================================================
    public static void modifyCPC(double cpc, Player player) {
        try {
            DatabaseManager.updateUser(player, 0.0, cpc, 0, 0.0); // Add/Sett values in Database
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static double getCPC(Player player) throws SQLException {
        DatabaseManager.Connect();
        ResultSet resultSet = DatabaseManager.getConnection().createStatement().executeQuery("SELECT * FROM Cookies WHERE UUID = '" + player.getUniqueId() + "'"); // Get every result with given UUID
        if (!resultSet.next()) {
            //Keine Einträge vorhanden
            return 0.0;
        }
        double cpc = Double.parseDouble(String.format("%.2f", resultSet.getDouble("cpc")).replace(",", ".")); // Filter out wanted element
        //Halte die Connection warm. Sonst gibt's z.B. Lags
        //DatabaseManager.Disconnect();
        return cpc;
    }

    //=================================================================================================================
    // CPS
    //=================================================================================================================
    public static void modifyCPS(double cps, Player player) {
        try {
            DatabaseManager.updateUser(player, 0.0, 0.0, 0, cps); // Add/Sett values in Database
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static double getCPS(Player player) throws SQLException {
        DatabaseManager.Connect();
        ResultSet resultSet = DatabaseManager.getConnection().createStatement().executeQuery("SELECT * FROM Cookies WHERE UUID = '" + player.getUniqueId() + "'"); // Get every result with given UUID
        if (!resultSet.next()) {
            //Keine Einträge vorhanden
            return 0.0;
        }
        double cps = Double.parseDouble(String.format("%.2f", resultSet.getDouble("cps")).replace(",", ".")); // Filter out wanted element
        //Halte die Connection warm. Sonst gibt's z.B. Lags
        //DatabaseManager.Disconnect();
        return cps;
    }

    //=================================================================================================================
    // GoldenCookies
    //=================================================================================================================
    public static void modifyGoldenCookies(double cps, Player player) {
        try {
            DatabaseManager.updateUser(player, 0.0, 0.0, 0, cps); // Add/Sett values in Database
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static double getGoldenCookies(Player player) throws SQLException {
        DatabaseManager.Connect();
        ResultSet resultSet = DatabaseManager.getConnection().createStatement().executeQuery("SELECT * FROM Cookies WHERE UUID = '" + player.getUniqueId() + "'"); // Get every result with given UUID
        if (resultSet.next()) {
            //Keine Einträge vorhanden
            return 0.0;
        }
        double goldenCookies = Double.parseDouble(String.format("%.2f", resultSet.getDouble("goldenCookies")).replace(",", ".")); // Filter out wanted element
        //Halte die Connection warm. Sonst gibt's z.B. Lags
        //DatabaseManager.Disconnect();
        return goldenCookies;
    }
}
