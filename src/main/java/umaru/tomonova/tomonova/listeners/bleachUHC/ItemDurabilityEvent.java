package umaru.tomonova.tomonova.listeners.bleachUHC;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import umaru.tomonova.tomonova.core.TomoNova;

public class ItemDurabilityEvent implements Listener {
    @EventHandler
    public void onItemDamage(PlayerItemDamageEvent event) {
        if(TomoNova.getPlugin().gameManager.isBleachUhc()){
            event.setDamage(0);
        }
    }
}
