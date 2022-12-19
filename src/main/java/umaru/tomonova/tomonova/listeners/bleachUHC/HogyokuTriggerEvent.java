package umaru.tomonova.tomonova.listeners.bleachUHC;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityResurrectEvent;
import umaru.tomonova.tomonova.core.task.bleachUHCTask.HogyokuActifTask;

public class HogyokuTriggerEvent implements Listener {
    @EventHandler
    public void UndiyingEvent(EntityResurrectEvent event){

        if(event.getEntity() instanceof Player){
            HogyokuActifTask.setCancel(true);
        }
    }
}
