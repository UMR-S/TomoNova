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
import umaru.tomonova.tomonova.gui.timergui.TimerGui;
import umaru.tomonova.tomonova.lang.Lang;
import umaru.tomonova.tomonova.utils.gui.ItemsCreator;
import umaru.tomonova.tomonova.utils.worldborder.WorldBorderUtils;

import java.util.Arrays;

public class FinalBorderSizeGui extends TimerGui {
    public FinalBorderSizeGui(Player player) {
        super(player, 9, ChatColor.LIGHT_PURPLE + Lang.GUIS_BD_FINAL_SIZE.toString());
        final ItemsCreator ic = new ItemsCreator(Material.RED_STAINED_GLASS_PANE, ChatColor.AQUA + Integer.toString(TomoNova.getPlugin().worldBorderUtils.getFinalSize()), Arrays.asList(Lang.GUIS_BDFS_LORE.toString(), Lang.GUIS_BDFS_LORE1.toString(), Lang.GUIS_BDFS_LORE2.toString()));
        FinalBorderSizeGui.inventory.setItem(4, ItemsCreator.create(ic));
    }

    @EventHandler
    public void onClick(final InventoryClickEvent event) {
        if (event.getClickedInventory() == null) {
            return;
        }
        if (event.getClickedInventory().equals(FinalBorderSizeGui.inventory)) {
            final ItemStack is = event.getCurrentItem();
            if (is == null || is.getType() == Material.AIR) {
                return;
            }
            event.setCancelled(true);
            switch (is.getType()) {
                case RED_STAINED_GLASS_PANE: {
                    this.player.closeInventory();
                    new BorderGui(this.player).show();
                    break;
                }
                case RED_BANNER: {
                    final String name = ChatColor.stripColor(is.getItemMeta().getDisplayName());
                    final int value = TomoNova.getPlugin().worldBorderUtils.getFinalSize() + Integer.parseInt(name);
                    if (value < 1) {
                        break;
                    }
                    TomoNova.getPlugin().worldBorderUtils.setFinalSize(value);
                    final ItemsCreator ic = new ItemsCreator(Material.RED_STAINED_GLASS_PANE, ChatColor.AQUA + Integer.toString(TomoNova.getPlugin().worldBorderUtils.getFinalSize()), Arrays.asList(Lang.GUIS_BDFS_LORE.toString(), Lang.GUIS_BDFS_LORE1.toString(), Lang.GUIS_BDFS_LORE2.toString()));
                    FinalBorderSizeGui.inventory.setItem(4, ItemsCreator.create(ic));
                    break;
                }
                case GREEN_BANNER: {
                    final String name = ChatColor.stripColor(is.getItemMeta().getDisplayName());
                    final int value = TomoNova.getPlugin().worldBorderUtils.getFinalSize() + Integer.parseInt(name);
                    TomoNova.getPlugin().worldBorderUtils.setFinalSize(value);
                    final ItemsCreator ic = new ItemsCreator(Material.RED_STAINED_GLASS_PANE, ChatColor.AQUA + Integer.toString(TomoNova.getPlugin().worldBorderUtils.getFinalSize()), Arrays.asList(Lang.GUIS_BDFS_LORE.toString(), Lang.GUIS_BDFS_LORE1.toString(), Lang.GUIS_BDFS_LORE2.toString()));
                    FinalBorderSizeGui.inventory.setItem(4, ItemsCreator.create(ic));
                    break;
                }
            }
        }
    }

    @EventHandler
    public void onClick(final InventoryCloseEvent event) {
        if (event.getInventory().equals(FinalBorderSizeGui.inventory)) {
            HandlerList.unregisterAll((Listener) this);
        }
    }

}
