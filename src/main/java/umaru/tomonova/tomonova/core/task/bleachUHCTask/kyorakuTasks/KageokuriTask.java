package umaru.tomonova.tomonova.core.task.bleachUHCTask.kyorakuTasks;

import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.mobs.ActiveMob;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.utils.constants.BleachUHCConstants;

import java.util.*;

public class KageokuriTask extends BukkitRunnable {

    List<Player> playersInGame;
    int seconds = 0;
    boolean damageKyoraku = true;
    boolean shouldDamageAll = false;

    public KageokuriTask(List<Player> playersInGame) {
        this.playersInGame = playersInGame;
    }

    @Override
    public void run() {
        if(seconds == 2){
            for(Player player : playersInGame){
                player.sendMessage(ChatColor.MAGIC + "Regardez moi !");
            }
        }
        if (seconds >= 2) {
            for (Player player : playersInGame) {
                if (!isLookingAtKyoraku(player)) {
                    damageKyoraku = false;
                    shouldDamageAll = true;
                }
            }
            if (shouldDamageAll) {
                damageAll();
                BukkitTask newMinigameask = new NewMinigameTask().runTaskTimer(TomoNova.getPlugin(),0,20);
                this.cancel();
            }
        }
        if(seconds == 10){
            if (damageKyoraku) {
                damageKyoraku();
            }
            BukkitTask newMinigameask = new NewMinigameTask().runTaskTimer(TomoNova.getPlugin(),0,20);
            this.cancel();
        }
        seconds++;
    }

    public void damageAll() {
        for (Player player : playersInGame) {
            player.damage(14.0);
        }
    }

    private void damageKyoraku() {
        if (MythicBukkit.inst().getMobManager() != null) {
            for (ActiveMob mob : MythicBukkit.inst().getMobManager().getActiveMobs()) {
                if (!mob.isDead() && mob.getEntity().getBukkitEntity() != null) {
                    String customName = mob.getEntity().getBukkitEntity().getCustomName();
                    if (customName != null && customName.equalsIgnoreCase(BleachUHCConstants.KYORAKU_NAME)) {
                        mob.getEntity().damage(50.0f);
                    }
                }
            }
        }
    }

    public boolean isLookingAtKyoraku(Player player) {
        Location eyeLocation = player.getEyeLocation();

        Vector playerDirection = eyeLocation.getDirection().normalize();

        if (MythicBukkit.inst().getMobManager() != null) {
            for (ActiveMob mob : MythicBukkit.inst().getMobManager().getActiveMobs()) {
                if (!mob.isDead() && mob.getEntity().getBukkitEntity() != null) {
                    String customName = mob.getEntity().getBukkitEntity().getCustomName();
                    if (customName != null && customName.equalsIgnoreCase(BleachUHCConstants.KYORAKU_NAME)) {
                        Location kyorakuLocation = mob.getEntity().getBukkitEntity().getLocation();
                        Vector toKyoraku = kyorakuLocation.toVector().subtract(eyeLocation.toVector()).normalize();
                        double dotProduct = playerDirection.dot(toKyoraku);  // Value between -1 and 1
                        if (dotProduct > 0.95) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}