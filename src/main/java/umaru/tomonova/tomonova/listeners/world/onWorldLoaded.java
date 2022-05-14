package umaru.tomonova.tomonova.listeners.world;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;
import umaru.tomonova.tomonova.utils.lobby.LobbyUtils;
import umaru.tomonova.tomonova.utils.rules.SettingRulesUtils;

public class onWorldLoaded implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onWorldLoaded (WorldLoadEvent event){
        SettingRulesUtils.setGamerules();
        LobbyUtils.spawnLobby();
    }
}
