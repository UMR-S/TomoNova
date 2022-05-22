package umaru.tomonova.tomonova.listeners.players;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import umaru.tomonova.tomonova.core.game.GameStates;

public class PlayerMove {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (GameStates.isState(GameStates.PREGAME)) {
            event.setCancelled(true);
        }
    }
}
