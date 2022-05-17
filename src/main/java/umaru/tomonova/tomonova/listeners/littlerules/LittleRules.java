package umaru.tomonova.tomonova.listeners.littlerules;

import org.bukkit.Material;
import umaru.tomonova.tomonova.listeners.littlerules.littleRules.*;

public enum LittleRules {
    ETERNAL_DAY("ETERNAL_DAY",0,new EternalDay()),
    AUTOSMELL("AUTOSMELL", 1, new Autosmell()),
    COLLISIONS("COLLISIONS",2, new Collisions()),
    FRIENDLY_FIRE("FRIENDLY_FIRE",3,new FriendlyFire()),
    HORSELESS("HORSELESS",4, new Horseless()),
    RODLESS("RODLESS",5, new Rodless()),
    WOOD_CUTTER("WOOD_CUTTER",6, new WoodCutter()),
    FIRELESS("FIRELESS",7, new Fireless()),
    REGEN("REGEN", 8, new Regen()),

    ;

    private LittleRule littleRule;

    private LittleRules(final String s, final int n, final LittleRule littleRule) {
        this.littleRule = littleRule;
    }

    public LittleRule getLittleRule() {
        return this.littleRule;
    }

    public void setLittleRule(final LittleRule littleRule) {
        this.littleRule = littleRule;
    }
}
