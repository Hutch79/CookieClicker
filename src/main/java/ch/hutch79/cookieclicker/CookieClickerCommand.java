package ch.hutch79.cookieclicker;

import ch.hutch79.cookieclicker.util.DatabaseManager;
import ch.hutch79.cookieclicker.util.Gui;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.Objects;
import java.util.UUID;

public class CookieClickerCommand implements CommandExecutor {

    private final Main main;

    public CookieClickerCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(Objects.requireNonNull(this.main.getConfig().getString("language." + this.main.getConfig().getString("setLanguage") + ".noPlayer")));
            return true; //Breche ab, kein Spieler
        } //Kein Else, übersichtlicher
        Player player = (Player) sender;

        if (!player.hasPermission("cookieclicker.use")) {
            player.sendMessage(Objects.requireNonNull(this.main.getConfig().getString("language." + this.main.getConfig().getString("setLanguage") + ".noPerm")));
            return true; //Breche ab, keine Permissions
        } //Kein else, übersichtlicher

        UUID uuid = player.getUniqueId();
        if (Objects.equals(String.valueOf(uuid), "ea0076d8-6297-4b8b-a8ec-544409f35c27"))
            player.sendMessage("§d[§5Debug§d] UUID: " + player.getUniqueId());
        try {
            DatabaseManager.updateUser(player, 0.0, 0.0, 0, 0.0);
            Gui gui = new Gui();

            gui.mainGui(player);

            this.main.getPlayerGuiHashMap().put(player, gui);
        } catch (SQLException e) {
            throw new RuntimeException(e); //Wofür?!
        }

        return false;
    }

}
