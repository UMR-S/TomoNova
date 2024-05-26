package umaru.tomonova.tomonova.gamemode.bleachUHC.items;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PhotoDeYoruichi {
    public static void PhotoDeYoruichiUse(String playerName) {
        Player caster = Bukkit.getPlayer(playerName);
        assert caster != null;
        Location casterLoc = caster.getLocation().clone();
        Block nearestBlock = caster.getTargetBlockExact(50);
        Vector vectorCasterToBlock;
        if (nearestBlock != null) {
            vectorCasterToBlock = new Vector(nearestBlock.getX() - casterLoc.getX(), nearestBlock.getY() - casterLoc.getY(), nearestBlock.getZ() - casterLoc.getZ());
        } else {
            vectorCasterToBlock = casterLoc.getDirection().clone().normalize().multiply(50);
        }
        Location locExplosion = casterLoc.clone().add(vectorCasterToBlock);
        for(Player players : Bukkit.getOnlinePlayers()){
            Objects.requireNonNull(locExplosion.getWorld()).playSound(players.getLocation(), Sound.ENTITY_GENERIC_EXPLODE,5.0F,0.5F);
        }
        for(Location locBlock : generateSphere(locExplosion.clone(),50,false)){
            if(!locBlock.getBlock().getType().equals(Material.BEDROCK) && !locBlock.getBlock().getType().equals(Material.AIR)){
                locBlock.getBlock().setType(Material.AIR);
            }
            //locExplosion.getWorld().spawnParticle(Particle.EXPLOSION_HUGE,locBlock,1);
        }
        for(Entity entityExplosion : Objects.requireNonNull(locExplosion.getWorld()).getNearbyEntities(locExplosion,50,50,50)){
            if(entityExplosion instanceof LivingEntity){
                ((LivingEntity) entityExplosion).setHealth(((LivingEntity) entityExplosion).getHealth() - 10);
            }
        }
    }
    public static List<Location> generateSphere(Location centerBlock, int radius, boolean hollow) {
        if (centerBlock == null) {
            return new ArrayList<>();
        }

        List<Location> circleBlocks = new ArrayList<Location>();

        int bx = centerBlock.getBlockX();
        int by = centerBlock.getBlockY();
        int bz = centerBlock.getBlockZ();

        for(int x = bx - radius; x <= bx + radius; x++) {
            for(int y = by - radius; y <= by + radius; y++) {
                for(int z = bz - radius; z <= bz + radius; z++) {

                    double distance = ((bx-x) * (bx-x) + ((bz-z) * (bz-z)) + ((by-y) * (by-y)));

                    if(distance < radius * radius && !(hollow && distance < ((radius - 1) * (radius - 1)))) {

                        Location l = new Location(centerBlock.getWorld(), x, y, z);

                        circleBlocks.add(l);

                    }

                }
            }
        }

        return circleBlocks;
    }
}
