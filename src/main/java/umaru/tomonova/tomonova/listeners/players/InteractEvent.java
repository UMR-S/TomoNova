package umaru.tomonova.tomonova.listeners.players;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.core.game.GameStates;
import umaru.tomonova.tomonova.gui.MainGui;
import umaru.tomonova.tomonova.gui.teamsgui.TeamsGui;
import umaru.tomonova.tomonova.lang.Lang;


public class InteractEvent implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        //Lobby
        if ((event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && player.getInventory().getItemInMainHand().getType() == Material.BLACK_BANNER && GameStates.isState(GameStates.LOBBY)) {
            if (TomoNova.getPlugin().gameManager.getPlayersPerTeam() > 1) {
                new TeamsGui(player).show();
            } else {
                event.getPlayer().sendMessage(Lang.SOLO_MESSAGE.toString());
            }
        }
        if ((event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && player.getInventory().getItemInMainHand().getType() == Material.PAPER && (GameStates.isState(GameStates.LOBBY) || GameStates.isState(GameStates.LOBBY_END))) {
            new MainGui(player).show();
        }
        if (GameStates.isState(GameStates.GAME) && TomoNova.getPlugin().gameManager.isTomoLostVillage()) {
            if ((event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && player.getInventory().getItemInMainHand().getType() == Material.COMPASS) {
                if (TomoNova.getPlugin().tomoLostVillage.isCompass(event.getPlayer().getName())) {
                    if (event.getPlayer().getInventory().contains(Material.IRON_INGOT)) {
                        //On cherche le fer
                        for (ItemStack item : event.getPlayer().getInventory()) {
                            if(item != null){
                                if (item.getType() == Material.IRON_INGOT) {
                                    item.setAmount(item.getAmount() - 1);

                                    //On pointe
                                    String nearestPlayer = player.getName();
                                    int distance = 0;
                                    World playerWorld = player.getWorld();
                                    for (String otherPlayer : TomoNova.getPlugin().gameManager.getPlayers()) {
                                        if (Bukkit.getPlayer(otherPlayer).getWorld() == playerWorld) {
                                            double dist = player.getLocation().distance(Bukkit.getPlayer(otherPlayer).getLocation());
                                            if (distance == 0) {
                                                distance = (int) dist;
                                                nearestPlayer = otherPlayer;
                                            }
                                            if (dist < distance) {
                                                distance = (int) dist;
                                                nearestPlayer = otherPlayer;
                                            }
                                        }
                                    }
                                    player.setCompassTarget(Bukkit.getPlayer(nearestPlayer).getLocation());
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
