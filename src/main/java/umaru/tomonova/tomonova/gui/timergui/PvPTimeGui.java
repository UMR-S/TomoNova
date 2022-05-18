package umaru.tomonova.tomonova.gui.timergui;

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
import umaru.tomonova.tomonova.gui.MainGui;
import umaru.tomonova.tomonova.lang.Lang;
import umaru.tomonova.tomonova.utils.gui.ItemsCreator;

import java.util.Arrays;

public class PvPTimeGui extends TimerGui {
    public PvPTimeGui(Player player) {
        super(player, 9,ChatColor.LIGHT_PURPLE  + Lang.GUIS_PVP_TIME_NAME.toString());
        final ItemsCreator ic = new ItemsCreator(Material.DIAMOND_SWORD,ChatColor.AQUA + Integer.toString(TomoNova.getPlugin().gameManager.getPvpTime()), Arrays.asList(Lang.GUIS_PVP_TIME_LORE.toString()));
        PvPTimeGui.inventory.setItem(4, ItemsCreator.create(ic));
    }

    @EventHandler
    public void onClick(final InventoryClickEvent event) {
        if (event.getClickedInventory() == null) {
            return;
        }
        if (event.getClickedInventory().equals(PvPTimeGui.inventory)) {
            final ItemStack is = event.getCurrentItem();
            if (is == null || is.getType() == Material.AIR) {
                return;
            }
            event.setCancelled(true);
            switch (is.getType()) {
                case DIAMOND_SWORD: {
                    this.player.closeInventory();
                    new MainGui(this.player).show();
                    break;
                }
                case RED_BANNER: {
                    final String name = ChatColor.stripColor(is.getItemMeta().getDisplayName());
                    final int value = TomoNova.getPlugin().gameManager.getPvpTime() + Integer.parseInt(name);
                    if (value < 0) {
                        break;
                    }
                    TomoNova.getPlugin().gameManager.setPvpTime(value);
                    final ItemsCreator ic = new ItemsCreator(Material.DIAMOND_SWORD,ChatColor.AQUA + Integer.toString(TomoNova.getPlugin().gameManager.getPvpTime()), Arrays.asList(Lang.GUIS_PVP_TIME_LORE.toString()));
                    PvPTimeGui.inventory.setItem(4, ItemsCreator.create(ic));
                    break;
                }
                case GREEN_BANNER: {
                    final String name = ChatColor.stripColor(is.getItemMeta().getDisplayName());
                    final int value = TomoNova.getPlugin().gameManager.getPvpTime() + Integer.parseInt(name);
                    TomoNova.getPlugin().gameManager.setPvpTime(value);
                    final ItemsCreator ic = new ItemsCreator(Material.DIAMOND_SWORD,ChatColor.AQUA + Integer.toString(TomoNova.getPlugin().gameManager.getPvpTime()), Arrays.asList(Lang.GUIS_PVP_TIME_LORE.toString()));
                    PvPTimeGui.inventory.setItem(4, ItemsCreator.create(ic));
                    break;
                }
            }
        }
    }

    @EventHandler
    public void onClick(final InventoryCloseEvent event) {
        if (event.getInventory().equals(PvPTimeGui.inventory)) {
            HandlerList.unregisterAll((Listener)this);
        }
    }
}
