package umaru.tomonova.tomonova.core.task.bleachUHCTask;

import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.gamemode.bleachUHC.items.IceCage;

public class BreakIceCageTask extends BukkitRunnable {

    private static int breakTime;
    private static Location locPlayer;
    private TomoNova tomoNova;

    public BreakIceCageTask(TomoNova tomoNova) {
        this.tomoNova = tomoNova;
    }

    @Override
    public void run() {

        breakTime--;
        if (breakTime == 0) {
            this.cancel();
            IceCage.breakIceCage(locPlayer);
        }
    }

    public static void setbreakTime(int breakTime) {
        BreakIceCageTask.breakTime = breakTime;
    }

    public static void setLocPlayer(Location locPlayer) {
        BreakIceCageTask.locPlayer = locPlayer;
    }
}
