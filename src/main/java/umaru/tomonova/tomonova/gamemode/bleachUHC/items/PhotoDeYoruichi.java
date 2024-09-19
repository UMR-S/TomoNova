package umaru.tomonova.tomonova.gamemode.bleachUHC.items;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import umaru.tomonova.tomonova.utils.bleachUHC.sounds.SoundsConstants;
import umaru.tomonova.tomonova.utils.bleachUHC.sounds.SoundsUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PhotoDeYoruichi {

    private static final int EXPLOSION_RADIUS = 50;
    private static final float EXPLOSION_SOUND_VOLUME = 5.0F;
    private static final float EXPLOSION_SOUND_PITCH = 0.5F;
    private static final double DAMAGE_AMOUNT = 10.0;

    public static void PhotoDeYoruichi(String playerName) {
        Player caster = getPlayer(playerName);
        if (caster == null) return;

        Location explosionLocation = calculateExplosionLocation(caster);
        playExplosionSound(explosionLocation);
        clearBlocksInSphere(explosionLocation);
        damageEntitiesInSphere(explosionLocation);
    }

    private static Player getPlayer(String playerName) {
        return Bukkit.getPlayer(playerName);
    }

    private static Location calculateExplosionLocation(Player caster) {
        Location casterLoc = caster.getLocation().clone();
        Block nearestBlock = caster.getTargetBlockExact(EXPLOSION_RADIUS);
        Vector vectorCasterToBlock = nearestBlock != null ?
                new Vector(nearestBlock.getX() - casterLoc.getX(), nearestBlock.getY() - casterLoc.getY(), nearestBlock.getZ() - casterLoc.getZ()) :
                casterLoc.getDirection().clone().normalize().multiply(EXPLOSION_RADIUS);
        return casterLoc.clone().add(vectorCasterToBlock);
    }

    private static void playExplosionSound(Location explosionLocation) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            SoundsUtils.playSoundIfInRange(explosionLocation, SoundsConstants.SOIFON_ITEMUNIQUE,256);
        }
    }

    private static void clearBlocksInSphere(Location center) {
        for (Location loc : generateSphere(center, EXPLOSION_RADIUS, false)) {
            Block block = loc.getBlock();
            if (block.getType() != Material.BEDROCK && block.getType() != Material.AIR) {
                block.setType(Material.AIR);
            }
        }
    }

    private static void damageEntitiesInSphere(Location center) {
        for (Entity entity : Objects.requireNonNull(center.getWorld()).getNearbyEntities(center, EXPLOSION_RADIUS, EXPLOSION_RADIUS, EXPLOSION_RADIUS)) {
            if (entity instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) entity;
                livingEntity.setHealth(Math.max(0, livingEntity.getHealth() - DAMAGE_AMOUNT));
            }
        }
    }

    public static List<Location> generateSphere(Location center, int radius, boolean hollow) {
        List<Location> sphere = new ArrayList<>();
        if (center == null) return sphere;

        int bx = center.getBlockX();
        int by = center.getBlockY();
        int bz = center.getBlockZ();

        for (int x = bx - radius; x <= bx + radius; x++) {
            for (int y = by - radius; y <= by + radius; y++) {
                for (int z = bz - radius; z <= bz + radius; z++) {
                    double distance = (bx - x) * (bx - x) + (by - y) * (by - y) + (bz - z) * (bz - z);
                    if (distance < radius * radius && !(hollow && distance < (radius - 1) * (radius - 1))) {
                        sphere.add(new Location(center.getWorld(), x, y, z));
                    }
                }
            }
        }
        return sphere;
    }
}
