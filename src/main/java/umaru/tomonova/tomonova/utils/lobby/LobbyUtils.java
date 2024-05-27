package umaru.tomonova.tomonova.utils.lobby;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.core.game.GameStates;

public class LobbyUtils {

    private static final int BOUNDARY = 15;
    private static final double BASE_Y = 200.0;

    public static void spawnLobby() {
        World world = TomoNova.getPlugin().worldUtils.getWorld();
        Location location = new Location(world, 0.0, BASE_Y, 0);
        for (int i = -BOUNDARY; i <= BOUNDARY; i++) {
            for (int j = -BOUNDARY; j <= BOUNDARY; j++) {
                setBlock(world, location, i, j, BASE_Y, Material.WHITE_STAINED_GLASS);
                if (isBoundary(i, j)) {
                    setBoundaryBlocks(world, location, i, j);
                }
            }
        }
        GameStates.setCurrentState(GameStates.LOBBY);
    }

    public static void deleteLobby() {
        World world = TomoNova.getPlugin().worldUtils.getWorld();
        Location location = new Location(world, 0.0, BASE_Y, 0);
        for (int i = -BOUNDARY; i <= BOUNDARY; i++) {
            for (int j = -BOUNDARY; j <= BOUNDARY; j++) {
                setBlock(world, location, i, j, BASE_Y, Material.AIR);
                if (isBoundary(i, j)) {
                    removeBoundaryBlocks(world, location, i, j);
                }
            }
        }
    }

    private static void setBlock(World world, Location location, int x, int z, double y, Material material) {
        location.setX(x);
        location.setY(y);
        location.setZ(z);
        world.getBlockAt(location).setType(material);
    }

    private static void setBoundaryBlocks(World world, Location location, int x, int z) {
        setBlock(world, location, x, z, BASE_Y + 1, Material.WHITE_STAINED_GLASS_PANE);
        setBlock(world, location, x, z, BASE_Y + 2, Material.WHITE_STAINED_GLASS_PANE);
    }

    private static void removeBoundaryBlocks(World world, Location location, int x, int z) {
        setBlock(world, location, x, z, BASE_Y + 1, Material.AIR);
        setBlock(world, location, x, z, BASE_Y + 2, Material.AIR);
    }

    private static boolean isBoundary(int x, int z) {
        return x == -BOUNDARY || z == -BOUNDARY || x == BOUNDARY || z == BOUNDARY;
    }
}
