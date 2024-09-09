package umaru.tomonova.tomonova.core.task.bleachUHCTask;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import umaru.tomonova.tomonova.core.TomoNova;

public class BaveMinazukiTask extends BukkitRunnable {
    private TomoNova tomoNova;
    private Player player;
    private int duree;
    public BaveMinazukiTask(TomoNova tomoNova, String playerName) {
        this.tomoNova = tomoNova;
        this.duree = 0;
        this.player = Bukkit.getPlayer(playerName);
    }

    @Override
    public void run() {
        System.out.println(player.isHandRaised());
        if(player.isHandRaised()){
            player.setHealth(player.getHealth() - 1);
            duree++;
        }
        else{
            this.cancel();
            tomoNova.classesSpells.baveDeMinazuki(player.getName(), duree);
            tomoNova.classesSpells.setMinazukiActive(false);
        }
    }
}
