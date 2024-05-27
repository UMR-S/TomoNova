package umaru.tomonova.tomonova.gamemode.bleachUHC.items;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class SakeKyoraku {

    private static final double TELEPORT_DISTANCE = 100;

    public static void sakeTeleport(String firstPlayerName, String secondPlayerName) {
        Player firstPlayer = getPlayer(firstPlayerName);
        Player secondPlayer = getPlayer(secondPlayerName);
        if (firstPlayer == null || secondPlayer == null) return;

        Location firstPlayerLoc = firstPlayer.getLocation().clone();
        Location secondPlayerLoc = secondPlayer.getLocation().clone();

        Vector playersVector = calculatePlayersVector(firstPlayerLoc, secondPlayerLoc);
        adjustLocations(firstPlayerLoc, secondPlayerLoc, playersVector);

        firstPlayer.teleport(findSafeLocation(firstPlayerLoc));
        secondPlayer.teleport(findSafeLocation(secondPlayerLoc));
    }

    private static Player getPlayer(String playerName) {
        return Bukkit.getPlayer(playerName);
    }

    private static Vector calculatePlayersVector(Location firstPlayerLoc, Location secondPlayerLoc) {
        return secondPlayerLoc.toVector().subtract(firstPlayerLoc.toVector()).normalize().multiply(TELEPORT_DISTANCE);
    }

    private static void adjustLocations(Location firstPlayerLoc, Location secondPlayerLoc, Vector playersVector) {
        secondPlayerLoc.add(playersVector);
        firstPlayerLoc.add(playersVector.clone().multiply(-1));
    }

    private static Location findSafeLocation(Location location) {
        while (!isSafeLocation(location)) {
            location.add(0, 1, 0);
        }
        return location;
    }

    private static boolean isSafeLocation(Location location) {
        return location.getBlock().isEmpty() && location.clone().add(0, 1, 0).getBlock().isEmpty();
    }
}
