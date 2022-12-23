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
        getCone(player.getLocation().clone().add(0,-1,0),player.getEyeLocation().getDirection().clone().normalize());
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
                if(cosAngle > 0
                        && cosAngle < cosThetaMax){
                    ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW,200,1));
                    ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS,200,1));
                }
            }
        }
    }
    //Transformation des blocs
    public static List<Location> getCone(Location startingPoint, Vector directionNormalize){
        double angleRadian = Math.PI/6;
        int height = 8;
        List<Location> locations = new ArrayList<>();
        World world = startingPoint.getWorld();
        for(int d=0;d<=height;d++){
            for(int r = 0; r <= (int)d*Math.tan(angleRadian);r++){
                locations = getCircle(startingPoint,r,8*r^2);
                for(Location loc : locations){
                    if(!loc.getBlock().isEmpty()){
                        loc.getBlock().setType(Material.PACKED_ICE);
                    }
                }
            }
            startingPoint.add(directionNormalize);
        }
        return locations;
    }
    public static List<Location> getCircle(Location center, double radius, int amount) {
        List<Location> locations = new ArrayList<>();
        World world = center.getWorld();
        double increment = (2 * Math.PI) / amount;
        for (int i = 0; i < amount; i++) {
            double angle = i * increment;
            double x = center.getX() + (radius * Math.cos(angle));
            double z = center.getZ() + (radius * Math.sin(angle));
            locations.add(new Location(world, x, center.getY(), z));
        }
        return locations;
    }
}
