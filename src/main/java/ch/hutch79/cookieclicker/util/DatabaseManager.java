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
        this.executeUpdate("CREATE TABLE IF NOT EXISTS Cookies (uuid VARCHAR(100), cookies DECIMAL(30, 2), cpc DECIMAL(20, 2), cps DECIMAL(20, 2), goldenCookies DECIMAL(30, 0))");
    }

    public static void Connect() throws SQLException {
        String HOST = main.getConfig().getString("database.address");
        int PORT = main.getConfig().getInt("database.port");
        String DATABASE = main.getConfig().getString("database.database");
        String USER = main.getConfig().getString("database.user");
        String PASSWORD = main.getConfig().getString("database.password");

        connection = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?useSSL=false", USER, PASSWORD);
    }

    public static void updateUser(Player player, Double cookies, Double cpc, Double golden_cookies, Double cps) throws SQLException {
        Connect();

        double cookies_new = cookies;
        double cpc_new = cpc;
        double cps_new = cps;
        double golden_cookies_new = golden_cookies;

        try {
            ResultSet resultSet = DatabaseManager.getConnection().createStatement().executeQuery("SELECT * FROM Cookies WHERE UUID = '" + player.getUniqueId() + "'");
            resultSet.next();
            double cookies_old = Double.parseDouble(String.format("%.2f", resultSet.getDouble("cookies")).replace(",", "."));
            double cpc_old = Double.parseDouble(String.format("%.2f", resultSet.getDouble("cpc")).replace(",", "."));
            double cps_old = Double.parseDouble(String.format("%.2f", resultSet.getDouble("cps")).replace(",", "."));
            double golden_cookies_old = Double.parseDouble(String.format("%.2f", resultSet.getDouble("goldenCookies")).replace(",", "."));

            cookies_new = cookies + cookies_old;
            cpc_new = cpc + cpc_old;
            cps_new = cps + cps_old;
            golden_cookies_new = golden_cookies + golden_cookies_old;
        } catch (SQLException ignored){
        }

        DatabaseManager.getConnection().createStatement().execute("DELETE FROM Cookies WHERE uuid = '" + player.getUniqueId() + "'");

        PreparedStatement ps = getConnection().prepareStatement("INSERT INTO Cookies (uuid,cookies,cpc,cps,goldenCookies) VALUES (?,?,?,?,?)");
        UUID uuid = player.getUniqueId();
        ps.setString(1, String.valueOf(uuid));
        ps.setDouble(2, cookies_new);
        ps.setDouble(3, cpc_new);
        ps.setDouble(4, cps_new);
        ps.setDouble(5, golden_cookies_new);
        ps.execute();
        Disconnect();
    }

    public static Connection getConnection() {return connection;}

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
