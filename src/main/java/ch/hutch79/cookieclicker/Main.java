package ch.hutch79.cookieclicker;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin{

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getConsoleSender().sendMessage(getConfig().getString("prefix") + "------------------------------------------");
        Bukkit.getConsoleSender().sendMessage(getConfig().getString("prefix") + " §6CookieClicker §bby Hutch79");
        Bukkit.getConsoleSender().sendMessage(getConfig().getString("prefix") + " §7Has been §2Enabled");
        Bukkit.getConsoleSender().sendMessage(getConfig().getString("prefix") + "------------------------------------------");

        getCommand("cookieclicker").setExecutor(new CookieClickerCommand(this));
        getConfig().options().copyDefaults();
        saveDefaultConfig();

    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getConsoleSender().sendMessage(getConfig().getString("prefix") + "------------------------------------------");
        Bukkit.getConsoleSender().sendMessage(getConfig().getString("prefix") + " §6CookieClicker §bby Hutch79");
        Bukkit.getConsoleSender().sendMessage(getConfig().getString("prefix") + " §7Has been §cDisabled");
        Bukkit.getConsoleSender().sendMessage(getConfig().getString("prefix") + "------------------------------------------");
    }
}

