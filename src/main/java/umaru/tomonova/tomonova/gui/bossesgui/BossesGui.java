package umaru.tomonova.tomonova.gui.bossesgui;

import org.bukkit.entity.Player;
import umaru.tomonova.tomonova.gui.Gui;
import umaru.tomonova.tomonova.lang.Lang;

public class BossesGui extends Gui {
    public BossesGui(Player player) {
        super(player, 54, Lang.GUIS_BOSS_NAME.toString());
    }
}
