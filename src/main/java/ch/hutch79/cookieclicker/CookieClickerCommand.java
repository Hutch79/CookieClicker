package ch.hutch79.cookieclicker;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ch.hutch79.cookieclicker.Gui;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CookieClickerCommand implements CommandExecutor{

    private Main main;
    public CookieClickerCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("cookieclicker.use")) {

                Inventory inv = Bukkit.createInventory(player, 54, "CookieClicker");

                // Placeholder
                ItemStack placeholder = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                ItemMeta placeholderMeta = placeholder.getItemMeta();
                placeholderMeta.setDisplayName(" ");
                placeholder.setItemMeta(placeholderMeta);

                for( int i : new int[]{1, 10, 19, 28, 37, 46}) {
                    inv.setItem(i, placeholder);
                }

                // Shop
                ItemStack shop = new ItemStack(Material.GOLD_INGOT);
                ItemMeta shopMeta = shop.getItemMeta();
                shopMeta.setDisplayName("§6Shop");
                shop.setItemMeta(shopMeta);
                inv.setItem(9, shop);

                player.openInventory(inv);
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
