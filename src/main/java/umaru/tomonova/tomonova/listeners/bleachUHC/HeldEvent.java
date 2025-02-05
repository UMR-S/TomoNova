package umaru.tomonova.tomonova.listeners.bleachUHC;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.core.task.bleachUHCTask.SuzumebachiTask;
import umaru.tomonova.tomonova.utils.constants.BleachUHCConstants;

import java.util.List;

public class HeldEvent implements Listener {

    @EventHandler
    public void onPlayerItemHeld(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        ItemStack newItem = player.getInventory().getItem(event.getNewSlot());
        ItemStack oldItem =  player.getInventory().getItem(event.getPreviousSlot());
        if (newItem == null || newItem.getType() == Material.AIR) {
            return;
        }
        if (itemHasSuzumebachiData(newItem)) {
            triggerSuzumebachiEffect(event);
        }
        if (isSogyoOrKaten(newItem)) {
            checkAndApplySharpnessUpgrade(player);
        }
        if (oldItem == null || oldItem.getType() == Material.AIR) {
            return;
        }
        if(isSogyoOrKaten(oldItem) && !isSogyoOrKaten(newItem)){
            checkAndApplySharpnessDowngrade(player,oldItem);
        }
    }

    private boolean itemHasSuzumebachiData(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        return meta != null && meta.hasCustomModelData() && meta.getCustomModelData() == BleachUHCConstants.SUZUMEBACHI;
    }

    private void triggerSuzumebachiEffect(PlayerItemHeldEvent event) {
        BukkitTask task = new SuzumebachiTask(TomoNova.getPlugin(), event.getPlayer().getName()).runTaskTimer(TomoNova.getPlugin(), 0, 5);
        event.getPlayer().sendMessage("Suzumebachi activated");
    }

    private boolean isSogyoOrKaten(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null || !meta.hasCustomModelData()) {
            return false;
        }
        int customModelData = meta.getCustomModelData();
        return customModelData == BleachUHCConstants.SOGYO_NO_KOTOWARI || customModelData == BleachUHCConstants.KATEN_KYOKOTSU;
    }

    private void checkAndApplySharpnessUpgrade(Player player) {

        List<String> playersTeam = TomoNova.getPlugin().teamUtils.getTeamPlayersNames(player.getName());
        if (playersTeam.isEmpty()) {
            return;
        }
        for (String teammateName : playersTeam) {
            Player teammate = Bukkit.getPlayer(teammateName);
            if (!teammate.equals(player)) {
                ItemStack otherPlayerItem = teammate.getInventory().getItemInMainHand();

                if (otherPlayerItem != null && isSogyoOrKaten(otherPlayerItem)) {
                    applySharpnessUpgrade(player.getInventory().getItemInMainHand(), otherPlayerItem);
                    player.sendMessage("Sharpness II!");
                    teammate.sendMessage("Sharpness II!");
                }
            }
        }
    }

    private void checkAndApplySharpnessDowngrade(Player player, ItemStack oldItem) {

        List<String> playersTeam = TomoNova.getPlugin().teamUtils.getTeamPlayersNames(player.getName());
        if (playersTeam.isEmpty()) {
            return;
        }
        for (String teammateName : playersTeam) {
            Player teammate = Bukkit.getPlayer(teammateName);
            if (!teammate.equals(player)) {
                ItemStack otherPlayerItem = teammate.getInventory().getItemInMainHand();

                if (otherPlayerItem != null && isSogyoOrKaten(otherPlayerItem)) {
                    applySharpnessUpgrade(oldItem, otherPlayerItem);
                    player.sendMessage("Sharpness I!");
                    teammate.sendMessage("Sharpness I!");
                }
            }
        }
    }

    private void applySharpnessUpgrade(ItemStack item1, ItemStack item2) {
        // Apply Sharpness II enchantment to both items
        if (item1 != null) {
            item1.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
        }
        if (item2 != null) {
            item2.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
        }
    }
    private void applySharpnessDowngrade(ItemStack item1, ItemStack item2) {
        // Apply Sharpness II enchantment to both items
        if (item1 != null) {
            item1.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
        }
        if (item2 != null) {
            item2.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
        }
    }
}
