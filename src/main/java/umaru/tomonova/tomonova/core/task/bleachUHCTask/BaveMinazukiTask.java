package umaru.tomonova.tomonova.core.task.bleachUHCTask;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import umaru.tomonova.tomonova.core.TomoNova;

public class BaveMinazukiTask extends BukkitRunnable {
    private TomoNova tomoNova;
    private Player player;
    private int duree;
    public static boolean baveActive = false;
    public static int utilisationsBave = 3;
    public BaveMinazukiTask(TomoNova tomoNova, String playerName) {
        this.tomoNova = tomoNova;
        this.duree = 0;
        this.player = Bukkit.getPlayer(playerName);
    }

    @Override
    public void run() {
        if(player.isBlocking()){
            player.setHealth(player.getHealth() - 1);
            duree++;
        }
        else{
            this.cancel();
            tomoNova.classesSpells.baveDeMinazuki(player.getName(), duree);
            setBaveActive(false);
        }
    }

    public static boolean isBaveActive() {
        return baveActive;
    }

    public static void setBaveActive(boolean baveActive) {
        BaveMinazukiTask.baveActive = baveActive;
    }

    public static int getUtilisationsBave() {
        return utilisationsBave;
    }

    public static void setUtilisationsBave(int utilisationsBave) {
        BaveMinazukiTask.utilisationsBave = utilisationsBave;
    }
}
