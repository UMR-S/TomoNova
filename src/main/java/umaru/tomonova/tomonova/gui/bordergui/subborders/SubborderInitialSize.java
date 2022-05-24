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
import umaru.tomonova.tomonova.gui.bordergui.StartBorderSizeGui;
import umaru.tomonova.tomonova.gui.timergui.LargeTimerGui;
import umaru.tomonova.tomonova.lang.Lang;
import umaru.tomonova.tomonova.utils.gui.ItemsCreator;

import java.util.Arrays;

public class SubborderInitialSize extends LargeTimerGui {
    public SubborderInitialSize(Player player) {
        super(player, 9, ChatColor.LIGHT_PURPLE + Lang.GUIS_BD_INITIAL_SIZE.toString());
        final ItemsCreator ic = new ItemsCreator(Material.LIME_STAINED_GLASS_PANE, ChatColor.AQUA + Integer.toString(TomoNova.getPlugin().worldBorderUtils.getStartSize()), Arrays.asList(Lang.GUIS_BDSS_LORE.toString(), Lang.GUIS_BDSS_LORE1.toString(), Lang.GUIS_BDSS_LORE2.toString()));
        SubborderInitialSize.inventory.setItem(4, ItemsCreator.create(ic));
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
                    final int value = TomoNova.getPlugin().worldBorderUtils.getStartSize() + Integer.parseInt(name);
                    if (value < 1) {
                        break;
                    }
                    TomoNova.getPlugin().worldBorderUtils.setStartSize(value);
                    final ItemsCreator ic = new ItemsCreator(Material.LIME_STAINED_GLASS_PANE, ChatColor.AQUA + Integer.toString(TomoNova.getPlugin().worldBorderUtils.getStartSize()), Arrays.asList(Lang.GUIS_BDSS_LORE.toString(), Lang.GUIS_BDSS_LORE1.toString(), Lang.GUIS_BDSS_LORE2.toString()));
                    SubborderInitialSize.inventory.setItem(4, ItemsCreator.create(ic));
                    break;
                }
                case GREEN_BANNER: {
                    final String name = ChatColor.stripColor(is.getItemMeta().getDisplayName());
                    final int value = TomoNova.getPlugin().worldBorderUtils.getStartSize() + Integer.parseInt(name);
                    TomoNova.getPlugin().worldBorderUtils.setStartSize(value);
                    final ItemsCreator ic = new ItemsCreator(Material.LIME_STAINED_GLASS_PANE, ChatColor.AQUA + Integer.toString(TomoNova.getPlugin().worldBorderUtils.getStartSize()), Arrays.asList(Lang.GUIS_BDSS_LORE.toString(), Lang.GUIS_BDSS_LORE1.toString(), Lang.GUIS_BDSS_LORE2.toString()));
                    SubborderInitialSize.inventory.setItem(4, ItemsCreator.create(ic));
                    break;
                }
            }
        }
    }

    @EventHandler
    public void onClick(final InventoryCloseEvent event) {
        if (event.getInventory().equals(SubborderInitialSize.inventory)) {
            HandlerList.unregisterAll((Listener) this);
        }
    }
}
