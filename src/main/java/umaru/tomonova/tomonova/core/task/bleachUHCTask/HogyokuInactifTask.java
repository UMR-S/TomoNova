package umaru.tomonova.tomonova.core.task.bleachUHCTask;

import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.gamemode.bleachUHC.GiveItem;

public class HogyokuInactifTask extends BukkitRunnable {
    private int timeInactive;
    private String playerName;
    private TomoNova tomoNova;

    public HogyokuInactifTask(TomoNova tomoNova, String playerName) {
        this.tomoNova = tomoNova;
        this.playerName = playerName;
        this.timeInactive = 6000;
    }
    @Override
    public void run() {
        if(timeInactive==0){
            GiveItem.giveHogyokuActif(playerName);

            BukkitTask hogyokuActif = new HogyokuActifTask(tomoNova, playerName).runTaskTimer(tomoNova, 0, 20);
            this.cancel();
        }
        timeInactive--;
    }
}
