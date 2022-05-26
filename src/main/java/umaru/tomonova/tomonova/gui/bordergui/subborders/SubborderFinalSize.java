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
import umaru.tomonova.tomonova.gui.timergui.LargeTimerGui;
import umaru.tomonova.tomonova.lang.Lang;
import umaru.tomonova.tomonova.utils.gui.ItemsCreator;

import java.util.Arrays;

public class SubborderFinalSize extends LargeTimerGui {
    public SubborderFinalSize(Player player) {
        super(player, 9, ChatColor.LIGHT_PURPLE + Lang.GUIS_BD_FINAL_SIZE.toString());
        final ItemsCreator ic = new ItemsCreator(Material.LIME_STAINED_GLASS_PANE, ChatColor.AQUA + Integer.toString(TomoNova.getPlugin().gameManager.getActualSubborderFinalSize()), Arrays.asList(Lang.GUIS_BDSS_LORE.toString(), Lang.GUIS_BDSS_LORE1.toString(), Lang.GUIS_BDSS_LORE2.toString()));
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
                case LIME_STAINED_GLASS_PANE: {
                    this.player.closeInventory();
                    new BorderGui(this.player).show();
                    break;
                }
                case RED_BANNER: {
                    final String name = ChatColor.stripColor(is.getItemMeta().getDisplayName());
                    final int value = TomoNova.getPlugin().gameManager.getActualSubborderFinalSize() + Integer.parseInt(name);
                    if (value < 1) {
                        break;
                    }
                    TomoNova.getPlugin().gameManager.setActualSubborderFinalSize(value);
                    final ItemsCreator ic = new ItemsCreator(Material.LIME_STAINED_GLASS_PANE, ChatColor.AQUA + Integer.toString(TomoNova.getPlugin().gameManager.getActualSubborderFinalSize()), Arrays.asList(Lang.GUIS_BDSS_LORE.toString(), Lang.GUIS_BDSS_LORE1.toString(), Lang.GUIS_BDSS_LORE2.toString()));
                    this.inventory.setItem(4, ItemsCreator.create(ic));
                    break;
                }
                case GREEN_BANNER: {
                    final String name = ChatColor.stripColor(is.getItemMeta().getDisplayName());
                    final int value = TomoNova.getPlugin().gameManager.getActualSubborderFinalSize() + Integer.parseInt(name);
                    TomoNova.getPlugin().gameManager.setActualSubborderFinalSize(value);
                    final ItemsCreator ic = new ItemsCreator(Material.LIME_STAINED_GLASS_PANE, ChatColor.AQUA + Integer.toString(TomoNova.getPlugin().gameManager.getActualSubborderFinalSize()), Arrays.asList(Lang.GUIS_BDSS_LORE.toString(), Lang.GUIS_BDSS_LORE1.toString(), Lang.GUIS_BDSS_LORE2.toString()));
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
