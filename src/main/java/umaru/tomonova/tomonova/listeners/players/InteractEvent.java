package umaru.tomonova.tomonova.listeners.players;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
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
        if ((event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                && player.getInventory().getItemInMainHand().getType() == Material.BLACK_BANNER
                && (GameStates.isState(GameStates.LOBBY) || TomoNova.test)) {
            if (TomoNova.getPlugin().gameManager.getPlayersPerTeam() > 1) {
                new TeamsGui(player).show();
            } else {
                event.getPlayer().sendMessage(Lang.SOLO_MESSAGE.toString());
            }
        }
        if ((event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                && player.getInventory().getItemInMainHand().getType() == Material.PAPER
                && (GameStates.isState(GameStates.LOBBY) || GameStates.isState(GameStates.LOBBY_END) || TomoNova.test)) {
            new MainGui(player).show();
        }
        if ((event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                && player.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SCRAP
                && (GameStates.isState(GameStates.LOBBY) || GameStates.isState(GameStates.LOBBY_END) || TomoNova.test)) {
            new MainGui(player).show();
        }
        if (GameStates.isState(GameStates.GAME) && TomoNova.getPlugin().gameManager.isTomoLostVillage()) {
            if ((event.getAction() == Action.LEFT_CLICK_AIR
                    || event.getAction() == Action.LEFT_CLICK_BLOCK
                    || event.getAction() == Action.RIGHT_CLICK_AIR
                    || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                    && player.getInventory().getItemInMainHand().getType() == Material.COMPASS
                    && player.getCooldown(Material.COMPASS) == 0) {
                if (TomoNova.getPlugin().tomoLostVillage.isCompass(event.getPlayer().getName())) {
                    if (event.getPlayer().getInventory().contains(Material.IRON_INGOT)) {
                        //On cherche le fer
                        for (ItemStack item : event.getPlayer().getInventory()) {
                            if(item != null){
                                if (item.getType() == Material.IRON_INGOT) {
                                    item.setAmount(item.getAmount() - 1);

                                    //On pointe
                                    Player nearestPlayer = getNearest(player,10000.0);
                                    if (nearestPlayer != null) {
                                        event.getPlayer().setCompassTarget(nearestPlayer.getLocation());
                                        player.setCooldown(Material.COMPASS,100);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public Player getNearest(Player p, Double range) {
        double distance = Double.POSITIVE_INFINITY;
        Player target = null;
        for (final Entity e : p.getNearbyEntities((double)range, (double)range, (double)range)) {
            if (!(e instanceof Player)) {
                continue;
            }
            if (((Player)e).getGameMode() == GameMode.SPECTATOR) {
                continue;
            }
            if (TomoNova.getPlugin().tomoLostVillage.playersInSameTeam(e.getName(),p.getName())) {
                continue;
            }
            final double distanceto = p.getLocation().distance(e.getLocation());
            if (distanceto > distance) {
                continue;
            }
            distance = distanceto;
            target = (Player)e;
        }
        return target;
    }
}
