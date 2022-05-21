package umaru.tomonova.tomonova.core.task;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.utils.lobby.LobbyUtils;

public class TaskFinalCountdown extends BukkitRunnable {
    private static int preStartTime = 10; //En sec

    TomoNova tomoNova;

    public TaskFinalCountdown(TomoNova tomoNova) {
        this.tomoNova = tomoNova;
    }

    @Override
    public void run() {
        Bukkit.broadcastMessage(String.valueOf(preStartTime));
        preStartTime--;
        if (preStartTime == 0) {
            TomoNova.getPlugin().gameManager.deletePreGameLobby();
            LobbyUtils.deleteLobby();
            this.cancel();
        }
    }

    public static void setPreStartTime(int preStartTime) {
        TaskFinalCountdown.preStartTime = preStartTime;
    }
}
