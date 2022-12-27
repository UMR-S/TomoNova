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
        System.out.println(duree);
        if(player.isBlocking()){
            player.setHealth(player.getHealth() - 1);
            duree++;
            System.out.println("hap");
        }
        else{
            this.cancel();
            tomoNova.classesSpells.baveDeMinazuki(player.getName(), duree);
            tomoNova.classesSpells.setMinazukiActive(false);
        }
    }
}
