package umaru.tomonova.tomonova.listeners.bleachUHC;

import io.lumine.mythic.bukkit.MythicBukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import umaru.tomonova.tomonova.core.TomoNova;

public class CombatZoneEvent implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        handleBlockEvent(event.getBlock(), event);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        handleBlockEvent(event.getBlock(), event);
    }

    @EventHandler
    public void onPlayerHitBoss(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player && isMythicMob(event.getEntity())) {
            handleEntityDamageEvent(event);
        }
    }

    private void handleBlockEvent(Block block, Cancellable event) {
        if (TomoNova.getPlugin().gameManager.isBleachUhc()
                && TomoNova.getPlugin().combatZoneUtils.isBlockInZone(block.getX(), block.getY(), block.getZ())) {
            event.setCancelled(true);
        }
    }

    private boolean isMythicMob(Entity entity) {
        return MythicBukkit.inst().getAPIHelper().isMythicMob(entity);
    }

    private void handleEntityDamageEvent(EntityDamageByEntityEvent event) {
        if (TomoNova.getPlugin().gameManager.isBleachUhc()
                && !isPlayerInCombatZone(event.getDamager().getName())) {
            event.setCancelled(true);
        }
    }

    private boolean isPlayerInCombatZone(String playerName) {
        return TomoNova.getPlugin().combatZoneUtils.isPlayerInZone(playerName);
    }
}
