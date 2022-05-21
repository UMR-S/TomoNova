package umaru.tomonova.tomonova.listeners.littlerules.littleRules;

import org.bukkit.Material;
import umaru.tomonova.tomonova.lang.Lang;
import umaru.tomonova.tomonova.listeners.littlerules.LittleRule;

import java.util.Arrays;

public class Collisions extends LittleRule {
    public Collisions() {
        super("Collisions", Arrays.asList(Lang.GUIS_PETITES_REGLES_COLLISIONS.toString()), Material.BLACKSTONE_WALL);
    }
}
