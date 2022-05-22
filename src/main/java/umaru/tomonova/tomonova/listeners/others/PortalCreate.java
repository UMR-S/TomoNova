package umaru.tomonova.tomonova.listeners.others;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.PortalCreateEvent;
import umaru.tomonova.tomonova.core.TomoNova;

public class PortalCreate implements Listener {
    @EventHandler
    public void onPortalCreate(final PortalCreateEvent event) {
        if (!TomoNova.getPlugin().gameManager.isNether()) {
            event.setCancelled(true);
        }
    }
}

