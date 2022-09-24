package ch.hutch79.cookieclicker.util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Gui {

    public static Inventory mainInv;
    public static void mainGui(Player player) {

        mainInv = Bukkit.createInventory(player, 54, "CookieClicker");

        // Placeholder
        ItemStack placeholder = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta placeholderMeta = placeholder.getItemMeta();
        assert placeholderMeta != null;
        placeholderMeta.setDisplayName(" ");
        placeholder.setItemMeta(placeholderMeta);

        for( int i : new int[]{1, 10, 19, 28, 37, 46}) {
            mainInv.setItem(i, placeholder);
        }

        // Shop
        ItemStack shop = new ItemStack(Material.GOLD_INGOT);
        ItemMeta shopMeta = shop.getItemMeta();
        assert shopMeta != null;
        shopMeta.setDisplayName("§6Shop");
        shop.setItemMeta(shopMeta);
        mainInv.setItem(9, shop);

        // AutoKlick Detection
        ItemStack akDetection = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
        ItemMeta akDetectionMeta = placeholder.getItemMeta();
        assert akDetectionMeta != null;
        akDetectionMeta.setDisplayName(" ");
        akDetection.setItemMeta(placeholderMeta);

        for( int i : new int[]{2,3,4,5,6,7,8,11,12,13,14,15,16,17,20,21,22,23,24,25,26,29,30,31,32,33,34,35,38,39,40,41,42,43,44,47,48,49,50,51,52,53}) {
            mainInv.setItem(i, akDetection);
        }

        player.openInventory(mainInv);

    }
    public static Inventory getMainInv() {
        return mainInv;
    }

}
