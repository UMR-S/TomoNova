package umaru.tomonova.tomonova.core.task;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.lang.Lang;

import java.util.ArrayList;
import java.util.List;

public class TaskManager extends BukkitRunnable {
    private TomoNova tomoNova;
    private int count;
    private int pvpTime;
    private int borderTime;
    private int suddenDeathTime;
    private int netherEndTime;
    private int NetherDamage;
    private int BetweenNetherDamage;
    private List<Integer> listSubborderTime = new ArrayList<Integer>();
    private List<Integer> listSubborderFinalSize = new ArrayList<Integer>();

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
        this.listSubborderTime.addAll(tomoNova.gameManager.getListSubborderTime());
        this.listSubborderFinalSize.addAll(tomoNova.gameManager.getListSubborderFinalSize());
    }

    @Override
    public void run() {
        if(count == 0){
            TaskManagerInitialisation();
        }
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
        if(listSubborderTime.size() > 0){
            if(count == listSubborderTime.get(0)*60){
                tomoNova.worldBorderUtils.change(listSubborderFinalSize.get(0),((int)tomoNova.worldUtils.getWorld().getWorldBorder().getSize() - listSubborderFinalSize.get(0)) / tomoNova.worldBorderUtils.getSpeed());
                listSubborderTime.remove(0);
                listSubborderFinalSize.remove(0);
            }
        }
        if (count == borderTime) {
            tomoNova.worldBorderUtils.change(tomoNova.worldBorderUtils.getFinalSize(), (tomoNova.worldBorderUtils.getStartSize() - tomoNova.worldBorderUtils.getFinalSize()) / tomoNova.worldBorderUtils.getSpeed());
        }
        for (final Player player : Bukkit.getOnlinePlayers()) {
            tomoNova.scoreboardUtils.updateGame(player.getName(), count);
        }
    }
}
