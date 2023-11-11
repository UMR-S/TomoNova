package umaru.tomonova.tomonova.core.task.bleachUHCTask;

import org.bukkit.Bukkit;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.gamemode.bleachUHC.GiveItem;

public class HogyokuActifTask extends BukkitRunnable {
    private int timeActive;
    private String playerName;

    private static boolean cancel = false;
    private TomoNova tomoNova;

    public HogyokuActifTask(TomoNova tomoNova,String playerName) {
        this.tomoNova = tomoNova;
        this.playerName = playerName;
        this.timeActive =0;
    }
    @Override
    public void run() {
        if(timeActive == 0){
            Bukkit.getPlayer(playerName).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 2160000, 0, false, true, true));
            Bukkit.getPlayer(playerName).addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 2160000, 10, false, true, true));
            Bukkit.getPlayer(playerName).addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 2160000, 0, false, true, true));
            Bukkit.getPlayer(playerName).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2160000, 0, false, true, true));
            Bukkit.getPlayer(playerName).addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 2160000, 0, false, false, false));
        }
        if(timeActive%60 == 0){
            Bukkit.getPlayer(playerName).addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 24, 2, false, true, true));;
        }
        if(cancel){
            this.cancel();
            BukkitTask hogyokuInactif = new HogyokuInactifTask(tomoNova, playerName).runTaskTimer(tomoNova,0,20);
            GiveItem.giveHogyokuInactif(playerName);
            Bukkit.getPlayer(playerName).removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
            Bukkit.getPlayer(playerName).removePotionEffect(PotionEffectType.SATURATION);
            Bukkit.getPlayer(playerName).removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
            Bukkit.getPlayer(playerName).removePotionEffect(PotionEffectType.SPEED);
            Bukkit.getPlayer(playerName).removePotionEffect(PotionEffectType.GLOWING);
        }
        timeActive++;
    }

    public static void setCancel(boolean cancel) {
        HogyokuActifTask.cancel = cancel;
    }
}
