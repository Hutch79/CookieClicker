package ch.hutch79.cookieclicker.util;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;

import java.lang.reflect.Type;
import java.util.Objects;


public class GuiListerne implements Listener {



    @EventHandler
    public void on(InventoryDragEvent e) {

        if (e.getInventory().equals(Gui.getMainInv())) {

            for (int i : e.getRawSlots()) {
                if (i <= 53) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        if (e.getInventory().equals(Gui.getMainInv()) && e.getRawSlot() <= 53) {
            e.setCancelled(true);
            Player player = (Player) e.getWhoClicked();

            switch (e.getRawSlot()){
                case 0:
                    player.sendMessage("Slot 0");
                    break;

                case 9: // Shop
                    player.sendMessage("Das ist der Shop");
                    break;

                case 18:
                    player.sendMessage("Slot 18");
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

                    if(Objects.requireNonNull(e.getCurrentItem()).getType().equals(Material.COOKIE)) {
                        player.sendMessage("Keksiiii");
                    } else if (e.getCurrentItem().getType().equals(Material.BLACK_STAINED_GLASS_PANE)) {
                        player.sendMessage("Platzhalter");

                    } else {
                        player.sendMessage("Da ist nix ");
                    }

            }

        }

    }

}
