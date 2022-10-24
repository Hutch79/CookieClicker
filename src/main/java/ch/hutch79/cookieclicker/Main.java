package ch.hutch79.cookieclicker;

import ch.hutch79.cookieclicker.util.DatabaseManager;
import ch.hutch79.cookieclicker.util.GuiListener;
import ch.hutch79.cookieclicker.util.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import com.jeff_media.updatechecker.*;

import java.sql.SQLException;
import java.util.Objects;

public final class Main extends JavaPlugin {

    PluginDescriptionFile pdf = this.getDescription();

    public DatabaseManager database = new DatabaseManager(this);

    @Override
    public void onEnable() {
        // Plugin startup logic

        int pluginId = 16433; // bStats
        final int SPIGOT_RESOURCE_ID = 105878; // Update checker
        Metrics metrics = new Metrics(this, pluginId);

        Objects.requireNonNull(getCommand("cookieclicker")).setExecutor(new CookieClickerCommand(this));

        Bukkit.getPluginManager().registerEvents(new GuiListener(), this);

        getConfig().options().copyDefaults();
        saveDefaultConfig();


        try {
            DatabaseManager.Connect();
            database.createTable();
            DatabaseManager.Disconnect();
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(getConfig().getString("prefix") + "§cMySQL-Error - Pleas check your MySQL Data!");
            throw new RuntimeException(e);
        }
        Bukkit.getConsoleSender().sendMessage(getConfig().getString("prefix") + "==========================================");
        Bukkit.getConsoleSender().sendMessage(getConfig().getString("prefix") + "| §6Database: " + DatabaseManager.isConnected());
        Bukkit.getConsoleSender().sendMessage(getConfig().getString("prefix") + "------------------------------------------");
        Bukkit.getConsoleSender().sendMessage(getConfig().getString("prefix") + "| §6CookieClicker " + pdf.getVersion() + " §bby Hutch79");
        Bukkit.getConsoleSender().sendMessage(getConfig().getString("prefix") + "| §7Has been §2Enabled");
        Bukkit.getConsoleSender().sendMessage(getConfig().getString("prefix") + "==========================================");

        if (pdf.getVersion().contains("Beta")) {
            getLogger().warning("It seems you're using a dev Build from GitHub.");
            getLogger().warning("Please note, that this Version should not be used in production environment.");
            getLogger().warning("If you find any Bugs, please report them on GitHub: https://github.com/Hutch79/CookieClicker");
        }
        new UpdateChecker(this, UpdateCheckSource.SPIGET, "" + SPIGOT_RESOURCE_ID + "")
                .setDownloadLink("https://www.spigotmc.org/resources/105878/")
                .setChangelogLink("https://www.spigotmc.org/resources/105878/updates")
                .setColoredConsoleOutput(true)
                .setNotifyOpsOnJoin(true)
                .setNotifyByPermissionOnJoin("cookieclicker.admin")
                .setUserAgent(new UserAgentBuilder().addPluginNameAndVersion().addServerVersion())
                .checkEveryXHours(12) //Warn people every 12 hours
                .checkNow();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        DatabaseManager.Disconnect();

        Bukkit.getConsoleSender().sendMessage(getConfig().getString("prefix") + "==========================================");
        Bukkit.getConsoleSender().sendMessage(getConfig().getString("prefix") + "| §6CookieClicker " + pdf.getVersion() + " §bby Hutch79");
        Bukkit.getConsoleSender().sendMessage(getConfig().getString("prefix") + "| §7Has been §cDisabled");
        Bukkit.getConsoleSender().sendMessage(getConfig().getString("prefix") + "==========================================");
    }
}

