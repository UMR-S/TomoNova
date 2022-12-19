package umaru.tomonova.tomonova.core.task.bleachUHCTask;

import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.gamemode.bleachUHC.GiveItem;

public class HogyokuInactifTask extends BukkitRunnable {
    private static int timeInactive;
    private static String playerName;
    private TomoNova tomoNova;

    public HogyokuInactifTask(TomoNova tomoNova) {
        this.tomoNova = tomoNova;
    }
    @Override
    public void run() {
        if(timeInactive==0){
            GiveItem.giveHogyokuActif(playerName);
            HogyokuActifTask.setPlayerName(playerName);
            HogyokuActifTask.setTimeActive(0);
            BukkitTask hogyokuActif = new HogyokuActifTask(TomoNova.getPlugin()).runTaskTimer(TomoNova.getPlugin(), 0, 20);
            this.cancel();
        }
        timeInactive--;
    }

    public static void setTimeInactive(int timeInactive) {
        HogyokuInactifTask.timeInactive = timeInactive;
    }

    public static void setPlayerName(String playerName) {
        HogyokuInactifTask.playerName = playerName;
    }
}
