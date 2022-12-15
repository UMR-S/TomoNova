package umaru.tomonova.tomonova.utils.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.*;
import org.bukkit.util.Vector;
import umaru.tomonova.tomonova.core.TomoNova;

import java.util.HashMap;

public class ScoreboardUtils {

    private Plugin tomoNova = TomoNova.getPlugin();

    private HashMap<String, Scoreboard> scoreboardMap;

    public ScoreboardUtils() {
        this.scoreboardMap = new HashMap<String, Scoreboard>();
    }


    public String getColoredDirectionTo(Player p, final Location locToPoint) {
        final Location point = new Location(locToPoint.getWorld(), locToPoint.getX(), locToPoint.getY(), locToPoint.getZ());
        final Location ploc = p.getLocation();
        ploc.setY(0.0);
        point.setY(0.0);
        final Vector d = ploc.getDirection();
        final Vector v = point.subtract(ploc).toVector().normalize();
        double a = Math.toDegrees(Math.atan2(d.getX(), d.getZ()));
        a -= Math.toDegrees(Math.atan2(v.getX(), v.getZ()));
        a = (int) (a + 22.5) % 360;
        if (a < 0.0) {
            a += 360.0;
        }
        final String s = new StringBuilder().append("\u2b06\u2b08\u27a1\u2b0a\u2b07\u2b0b\u2b05\u2b09".charAt((int) a / 45)).toString();
        final int distance = (int) ploc.distance(point);
        if (distance > 300) {
            return "§1" + s;
        }
        if (distance > 200 && distance < 300) {
            return "§9" + s;
        }
        if (distance > 100 && distance < 200) {
            return "§3" + s;
        }
        if (distance > 50 && distance < 100) {
            return "§c" + s;
        }
        return "§a" + s;
    }

    public int getSpawnDistance(String playerName){
        Player player =  Bukkit.getPlayer(playerName);
        final Location locPlayer = new Location(player.getWorld(), player.getLocation().getX(),0.0,player.getLocation().getZ());
        final Location locSpawn = TomoNova.getPlugin().worldUtils.getWorld().getWorldBorder().getCenter();
        locSpawn.setY(0.0);
        return (int)locPlayer.distance(locSpawn);
    }

    public HashMap<String, Scoreboard> getScoreboardMap() {
        return scoreboardMap;
    }
}
