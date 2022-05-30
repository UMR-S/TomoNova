package umaru.tomonova.tomonova.listeners.players;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import umaru.tomonova.tomonova.core.TomoNova;

public class PlayerChangeWorld implements Listener {
    @EventHandler
    public void onPlayerChangeWorld(PlayerChangedWorldEvent event) {
        if (event.getPlayer().getWorld() == Bukkit.getWorld("world_nether")) {
            TomoNova.getPlugin().gameManager.addNetherSpawn(event.getPlayer().getName());
        }
    }
}
