package umaru.tomonova.tomonova.core.task.bleachUHCTask.kyorakuTasks;

import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.mobs.ActiveMob;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.utils.constants.BleachUHCConstants;

import java.util.*;

public class TakaoniTask extends BukkitRunnable {

    List<Player> playersInGame;
    int seconds = 0;
    boolean damageKyoraku = true;
    boolean shouldDamageAll = false;

    public TakaoniTask(List<Player> playersInGame) {
        this.playersInGame = playersInGame;
    }

    @Override
    public void run() {
        if (seconds == 8) {
            for (Player player : playersInGame) {
                if (!isAboveKyoraku(player)) {
                    damageKyoraku = false;
                    shouldDamageAll = true;
                }
            }
            if (shouldDamageAll) {
                damageAll();
            }
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

    public boolean isAboveKyoraku(Player player) {
        Location playerLocation = player.getLocation();
        int playerY = playerLocation.getBlockY();

        if (MythicBukkit.inst().getMobManager() != null) {
            for (ActiveMob mob : MythicBukkit.inst().getMobManager().getActiveMobs()) {
                if (!mob.isDead() && mob.getEntity().getBukkitEntity() != null) {
                    String customName = mob.getEntity().getBukkitEntity().getCustomName();
                    if (customName != null && customName.equalsIgnoreCase(BleachUHCConstants.KYORAKU_NAME)) {
                        if(playerY-mob.getEntity().getLocation().getBlockY() > 1){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
