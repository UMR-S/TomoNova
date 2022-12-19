package umaru.tomonova.tomonova.listeners.bleachUHC;

import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import umaru.tomonova.tomonova.core.TomoNova;

public class CombatZoneEvent implements Listener {

    @EventHandler
    public void BreakBlockInCombatZone(BlockBreakEvent event){

        Block block = event.getBlock();

        if(TomoNova.combatzoneUtils.isBlockInZone(block.getX(), block.getY(), block.getZ())){
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void PlaceBlockInCombatZone(BlockPlaceEvent event){

        Block block = event.getBlock();

        if(TomoNova.combatzoneUtils.isBlockInZone(block.getX(), block.getY(), block.getZ())){
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void PlayerHitBossOutsideZone(EntityDamageByEntityEvent event){

        if(event.getDamager() instanceof Player){
            // A changer pour Mythic mob
            if(event.getEntity() instanceof LivingEntity){
                if(!TomoNova.combatzoneUtils.isPlayerInZone(event.getDamager().getName())){
                    event.setCancelled(true);
                }
            }
        }
    }

}
