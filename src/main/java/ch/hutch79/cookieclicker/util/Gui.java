package ch.hutch79.cookieclicker.util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Gui {

    private static int keksCount = 0;
    private static int random = 0;

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static void mainGui(Player player, int page) throws SQLException {
        Inventory mainInv;

        if (page == 2) {
            mainInv = Bukkit.createInventory(player, 54, "§6CookieClicker - Upgrade");
        } else {
            mainInv = Bukkit.createInventory(player, 54, "§6CookieClicker");
        }

        // Placeholder
        ItemStack placeholder = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta placeholderMeta = placeholder.getItemMeta();
        assert placeholderMeta != null;
        placeholderMeta.setDisplayName(" ");
        placeholder.setItemMeta(placeholderMeta);

        for (int i : new int[]{1, 10, 19, 28, 37, 46}) {
            mainInv.setItem(i, placeholder);
        }

        // Player Head
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta headMeta = (SkullMeta) head.getItemMeta();
        assert headMeta != null;
        headMeta.setDisplayName(player.getName());
        headMeta.setOwningPlayer(player);
        List<String> lore = new ArrayList<String>(); //create a List<String> for the lore
        lore.add("§3Kekse: §6" + CookieManager.getCookie(player));
        lore.add("§3CPC: §6" + CookieManager.getCPC(player));
        headMeta.setLore(lore);
        head.setItemMeta(headMeta);
        mainInv.setItem(0, head);

        // Klicker
        ItemStack keksMenu = new ItemStack(Material.COOKIE);
        ItemMeta keksMenuMeta = keksMenu.getItemMeta();
        assert keksMenuMeta != null;
        keksMenuMeta.setDisplayName("§6Klicker");
        keksMenu.setItemMeta(keksMenuMeta);
        mainInv.setItem(9, keksMenu);

        // Shop
        ItemStack shop = new ItemStack(Material.GOLD_INGOT);
        ItemMeta shopMeta = shop.getItemMeta();
        assert shopMeta != null;
        shopMeta.setDisplayName("§6Shop");
        shop.setItemMeta(shopMeta);
        mainInv.setItem(18, shop);

        if (page == 1) {

            // AutoKlick Detection
            ItemStack akDetection = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
            ItemMeta akDetectionMeta = placeholder.getItemMeta();
            assert akDetectionMeta != null;
            akDetectionMeta.setDisplayName(" ");
            akDetection.setItemMeta(placeholderMeta);

            for (int i : new int[]{2, 3, 4, 5, 6, 7, 8, 11, 12, 13, 14, 15, 16, 17, 20, 21, 22, 23, 24, 25, 26, 29, 30, 31, 32, 33, 34, 35, 38, 39, 40, 41, 42, 43, 44, 47, 48, 49, 50, 51, 52, 53}) {
                mainInv.setItem(i, akDetection);
            }

            // Cookie
            ArrayList list = new ArrayList();
            for (int i : new int[]{2, 3, 4, 5, 6, 7, 8, 11, 12, 13, 14, 15, 16, 17, 20, 21, 22, 23, 24, 25, 26, 29, 30, 31, 32, 33, 34, 35, 38, 39, 40, 41, 42, 43, 44, 47, 48, 49, 50, 51, 52, 53}) {
                list.add(i);
            }

            ItemStack keks = new ItemStack(Material.COOKIE);
            ItemMeta keksMeta = keks.getItemMeta();
            assert keksMeta != null;
            keksMeta.setDisplayName("§6Cookie");
            List<String> lore1 = new ArrayList<String>(); //create a List<String> for the lore
            if (keksCount <= 1) {
                keksCount = getRandomNumber(32, 128);
                random = getRandomNumber(0, 53);
                while (!list.contains(random)) {
                    random = getRandomNumber(0, 53);
                }
            } else {
                keksCount = keksCount - 1;
            }
            lore1.add("§3Anzahl: §6" + keksCount);
            keksMeta.setLore(lore1);
            keks.setItemMeta(keksMeta);
            mainInv.setItem(random, keks);

        } else if (page == 2) {
            // 1. Upgrade
            ItemStack upgrade1 = new ItemStack(Material.DIAMOND);
            ItemMeta upgrade1Meta = upgrade1.getItemMeta();
            assert upgrade1Meta != null;
            upgrade1Meta.setDisplayName("§6Upgrade 1");
            upgrade1.setItemMeta(upgrade1Meta);
            mainInv.setItem(2, upgrade1);
        }

        player.openInventory(mainInv);
    }

}
