package umaru.tomonova.tomonova.gui.bordergui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
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

import java.util.Arrays;

public class BorderGui extends Gui {
    public BorderGui(Player player) {
        super(player, 18 + TomoNova.getPlugin().gameManager.getSubBorders() * 9, ChatColor.LIGHT_PURPLE + Lang.GUIS_BD_NAME.toString());
        ItemsCreator ic = new ItemsCreator(Material.LIME_STAINED_GLASS_PANE, ChatColor.GREEN + Lang.GUIS_BD_INITIAL_SIZE.toString(), Arrays.asList(Lang.GUIS_BD_INITIAL_SIZE_LORE.toString()));
        BorderGui.inventory.setItem(3, ItemsCreator.create(ic));
        ic = new ItemsCreator(Material.NETHER_STAR, Lang.GUIS_BD_SPEED.toString(), Arrays.asList(Lang.GUIS_BD_SPEED_LORE.toString()));
        BorderGui.inventory.setItem(4, ItemsCreator.create(ic));
        ic = new ItemsCreator(Material.RED_STAINED_GLASS_PANE, ChatColor.RED + Lang.GUIS_BD_FINAL_SIZE.toString(), Arrays.asList(Lang.GUIS_BD_FINAL_SIZE_LORE.toString()));
        BorderGui.inventory.setItem(5, ItemsCreator.create(ic));
        ic = new ItemsCreator(Material.STICK, Lang.GUIS_BD_SUBBORDER_NAME.toString(), Arrays.asList(Lang.GUIS_BD_SUBBORDER_LORE.toString()));
        BorderGui.inventory.setItem(8, ItemsCreator.create(ic));
        ic = new ItemsCreator(Material.COBBLESTONE_WALL, Lang.GUIS_BD_TIME.toString(), Arrays.asList(Lang.GUIS_BD_TIME_LORE.toString()));
        BorderGui.inventory.setItem(13, ItemsCreator.create(ic));
        ic = new ItemsCreator(Material.BARRIER, Lang.GUIS_BACK.toString(), null);
        BorderGui.inventory.setItem(17, ItemsCreator.create(ic));
    }

    @EventHandler
    public void onClick(final InventoryClickEvent event) {
        if (event.getClickedInventory() == null) {
            return;
        }
        if (event.getClickedInventory().equals(BorderGui.inventory)) {
            final ItemStack is = event.getCurrentItem();
            if (is == null || is.getType() == Material.AIR) {
                return;
            }
            event.setCancelled(true);
            if (is.getType() == Material.LIME_STAINED_GLASS_PANE) {
                event.getWhoClicked().closeInventory();
                new StartBorderSizeGui(this.player).show();
            } else if (is.getType() == Material.NETHER_STAR) {
                event.getWhoClicked().closeInventory();
                new BorderSpeedGui(this.player).show();
            } else if (is.getType() == Material.RED_STAINED_GLASS_PANE) {
                event.getWhoClicked().closeInventory();
                new FinalBorderSizeGui(this.player).show();
            } else if (is.getType() == Material.COBBLESTONE_WALL) {
                event.getWhoClicked().closeInventory();
                new BorderTimeGui(this.player).show();
            } else if (is.getType() == Material.BARRIER) {
                event.getWhoClicked().closeInventory();
                new MainGui(this.player).show();
            } else if (is.getType() == Material.STICK) {
                event.getWhoClicked().closeInventory();
                new SubborderGui(this.player).show();
            }
        }
    }

    @EventHandler
    public void onClick(final InventoryCloseEvent event) {
        if (event.getInventory().equals(BorderGui.inventory)) {
            HandlerList.unregisterAll((Listener) this);
        }
    }
}
