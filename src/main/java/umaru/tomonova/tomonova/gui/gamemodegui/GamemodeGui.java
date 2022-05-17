package umaru.tomonova.tomonova.gui.gamemodegui;

import org.bukkit.entity.Player;
import umaru.tomonova.tomonova.gui.Gui;
import umaru.tomonova.tomonova.lang.Lang;

public class GamemodeGui extends Gui {
    public GamemodeGui(Player player) {
        super(player, 9, Lang.GUIS_GAMEMODE_NAME.toString());

    }
}
