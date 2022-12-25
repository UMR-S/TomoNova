package umaru.tomonova.tomonova.core.task.bleachUHCTask;

import org.bukkit.scheduler.BukkitRunnable;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.gamemode.bleachUHC.classes.cooldowns.Cooldown;

public class CooldownTask extends BukkitRunnable {
    TomoNova tomoNova;

    public CooldownTask(TomoNova tomoNova) {
        this.tomoNova = tomoNova;
    }

    public void run() {
        Cooldown.handleCooldowns();
    }
}
