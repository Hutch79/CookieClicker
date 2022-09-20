package ch.hutch79.cookieclicker;

import ch.hutch79.cookieclicker.util.CookieManager;
import ch.hutch79.cookieclicker.util.DatabaseManager;
import ch.hutch79.cookieclicker.util.GuiListerne;
import ch.hutch79.cookieclicker.util.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class Main extends JavaPlugin{

    private DatabaseManager database;
    @Override
    public void onEnable() {
        // Plugin startup logic

        int pluginId = 16433; // <-- Replace with the id of your plugin!
        Metrics metrics = new Metrics(this, pluginId);

        getCommand("cookieclicker").setExecutor(new CookieClickerCommand(this));

        Bukkit.getPluginManager().registerEvents(new GuiListerne(), this);

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        database = new DatabaseManager(this);
        try {
            database.dbConnect();
            database.createTable();
            // PreparedStatement ps = database.getConnection().prepareStatement("");


        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(getConfig().getString("prefix") + "§cMySQL-Error - Pleas check your MySQL Data!");
            throw new RuntimeException(e);
        }

        Bukkit.getConsoleSender().sendMessage(getConfig().getString("prefix") + "§6Database: " + database.isConnected());
        Bukkit.getConsoleSender().sendMessage(getConfig().getString("prefix") + "------------------------------------------");
        Bukkit.getConsoleSender().sendMessage(getConfig().getString("prefix") + " §6CookieClicker §bby Hutch79");
        Bukkit.getConsoleSender().sendMessage(getConfig().getString("prefix") + " §7Has been §2Enabled");
        Bukkit.getConsoleSender().sendMessage(getConfig().getString("prefix") + "------------------------------------------");



    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic

        database.dbDisconnect();

        Bukkit.getConsoleSender().sendMessage(getConfig().getString("prefix") + "------------------------------------------");
        Bukkit.getConsoleSender().sendMessage(getConfig().getString("prefix") + " §6CookieClicker §bby Hutch79");
        Bukkit.getConsoleSender().sendMessage(getConfig().getString("prefix") + " §7Has been §cDisabled");
        Bukkit.getConsoleSender().sendMessage(getConfig().getString("prefix") + "------------------------------------------");
    }
}

