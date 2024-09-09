package umaru.tomonova.tomonova.utils.cooldowns;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import umaru.tomonova.tomonova.utils.constants.BleachUHCConstants;
public class CooldownTask extends BukkitRunnable {
    private CooldownManager cooldownManager;
    private Player player;
    private ItemStack item;
    private int cooldown;

    public CooldownTask(CooldownManager cooldownManager, Player player, ItemStack item,int cooldown) {
        this.cooldownManager = cooldownManager;
        this.player = player;
        this.item = item;
        this.cooldown = cooldown;
    }

    @Override
    public void run() {
        if (cooldown <= 0) {
            cooldownManager.removeCooldown(player, item);
            item.setDurability((short)0);
            this.cancel();
        } else {
            double maxDurability = item.getType().getMaxDurability();
            int customModelData = item.getItemMeta().getCustomModelData();
            double percentage = ((double) cooldown / BleachUHCConstants.ITEM_COOLDOWNS.getOrDefault(customModelData, 1));
            item.setDurability((short) (maxDurability * percentage));
        }
        cooldown = cooldown-1;
    }
}
