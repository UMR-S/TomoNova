package umaru.tomonova.tomonova.core.task.bleachUHCTask.kyorakuTasks;

import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.mobs.ActiveMob;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.utils.constants.BleachUHCConstants;

import java.util.*;

public class IrooniTask extends BukkitRunnable {

    Material colorBlock;
    List<Player> playersInGame;
    int seconds = 0;
    boolean damageKyoraku = true;
    boolean shouldDamageAll = false;
    Set<Location> uniqueLocations;

    public IrooniTask(Material colorBlock, List<Player> playersInGame) {
        this.colorBlock = colorBlock;
        this.playersInGame = playersInGame;
        replaceBlocksWithWools();
    }

    @Override
    public void run() {
        if (seconds == 5) {
            for (Player player : playersInGame) {
                Block solidBlock = getSolidBlockAtFeet(player);
                if (solidBlock != null && !solidBlock.getType().equals(colorBlock)) {
                    damageKyoraku = false;
                    shouldDamageAll = true;
                }
            }
            if (shouldDamageAll) {
                damageAll();
            }
            if (damageKyoraku) {
                damageKyoraku();
            }
            replaceWoolWithBlocks();
            BukkitTask newMinigameask = new NewMinigameTask().runTaskTimer(TomoNova.getPlugin(),0,20);
            this.cancel();
        }
        seconds++;
    }

    public Block getSolidBlockAtFeet(Player player) {
        Block blockAtFeet = player.getLocation().getBlock();
        for (int y = blockAtFeet.getY(); y >= 0; y--) {
            Block blockBelow = blockAtFeet.getWorld().getBlockAt(blockAtFeet.getX(), y, blockAtFeet.getZ());
            if (blockBelow.getType().isSolid()) {
                return blockBelow;
            }
        }
        return null;
    }

    public void damageAll() {
        for (Player player : playersInGame) {
            player.damage(14.0);
        }
    }

    private void damageKyoraku() {
        if (MythicBukkit.inst().getMobManager() != null) {
            for (ActiveMob mob : MythicBukkit.inst().getMobManager().getActiveMobs()) {
                if (!mob.isDead() && mob.getEntity().getBukkitEntity() != null) {
                    String customName = mob.getEntity().getBukkitEntity().getCustomName();
                    if (customName != null && customName.equalsIgnoreCase(BleachUHCConstants.KYORAKU_NAME)) {
                        mob.getEntity().damage(50.0f);
                    }
                }
            }
        }
    }

    public void getUniqueRandomLocationsInRectangleKyoraku(int numberOfLocations) {
        int minX = -282;
        int maxX = -231;
        int minZ = 366;
        int maxZ = 395;
        int y = 33;

        Random random = new Random();
        while (uniqueLocations.size() < numberOfLocations) {
            int randomX = minX + random.nextInt(maxX - minX + 1); // Generates a value between minX and maxX
            int randomZ = minZ + random.nextInt(maxZ - minZ + 1); // Generates a value between minZ and maxZ
            Location randomLocation = new Location(TomoNova.getPlugin().worldUtils.getWorld(), randomX, y, randomZ);
            uniqueLocations.add(randomLocation);
        }
    }

    public void replaceBlocksWithWools() {
        getUniqueRandomLocationsInRectangleKyoraku( 9);
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
        int index = 0;
        for (Location loc : uniqueLocations) {
            Block block = loc.getBlock();
            block.setType(woolColors[index]);
            index++;
        }
    }
    public void replaceWoolWithBlocks() {
        for (Location loc : uniqueLocations) {
            Block block = loc.getBlock();
            block.setType(Material.SMOOTH_STONE);
        }
    }
}
