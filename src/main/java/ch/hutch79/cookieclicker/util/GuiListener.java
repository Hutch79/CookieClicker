package ch.hutch79.cookieclicker.util;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;


import java.sql.SQLException;


public class GuiListener implements Listener {

    int autoklicker = 0;
    int autoklickerReset = 0;

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent e) {
        if (e.getView().getTitle().equals("§6CookieClicker") || e.getView().getTitle().equals("§6CookieClicker - Upgrade")) {
            for (int i : e.getRawSlots()) {
                if (i <= 53) {
                    e.setCancelled(true);
                    break;
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) throws SQLException {
        Player player = (Player) e.getWhoClicked();

        if ((e.getView().getTitle().equals("§6CookieClicker") || e.getView().getTitle().equals("§6CookieClicker - Upgrade")) && e.getRawSlot() <= 53) {
            e.setCancelled(true);

            switch (e.getRawSlot()) {
                case 0:

                    break;

                case 9: // Klicker
                    Gui.mainGui(player, 1);
                    break;

                case 18: // Shop
                    Gui.mainGui(player, 2);
                    break;

                case 27:
                    player.sendMessage("Slot 27");
                    break;

                case 36:
                    player.sendMessage("Slot 36");
                    break;

                case 45:
                    player.sendMessage("Slot 45");
                    break;

                default:
            }

            if (e.getView().getTitle().equals("§6CookieClicker") && e.getRawSlot() <= 53) {
                // Cookie
                try {
                    if (e.getCurrentItem().getType().equals(Material.COOKIE)) {
                        double cpc = CookieManager.getCPC(player);
                        CookieManager.modifyCookie(cpc, player);
                        autoklickerReset = autoklickerReset + 1;
                        if (autoklickerReset >= 10) {
                            autoklicker = 0;
                        }
                        Gui.mainGui(player, 1);

                        // AutoKlicker Detection
                    } else if (e.getCurrentItem().getType().equals(Material.LIGHT_GRAY_STAINED_GLASS_PANE)) {
                        autoklicker = autoklicker + 1;
                        autoklickerReset = 0;
                        if (autoklicker >= 8) {
                            player.closeInventory();
                            autoklicker = 0;
                        }
                    }

                } catch (NullPointerException ignored) {
                    // Einfach irgnorieren
                }
            }
            if (e.getView().getTitle().equals("§6CookieClicker - Upgrade") && e.getRawSlot() <= 53) {
                switch (e.getRawSlot()) {
                    case 2: // Upgrade 1
                        double price = CookieManager.getUpgrade(1, player);
                        if (price < 100) {
                            price = 100.0;
                            CookieManager.modifyUpgrade(1, price, player);
                            Gui.mainGui(player, 2);
                        }
                        if (CookieManager.getCookie(player) >= price) {
                            CookieManager.modifyCookie(-price, player);
                            CookieManager.modifyCPC(0.1, player);
                            double price_new = (price * 1.15) - price;
                            CookieManager.modifyUpgrade(1, price_new, player);

                            Gui.mainGui(player, 2);
                        }
                        break;

                    case 3: // Upgrade 2
                        double price2 = CookieManager.getUpgrade(2, player);
                        if (price2 < 500) {
                            price2 = 500.0;
                            CookieManager.modifyUpgrade(2, price2, player);
                            Gui.mainGui(player, 2);
                        }
                        if (CookieManager.getCookie(player) >= price2) {
                            CookieManager.modifyCookie(-price2, player);
                            CookieManager.modifyCPC(0.3, player);
                            double price2_new = (price2 * 1.15) - price2;
                            CookieManager.modifyUpgrade(2, price2_new, player);

                            Gui.mainGui(player, 2);
                        }
                        break;

                    case 4: // Upgrade 3
                        double price3 = CookieManager.getUpgrade(3, player);
                        if (price3 < 1500) {
                            price3 = 1500.0;
                            CookieManager.modifyUpgrade(3, price3, player);
                            Gui.mainGui(player, 2);
                        }
                        if (CookieManager.getCookie(player) >= price3) {
                            CookieManager.modifyCookie(-price3, player);
                            CookieManager.modifyCPC(1.0, player);
                            double price3_new = (price3 * 1.15) - price3;
                            CookieManager.modifyUpgrade(3, price3_new, player);

                            Gui.mainGui(player, 2);
                        }
                        break;

                    case 5: // Upgrade 4
                        double price4 = CookieManager.getUpgrade(4, player);
                        if (price4 < 5000) {
                            price4 = 5000.0;
                            CookieManager.modifyUpgrade(4, price4, player);
                            Gui.mainGui(player, 2);
                        }
                        if (CookieManager.getCookie(player) >= price4) {
                            CookieManager.modifyCookie(-price4, player);
                            CookieManager.modifyCPC(3.5, player);
                            double price4_new = (price4 * 1.15) - price4;
                            CookieManager.modifyUpgrade(4, price4_new, player);

                            Gui.mainGui(player, 2);
                        }
                        break;

                    case 6: // Upgrade 5
                        double price5 = CookieManager.getUpgrade(5, player);
                        if (price5 < 10000) {
                            price5 = 10000.0;
                            CookieManager.modifyUpgrade(5, price5, player);
                            Gui.mainGui(player, 2);
                        }
                        if (CookieManager.getCookie(player) >= price5) {
                            CookieManager.modifyCookie(-price5, player);
                            CookieManager.modifyCPC(7.0, player);
                            double price5_new = (price5 * 1.15) - price5;
                            CookieManager.modifyUpgrade(5, price5_new, player);

                            Gui.mainGui(player, 2);
                        }
                        break;

                    case 7:

                        break;

                    default:
                }
            }
        }
    }
}
