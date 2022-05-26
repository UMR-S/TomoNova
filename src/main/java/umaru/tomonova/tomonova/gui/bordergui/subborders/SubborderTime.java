package umaru.tomonova.tomonova.gui.bordergui.subborders;

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
import umaru.tomonova.tomonova.gui.bordergui.BorderGui;
import umaru.tomonova.tomonova.gui.bordergui.BorderSpeedGui;
import umaru.tomonova.tomonova.gui.bordergui.BorderTimeGui;
import umaru.tomonova.tomonova.gui.timergui.TimerGui;
import umaru.tomonova.tomonova.lang.Lang;
import umaru.tomonova.tomonova.utils.gui.ItemsCreator;

import java.util.Arrays;

public class SubborderTime extends TimerGui {
    public SubborderTime(Player player) {
        super(player, 9, ChatColor.LIGHT_PURPLE + Lang.GUIS_BD_TIME.toString());
        final ItemsCreator ic = new ItemsCreator(Material.COBBLESTONE_WALL, ChatColor.AQUA + Integer.toString(TomoNova.getPlugin().gameManager.getTimeBorder()), Arrays.asList(Lang.GUIS_BDT_LORE.toString()));
        this.inventory.setItem(4, ItemsCreator.create(ic));
    }

    @EventHandler
    public void onClick(final InventoryClickEvent event) {
        if (event.getClickedInventory() == null) {
            return;
        }
        if (event.getClickedInventory().equals(this.inventory)) {
            final ItemStack is = event.getCurrentItem();
            if (is == null || is.getType() == Material.AIR) {
                return;
            }
            event.setCancelled(true);
            switch (is.getType()) {
                case COBBLESTONE_WALL: {
                    this.player.closeInventory();
                    new BorderGui(this.player).show();
                    break;
                }
                case RED_BANNER: {
                    final String name = ChatColor.stripColor(is.getItemMeta().getDisplayName());
                    final int value = TomoNova.getPlugin().gameManager.getTimeBorder() + Integer.parseInt(name);
                    if (value < 1) {
                        break;
                    }
                    TomoNova.getPlugin().gameManager.setTimeBorder(value);
                    final ItemsCreator ic = new ItemsCreator(Material.COBBLESTONE_WALL, ChatColor.AQUA + Integer.toString(TomoNova.getPlugin().gameManager.getTimeBorder()), Arrays.asList(Lang.GUIS_BDT_LORE.toString()));
                    this.inventory.setItem(4, ItemsCreator.create(ic));
                    break;
                }
                case GREEN_BANNER: {
                    final String name = ChatColor.stripColor(is.getItemMeta().getDisplayName());
                    final int value = TomoNova.getPlugin().gameManager.getTimeBorder() + Integer.parseInt(name);
                    TomoNova.getPlugin().gameManager.setTimeBorder(value);
                    final ItemsCreator ic = new ItemsCreator(Material.COBBLESTONE_WALL, ChatColor.AQUA + Integer.toString(TomoNova.getPlugin().gameManager.getTimeBorder()), Arrays.asList(Lang.GUIS_BDT_LORE.toString()));
                    this.inventory.setItem(4, ItemsCreator.create(ic));
                    break;
                }
            }
        }
    }

    @EventHandler
    public void onClick(final InventoryCloseEvent event) {
        if (event.getInventory().equals(this.inventory)) {
            HandlerList.unregisterAll((Listener) this);
        }
    }

}
