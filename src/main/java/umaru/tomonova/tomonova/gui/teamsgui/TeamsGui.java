package umaru.tomonova.tomonova.gui.teamsgui;

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
import umaru.tomonova.tomonova.lang.Lang;
import umaru.tomonova.tomonova.utils.gui.ItemsCreator;
import umaru.tomonova.tomonova.utils.teams.Teams;

import java.util.HashMap;


public class TeamsGui extends Gui {
    public TeamsGui(Player player) {
        super(player, 54, ChatColor.RED + Lang.GUIS_TEAMS_NAME.toString());
        for (Teams team : Teams.values()) {
            ItemsCreator ic = new ItemsCreator(team.getBanner(), team.getBaseColor() + team.getName(), null);
            TeamsGui.inventory.addItem(ItemsCreator.create(ic));
        }
    }

    @EventHandler
    public void onClick(final InventoryClickEvent event) {
        if (event.getClickedInventory() == null) {
            return;
        }
        if (event.getClickedInventory().equals(TeamsGui.inventory)) {
            final ItemStack is = event.getCurrentItem();
            if (is == null || is.getType() == Material.AIR) {
                return;
            } else {
                TomoNova.getPlugin().teamUtils.playerQuitTeam(event.getWhoClicked().getName());
                HashMap<String, Teams> listeTeams = TomoNova.getPlugin().teamUtils.getTeamHashMap();
                for (String teamName : listeTeams.keySet()) {
                    if (teamName.equals(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()))) {
                        TomoNova.getPlugin().teamUtils.playerJoinTeam(teamName, (event.getWhoClicked().getName()));
                    }
                }
                event.getWhoClicked().closeInventory();
            }
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onClick(final InventoryCloseEvent event) {
        if (event.getInventory().equals(TeamsGui.inventory)) {
            HandlerList.unregisterAll((Listener) this);
        }
    }
}

