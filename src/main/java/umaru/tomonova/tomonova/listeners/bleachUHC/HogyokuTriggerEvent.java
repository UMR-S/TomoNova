package umaru.tomonova.tomonova.listeners.bleachUHC;

import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.core.task.bleachUHCTask.HogyokuActifTask;
import umaru.tomonova.tomonova.gamemode.bleachUHC.GiveItem;
import umaru.tomonova.tomonova.utils.constants.BleachUHCConstants;

public class HogyokuTriggerEvent implements Listener {

    public void onCraft(PrepareItemCraftEvent event) {
        ItemStack result = event.getInventory().getResult();
        if (result != null && result.hasItemMeta()) {
            ItemMeta meta = result.getItemMeta();
            if (meta.hasCustomModelData()) {
                int customModelData = meta.getCustomModelData();
                if (customModelData == BleachUHCConstants.HOGYOKU_ACTIF) {
                    CraftingInventory inventory = event.getInventory();
                    HumanEntity player = inventory.getViewers().get(0);

                    if (player instanceof Player) {
                        Player crafter = (Player) player;
                        new HogyokuActifTask(TomoNova.getPlugin(), crafter.getName()).runTaskTimer(TomoNova.getPlugin(), 0, Integer.MAX_VALUE);
                        crafter.sendMessage(ChatColor.GREEN + "You have crafted the Hōgyoku. It is now bound to you.");
                        removeCraftingIngredients(inventory);
                        GiveItem.giveHogyokuActif(crafter.getName());
                    }
                }
            }
        }
    }

    // Helper function to remove the ingredients from the crafting grid
    private void removeCraftingIngredients(CraftingInventory inventory) {
        for (int i = 1; i < inventory.getSize(); i++) { // Skip the result slot (index 0)
            ItemStack item = inventory.getItem(i);
            if (item != null) {
                item.setAmount(0);  // Set amount to 0 to remove the item
            }
        }
    }
    @EventHandler
    public void UndiyingEvent(EntityResurrectEvent event){

        if(event.getEntity() instanceof Player){
            HogyokuActifTask.setCancel(true);
        }
    }
}
