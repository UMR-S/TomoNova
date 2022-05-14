package umaru.tomonova.tomonova.listeners.players;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import umaru.tomonova.tomonova.core.game.GameManager;
import umaru.tomonova.tomonova.core.game.GameStates;

import java.util.Arrays;

public class onQuit implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onQuit(PlayerQuitEvent event) {
        if (GameStates.isState(GameStates.LOBBY)) {
            Player player = event.getPlayer();
            GameManager.removePlayer(player);
        }
        System.out.println(Arrays.toString(GameManager.getPlayersName().toArray()));
    }
}

