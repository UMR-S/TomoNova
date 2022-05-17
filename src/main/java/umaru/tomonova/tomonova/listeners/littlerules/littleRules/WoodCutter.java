package umaru.tomonova.tomonova.listeners.littlerules.littleRules;

import org.bukkit.Material;
import umaru.tomonova.tomonova.lang.Lang;
import umaru.tomonova.tomonova.listeners.littlerules.LittleRule;

import java.util.Arrays;

public class WoodCutter extends LittleRule {
    public WoodCutter() {
        super("Wood Cutter", Arrays.asList(Lang.GUIS_PETITES_REGLES_WOODCUTTER.toString()), Material.OAK_LOG);
    }

}
