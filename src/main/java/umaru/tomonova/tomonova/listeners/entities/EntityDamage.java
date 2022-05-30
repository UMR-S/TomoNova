package umaru.tomonova.tomonova.listeners.entities;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.core.game.GameStates;

public class EntityDamage implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamage(final EntityDamageEvent event) {
        if (GameStates.isState(GameStates.LOBBY) || GameStates.isState(GameStates.LOBBY_END) || GameStates.isState(GameStates.PREGAME)) {
            event.setCancelled(true);
        }
        if (event.getEntity() instanceof Player && !TomoNova.getPlugin().gameManager.isDamage()) {
            event.setCancelled(true);
        }

    }

}
