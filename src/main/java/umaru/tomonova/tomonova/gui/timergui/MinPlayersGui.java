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

public class MinPlayersGui extends TimerGui {
    public MinPlayersGui(Player player) {
        super(player, 9, ChatColor.LIGHT_PURPLE + Lang.GUIS_MIN_PLAYERS_NAME.toString());
        final ItemsCreator ic = new ItemsCreator(Material.OAK_BOAT, ChatColor.AQUA + Integer.toString(TomoNova.getPlugin().gameManager.getMinPlayers()), Arrays.asList(Lang.GUIS_MIN_PLAYERS_LORE.toString(), Lang.GUIS_MIN_PLAYERS_LORE1.toString()));
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
                case OAK_BOAT: {
                    this.player.closeInventory();
                    new MainGui(this.player).show();
                    break;
                }
                case RED_BANNER: {
                    final String name = ChatColor.stripColor(is.getItemMeta().getDisplayName());
                    final int value = TomoNova.getPlugin().gameManager.getMinPlayers() + Integer.parseInt(name);
                    if (value < 2) {
                        break;
                    }
                    TomoNova.getPlugin().gameManager.setMinPlayers(value);
                    final ItemsCreator ic = new ItemsCreator(Material.OAK_BOAT, ChatColor.AQUA + Integer.toString(TomoNova.getPlugin().gameManager.getMinPlayers()), Arrays.asList(Lang.GUIS_MIN_PLAYERS_LORE.toString(), Lang.GUIS_MIN_PLAYERS_LORE1.toString()));
                    this.inventory.setItem(4, ItemsCreator.create(ic));
                    break;
                }
                case GREEN_BANNER: {
                    final String name = ChatColor.stripColor(is.getItemMeta().getDisplayName());
                    final int value = TomoNova.getPlugin().gameManager.getMinPlayers() + Integer.parseInt(name);
                    TomoNova.getPlugin().gameManager.setMinPlayers(value);
                    final ItemsCreator ic = new ItemsCreator(Material.OAK_BOAT, ChatColor.AQUA + Integer.toString(TomoNova.getPlugin().gameManager.getMinPlayers()), Arrays.asList(Lang.GUIS_MIN_PLAYERS_LORE.toString(), Lang.GUIS_MIN_PLAYERS_LORE1.toString()));
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
