package umaru.tomonova.tomonova.listeners.entities;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EntitySpawn implements Listener {

    @EventHandler
    public void onSpawn(CreatureSpawnEvent event){
        if (event.getEntity().getType() == EntityType.HOGLIN){
            event.setCancelled(true);
        }
    }
}
