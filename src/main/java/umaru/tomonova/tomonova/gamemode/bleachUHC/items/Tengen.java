package umaru.tomonova.tomonova.gamemode.bleachUHC.items;

import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.core.mobs.ActiveMob;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import umaru.tomonova.tomonova.core.TomoNova;

public class Tengen {
    public static void tengen(String playerName){
        Player player = Bukkit.getPlayer(playerName);
        MythicMob samourai = TomoNova.getPlugin().bleachUHC.getBossMM("PlayerSamourai");
        if(samourai != null){
            ActiveMob samouraiActive = samourai.spawn(BukkitAdapter.adapt(player.getLocation()),1);
            Wolf samouraiEntity = (Wolf) samouraiActive.getEntity().getBukkitEntity();
            samouraiEntity.setOwner(player);

        }
    }
}
