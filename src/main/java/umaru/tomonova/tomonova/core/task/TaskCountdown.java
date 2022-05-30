package umaru.tomonova.tomonova.core.task;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;
import umaru.tomonova.tomonova.core.TomoNova;

public class TaskCountdown extends BukkitRunnable {

    private static int preStartTime; //En sec
    private ChatColor color = ChatColor.GREEN;

    TomoNova tomoNova;

    public TaskCountdown(TomoNova tomoNova) {
        this.tomoNova = tomoNova;
    }

    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(p -> p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f, 1.0f));
        Bukkit.getOnlinePlayers().forEach(p -> p.sendTitle(color + Integer.toString(preStartTime), "", 1, 18, 1));
        if (preStartTime <= 5) {
            color = ChatColor.YELLOW;
        }
        if (preStartTime <= 2) {
            color = ChatColor.RED;
        }
        preStartTime--;
        if (preStartTime == 0) {
            this.cancel();
            TomoNova.getPlugin().gameManager.start();
        }
    }

    public static void setPreStartTime(int preStartTime) {
        TaskCountdown.preStartTime = preStartTime;
    }
}
