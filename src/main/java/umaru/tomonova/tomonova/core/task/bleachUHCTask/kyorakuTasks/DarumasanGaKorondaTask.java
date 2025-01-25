package umaru.tomonova.tomonova.core.task.bleachUHCTask.kyorakuTasks;

import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.mobs.ActiveMob;
import org.bukkit.Bukkit;
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
    private final int yKyoraku = 34;
    private final int zKyoraku = 367;
    private final Location kyorakuLoc = new Location(TomoNova.getPlugin().worldUtils.getWorld(), xKyorkaku, yKyoraku, zKyoraku);

    List<Player> playersInGame;
    int seconds = 0;

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
            if(player.getLocation().distance(kyorakuLoc) < 2){
                Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage("Jeu réussi"));
                damageKyoraku();
                BukkitTask newMinigameask = new NewMinigameTask().runTaskTimer(TomoNova.getPlugin(),100,20);
                this.cancel();
            }
        }
        if (seconds == 30) {
            damageAll();
            BukkitTask newMinigameask = new NewMinigameTask().runTaskTimer(TomoNova.getPlugin(),100,20);
            Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage("Jeu raté"));
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
    public void randomTpWithSkyAccess(Player player, int range) {
        World world = player.getWorld();
        Random random = new Random();
        Location playerLocation = player.getLocation();

        Location possibleLocation = null;
        double r = random.nextDouble() * range;
        double theta = random.nextDouble() * 2 * Math.PI;
        double randomX = playerLocation.getX() + r * Math.cos(theta);
        double randomZ = playerLocation.getZ() + r * Math.sin(theta);
        int highestY = 128;
        possibleLocation = new Location(world, randomX, highestY, randomZ);
        Material blockType = world.getBlockAt(possibleLocation).getType();

        while (blockType.equals(Material.AIR)){
            possibleLocation.add(0,- 1,0);
            blockType = world.getBlockAt(possibleLocation).getType();
        }
        possibleLocation.add(0, 3, 0);
        player.teleport(possibleLocation);
    }
}
