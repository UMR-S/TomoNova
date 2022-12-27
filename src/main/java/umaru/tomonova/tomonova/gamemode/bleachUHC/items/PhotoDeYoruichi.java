package umaru.tomonova.tomonova.gamemode.bleachUHC.items;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import umaru.tomonova.tomonova.utils.particles.ParticlesShape;

import java.util.ArrayList;
import java.util.Collection;

public class PhotoDeYoruichi {
    public static void PhotoDeYoruichi(String playerName) {
        Player caster = Bukkit.getPlayer(playerName);
        Location casterLoc = caster.getLocation().clone();
        Block nearestBlock = caster.getTargetBlockExact(50);
        Vector vectorCasterToBlock;
        if(nearestBlock != null){
            vectorCasterToBlock = new Vector(nearestBlock.getX() - casterLoc.getX(), nearestBlock.getY() - casterLoc.getY(), nearestBlock.getZ() - casterLoc.getZ());
        }
        else {
            vectorCasterToBlock = casterLoc.getDirection().clone().normalize().multiply(50);
        }
        casterLoc.add(vectorCasterToBlock);
        for(Player player : Bukkit.getOnlinePlayers()){
            if(player.getLocation().distance(casterLoc) <= 50){
                player.damage(10);
            }
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,10,4));
        }
        Collection<Vector> locExplosionList = new ArrayList<>();
        for(int i = 50; i < 50; i = + 5){
            ParticlesShape.collectionVectSphere(i,i*100).forEach(v -> locExplosionList.add(v));
        }
        for(Vector explosionVector : locExplosionList){
            caster.getWorld().createExplosion(casterLoc.clone().add(explosionVector),40,false,true,caster);
        }
    }
}
