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

    public Inventory mainInv;
    private int keksCount = 0;
    private int random = 0;
    private int autoklicker = 0;
    private int autoklickerReset = 0;

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void mainGui(Player player) throws SQLException {
        player.sendMessage("Execute mainGui");
        this.mainInv = Bukkit.createInventory(player, 54, "§cCookieClicker");

        // Placeholder
        ItemStack placeholder = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta placeholderMeta = placeholder.getItemMeta();
        assert placeholderMeta != null;
        placeholderMeta.setDisplayName(" ");
        placeholder.setItemMeta(placeholderMeta);

        for (int i : new int[]{1, 10, 19, 28, 37, 46}) this.mainInv.setItem(i, placeholder);

        // Shop
        ItemStack shop = new ItemStack(Material.GOLD_INGOT);
        ItemMeta shopMeta = shop.getItemMeta();
        assert shopMeta != null;
        shopMeta.setDisplayName("§6Shop");
        shop.setItemMeta(shopMeta);
        this.mainInv.setItem(9, shop);

        // AutoKlick Detection
        ItemStack akDetection = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
        ItemMeta akDetectionMeta = placeholder.getItemMeta();
        assert akDetectionMeta != null;
        akDetectionMeta.setDisplayName(" ");
        akDetection.setItemMeta(placeholderMeta);

        //Bessere for-Schleife, übersichtlicher
        for (int i = 2; i < 54; i++)
            this.mainInv.setItem(i, akDetection);

        // Player Head
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta headMeta = (SkullMeta) head.getItemMeta();
        assert headMeta != null;
        headMeta.setDisplayName(player.getName());
        headMeta.setOwningPlayer(player);
        List<String> lore = new ArrayList<>(); //create a List<String> for the lore
        lore.add("§3Kekse: §6" + CookieManager.getCookie(player));
        lore.add("§3CPC: §6" + CookieManager.getCPC(player));
        headMeta.setLore(lore);
        head.setItemMeta(headMeta);
        this.mainInv.setItem(0, head);

        // Cookie

        ArrayList list = new ArrayList();
        //Bessere for-Schleife, übersichtlicher
        for (int i = 2; i < 54; i++)
            list.add(i);

        ItemStack keks = new ItemStack(Material.COOKIE);
        ItemMeta keksMeta = keks.getItemMeta();
        assert keksMeta != null;
        keksMeta.setDisplayName("§6Cookie");
        List<String> lore1 = new ArrayList<>(); //create a List<String> for the lore
        if (this.keksCount > 1) this.keksCount = this.keksCount - 1;
        else {
            this.keksCount = this.getRandomNumber(32, 128);
            this.random = this.getRandomNumber(0, 53);
            while (!list.contains(this.random))
                this.random = this.getRandomNumber(0, 53);
        }
        lore1.add("§3Anzahl: §6" + this.keksCount);
        keksMeta.setLore(lore1);
        keks.setItemMeta(keksMeta);
        this.mainInv.setItem(this.random, keks);

        player.openInventory(this.mainInv);

    }

    public int getAutoklicker() {
        return this.autoklicker;
    }

    public void setAutoklicker(int autoklicker) {
        this.autoklicker = autoklicker;
    }

    public int getAutoklickerReset() {
        return this.autoklickerReset;
    }

    public void setAutoklickerReset(int autoklickerReset) {
        this.autoklickerReset = autoklickerReset;
    }

    public Inventory getMainInv() {
        return this.mainInv;
    }

}
