package umaru.tomonova.tomonova.listeners.littlerules.littleRules;

import org.bukkit.Material;
import umaru.tomonova.tomonova.lang.Lang;
import umaru.tomonova.tomonova.listeners.littlerules.LittleRule;

import java.util.Arrays;

public class FriendlyFire extends LittleRule {
    public FriendlyFire() {
        super("Friendly Fire", Arrays.asList(Lang.GUIS_PETITES_REGLES_FRIENDLY_FIRE.toString()), Material.IRON_SWORD);
    }
}
