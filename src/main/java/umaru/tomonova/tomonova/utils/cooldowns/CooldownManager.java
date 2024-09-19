package umaru.tomonova.tomonova.utils.cooldowns;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.utils.constants.BleachUHCConstants;

import java.util.*;

public class CooldownManager {
    // Stores cooldown end times for each player's item
    private final Map<UUID, List<Integer>> cooldowns = new HashMap<>();

    /**
     * Starts the cooldown for the specified item.
     *
     * @param player The player using the item.
     * @param item   The item to apply the cooldown to.
     */
    public void startCooldown(Player player, ItemStack item) {
        if(!isCooldown(player,item)){
            UUID playerUUID = player.getUniqueId();
            int customModelData = item.getItemMeta().getCustomModelData();
            int cooldownTime = BleachUHCConstants.ITEM_COOLDOWNS.getOrDefault(customModelData, 20);

            cooldowns.putIfAbsent(playerUUID, new ArrayList<Integer>());
            cooldowns.get(playerUUID).add(customModelData);

            // Set item durability to 1 to indicate the cooldown start
            item.setDurability((short) 1);

            // Start the task to update durability over time
            new CooldownTask(this, player, item, cooldownTime).runTaskTimer(TomoNova.getPlugin(), 0, 20);
        }
    }

    /**
     * Starts the cooldown for the "Lys des neiges".
     *
     * @param player The player using the item.
     * @param item   The item to apply the cooldown to.
     * @param lys If the lys hit or miss
     */
    public void startCooldown(Player player, ItemStack item, boolean lys) {
        if(!isCooldown(player,item)){
            UUID playerUUID = player.getUniqueId();
            int customModelData = item.getItemMeta().getCustomModelData();
            int cooldownTime = 60;
            if(lys){
                cooldownTime = BleachUHCConstants.ITEM_COOLDOWNS.getOrDefault(customModelData, 20);
            }

            cooldowns.putIfAbsent(playerUUID, new ArrayList<Integer>());
            cooldowns.get(playerUUID).add(customModelData);

            // Set item durability to 1 to indicate the cooldown start
            item.setDurability((short) 1);

            // Start the task to update durability over time
            new CooldownTask(this, player, item, cooldownTime).runTaskTimer(TomoNova.getPlugin(), 0, 20);
        }
    }

    /**
     * Checks if the specified item is currently on cooldown.
     *
     * @param player The player using the item.
     * @param item   The item to check the cooldown for.
     * @return true if the item is on cooldown, false otherwise.
     */
    public boolean isCooldown(Player player, ItemStack item) {
        UUID playerUUID = player.getUniqueId();
        int customModelData = item.getItemMeta().getCustomModelData();

        if (cooldowns.containsKey(playerUUID)) {
            List<Integer> playerCooldowns = cooldowns.get(playerUUID);
            if (playerCooldowns.contains(customModelData)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes the cooldown for the specified item.
     *
     * @param player The player using the item.
     * @param item   The item to remove the cooldown from.
     */
    public void removeCooldown(Player player, ItemStack item) {
        UUID playerUUID = player.getUniqueId();
        int customModelData = item.getItemMeta().getCustomModelData();

        if (cooldowns.containsKey(playerUUID)) {
            cooldowns.get(playerUUID).remove(cooldowns.get(playerUUID).indexOf(customModelData));
        }
    }
}
