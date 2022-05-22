package umaru.tomonova.tomonova.listeners.players;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import umaru.tomonova.tomonova.core.game.GameStates;

public class PlayerDropItem implements Listener {

    @EventHandler
    public void onPlayerDropItem(final PlayerDropItemEvent event) {
        if (GameStates.isState(GameStates.LOBBY)) {
            event.setCancelled(true);
        }
    }
}
