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

import java.util.ArrayList;
import java.util.List;

public class Hyorinmaru {
    //range = 8
    //angle = pi/6
    public static void hyorinmaru(String playerName){
        damageToPlayers(playerName);
        Player player = Bukkit.getPlayer(playerName);
        getCone(player.getLocation().clone(),player.getEyeLocation().getDirection().clone().normalize());
    }
    //Dégâts aux joueurs
    public static void damageToPlayers(String playerName){
        int range = 8;
        double cosThetaMax = Math.sqrt(3)/2; //cos pi/6
        Player player = Bukkit.getPlayer(playerName);
        Vector playerVisionVector = player.getEyeLocation().getDirection().clone();
        Vector playerToEntityVector;
        Location entityLoc;
        double cosAngle;
        for(Entity entity : player.getNearbyEntities(range, range, range)){
            if(entity instanceof LivingEntity){
                entityLoc = entity.getLocation().clone();
                playerToEntityVector = new Vector(  entityLoc.getX() - player.getEyeLocation().getX(),
                        entityLoc.getY() - player.getEyeLocation().getY(),
                        entityLoc.getZ() - player.getEyeLocation().getZ());
                cosAngle = playerVisionVector.dot(playerToEntityVector)/(playerVisionVector.length()*playerToEntityVector.length());
                if(cosAngle < 1
                        && cosAngle > cosThetaMax){
                    ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW,200,1));
                    ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS,200,1));
                }
            }
        }
    }
    //Transformation des blocs
    public static void getCone(Location startingPoint, Vector directionNormalize){
        double angleRadian = Math.PI/6;
        int maxHeight = 8;
        int height = 1;
        World world = startingPoint.getWorld();
        Vector vectorBase = new Vector(1,0,0);
        Vector perpendicularVector = vectorBase.crossProduct(directionNormalize.clone()).normalize();
        while(height <= maxHeight){
            double radius = 0;
            while (radius < height*Math.tan(angleRadian)){
                double angleCircle = 0;
                while(angleCircle < 2*Math.PI){
                    angleCircle = angleCircle + Math.PI/10;
                    Location loc = startingPoint.clone().add(directionNormalize.clone().multiply(height));
                    loc.add(perpendicularVector.clone().multiply(radius).rotateAroundAxis(directionNormalize,angleCircle));
                    if(!loc.getBlock().isEmpty()){
                        loc.getBlock().setType(Material.PACKED_ICE);
                    }
                }
                radius = radius + 0.5;
            }
            height++;
        }
    }
}
