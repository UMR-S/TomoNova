package umaru.tomonova.tomonova.core.task;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import umaru.tomonova.tomonova.core.TomoNova;

public class TaskCountdown extends BukkitRunnable {

    private static int preStartTime; //En sec

    TomoNova tomoNova;

    public TaskCountdown(TomoNova tomoNova) {
        this.tomoNova = tomoNova;
    }

    @Override
    public void run() {
        Bukkit.broadcastMessage(String.valueOf(preStartTime));
        preStartTime--;
        if (preStartTime == 1) {
            this.cancel();
            TomoNova.getPlugin().gameManager.start();
        }
    }

    public static void setPreStartTime(int preStartTime) {
        TaskCountdown.preStartTime = preStartTime;
    }
}
