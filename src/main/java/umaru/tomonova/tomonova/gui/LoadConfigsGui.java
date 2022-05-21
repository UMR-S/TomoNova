package umaru.tomonova.tomonova.gui;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import umaru.tomonova.tomonova.lang.Lang;

public class LoadConfigsGui extends Gui {
    public LoadConfigsGui(Player player) {
        super(player, 54, ChatColor.GRAY + Lang.GUIS_LOAD_CONFIG_NAME.toString());
    }
}
