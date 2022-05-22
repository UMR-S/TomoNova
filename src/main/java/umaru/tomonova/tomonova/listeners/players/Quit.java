package umaru.tomonova.tomonova.listeners.players;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.core.game.GameStates;

public class Quit implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onQuit(PlayerQuitEvent event) {
        if (GameStates.isState(GameStates.LOBBY)) {
            Player player = event.getPlayer();
            TomoNova.getPlugin().gameManager.removePlayer(player);
            TomoNova.getPlugin().teamUtils.playerQuitTeam(player);
        }
    }
}

