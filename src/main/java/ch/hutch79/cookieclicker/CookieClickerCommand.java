package ch.hutch79.cookieclicker;

import ch.hutch79.cookieclicker.util.CookieManager;
import ch.hutch79.cookieclicker.util.DatabaseManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import ch.hutch79.cookieclicker.util.Gui;

import java.sql.SQLException;
import java.util.Objects;
import java.util.UUID;

public class CookieClickerCommand implements CommandExecutor {

    private final Main main;

    public CookieClickerCommand(Main main) {
        this.main = main;
    }

    // Main main = new Main();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof ConsoleCommandSender) {
            Bukkit.getConsoleSender().sendMessage(Objects.requireNonNull(main.getConfig().getString("language." + main.getConfig().getString("setLanguage") + ".noPlayer")));
            return true;
        }

        if (args.length == 0) { // Main command
            Main(sender);
            return true;
        }

        if (args[0].equals("add")) { // cookie add
            CookieAdd(sender, args);
            return true;
        }
        return true;
    }

    /*/
    / Main Command
    /*-
    / Opens the GUI and also tries to create the Database table for new Players
    /*/
    public void Main(CommandSender sender) {
        Player player = (Player) sender;
        if (!player.hasPermission("cookieclicker.use") || !player.hasPermission("cookieclicker.admin")) {
            player.sendMessage(Objects.requireNonNull(main.getConfig().getString("language." + main.getConfig().getString("setLanguage") + ".noPerm")));
            return;
        }
        try {
            DatabaseManager.updateUser(player, 0.0, 0.0, 0, 0.0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            Gui.mainGui(player, 1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /*/
    / add command
    /*-
    / adds Cookies to a specific player
    /*/
    public void CookieAdd(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if (!player.hasPermission("cookieclicker.admin")) {
            player.sendMessage(Objects.requireNonNull(main.getConfig().getString("language." + main.getConfig().getString("setLanguage") + ".noPerm")));
            return;
        }
        if (args.length < 3) {
            player.sendMessage(main.getConfig().getString("prefix") + Objects.requireNonNull(main.getConfig().getString("language." + main.getConfig().getString("setLanguage") + ".cmdAdd")));
            return;
        }
        CookieManager.modifyCookie(Double.parseDouble(args[2]), Bukkit.getPlayer(args[1]));
    }
}
