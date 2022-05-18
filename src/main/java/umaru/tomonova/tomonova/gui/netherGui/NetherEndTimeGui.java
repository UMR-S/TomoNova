package umaru.tomonova.tomonova.gui.netherGui;

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

public class NetherEndTimeGui extends TimerGui {
    public NetherEndTimeGui(Player player) {
        super(player, 9,ChatColor.DARK_PURPLE + Lang.GUIS_MAIN_NETHER.toString());
        final ItemsCreator ic = new ItemsCreator(Material.OBSIDIAN, Lang.GUIS_NET_NAME.toString() + TomoNova.getPlugin().gameManager.getNetherEndTime(), Arrays.asList(Lang.GUIS_NET_LORE.toString()));
        NetherEndTimeGui.inventory.setItem(4, ItemsCreator.create(ic));
    }

    @EventHandler
    public void onClick(final InventoryClickEvent event) {
        if (event.getClickedInventory() == null) {
            return;
        }
        if (event.getClickedInventory().equals(NetherEndTimeGui.inventory)) {
            final ItemStack is = event.getCurrentItem();
            if (is == null || is.getType() == Material.AIR) {
                return;
            }
            event.setCancelled(true);
            switch (is.getType()) {
                case OBSIDIAN: {
                    this.player.closeInventory();
                    new NetherGui(this.player).show();
                    break;
                }
                case RED_BANNER: {
                    final String name = ChatColor.stripColor(is.getItemMeta().getDisplayName());
                    final int value = TomoNova.getPlugin().gameManager.getNetherEndTime() + Integer.parseInt(name);
                    if (value < 0) {
                        break;
                    }
                    TomoNova.getPlugin().gameManager.setNetherEndTime(value);
                    final ItemsCreator ic = new ItemsCreator(Material.OBSIDIAN, Lang.GUIS_NET_NAME.toString() + TomoNova.getPlugin().gameManager.getNetherEndTime(), Arrays.asList(Lang.GUIS_NET_LORE.toString()));
                    NetherEndTimeGui.inventory.setItem(4, ItemsCreator.create(ic));
                    break;
                }
                case GREEN_BANNER:{
                    final String name = ChatColor.stripColor(is.getItemMeta().getDisplayName());
                    final int value = TomoNova.getPlugin().gameManager.getNetherEndTime() + Integer.parseInt(name);
                    TomoNova.getPlugin().gameManager.setNetherEndTime(value);
                    final ItemsCreator ic = new ItemsCreator(Material.OBSIDIAN, Lang.GUIS_NET_NAME.toString() + TomoNova.getPlugin().gameManager.getNetherEndTime(), Arrays.asList(Lang.GUIS_NET_LORE.toString()));
                    NetherEndTimeGui.inventory.setItem(4, ItemsCreator.create(ic));
                    break;
                }
            }
        }
    }

    @EventHandler
    public void onClick(final InventoryCloseEvent event) {
        if (event.getInventory().equals(NetherEndTimeGui.inventory)) {
            HandlerList.unregisterAll((Listener)this);
        }
    }
}
