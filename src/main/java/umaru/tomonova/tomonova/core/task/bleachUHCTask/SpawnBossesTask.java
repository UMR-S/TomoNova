package umaru.tomonova.tomonova.core.task.bleachUHCTask;

import io.lumine.mythic.bukkit.MythicBukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import umaru.tomonova.tomonova.core.TomoNova;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class SpawnBossesTask extends BukkitRunnable {

    private TomoNova tomoNova;
    private HashMap<String, Location> bossesLoc;

    public SpawnBossesTask(TomoNova tomoNova, HashMap<String, Location> bossesLoc) {
        this.tomoNova = tomoNova;
        this.bossesLoc = bossesLoc;
    }

    @Override
    public void run() {
        for (String boss : bossesLoc.keySet()) {
            List<Player> players = getPlayersAroundLocation(bossesLoc.get(boss), 100);
            if (players != null && !players.isEmpty()) {
                MythicBukkit.inst().getMobManager().spawnMob(boss,bossesLoc.get(boss));
                bossesLoc.remove(boss);
            }
        }
        if(bossesLoc.isEmpty()){
            this.cancel();
        }
    }
    public List<Player> getPlayersAroundLocation(Location location, double radius) {
        World world = location.getWorld();
        if (world == null) return null;

        List<Entity> nearbyEntities = world.getNearbyEntities(location, radius, radius, radius).stream()
                .filter(entity -> entity instanceof Player)
                .collect(Collectors.toList());

        return nearbyEntities.stream().map(entity -> (Player) entity).collect(Collectors.toList());
    }
}
