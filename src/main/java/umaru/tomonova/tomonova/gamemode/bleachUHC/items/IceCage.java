package umaru.tomonova.tomonova.gamemode.bleachUHC.items;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.core.task.bleachUHCTask.BreakIceCageTask;

import java.util.ArrayList;
import java.util.List;

public class IceCage {

    private static final int CAGE_HEIGHT = 10;
    private static final double INITIAL_OFFSET_Y = -1.0;
    private static final double OFFSET_Y = 1.0;
    private static final double RESET_Y = -9.0;
    private static final Material ICE_MATERIAL = Material.PACKED_ICE;
    private static final Material AIR_MATERIAL = Material.AIR;

    public static void iceCage(Location loc) {
        adjustLocation(loc, 0.5, INITIAL_OFFSET_Y, 0.5);

        for (int i = 0; i < CAGE_HEIGHT; i++) {
            createIceLayer(loc);
            loc.add(0.0, OFFSET_Y, 0.0);
        }

        resetLocationY(loc, RESET_Y);
        scheduleBreakIceCageTask(loc);
    }

    private static void adjustLocation(Location loc, double x, double y, double z) {
        loc.add(x, y, z);
    }

    private static void resetLocationY(Location loc, double y) {
        loc.add(0.0, y, 0.0);
    }

    private static void createIceLayer(Location loc) {
        List<Location> locList = new ArrayList<>();
        locList.addAll(getCircle(loc.clone(), 3, 16));
        locList.addAll(getCircle(loc.clone(), 2, 12));
        locList.addAll(getCircle(loc.clone(), 1, 8));
        locList.addAll(getCircle(loc.clone(), 0, 1));

        for (Location location : locList) {
            if (location.getBlock().getBlockData().getMaterial().isAir()) {
                location.getBlock().setType(ICE_MATERIAL);
            }
        }
    }

    private static void scheduleBreakIceCageTask(Location loc) {
        BreakIceCageTask.setLocPlayer(loc);
        BreakIceCageTask.setbreakTime(10);
        new BreakIceCageTask(TomoNova.getPlugin()).runTaskTimer(TomoNova.getPlugin(), 0, 20);
    }

    public static void breakIceCage(Location loc) {
        adjustLocation(loc, 0.5, INITIAL_OFFSET_Y, 0.5);

        for (int i = 0; i < CAGE_HEIGHT; i++) {
            clearIceLayer(loc);
            loc.add(0.0, OFFSET_Y, 0.0);
        }
    }

    private static void clearIceLayer(Location loc) {
        for (int x = -15; x < 8; x++) {
            for (int z = -8; z < 8; z++) {
                Location blockLoc = loc.clone().add(x, 0.0, z);
                if (blockLoc.getBlock().getType().equals(ICE_MATERIAL)) {
                    blockLoc.getBlock().setType(AIR_MATERIAL);
                }
            }
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
