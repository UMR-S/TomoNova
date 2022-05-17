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
import umaru.tomonova.tomonova.gui.timergui.LargeTimerGui;
import umaru.tomonova.tomonova.lang.Lang;
import umaru.tomonova.tomonova.utils.gui.ItemsCreator;
import umaru.tomonova.tomonova.utils.worldborder.WorldBorderUtils;

import java.util.Arrays;

public class StartBorderSizeGui extends LargeTimerGui {
    public StartBorderSizeGui(Player player) {
        super(player, 9, Lang.GUIS_BD_INITIAL_SIZE.toString());
        final ItemsCreator ic = new ItemsCreator(Material.LIME_STAINED_GLASS_PANE, Lang.GUIS_BDSS_NAME.toString() + WorldBorderUtils.getStartSize(), Arrays.asList(Lang.GUIS_BDSS_LORE.toString(),Lang.GUIS_BDSS_LORE1.toString(),Lang.GUIS_BDSS_LORE2.toString()));
        StartBorderSizeGui.inventory.setItem(4, ItemsCreator.create(ic));
    }

    @EventHandler
    public void onClick(final InventoryClickEvent event) {
        if (event.getClickedInventory() == null) {
            return;
        }
        if (event.getClickedInventory().equals(StartBorderSizeGui.inventory)) {
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
                    final int value = WorldBorderUtils.getStartSize() + Integer.parseInt(name);
                    if (value < 1) {
                        break;
                    }
                    WorldBorderUtils.setStartSize(value);
                    final ItemsCreator ic = new ItemsCreator(Material.LIME_STAINED_GLASS_PANE, Lang.GUIS_BDSS_NAME.toString() + WorldBorderUtils.getStartSize(), Arrays.asList(Lang.GUIS_BDSS_LORE.toString(),Lang.GUIS_BDSS_LORE1.toString(),Lang.GUIS_BDSS_LORE2.toString()));
                    StartBorderSizeGui.inventory.setItem(4, ItemsCreator.create(ic));
                    break;
                }
                case GREEN_BANNER: {
                    final String name = ChatColor.stripColor(is.getItemMeta().getDisplayName());
                    final int value = WorldBorderUtils.getStartSize() + Integer.parseInt(name);
                    WorldBorderUtils.setStartSize(value);
                    final ItemsCreator ic = new ItemsCreator(Material.LIME_STAINED_GLASS_PANE, Lang.GUIS_BDSS_NAME.toString() + WorldBorderUtils.getStartSize(), Arrays.asList(Lang.GUIS_BDSS_LORE.toString(),Lang.GUIS_BDSS_LORE1.toString(),Lang.GUIS_BDSS_LORE2.toString()));
                    StartBorderSizeGui.inventory.setItem(4, ItemsCreator.create(ic));
                    break;
                }
            }
        }
    }

    @EventHandler
    public void onClick(final InventoryCloseEvent event) {
        if (event.getInventory().equals(StartBorderSizeGui.inventory)) {
            HandlerList.unregisterAll((Listener)this);
        }
    }
}
