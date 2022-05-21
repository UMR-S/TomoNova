package umaru.tomonova.tomonova.utils.lobby;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.core.game.GameStates;

public class LobbyUtils {
    public static void spawnLobby() {
        World world = TomoNova.getPlugin().worldUtils.getWorld();
        System.out.println(world);
        Location location = new Location(world, 0.0, 200.0, 0);
        for (int i = -15; i < 16; i++) {
            for (int j = -15; j < 16; j++) {
                location.setX(i);
                location.setZ(j);
                world.getBlockAt(location).setType(Material.WHITE_STAINED_GLASS);
                if (i == -15 || j == -15 || i == 15 || j == 15) {
                    location.setY(201.0);
                    world.getBlockAt(location).setType(Material.WHITE_STAINED_GLASS_PANE);
                    location.setY(202.0);
                    world.getBlockAt(location).setType(Material.WHITE_STAINED_GLASS_PANE);
                    location.setY(200.0);
                }
            }
        }
        GameStates.setCurrentState(GameStates.LOBBY);
    }

    public static void deleteLobby() {
        World world = TomoNova.getPlugin().worldUtils.getWorld();
        Location location = new Location(world, 0.0, 200.0, 0);
        for (int i = -15; i < 16; i++) {
            for (int j = -15; j < 16; j++) {
                location.setX(i);
                location.setZ(j);
                world.getBlockAt(location).setType(Material.AIR);
                if (i == -15 || j == -15 || i == 15 || j == 15) {
                    location.setY(201.0);
                    world.getBlockAt(location).setType(Material.AIR);
                    location.setY(202.0);
                    world.getBlockAt(location).setType(Material.AIR);
                    location.setY(200.0);
                }
            }
        }
    }
}
