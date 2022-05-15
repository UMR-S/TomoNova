package umaru.tomonova.tomonova.gui;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class GUI {

    public static Inventory createGUI(int size, ChatColor colorTitle,String name){
        Inventory gui = Bukkit.createInventory(null ,size, colorTitle + name);
        return gui;
    }
    public static void showGUI(Player player, Inventory gui){
        player.openInventory(gui);
    }
}
