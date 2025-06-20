package umaru.tomonova.tomonova.core.task.bleachUHCTask;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.utils.particles.ParticlesShape;

import java.util.Collection;

public class SenbonzakuraTask extends BukkitRunnable {
    private int breakTime;
    private String playerName;
    private TomoNova tomoNova;
    private Collection<Vector> normalizeLayerParticle;
    private Particle.DustOptions dustOptions;
    private Player player;
    public SenbonzakuraTask(TomoNova tomoNova, String playerName) {
        this.tomoNova = tomoNova;
        this.normalizeLayerParticle = ParticlesShape.collectionVectSphere(1,25);
        this.dustOptions = new Particle.DustOptions(Color.fromRGB(255, 192, 203), 1.0F);
        this.player = Bukkit.getPlayer(playerName);
        this.breakTime = 6;
    }
    @Override
    public void run() {
        Collection<Entity> nearbyEntities = player.getLocation().getWorld().getNearbyEntities(player.getLocation(),6,6,6);
        for(Entity entity : nearbyEntities){
            if(entity instanceof LivingEntity){
                if(3 <= player.getLocation().distance(entity.getLocation())
                        && player.getLocation().distance(entity.getLocation()) <= 6){
                    ((LivingEntity) entity).damage(6,player);
                }
            }
        }
        for(int radius = 3; radius<=6;radius = radius +3){
            for(Vector particleVectorNormalize : normalizeLayerParticle){
                Vector particleVector = particleVectorNormalize.clone().multiply(radius);
                player.getWorld().spawnParticle(Particle.DUST, player.getLocation().clone().add(particleVector), 1, dustOptions);
            }
        }
        if (breakTime == 0) {
            this.cancel();
        }
        breakTime--;
    }
}
