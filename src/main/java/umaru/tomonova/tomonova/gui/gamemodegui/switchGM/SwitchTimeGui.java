package umaru.tomonova.tomonova.gui.gamemodegui.switchGM;

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
import umaru.tomonova.tomonova.gui.bordergui.BorderGui;
import umaru.tomonova.tomonova.lang.Lang;
import umaru.tomonova.tomonova.utils.gui.ItemsCreator;

import java.util.Arrays;

public class SwitchTimeGui extends Gui {
    public SwitchTimeGui(Player player, int size, String name) {
        super(player, 9, Lang.GUIS_GM_SWITCH_TIME_NAME.toString());
        final ItemsCreator ic = new ItemsCreator(Material.CLOCK, ChatColor.RED + Integer.toString(TomoNova.getPlugin().gameManager.getBetweenSwitches()), Arrays.asList(Lang.GUIS_GM_SWITCH_TIME_LORE.toString()));
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
                case CLOCK: {
                    this.player.closeInventory();
                    new BorderGui(this.player).show();
                    break;
                }
                case RED_BANNER: {
                    final String name = ChatColor.stripColor(is.getItemMeta().getDisplayName());
                    final int value = TomoNova.getPlugin().gameManager.getBetweenSwitches() + Integer.parseInt(name);
                    if (value < 1) {
                        break;
                    }
                    TomoNova.getPlugin().gameManager.setBetweenSwitches(value);
                    final ItemsCreator ic = new ItemsCreator(Material.CLOCK, ChatColor.RED + Integer.toString(TomoNova.getPlugin().gameManager.getBetweenSwitches()), Arrays.asList(Lang.GUIS_GM_SWITCH_TIME_LORE.toString()));
                    this.inventory.setItem(4, ItemsCreator.create(ic));
                    break;
                }
                case GREEN_BANNER: {
                    final String name = ChatColor.stripColor(is.getItemMeta().getDisplayName());
                    final int value = TomoNova.getPlugin().gameManager.getBetweenSwitches() + Integer.parseInt(name);
                    TomoNova.getPlugin().gameManager.setBetweenSwitches(value);
                    final ItemsCreator ic = new ItemsCreator(Material.CLOCK, ChatColor.RED + Integer.toString(TomoNova.getPlugin().gameManager.getBetweenSwitches()), Arrays.asList(Lang.GUIS_GM_SWITCH_TIME_LORE.toString()));
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
