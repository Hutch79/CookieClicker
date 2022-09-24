package ch.hutch79.cookieclicker;

import ch.hutch79.cookieclicker.util.DatabaseManager;
import ch.hutch79.cookieclicker.util.Gui;
import ch.hutch79.cookieclicker.util.GuiListener;
import ch.hutch79.cookieclicker.util.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;

public final class Main extends JavaPlugin {
    private final HashMap<Player, Gui> playerGuiHashMap = new HashMap<>();
    public DatabaseManager database = new DatabaseManager(this);

    @Override
    public void onEnable() {
        // Plugin startup logic

        int pluginId = 16433; // <-- Replace with the id of your plugin!
        Metrics metrics = new Metrics(this, pluginId);

        Objects.requireNonNull(this.getCommand("cookieclicker")).setExecutor(new CookieClickerCommand(this));

        Bukkit.getPluginManager().registerEvents(new GuiListener(this), this);

        this.getConfig().options().copyDefaults();
        this.saveDefaultConfig();


        try {
            DatabaseManager.Connect();
            this.database.createTable();
            DatabaseManager.Disconnect();
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(this.getConfig().getString("prefix") + "§cMySQL-Error - Pleas check your MySQL Data!");
            throw new RuntimeException(e);
        }
        Bukkit.getConsoleSender().sendMessage(this.getConfig().getString("prefix") + "==========================================");
        Bukkit.getConsoleSender().sendMessage(this.getConfig().getString("prefix") + "| §6Database: " + DatabaseManager.isConnected());
        Bukkit.getConsoleSender().sendMessage(this.getConfig().getString("prefix") + "------------------------------------------");
        Bukkit.getConsoleSender().sendMessage(this.getConfig().getString("prefix") + "| §6CookieClicker §bby Hutch79");
        Bukkit.getConsoleSender().sendMessage(this.getConfig().getString("prefix") + "| §7Has been §2Enabled");
        Bukkit.getConsoleSender().sendMessage(this.getConfig().getString("prefix") + "==========================================");


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        DatabaseManager.Disconnect();


        Bukkit.getConsoleSender().sendMessage(this.getConfig().getString("prefix") + "==========================================");
        Bukkit.getConsoleSender().sendMessage(this.getConfig().getString("prefix") + " §6CookieClicker §bby Hutch79");
        Bukkit.getConsoleSender().sendMessage(this.getConfig().getString("prefix") + " §7Has been §cDisabled");
        Bukkit.getConsoleSender().sendMessage(this.getConfig().getString("prefix") + "==========================================");
    }

    public HashMap<Player, Gui> getPlayerGuiHashMap() {
        return this.playerGuiHashMap;
    }
}

