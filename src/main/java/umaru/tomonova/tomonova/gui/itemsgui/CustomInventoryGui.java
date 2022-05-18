package umaru.tomonova.tomonova.gui.itemsgui;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import umaru.tomonova.tomonova.gui.Gui;
import umaru.tomonova.tomonova.lang.Lang;


public class CustomInventoryGui extends Gui {
    public CustomInventoryGui(Player player) {
        super(player, 54, ChatColor.LIGHT_PURPLE +  Lang.GUIS_CUST_INV_NAME.toString());
    }
}
