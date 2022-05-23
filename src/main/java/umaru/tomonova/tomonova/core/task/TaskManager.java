package umaru.tomonova.tomonova.core.task;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.lang.Lang;

import java.util.Collection;

public class TaskManager extends BukkitRunnable {
    private TomoNova tomoNova;
    private int count;
    private int pvpTime;
    private int borderTime;
    private int suddenDeathTime;
    private int netherEndTime;
    private int NetherDamage;
    private int BetweenNetherDamage;

    public TaskManager(TomoNova tomoNova) {
        this.tomoNova = tomoNova;
    }

    public void TaskManagerInitialisation() {
        this.pvpTime = tomoNova.gameManager.getPvpTime() * 60;
        this.borderTime = tomoNova.gameManager.getTimeBorder() * 60;
        this.suddenDeathTime = tomoNova.gameManager.getSuddenDeathTime() * 60;
        this.netherEndTime = tomoNova.gameManager.getNetherEndTime() * 60;
        this.count = 0;
        this.NetherDamage = 0;
        this.BetweenNetherDamage = 30;
    }

    @Override
    public void run() {
        count++;
        if (count == 60) {
            Bukkit.broadcastMessage(Lang.DAMAGE_ACTIVATED.toString());
            tomoNova.gameManager.setDamage(true);
        }
        if (count == netherEndTime + BetweenNetherDamage * NetherDamage) {
            if (NetherDamage == 0) {
                Bukkit.broadcastMessage(Lang.NETHER_END.toString());
            }
            tomoNova.gameManager.setNether(false);
            NetherDamage++;
        }
        if (count == pvpTime) {
            Bukkit.broadcastMessage(Lang.PVP_ACTIVATED.toString());
            tomoNova.worldUtils.getWorld().setPVP(true);
            tomoNova.worldUtils.getNether().setPVP(true);
            tomoNova.worldUtils.getEnd().setPVP(true);
        }
        if (count == borderTime) {
            tomoNova.worldBorderUtils.change(tomoNova.worldBorderUtils.getFinalSize(), (tomoNova.worldBorderUtils.getStartSize() - tomoNova.worldBorderUtils.getFinalSize()) / tomoNova.worldBorderUtils.getSpeed());
        }
        for (final Player player : Bukkit.getOnlinePlayers()) {
            tomoNova.scoreboardUtils.updateGame(player.getName(), count);
        }
    }

    public int getPvpTime() {
        return pvpTime;
    }

    public int getBorderTime() {
        return borderTime;
    }

    public int getSuddenDeathTime() {
        return suddenDeathTime;
    }

    public int getNetherEndTime() {
        return netherEndTime;
    }
}
