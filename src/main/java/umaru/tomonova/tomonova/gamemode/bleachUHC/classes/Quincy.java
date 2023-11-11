package umaru.tomonova.tomonova.gamemode.bleachUHC.classes;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import umaru.tomonova.tomonova.gamemode.bleachUHC.GiveItem;

public class Quincy {

    public static void giveStatingStuff(String playerName){
        GiveItem.giveBow(playerName);
        GiveItem.giveCarquois(playerName);
        GiveItem.giveQuincyArrow(playerName,17);
    }

}
