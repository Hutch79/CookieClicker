package ch.hutch79.cookieclicker.util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Gui {

    public static void mainGui (Player player) {

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

}
