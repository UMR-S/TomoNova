package umaru.tomonova.tomonova.gui;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import umaru.tomonova.tomonova.lang.Lang;

public class LittleRules extends Gui {
    public LittleRules(Player player) {
        super(player, 27, ChatColor.LIGHT_PURPLE + Lang.GUIS_PETITES_REGLES_NAME.toString());
    }
}
