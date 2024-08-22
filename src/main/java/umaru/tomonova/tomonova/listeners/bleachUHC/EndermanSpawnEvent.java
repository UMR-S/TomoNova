package umaru.tomonova.tomonova.listeners.bleachUHC;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import umaru.tomonova.tomonova.core.TomoNova;

public class EndermanSpawnEvent implements Listener {
    @EventHandler
    public void onSpawn(CreatureSpawnEvent event) {
        if (event.getEntity().getType() == EntityType.ENDERMAN && TomoNova.getPlugin().gameManager.isBleachUhc()) {
            event.setCancelled(true);
        }
    }
}
