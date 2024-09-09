package umaru.tomonova.tomonova.listeners.players;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import umaru.tomonova.tomonova.core.TomoNova;

public class PlayerDeath implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDeath(PlayerDeathEvent event) {
        Bukkit.getOnlinePlayers().forEach(p -> p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1.0f, 1.0f));
        Player victim = event.getEntity();
        Player killer = victim.getKiller();
        String playerName = victim.getName();
        TomoNova.getPlugin().gameManager.removePlayer(playerName);
        TomoNova.getPlugin().gameManager.addDeadPlayer(playerName);
        if (TomoNova.getPlugin().gameManager.isTomoLostVillage()) {
            if(TomoNova.getPlugin().tomoLostVillage.isKilledInTeam(playerName)){
                TomoNova.getPlugin().tomoLostVillage.removePlayerToTeam(playerName);
            }
        }
        if (TomoNova.getPlugin().gameManager.getPlayersPerTeam() > 1) {
            TomoNova.getPlugin().teamUtils.playerQuitTeam(playerName);
        }

        if (killer != null) {
            String killerName = killer.getName();
            TomoNova.getPlugin().killCounter.incrementKillCount(killerName);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerRespawn(final PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        player.setGameMode(GameMode.SPECTATOR);
        player.teleport(new Location(TomoNova.getPlugin().worldUtils.getWorld(), 0.0, 200.0, 0.0));
    }

}
