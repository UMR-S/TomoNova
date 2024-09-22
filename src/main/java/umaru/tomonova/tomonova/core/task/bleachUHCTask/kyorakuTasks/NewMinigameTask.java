package umaru.tomonova.tomonova.core.task.bleachUHCTask.kyorakuTasks;

import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.mobs.ActiveMob;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.utils.bleachUHC.sounds.SoundsConstants;
import umaru.tomonova.tomonova.utils.bleachUHC.sounds.SoundsUtils;
import umaru.tomonova.tomonova.utils.constants.BleachUHCConstants;

import java.util.List;
import java.util.Random;

public class NewMinigameTask extends BukkitRunnable {

    private final int xKyorkaku = -222;
    private final int yKyoraku = 36;
    private final int zKyoraku = 367;
    private int seconds = 0;
    private List<Player> playersInGame;
    private Material colorBlock;

    @Override
    public void run() {
        if(seconds == 0){
            playersInGame = TomoNova.getPlugin().combatZoneUtils.playersInKyorakuZone();
            if(playersInGame.size() == 0){
                BukkitTask newMinigameask = new NewMinigameTask().runTaskTimer(TomoNova.getPlugin(),100,20);
                this.cancel();
            }
        }
        if (seconds == 5 && checkForKyoraku()) {
            Random random = new Random();
            int randomGame = random.nextInt(5);
            Location kyorakuLoc = new Location(TomoNova.getPlugin().worldUtils.getWorld(),xKyorkaku,yKyoraku,zKyoraku);
            switch (randomGame) {
                case 0:
                    SoundsUtils.playSoundIfInRange(kyorakuLoc, SoundsConstants.KYORAKU_IRONI,64);
                    colorBlock = getRandomWoolColor();
                    BukkitTask irooniTask = new IrooniTask(colorBlock,playersInGame).runTaskTimer(TomoNova.getPlugin(), 0, 20);
                    break;
                case 1:
                    SoundsUtils.playSoundIfInRange(kyorakuLoc, SoundsConstants.KYORAKU_KAGEONI,64);
                    BukkitTask KageoniTask = new KageoniTask(playersInGame).runTaskTimer(TomoNova.getPlugin(), 0, 20);
                    break;
                case 2:
                    SoundsUtils.playSoundIfInRange(kyorakuLoc, SoundsConstants.KYORAKU_TAKAONI,64);
                    BukkitTask TakaoniTask = new TakaoniTask(playersInGame).runTaskTimer(TomoNova.getPlugin(), 0, 20);
                    break;
                case 3:
                    SoundsUtils.playSoundIfInRange(kyorakuLoc, SoundsConstants.KYORAKU_KAGEOKURI,64);
                    BukkitTask KageokuriTask = new TakaoniTask(playersInGame).runTaskTimer(TomoNova.getPlugin(), 0, 20);
                    break;
                case 4:
                    SoundsUtils.playSoundIfInRange(kyorakuLoc, SoundsConstants.KYORAKU_DARUMASTART, 64);
                    BukkitTask darumasangaKorondaTask = new DarumasanGaKorondaTask(playersInGame).runTaskTimer(TomoNova.getPlugin(),0, 20);
                    break;
            }
            this.cancel();
        }
        seconds++;
    }

    private boolean checkForKyoraku() {
        if (MythicBukkit.inst().getMobManager() != null) {
            for (ActiveMob mob : MythicBukkit.inst().getMobManager().getActiveMobs()) {
                if (!mob.isDead() && mob.getEntity().getBukkitEntity() != null) {
                    String customName = mob.getEntity().getBukkitEntity().getCustomName();
                    if (customName != null && customName.equalsIgnoreCase(BleachUHCConstants.KYORAKU_NAME)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public Material getRandomWoolColor() {
        // Array of 9 wool colors
        Material[] woolColors = {
                Material.WHITE_WOOL,
                Material.ORANGE_WOOL,
                Material.MAGENTA_WOOL,
                Material.LIGHT_BLUE_WOOL,
                Material.YELLOW_WOOL,
                Material.LIME_WOOL,
                Material.PINK_WOOL,
                Material.GRAY_WOOL,
                Material.LIGHT_GRAY_WOOL
        };

        // Generate a random index between 0 and 8
        Random random = new Random();
        int randomIndex = random.nextInt(woolColors.length);

        // Return the wool color at the random index
        return woolColors[randomIndex];
    }
}
