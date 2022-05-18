package umaru.tomonova.tomonova.gui.teamsgui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.gui.Gui;
import umaru.tomonova.tomonova.lang.Lang;
import umaru.tomonova.tomonova.utils.gui.ItemsCreator;
import umaru.tomonova.tomonova.utils.teams.Teams;

import java.util.List;


public class TeamsGui extends Gui {
    public TeamsGui(Player player) {
        super(player, 54, ChatColor.RED + Lang.GUIS_TEAMS_NAME.toString());
        for(Teams team : Teams.values()){
            ItemsCreator ic = new ItemsCreator(team.getBanner(),team.getBaseColor() + team.getName(), null);
            TeamsGui.inventory.addItem(ItemsCreator.create(ic));
        }
        player.openInventory(inventory);
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
            }
            else {
                final String name = ChatColor.stripColor(is.getItemMeta().getDisplayName());
                for(Teams team : Teams.values()){
                    List<Player> listPlayers = team.getTeamPlayers();
                    if (listPlayers != null){
                        if(listPlayers.contains(player)){
                            TomoNova.getPlugin().teamUtils.playerQuitTeam(team.getName(), player);

                        }
                    }
                }
                TomoNova.getPlugin().teamUtils.playerJoinTeam(name,(Player) event.getWhoClicked());
                player.closeInventory();
            }
        }
        event.setCancelled(true);
    }
}

