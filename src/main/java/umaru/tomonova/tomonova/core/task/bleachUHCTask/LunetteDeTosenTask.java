package umaru.tomonova.tomonova.core.task.bleachUHCTask;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import umaru.tomonova.tomonova.core.TomoNova;

import java.util.Collection;

public class LunetteDeTosenTask extends BukkitRunnable {

    private int breakTime;
    private String playerName;
    private TomoNova tomoNova;
    private Location playerLoc;

    public LunetteDeTosenTask(TomoNova tomoNova, String playerName) {
        this.tomoNova = tomoNova;
        this.breakTime = 60;
        this.playerName = playerName;
        this.playerLoc = Bukkit.getPlayer(playerName).getLocation().clone();
    }

    @Override
    public void run() {
        Collection<Entity> nearbyEntities = playerLoc.getWorld().getNearbyEntities(playerLoc,6,6,6);
        for(Entity entity : nearbyEntities){
            if(entity instanceof Player){
                if(playerLoc.distance(entity.getLocation()) <= 50
                    && !tomoNova.bleachUHC.getLunettesBooleanPlayer(playerName)){
                    Player player = ((Player) entity).getPlayer();
                    boolean isBlind = false;
                    for(PotionEffect potionEffect : player.getActivePotionEffects()){
                        if(potionEffect.getType().equals(PotionEffectType.BLINDNESS)){
                            isBlind = true;
                        }
                    }
                    if(!isBlind){
                        ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,1210,0));
                    }
                }
            }
        }
        if(breakTime==0){
            this.cancel();
        }
        breakTime--;
    }

    public Location getPlayerLoc() {
        return playerLoc;
    }
}
