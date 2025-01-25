package umaru.tomonova.tomonova.core.task.bleachUHCTask.kyorakuTasks;

import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.mobs.ActiveMob;
import org.bukkit.Bukkit;
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

    private final int xKyorkaku = -222;
    private final int yKyoraku = 34;
    private final int zKyoraku = 367;
    private final Location kyorakuLoc = new Location(TomoNova.getPlugin().worldUtils.getWorld(), xKyorkaku, yKyoraku, zKyoraku);

    public KageokuriTask(List<Player> playersInGame) {
        this.playersInGame = playersInGame;
    }

    @Override
    public void run() {
        if(seconds == 2){
            for(Player player : playersInGame){
                player.sendMessage("Regardez moi !");
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
                Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage("Jeu raté"));
                damageAll();
                BukkitTask newMinigameask = new NewMinigameTask().runTaskTimer(TomoNova.getPlugin(),0,20);
                this.cancel();
            }
        }
        if(seconds == 10){
            if (damageKyoraku) {
                Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage("Jeu réussi"));
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
                    String customName = mob.getDisplayName();
                    if (customName != null && customName.equalsIgnoreCase(BleachUHCConstants.KYORAKU_NAME)) {
                        mob.getEntity().damage(50);
                    }
                }
            }
        }
    }

    public boolean isLookingAtKyoraku(Player player) {
        Location eyeLocation = player.getEyeLocation();

        Vector playerDirection = eyeLocation.getDirection().normalize();
        Vector toKyoraku = kyorakuLoc.toVector().subtract(eyeLocation.toVector()).normalize();
        double dotProduct = Math.abs(playerDirection.dot(toKyoraku));
        if (dotProduct > 0.95) {
            return true;
        }
        return false;
    }
}