package ch.hutch79.cookieclicker.util;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GuiListerne implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        if (e.getView().getTitle().equals("CookieClicker") && e.getCurrentItem() != null && e.getRawSlot() <= 53) {
            e.setCancelled(true);
            Player player = (Player) e.getWhoClicked();

            switch (e.getRawSlot()){
                case 0:

                    break;

                case 9: // Shop
                    player.sendMessage("Das ist der Shop");
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

                    if(e.getCurrentItem().getType().equals(Material.COOKIE)) {
                        player.sendMessage("Keksiiii");
                    } else if (e.getCurrentItem().getType().equals(Material.BLACK_STAINED_GLASS_PANE)) {
                        player.sendMessage("Platzhalter");

                    } else {
                        player.sendMessage("Da ist nix ");
                    }

                    return;
            }

        }

    }

}
