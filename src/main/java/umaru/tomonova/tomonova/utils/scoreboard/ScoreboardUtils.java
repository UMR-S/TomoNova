package umaru.tomonova.tomonova.utils.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.Vector;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.utils.world.WorldUtils;

import java.util.HashMap;

public class ScoreboardUtils {

    private final WorldUtils worldUtils = TomoNova.getPlugin().worldUtils;
    private final HashMap<String, Scoreboard> scoreboardMap;

    public ScoreboardUtils() {
        this.scoreboardMap = new HashMap<>();
    }

    public String getColoredDirectionTo(Player player, Location locToPoint) {
        Location point = new Location(locToPoint.getWorld(), locToPoint.getX(), 0.0, locToPoint.getZ());
        Location playerLoc = player.getLocation();
        playerLoc.setY(0.0);

        Vector direction = playerLoc.getDirection();
        Vector vector = point.subtract(playerLoc).toVector().normalize();
        double angle = calculateAngle(direction, vector);

        String arrow = getArrowForAngle(angle);
        int distance = (int) playerLoc.distance(point);

        return getColorForDistance(distance) + arrow;
    }

    private double calculateAngle(Vector direction, Vector vector) {
        double angle = Math.toDegrees(Math.atan2(direction.getX(), direction.getZ()));
        angle -= Math.toDegrees(Math.atan2(vector.getX(), vector.getZ()));
        angle = (int) (angle + 22.5) % 360;
        if (angle < 0.0) {
            angle += 360.0;
        }
        return angle;
    }

    private String getArrowForAngle(double angle) {
        String arrows = "\u2b06\u2b08\u27a1\u2b0a\u2b07\u2b0b\u2b05\u2b09";
        return String.valueOf(arrows.charAt((int) angle / 45));
    }

    private String getColorForDistance(int distance) {
        if (distance > 300) {
            return "§1";
        } else if (distance > 200) {
            return "§9";
        } else if (distance > 100) {
            return "§3";
        } else if (distance > 50) {
            return "§c";
        } else {
            return "§a";
        }
    }

    public int getSpawnDistance(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        if (player == null) {
            return -1;
        }
        Location locPlayer = new Location(player.getWorld(), player.getLocation().getX(), 0.0, player.getLocation().getZ());
        Location locSpawn = worldUtils.getWorld().getWorldBorder().getCenter();
        locSpawn.setY(0.0);
        return (int) locPlayer.distance(locSpawn);
    }

    public HashMap<String, Scoreboard> getScoreboardMap() {
        return scoreboardMap;
    }
}
