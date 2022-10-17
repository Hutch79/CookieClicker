package ch.hutch79.cookieclicker.util;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;


import java.sql.SQLException;
import java.util.Objects;


public class GuiListerne implements Listener {

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
        }

        if ((e.getView().getTitle().equals("§6CookieClicker") || e.getView().getTitle().equals("§6CookieClicker - Upgrade")) && e.getRawSlot() <= 53) {

            switch (e.getRawSlot()) {
                case 0:
                    player.sendMessage("Slot 0");
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
                if (Objects.requireNonNull(e.getCurrentItem()).getType().equals(Material.COOKIE)) {
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
                    }
                }
            }

            if (e.getView().getTitle().equals("§6CookieClicker - Upgrade") && e.getRawSlot() <= 53) {

                switch (e.getRawSlot()) {
                    case 2: // Upgrade 1
                        double price = CookieManager.getUpgrade(1, player);
                        if (CookieManager.getCookie(player) >= price) {
                            CookieManager.modifyCookie(-price, player);
                            CookieManager.modifyCPC(0.3, player);
                            double price_new = (price * 1.15) - price;
                            CookieManager.modifyUpgrade(1, price_new, player);

                            Gui.mainGui(player, 2);
                        }
                        break;

                    case 9:

                        break;

                    case 18:

                        break;

                    case 27:

                        break;

                    case 36:

                        break;

                    case 45:

                        break;

                    default:
                }
            }
        }
    }
}
