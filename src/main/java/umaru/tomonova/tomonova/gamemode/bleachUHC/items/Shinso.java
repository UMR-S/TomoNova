package umaru.tomonova.tomonova.gamemode.bleachUHC.items;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Shinso {
    public static void Shinso(String playerName) {

        Player caster = Bukkit.getPlayer(playerName);
        Location casterLoc = caster.getLocation();
        Block nearestBlock = caster.getTargetBlockExact(200);
        Vector vectorCasterToBlock = new Vector();
        if(nearestBlock != null){
            vectorCasterToBlock = new Vector(nearestBlock.getX() - casterLoc.getX(), nearestBlock.getY() - casterLoc.getY(), nearestBlock.getZ() - casterLoc.getZ());
        }
        else {
            vectorCasterToBlock = casterLoc.getDirection().clone().normalize().multiply(200);
        }
        Vector vectorCasterToBlockNormalize = new Vector();
        Vector vectorDirectionNormalized = caster.getLocation().getDirection().normalize();
        vectorCasterToBlockNormalize.copy(vectorCasterToBlock).normalize().multiply(0.5);
        Location loc = casterLoc;
        Particle.DustOptions dustOptions = new Particle.DustOptions(Color.WHITE, 1.0f);

        for (int i = 1; i < (int) Math.round(vectorCasterToBlock.length()) + 1; i++) {

            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.getName() == caster.getName()) {
                    continue;
                }
                Location playerHitPlaceUp = player.getLocation().clone().add(0.0, -0.2, 0.0);
                Location playerHitPlaceDown = player.getLocation().clone().add(0.0, -1.0, 0.0);

                if (loc.distance(playerHitPlaceUp) < 1.0 || loc.distance(playerHitPlaceDown) < 1.0) {

                    player.damage(7.0);
                    player.setVelocity(vectorDirectionNormalized);
                }
            }
            loc.getWorld().spawnParticle(Particle.REDSTONE, loc.clone().add(0.0, 1.3, 0.0), 50, dustOptions);
            loc.add(vectorDirectionNormalized);

        }

    }
}
