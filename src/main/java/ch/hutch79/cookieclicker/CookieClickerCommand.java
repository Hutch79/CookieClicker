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


public class CookieClickerCommand implements CommandExecutor {

    private final Main main;
    Gui Gui = new Gui();

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

        if (args[0].equalsIgnoreCase("add")) { // cookie add
            CookieAdd(sender, args);
            return true;
        }

        if (args[0].equalsIgnoreCase("take")) { // cookie take
            CookieTake(sender, args);
            return true;
        }

        if (args[0].equalsIgnoreCase("reset")) { // cookie reset
            try {
                CookieReset(sender, args);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("set")) { // cookie set
            try {
                CookieSet(sender, args);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
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
        if (player.hasPermission("cookieclicker.use") || player.hasPermission("cookieclicker.admin")) {
            try {
                DatabaseManager.updateUser(player, 0.0, 0.0, 0, 0.0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
                Gui.mainGui(player, 1);
                this.main.getPlayerGuiHashMap().put(player, Gui);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            player.sendMessage(Objects.requireNonNull(main.getConfig().getString("language." + main.getConfig().getString("setLanguage") + ".noPerm")));
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
        if (args.length != 3) {
            player.sendMessage(main.getConfig().getString("prefix") + Objects.requireNonNull(main.getConfig().getString("language." + main.getConfig().getString("setLanguage") + ".cmdAdd")));
            return;
        }
        CookieManager.modifyCookie(Double.parseDouble(args[2]), Bukkit.getPlayer(args[1]));
    }

    /*/
    / take command
    /*-
    / takes Cookies from a specific player
    /*/
    public void CookieTake(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if (!player.hasPermission("cookieclicker.admin")) {
            player.sendMessage(Objects.requireNonNull(main.getConfig().getString("language." + main.getConfig().getString("setLanguage") + ".noPerm")));
            return;
        }
        if (args.length != 3) {
            player.sendMessage(main.getConfig().getString("prefix") + Objects.requireNonNull(main.getConfig().getString("language." + main.getConfig().getString("setLanguage") + ".cmdAdd")));
            return;
        }
        double keksi = 0.0 - Double.parseDouble(args[2]);
        CookieManager.modifyCookie(keksi, Bukkit.getPlayer(args[1]));
    }

    /*/
    / reset command
    /*-
    / resets Cookies from of a specific player
    /*/
    public void CookieReset(CommandSender sender, String[] args) throws SQLException {
        Player player = (Player) sender;
        if (!player.hasPermission("cookieclicker.admin")) {
            player.sendMessage(Objects.requireNonNull(main.getConfig().getString("language." + main.getConfig().getString("setLanguage") + ".noPerm")));
            return;
        }
        if (args.length != 2) {
            player.sendMessage(main.getConfig().getString("prefix") + Objects.requireNonNull(main.getConfig().getString("language." + main.getConfig().getString("setLanguage") + ".cmdAdd")));
            return;
        }
        Player target = Bukkit.getPlayer(args[1]);
        assert target != null;
        double keksi = 0.0 - CookieManager.getCookie(target);
        CookieManager.modifyCookie(keksi, target);
    }

    /*/
    / set command
    /*-
    / sets Cookies of a specific player
    /*/
    public void CookieSet(CommandSender sender, String[] args) throws SQLException {
        Player player = (Player) sender;
        if (!player.hasPermission("cookieclicker.admin")) {
            player.sendMessage(Objects.requireNonNull(main.getConfig().getString("language." + main.getConfig().getString("setLanguage") + ".noPerm")));
            return;
        }
        if (args.length != 3) {
            player.sendMessage(main.getConfig().getString("prefix") + Objects.requireNonNull(main.getConfig().getString("language." + main.getConfig().getString("setLanguage") + ".cmdAdd")));
            return;
        }
        Player target = Bukkit.getPlayer(args[1]);
        assert target != null;
        double keksi = 0.0 - CookieManager.getCookie(target);
        CookieManager.modifyCookie(keksi + Double.parseDouble(args[2]), target);
    }

}
