package umaru.tomonova.tomonova.gui.opGUI.communGUI.gamemodeGUI;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import umaru.tomonova.tomonova.gui.GUI;
import umaru.tomonova.tomonova.gui.opGUI.ItemInMainGUI;
import umaru.tomonova.tomonova.utils.customItems.CustomItems;

public class gamemodeGUI {
    public static void openMainGUI(Player player){
        Inventory gui = GUI.createGUI(9, ChatColor.DARK_PURPLE,"Additionnals rules config");
        gui = mainGui(gui);
        GUI.showGUI(player, gui);

    }

    public static Inventory mainGui(Inventory inv){
        for(ItemInMainGUI item : ItemInMainGUI.values()){
            if(item.getInitiallyActivated()){
                inv = addItemInGUI(inv,item);

            }
        }
        return inv;
    }
    public static Inventory addItemInGUI(Inventory inv, ItemInMainGUI itemMainGUI){
        ItemStack item = CustomItems.createCustomItem(itemMainGUI.getMaterial(), itemMainGUI.getColorMainText(),itemMainGUI.getMainText(),itemMainGUI.getSubText());
        item.setAmount(itemMainGUI.getNumber());
        inv.setItem(itemMainGUI.getPlaceInInventory(), item);
        return inv;
    }
    public void onClick(InventoryClickEvent event){
        event.setCancelled(true);
        Material nextInv = event.getCurrentItem().getType();
        Player player = (Player) event.getWhoClicked();
        nextGui(player, nextInv);
    }
    public static void nextGui(Player player, Material nextInv){

    }
}
