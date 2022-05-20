package umaru.tomonova.tomonova.core.task;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import umaru.tomonova.tomonova.core.TomoNova;

public class TaskCountdown extends BukkitRunnable {

    private int preStartTime = 10; //En sec

    TomoNova tomoNova;

    public TaskCountdown(TomoNova tomoNova) {
        this.tomoNova = tomoNova;
    }

    @Override
    public void run(){
        Bukkit.broadcastMessage(String.valueOf(preStartTime));
        preStartTime--;
        if (preStartTime == 0){
            preStartTime = 10;
            TomoNova.getPlugin().gameManager.start();
            this.cancel();
        }
    }
}
