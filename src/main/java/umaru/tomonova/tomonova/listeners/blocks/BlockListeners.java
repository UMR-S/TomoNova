package umaru.tomonova.tomonova.listeners.blocks;

import org.bukkit.event.Listener;

import org.bukkit.event.*;
import org.bukkit.event.block.*;
import umaru.tomonova.tomonova.core.game.GameStates;

public class BlockListeners {
    public class BlockListener implements Listener
    {

        @EventHandler
        public void onBlockBreak(final BlockBreakEvent event) {
            if (GameStates.isState(GameStates.LOBBY) || GameStates.isState(GameStates.PREGAME))  {
                event.setCancelled(true);
            }
        }

        @EventHandler
        public void onBlockPlace(final BlockPlaceEvent event) {
            if (GameStates.isState(GameStates.LOBBY) || GameStates.isState(GameStates.PREGAME)) {
                event.setCancelled(true);
            }
        }
    }
}
