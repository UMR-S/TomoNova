package umaru.tomonova.tomonova.gamemode.bleachUHC.items;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Shinso {

    private static final int MAX_DISTANCE = 200;
    private static final double DAMAGE_AMOUNT = 7.0;
    private static final double PARTICLE_STEP = 0.5;
    private static final double PLAYER_HIT_DISTANCE_UP = -0.2;
    private static final double PLAYER_HIT_DISTANCE_DOWN = -1.0;
    private static final float DUST_SIZE = 1.0f;
    private static final Color DUST_COLOR = Color.WHITE;

    public static void use(String playerName) {
        Player caster = getPlayer(playerName);
        if (caster == null) return;

        Location casterLoc = caster.getLocation().clone();
        Vector direction = calculateDirection(caster, casterLoc);
        Particle.DustOptions dustOptions = new Particle.DustOptions(DUST_COLOR, DUST_SIZE);

        for (int i = 1; i < (int) Math.round(direction.length()) + 1; i++) {
            damageNearbyPlayers(caster, casterLoc, direction);
            spawnParticle(casterLoc, dustOptions);
            casterLoc.add(direction.clone().normalize().multiply(PARTICLE_STEP));
        }
    }

    private static Player getPlayer(String playerName) {
        return Bukkit.getPlayer(playerName);
    }

    private static Vector calculateDirection(Player caster, Location casterLoc) {
        Block nearestBlock = caster.getTargetBlockExact(MAX_DISTANCE);
        if (nearestBlock != null) {
            return new Vector(nearestBlock.getX() - casterLoc.getX(), nearestBlock.getY() - casterLoc.getY(), nearestBlock.getZ() - casterLoc.getZ());
        } else {
            return casterLoc.getDirection().clone().normalize().multiply(MAX_DISTANCE);
        }
    }

    private static void damageNearbyPlayers(Player caster, Location casterLoc, Vector direction) {
        Vector directionNormalized = direction.clone().normalize();
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!player.equals(caster)) {
                if (isPlayerHit(casterLoc, player)) {
                    player.damage(DAMAGE_AMOUNT);
                    player.setVelocity(directionNormalized);
                }
            }
        }
    }

    private static boolean isPlayerHit(Location casterLoc, Player player) {
        Location playerHitPlaceUp = player.getLocation().clone().add(0.0, PLAYER_HIT_DISTANCE_UP, 0.0);
        Location playerHitPlaceDown = player.getLocation().clone().add(0.0, PLAYER_HIT_DISTANCE_DOWN, 0.0);
        return casterLoc.distance(playerHitPlaceUp) < 1.0 || casterLoc.distance(playerHitPlaceDown) < 1.0;
    }

    private static void spawnParticle(Location casterLoc, Particle.DustOptions dustOptions) {
        casterLoc.getWorld().spawnParticle(Particle.REDSTONE, casterLoc.clone().add(0.0, 1.3, 0.0), 50, dustOptions);
    }
}
