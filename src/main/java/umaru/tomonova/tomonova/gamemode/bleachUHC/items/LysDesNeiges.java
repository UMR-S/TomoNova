package umaru.tomonova.tomonova.gamemode.bleachUHC.items;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import umaru.tomonova.tomonova.core.TomoNova;

public class LysDesNeiges {

    public static boolean lysDesNeiges(String playerName){
        Player player = Bukkit.getPlayer(playerName);
        Entity targetedEntity= TomoNova.getPlugin().classesSpells.getEntityInSight(player,50);
        if(targetedEntity != null){
            if(targetedEntity instanceof Player){
                IceCage.iceCage(targetedEntity.getLocation().clone());
                return true;
            }
        }
        return false;
    }
}
