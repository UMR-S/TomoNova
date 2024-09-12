package umaru.tomonova.tomonova.gamemode.bleachUHC.items;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;// Static method to apply nausea to enemies around the player
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import umaru.tomonova.tomonova.core.TomoNova;

import java.util.List;

public class KyokaSuigetsu{

    public static void applyNauseaToEnemies(String playerName) {

        Player player = Bukkit.getPlayer(playerName);
        if (player == null) {
            return;
        }

        int radius = 20;
        int duration = 400;  // 20 seconds in Minecraft ticks

        Location playerLocation = player.getLocation();

        for (Player target : Bukkit.getOnlinePlayers()) {
            if (TomoNova.getPlugin().teamUtils.arePlayersOnSameTeam(playerName,target.getName())) {
                continue;
            }
            if (target.getLocation().distance(playerLocation) <= radius) {
                target.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, duration, 0));
            }
        }
    }
}