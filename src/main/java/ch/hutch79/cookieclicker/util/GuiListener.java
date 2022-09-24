package ch.hutch79.cookieclicker.util;

import ch.hutch79.cookieclicker.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

import java.sql.SQLException;
import java.util.Objects;


public class GuiListener/*Schreibfehler ._.*/ implements Listener {
    private final Main main;

    public GuiListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent e) {
        if (!e.getView().getTitle().equalsIgnoreCase("§cCookieClicker")) //Ganz ehrlich? Scheiß auf Server, die es erlauben Truhen mit Farbcodes zu versehen.
            return;

        for (int i : e.getRawSlots()) //????????????
            if (i <= 53) {
                e.setCancelled(true);
                break;
            }
    }

    @EventHandler
    public void InventoryClose(InventoryCloseEvent e) {

    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) throws SQLException {
        if (!e.getView().getTitle().equalsIgnoreCase("§cCookieClicker")) //Ganz ehrlich? Scheiß auf Server, die es erlauben Truhen mit Farbcodes zu versehen.
            return;

        if (!e.isShiftClick())
            return;

        e.setCancelled(true);

        if (e.getRawSlot() > 53) //Warum RawSlot? Damit gehst du auch in das SpielerInventar selbst...
            return;

        e.setCancelled(true);
        Player player = (Player) e.getWhoClicked();

        switch (e.getRawSlot()) {
            case 0:
                player.sendMessage("Slot 0");
                break;

            case 9: // Shop
                player.sendMessage("Das ist der Shop");
                CookieManager.modifyCookie(1, player);
                player.sendMessage("Kekse: " + CookieManager.getCookie(player));
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

                // Cookie
                if (Objects.requireNonNull(e.getCurrentItem()).getType().equals(Material.COOKIE)) {
                    if (!this.main.getPlayerGuiHashMap().containsKey(player))
                        return; //Ignoriere, ist nicht in einem Cookie Clicker Gui, Fix gegen Color Rename lässt grüßen

                    Gui gui = this.main.getPlayerGuiHashMap().get(player);

                    gui.setAutoklicker(gui.getAutoklicker() + 1);


                    CookieManager.modifyCookie(CookieManager.getCPC(player), player);

                    gui.setAutoklickerReset(gui.getAutoklickerReset() + 1);
                    if (gui.getAutoklickerReset() >= 10)
                        gui.setAutoklicker(0);


                    //Wieso auch immer du ein neues GUI öffnest
                    /*Gui gui = new Gui();

                    this.playerGuiHashMap.put(player, gui);

                    gui.mainGui(player);*/

                    // AutoKlicker Detection
                } else if (e.getCurrentItem().getType().equals(Material.LIGHT_GRAY_STAINED_GLASS_PANE)) {
                    if (!this.main.getPlayerGuiHashMap().containsKey(player))
                        return; //Ignoriere, ist nicht in einem Cookie Clicker Gui, Fix gegen Color Rename lässt grüßen

                    Gui gui = this.main.getPlayerGuiHashMap().get(player);

                    gui.setAutoklicker(gui.getAutoklicker() + 1);
                    gui.setAutoklicker(0);
                    if (gui.getAutoklicker() >= 8)
                        player.closeInventory();
                } else
                    player.sendMessage("Da ist nix ");
        }
    }
}
