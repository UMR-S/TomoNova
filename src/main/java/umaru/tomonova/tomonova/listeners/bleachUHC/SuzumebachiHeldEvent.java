package umaru.tomonova.tomonova.listeners.bleachUHC;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.core.task.bleachUHCTask.SuzumebachiTask;

public class SuzumebachiHeldEvent implements Listener {
    @EventHandler
    public void playerHeldEventBleachUHC(PlayerItemHeldEvent event){
        ItemStack newItemInHand = event.getPlayer().getInventory().getItem(event.getNewSlot());
        if(!(newItemInHand == null || newItemInHand.getType().equals(Material.AIR))){
            if(newItemInHand.hasItemMeta()){
                if(newItemInHand.getItemMeta().getCustomModelData() == 1021105){
                    BukkitTask suzumebachiInvisibility = new SuzumebachiTask(TomoNova.getPlugin(),event.getPlayer().getName()).runTaskTimer(TomoNova.getPlugin(),0,20);
                }
            }
        }
    }
}
