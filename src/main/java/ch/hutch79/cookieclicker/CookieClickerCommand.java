package ch.hutch79.cookieclicker;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ch.hutch79.cookieclicker.util.Gui;

import java.util.Objects;
import java.util.UUID;

public class CookieClickerCommand implements CommandExecutor{

    private final Main main;
    public CookieClickerCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("cookieclicker.use")) {

                UUID uuid = player.getUniqueId();
                if (Objects.equals(String.valueOf(uuid), "ea0076d8-6297-4b8b-a8ec-544409f35c27")) {
                    player.sendMessage("UUID: " + player.getUniqueId());
                }

                Gui.mainGui(player);
            }
            else {
                player.sendMessage(main.getConfig().getString("language." + main.getConfig().getString("setLanguage") + ".noPerm"));
            }

        }
        else {
            Bukkit.getConsoleSender().sendMessage(main.getConfig().getString("language." + main.getConfig().getString("setLanguage") + ".noPlayer"));
        }

        return false;
    }

}
