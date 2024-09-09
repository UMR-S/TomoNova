package umaru.tomonova.tomonova.core.task.bleachUHCTask;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import umaru.tomonova.tomonova.core.TomoNova;

public class MedicamentUkitakeTask extends BukkitRunnable {
    TomoNova tomoNova;
    Player player;
    int duree;
    public MedicamentUkitakeTask(TomoNova tomoNova, String playerName) {
        this.tomoNova = tomoNova;
        this.player = Bukkit.getPlayer(playerName);
        this.duree = 12; //Periode d'une demi seconde
    }

    @Override
    public void run() {
        if(duree ==0){
            this.cancel();
        }
        player.getActivePotionEffects().forEach(p -> player.removePotionEffect(p.getType()));
        duree--;
    }
}
