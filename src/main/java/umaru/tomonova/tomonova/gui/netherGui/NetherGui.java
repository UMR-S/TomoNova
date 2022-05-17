package umaru.tomonova.tomonova.gui.netherGui;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.gui.Gui;
import umaru.tomonova.tomonova.gui.MainGui;
import umaru.tomonova.tomonova.lang.Lang;
import umaru.tomonova.tomonova.utils.gui.ItemsCreator;

import java.awt.*;
import java.util.Arrays;

public class NetherGui extends Gui {
    public NetherGui(Player player) {
        super(player, 9, Lang.GUIS_NETHER_NAME.toString());
        ItemsCreator ic = new ItemsCreator(Material.OBSIDIAN, Lang.GUIS_NETHER_TIME.toString(), Arrays.asList(Lang.GUIS_NETHER_TIME_LORE.toString()));
        NetherGui.inventory.setItem(3, ic.create(ic));
        if (TomoNova.getPlugin().gameManager.isNether()) {
            ic = new ItemsCreator(Material.LIME_STAINED_GLASS_PANE, Lang.GUIS_NETHER_STATE_ON.toString(), Arrays.asList(Lang.GUIS_NETHER_STATE_ON_LORE.toString()));
        } else {
            ic = new ItemsCreator(Material.RED_STAINED_GLASS_PANE, Lang.GUIS_NETHER_OFF.toString(), Arrays.asList(Lang.GUIS_NETHER_OFF_LORE.toString()));
        }
        NetherGui.inventory.setItem(4, ItemsCreator.create(ic));
        ic = new ItemsCreator(Material.BARRIER, Lang.GUIS_BACK.toString(), Arrays.asList(""));
        NetherGui.inventory.setItem(5, ItemsCreator.create(ic));
    }

    @EventHandler
    public void onClick(final InventoryClickEvent event) {
        if (event.getClickedInventory() == null) {
            return;
        }
        if (event.getClickedInventory().equals(NetherGui.inventory)) {
            final ItemStack is = event.getCurrentItem();
            if (is == null || is.getType() == Material.AIR) {
                return;
            }
            event.setCancelled(true);
            switch (is.getType()) {
                case BARRIER: {
                    event.getWhoClicked().closeInventory();
                    new MainGui(this.player).show();
                    break;
                }
                case OBSIDIAN: {
                    this.player.closeInventory();
                    new NetherEndTimeGui(this.player).show();
                    break;
                }
            }

            if (is.getType() == Material.LIME_STAINED_GLASS_PANE) {
                TomoNova.getPlugin().gameManager.setNether(!TomoNova.getPlugin().gameManager.isNether());
                ItemsCreator ic = new ItemsCreator(Material.RED_STAINED_GLASS_PANE, Lang.GUIS_NETHER_OFF.toString(), Arrays.asList(Lang.GUIS_NETHER_OFF_LORE.toString()));
                NetherGui.inventory.setItem(4, ItemsCreator.create(ic));
            } else if (is.getType() == Material.RED_STAINED_GLASS_PANE) {
                TomoNova.getPlugin().gameManager.setNether(!TomoNova.getPlugin().gameManager.isNether());
                ItemsCreator ic = new ItemsCreator(Material.LIME_STAINED_GLASS_PANE, Lang.GUIS_NETHER_STATE_ON.toString(), Arrays.asList(Lang.GUIS_NETHER_STATE_ON_LORE.toString()));
                NetherGui.inventory.setItem(4, ItemsCreator.create(ic));
            }
        }
    }


    @EventHandler
    public void onClick(final InventoryCloseEvent event) {
        if (event.getInventory().equals(NetherGui.inventory)) {
            HandlerList.unregisterAll((Listener) this);
        }
    }
}
