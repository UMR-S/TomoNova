package umaru.tomonova.tomonova.listeners.bleachUHC;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitTask;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.core.task.bleachUHCTask.SuzumebachiTask;

public class SuzumebachiHeldEvent implements Listener {

    @EventHandler
    public void onPlayerItemHeld(PlayerItemHeldEvent event) {
        ItemStack newItem = event.getPlayer().getInventory().getItem(event.getNewSlot());

        // Exit if the new item is null or AIR (empty slot)
        if (newItem == null || newItem.getType() == Material.AIR) {
            return;
        }

        // Check if item has metadata and the specific custom model data
        if (itemHasSuzumebachiData(newItem)) {
            triggerSuzumebachiEffect(event);
        }
    }

    private boolean itemHasSuzumebachiData(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        return meta != null && meta.hasCustomModelData() && meta.getCustomModelData() == 1021105;
    }

    private void triggerSuzumebachiEffect(PlayerItemHeldEvent event) {
        // Start the Suzumebachi task
        BukkitTask task = new SuzumebachiTask(TomoNova.getPlugin(), event.getPlayer().getName()).runTaskTimer(TomoNova.getPlugin(), 0, 5);
        event.getPlayer().sendMessage("Suzumebachi activated");
    }
}
