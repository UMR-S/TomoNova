package umaru.tomonova.tomonova.listeners.littlerules.littleRules;

import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import umaru.tomonova.tomonova.lang.Lang;
import umaru.tomonova.tomonova.listeners.custom.GameStartEvent;
import umaru.tomonova.tomonova.listeners.littlerules.LittleRule;

import java.util.Arrays;

public class EternalDay extends LittleRule {

    public EternalDay() {
        super("Eternal Day", Arrays.asList(Lang.GUIS_PETITES_REGLES_ETERNAL_DAY.toString()), Material.CLOCK);
    }

    @EventHandler
    public void onGameStart(final GameStartEvent event) {
        event.getWorld().setTime(6000L);
        event.getWorld().setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
    }
}
