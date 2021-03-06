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
import umaru.tomonova.tomonova.gui.timergui.MaxPlayersGui;
import umaru.tomonova.tomonova.gui.timergui.MinPlayersGui;
import umaru.tomonova.tomonova.gui.timergui.TimerGui;
import umaru.tomonova.tomonova.lang.Lang;
import umaru.tomonova.tomonova.utils.gui.ItemsCreator;

import java.util.Arrays;

public class SubborderGui extends TimerGui {

    public SubborderGui(Player player) {
        super(player, 9, ChatColor.LIGHT_PURPLE + Lang.GUIS_BD_SUBBD_ADD_NAME.toString());
        final ItemsCreator ic = new ItemsCreator(Material.STICK, ChatColor.AQUA + "", Arrays.asList(Lang.GUIS_BD_SUBBD_ADD_LORE.toString()));
        SubborderGui.inventory.setItem(4, ItemsCreator.create(ic));
    }

    @EventHandler
    public void onClick(final InventoryClickEvent event) {
        if (event.getClickedInventory() == null) {
            return;
        }
        if (event.getClickedInventory().equals(MaxPlayersGui.inventory)) {
            final ItemStack is = event.getCurrentItem();
            if (is == null || is.getType() == Material.AIR) {
                return;
            }
            event.setCancelled(true);
            switch (is.getType()) {
                case STICK: {
                    this.player.closeInventory();
                    new BorderGui(this.player).show();
                    break;
                }
                case RED_BANNER: {
                    final String name = ChatColor.stripColor(is.getItemMeta().getDisplayName());
                    Integer value = 0;
                    if (value < 0) {
                        break;
                    }

                    final ItemsCreator ic = new ItemsCreator(Material.STICK, ChatColor.AQUA + "", Arrays.asList(Lang.GUIS_BD_SUBBD_ADD_LORE.toString()));
                    SubborderGui.inventory.setItem(4, ItemsCreator.create(ic));
                    break;
                }
                case GREEN_BANNER: {
                    final String name = ChatColor.stripColor(is.getItemMeta().getDisplayName());
                    final int value = 0;
                    if (value > 2){
                        break;
                    }
                    final ItemsCreator ic = new ItemsCreator(Material.STICK, ChatColor.AQUA + "", Arrays.asList(Lang.GUIS_BD_SUBBD_ADD_LORE.toString()));
                    SubborderGui.inventory.setItem(4, ItemsCreator.create(ic));
                    break;
                }
            }
        }
    }

    @EventHandler
    public void onClick(final InventoryCloseEvent event) {
        if (event.getInventory().equals(MinPlayersGui.inventory)) {
            HandlerList.unregisterAll((Listener) this);
        }
    }
}
