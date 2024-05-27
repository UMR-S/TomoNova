package umaru.tomonova.tomonova.listeners.players;

import org.bukkit.GameMode;
import org.bukkit.Material;
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
import umaru.tomonova.tomonova.gui.gamemodegui.bleachUHC.ClassesGui;
import umaru.tomonova.tomonova.gui.teamsgui.TeamsGui;
import umaru.tomonova.tomonova.lang.Lang;

public class InteractEvent implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();

        if (isLobbyState() && isBanner(itemInHand)) {
            handleBannerInteraction(player);
        } else if (isLobbyOrEndState() && isPaper(itemInHand)) {
            new MainGui(player).show();
        } else if (isLobbyOrEndState() && isNetheriteScrap(itemInHand)) {
            new ClassesGui(player).show();
        } else if (isGameState() && TomoNova.getPlugin().gameManager.isTomoLostVillage() && isCompass(itemInHand)) {
            handleCompassInteraction(event, player);
        }
    }

    private boolean isLobbyState() {
        return GameStates.isState(GameStates.LOBBY) || TomoNova.test;
    }

    private boolean isLobbyOrEndState() {
        return isLobbyState() || GameStates.isState(GameStates.LOBBY_END);
    }

    private boolean isGameState() {
        return GameStates.isState(GameStates.GAME);
    }

    private boolean isBanner(ItemStack item) {
        return item.getType() == Material.BLACK_BANNER;
    }

    private boolean isPaper(ItemStack item) {
        return item.getType() == Material.PAPER;
    }

    private boolean isNetheriteScrap(ItemStack item) {
        return item.getType() == Material.NETHERITE_SCRAP;
    }

    private boolean isCompass(ItemStack item) {
        return item.getType() == Material.COMPASS;
    }

    private boolean isValidAction(Action action) {
        return action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK
                || action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK;
    }

    private void handleBannerInteraction(Player player) {
        if (TomoNova.getPlugin().gameManager.getPlayersPerTeam() > 1) {
            new TeamsGui(player).show();
        } else {
            player.sendMessage(Lang.SOLO_MESSAGE.toString());
        }
    }

    private void handleCompassInteraction(PlayerInteractEvent event, Player player) {
        if (player.getCooldown(Material.COMPASS) == 0 && TomoNova.getPlugin().tomoLostVillage.isCompass(player.getName())) {
            for (ItemStack item : player.getInventory().getContents()) {
                if (item != null && item.getType() == Material.IRON_INGOT) {
                    item.setAmount(item.getAmount() - 1);
                    Player nearestPlayer = getNearestPlayer(player, 10000.0);
                    if (nearestPlayer != null) {
                        player.setCompassTarget(nearestPlayer.getLocation());
                        player.setCooldown(Material.COMPASS, 100);
                    }
                    break;
                }
            }
        }
    }

    private Player getNearestPlayer(Player player, double range) {
        double closestDistance = Double.POSITIVE_INFINITY;
        Player closestPlayer = null;

        for (Entity entity : player.getNearbyEntities(range, range, range)) {
            if (entity instanceof Player) {
                Player targetPlayer = (Player) entity;
                if (targetPlayer.getGameMode() != GameMode.SPECTATOR
                        && !TomoNova.getPlugin().tomoLostVillage.playersInSameTeam(targetPlayer.getName(), player.getName())) {
                    double distance = player.getLocation().distance(targetPlayer.getLocation());
                    if (distance < closestDistance) {
                        closestDistance = distance;
                        closestPlayer = targetPlayer;
                    }
                }
            }
        }
        return closestPlayer;
    }

}
