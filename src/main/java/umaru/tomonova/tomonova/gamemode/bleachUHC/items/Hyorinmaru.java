package umaru.tomonova.tomonova.gamemode.bleachUHC.items;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class Hyorinmaru {

    private static final int RANGE = 8;
    private static final double ANGLE_RADIAN = Math.PI / 6;
    private static final double COS_THETA_MAX = Math.sqrt(3) / 2; // cos(pi/6)
    private static final int MAX_HEIGHT = 8;
    private static final double RADIUS_INCREMENT = 0.5;
    private static final double ANGLE_INCREMENT = Math.PI / 10;

    public static void hyorinmaru(String playerName) {
        Player player = getPlayer(playerName);
        if (player == null) return;

        damageNearbyEntities(player);
        transformBlocksInCone(player.getLocation(), player.getEyeLocation().getDirection().normalize());
    }

    private static Player getPlayer(String playerName) {
        return Bukkit.getPlayer(playerName);
    }

    private static void damageNearbyEntities(Player player) {
        Vector playerVisionVector = player.getEyeLocation().getDirection().clone();

        for (Entity entity : player.getNearbyEntities(RANGE, RANGE, RANGE)) {
            if (entity instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) entity;
                if (isInVisionCone(player, playerVisionVector, livingEntity)) {
                    applyEffects(livingEntity);
                }
            }
        }
    }

    private static boolean isInVisionCone(Player player, Vector playerVisionVector, LivingEntity entity) {
        Vector playerToEntityVector = entity.getLocation().toVector().subtract(player.getEyeLocation().toVector());
        double cosAngle = playerVisionVector.dot(playerToEntityVector) / (playerVisionVector.length() * playerToEntityVector.length());
        return cosAngle < 1 && cosAngle > COS_THETA_MAX;
    }

    private static void applyEffects(LivingEntity entity) {
        entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 1));
        entity.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 1));
    }

    private static void transformBlocksInCone(Location startingPoint, Vector directionNormalize) {
        World world = startingPoint.getWorld();
        Vector perpendicularVector = new Vector(1, 0, 0).crossProduct(directionNormalize).normalize();

        for (int height = 1; height <= MAX_HEIGHT; height++) {
            double radius = 0;
            while (radius < height * Math.tan(ANGLE_RADIAN)) {
                transformCircleBlocks(world, startingPoint, directionNormalize, perpendicularVector, height, radius);
                radius += RADIUS_INCREMENT;
            }
        }
    }

    private static void transformCircleBlocks(World world, Location startingPoint, Vector directionNormalize, Vector perpendicularVector, int height, double radius) {
        double angleCircle = 0;
        while (angleCircle < 2 * Math.PI) {
            angleCircle += ANGLE_INCREMENT;
            Location loc = calculateLocation(startingPoint, directionNormalize, perpendicularVector, height, radius, angleCircle);
            transformBlock(loc);
        }
    }

    private static Location calculateLocation(Location startingPoint, Vector directionNormalize, Vector perpendicularVector, int height, double radius, double angleCircle) {
        Location loc = startingPoint.clone().add(directionNormalize.clone().multiply(height));
        loc.add(perpendicularVector.clone().multiply(radius).rotateAroundAxis(directionNormalize, angleCircle));
        return loc;
    }

    private static void transformBlock(Location loc) {
        Block block = loc.getBlock();
        if (!block.isEmpty()) {
            block.setType(Material.PACKED_ICE);
        }
    }
}
