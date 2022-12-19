package umaru.tomonova.tomonova.gamemode.bleachUHC.items;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitTask;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.core.task.bleachUHCTask.BreakIceCageTask;

import java.util.ArrayList;
import java.util.List;

public class IceCage {
    public static void iceCage(Location loc) {

        loc.add(0.5, -1.0, 0.5);

        for (int i = 0; i < 10; i++) {

            List<Location> locList = getCircle(loc.clone(), 3, 16);
            locList.addAll(getCircle(loc.clone(), 2, 12));
            locList.addAll(getCircle(loc.clone(), 1, 8));
            locList.addAll(getCircle(loc.clone(), 0, 1));
            for (Location location : locList) {
                if (location.getBlock().getBlockData().getMaterial().isAir()) {
                    location.getBlock().setType(Material.PACKED_ICE);
                }
            }
            loc.add(0.0, 1.0, 0.0);
        }
        loc.add(0.0, -9.0, 0.0);
        BreakIceCageTask.setLocPlayer(loc);
        BreakIceCageTask.setbreakTime(10);
        BukkitTask breakIceCageTask = new BreakIceCageTask(TomoNova.getPlugin()).runTaskTimer(TomoNova.getPlugin(), 0, 20);
    }

    public static void breakIceCage(Location loc) {

        loc.add(0.5, -1.0, 0.5);

        for (int i = 0; i < 10; i++) {

            for (int x = -15; x < 8; x++) {
                for (int z = -8; z < 8; z++) {
                    if (loc.clone().add(x, 0.0, z).getBlock().getType().equals(Material.PACKED_ICE)) {
                        loc.clone().add(x, 0.0, z).getBlock().setType(Material.AIR);
                    }

                }
            }
            loc.add(0.0, 1.0, 0.0);
        }

    }

    public static List<Location> getCircle(Location center, double radius, int amount) {
        List<Location> locations = new ArrayList<>();
        World world = center.getWorld();
        double increment = (2 * Math.PI) / amount;
        for (int i = 0; i < amount; i++) {
            double angle = i * increment;
            double x = center.getX() + (radius * Math.cos(angle));
            double z = center.getZ() + (radius * Math.sin(angle));
            locations.add(new Location(world, x, center.getY(), z));
        }
        return locations;
    }
}
