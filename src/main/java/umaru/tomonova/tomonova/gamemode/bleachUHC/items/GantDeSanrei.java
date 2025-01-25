package umaru.tomonova.tomonova.gamemode.bleachUHC.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.gamemode.bleachUHC.GiveItem;
import umaru.tomonova.tomonova.utils.constants.BleachUHCConstants;

public class GantDeSanrei {

    public static void gantDeSanrei(String playerName) {
        Player player = getPlayer(playerName);
        if (player == null) return;

        fillArrows(playerName);
        replaceBowWithPowerFive(player);
        applySpeedEffect(player);
    }

    private static Player getPlayer(String playerName) {
        return Bukkit.getPlayer(playerName);
    }

    private static void fillArrows(String playerName) {
        TomoNova.getPlugin().classesSpells.carquois(playerName);
        TomoNova.getPlugin().classesSpells.carquois(playerName);
    }

    private static void replaceBowWithPowerFive(Player player) {
        for (ItemStack itemStack : player.getInventory()) {
            if (isBow(itemStack)) {
                player.getInventory().remove(itemStack);
                GiveItem.giveBowPowerFive(player.getName());
                break;
            }
        }
    }

    private static boolean isBow(ItemStack itemStack) {
        return itemStack != null && itemStack.getType().equals(Material.BOW);
    }

    private static boolean isQuincyBow(ItemStack itemStack) {
        return itemStack != null && itemStack.hasItemMeta() && itemStack.getItemMeta().hasCustomModelData() &&
                itemStack.getItemMeta().getCustomModelData() == BleachUHCConstants.ARC_QUINCY;
    }

    private static void applySpeedEffect(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2400, 1));
    }
}
