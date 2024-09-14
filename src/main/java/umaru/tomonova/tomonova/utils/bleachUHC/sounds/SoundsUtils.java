package umaru.tomonova.tomonova.utils.bleachUHC.sounds;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundsUtils {
    public static void playSoundIfInRange(Location sourceLoc, String sound, int range) {
        for(Player player : Bukkit.getOnlinePlayers()){
            if(player.getLocation().distance(sourceLoc) <= range){
                player.playSound(player.getLocation(),sound, 1.0f,1.0f);
            }
        }
    }

    public static void playSoundForAll(String sound){
        for(Player player : Bukkit.getOnlinePlayers()){
            player.playSound(player.getLocation(),sound, 1.0f,1.0f);
        }
    }
}

