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

import java.util.Arrays;

public class BorderSpeedGui extends TimerGui {
    public BorderSpeedGui(Player player) {
        super(player, 9, ChatColor.LIGHT_PURPLE + Lang.GUIS_BD_SPEED.toString());
        final ItemsCreator ic = new ItemsCreator(Material.NETHER_STAR, ChatColor.AQUA + Integer.toString(TomoNova.getPlugin().worldBorderUtils.getSpeed()), Arrays.asList(Lang.GUIS_BDS_LORE.toString(), Lang.GUIS_BDS_LORE1.toString()));
        BorderSpeedGui.inventory.setItem(4, ItemsCreator.create(ic));
    }

    @EventHandler
    public void onClick(final InventoryClickEvent event) {
        if (event.getClickedInventory() == null) {
            return;
        }
        if (event.getClickedInventory().equals(BorderSpeedGui.inventory)) {
            final ItemStack is = event.getCurrentItem();
            if (is == null || is.getType() == Material.AIR) {
                return;
            }
            event.setCancelled(true);
            switch (is.getType()) {
                case NETHER_STAR: {
                    this.player.closeInventory();
                    new BorderGui(this.player).show();
                    break;
                }
                case RED_BANNER: {
                    final String name = ChatColor.stripColor(is.getItemMeta().getDisplayName());
                    final int value = TomoNova.getPlugin().worldBorderUtils.getSpeed() + Integer.parseInt(name);
                    if (value < 1) {
                        break;
                    }
                    TomoNova.getPlugin().worldBorderUtils.setSpeed(value);
                    final ItemsCreator ic = new ItemsCreator(Material.NETHER_STAR, ChatColor.AQUA + Integer.toString(TomoNova.getPlugin().worldBorderUtils.getSpeed()), Arrays.asList(Lang.GUIS_BDS_LORE.toString(), Lang.GUIS_BDS_LORE1.toString()));
                    BorderSpeedGui.inventory.setItem(4, ItemsCreator.create(ic));
                    break;
                }
                case GREEN_BANNER: {
                    final String name = ChatColor.stripColor(is.getItemMeta().getDisplayName());
                    final int value = TomoNova.getPlugin().worldBorderUtils.getSpeed() + Integer.parseInt(name);
                    TomoNova.getPlugin().gameManager.setTimeBorder(value);
                    final ItemsCreator ic = new ItemsCreator(Material.NETHER_STAR, ChatColor.AQUA + Integer.toString(TomoNova.getPlugin().worldBorderUtils.getSpeed()), Arrays.asList(Lang.GUIS_BDS_LORE.toString(), Lang.GUIS_BDS_LORE1.toString()));
                    BorderSpeedGui.inventory.setItem(4, ItemsCreator.create(ic));
                    break;
                }
            }
        }
    }

    @EventHandler
    public void onClick(final InventoryCloseEvent event) {
        if (event.getInventory().equals(BorderSpeedGui.inventory)) {
            HandlerList.unregisterAll((Listener) this);
        }
    }
}
