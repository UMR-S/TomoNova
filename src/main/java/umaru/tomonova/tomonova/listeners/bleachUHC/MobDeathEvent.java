package umaru.tomonova.tomonova.listeners.bleachUHC;

import org.bukkit.Bukkit;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import umaru.tomonova.tomonova.gamemode.bleachUHC.GiveItem;

public class MobDeathEvent implements Listener {

    public void MobDeathEvent(EntityDeathEvent event){
        if(event.getEntity() instanceof Mob){
            Mob mob = (Mob) event.getEntity();
            event.setDroppedExp(0);
            if(mob.getKiller() instanceof Player){
                Player player = mob.getKiller();
                player.setLevel(player.getLevel() + 10);
            }
        }
    }
    public void BossDeathEvent(EntityDeathEvent event){

        String name = event.getEntity().getCustomName();
        if("Soi Fon".equals(name)){
            GiveItem.spawnSuzumebachi(event.getEntity().getLocation());
            GiveItem.spawnPhotoYoruichi(event.getEntity().getLocation());
        }

    }
}
