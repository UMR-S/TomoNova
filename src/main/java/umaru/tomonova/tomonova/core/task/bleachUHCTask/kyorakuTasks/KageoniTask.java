package umaru.tomonova.tomonova.core.task.bleachUHCTask.kyorakuTasks;

import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.mobs.ActiveMob;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.utils.constants.BleachUHCConstants;

import java.util.*;

public class KageoniTask extends BukkitRunnable {

    List<Player> playersInGame;
    int seconds = 0;
    boolean damageKyoraku = true;
    boolean shouldDamageAll = false;

    public KageoniTask(List<Player> playersInGame) {
        this.playersInGame = playersInGame;
    }

    @Override
    public void run() {
        if (seconds == 5) {
            for (Player player : playersInGame) {
                if (hasSkyAccess(player)) {
                    damageKyoraku = false;
                    shouldDamageAll = true;
                }
            }
            if (shouldDamageAll) {
                Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage("Jeu raté"));
                damageAll();
            }
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
                    if (customName != null && customName.equals(BleachUHCConstants.KYORAKU_NAME)) {
                        mob.getEntity().damage(50);
                    }
                }
            }
        }
    }
    public boolean hasSkyAccess(Player player) {
        Location playerLocation = player.getLocation();
        World world = player.getWorld();
        int playerY = playerLocation.getBlockY();
        int maxY = world.getMaxHeight();

        for (int y = playerY + 1; y < maxY; y++) {
            Block blockAbove = world.getBlockAt(playerLocation.getBlockX(), y, playerLocation.getBlockZ());
            if (!blockAbove.getType().equals(Material.AIR)) {
                return false;
            }
        }
        return true;
    }
}
