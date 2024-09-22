package umaru.tomonova.tomonova.core.task.bleachUHCTask.kyorakuTasks;

import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.mobs.ActiveMob;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.utils.constants.BleachUHCConstants;

import java.util.List;
import java.util.Random;

public class DarumasanGaKorondaTask extends BukkitRunnable {

    private final int xKyorkaku = -222;
    private final int yKyoraku = 36;
    private final int zKyoraku = 367;
    private final Location kyorakuLoc = new Location(TomoNova.getPlugin().worldUtils.getWorld(), xKyorkaku, yKyoraku, zKyoraku);

    List<Player> playersInGame;
    int seconds = 0;
    boolean damageKyoraku = true;
    boolean shouldDamageAll = false;

    public DarumasanGaKorondaTask(List<Player> playersInGame) {
        this.playersInGame = playersInGame;
    }

    @Override
    public void run() {
        if(seconds ==0){
            for(Player player : playersInGame){
                randomTpWithSkyAccess(player,30);
            }
        }
        for (Player player : playersInGame) {
            if(player.getLocation().distance(kyorakuLoc) < 1){
                damageKyoraku();
                BukkitTask newMinigameask = new NewMinigameTask().runTaskTimer(TomoNova.getPlugin(),100,20);
                this.cancel();
            }
        }
        if (seconds == 30) {
            damageAll();
            BukkitTask newMinigameask = new NewMinigameTask().runTaskTimer(TomoNova.getPlugin(),100,20);
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
    public void randomTpWithSkyAccess(Player player, int range) {
        World world = player.getWorld();
        Random random = new Random();
        Location playerLocation = player.getLocation();
        int maxAttempts = 100;
        Location randomLocation = null;

        for (int attempts = 0; attempts < maxAttempts; attempts++) {
            double r = random.nextDouble() * range;
            double theta = random.nextDouble() * 2 * Math.PI;

            double randomX = playerLocation.getX() + r * Math.cos(theta);
            double randomZ = playerLocation.getZ() + r * Math.sin(theta);

            int highestY = world.getHighestBlockYAt((int) randomX, (int) randomZ);
            Location possibleLocation = new Location(world, randomX, highestY, randomZ);

            if (hasSkyAccess(possibleLocation)) {
                randomLocation = possibleLocation;
                break;
            }
        }

        if (randomLocation != null) {
            player.teleport(randomLocation);
        }
    }

    public boolean hasSkyAccess(Location location) {
        World world = location.getWorld();
        int startY = location.getBlockY();
        int maxY = world.getMaxHeight();

        for (int y = startY + 1; y < maxY; y++) {
            if (!world.getBlockAt(location.getBlockX(), y, location.getBlockZ()).getType().equals(Material.AIR)) {
                return false;
            }
        }
        return true;
    }
}
