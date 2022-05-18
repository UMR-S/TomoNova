package umaru.tomonova.tomonova.listeners.littlerules.littleRules;

import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import umaru.tomonova.tomonova.lang.Lang;
import umaru.tomonova.tomonova.listeners.custom.GameStartEvent;
import umaru.tomonova.tomonova.listeners.littlerules.LittleRule;
import umaru.tomonova.tomonova.utils.world.WorldUtils;

import java.util.Arrays;

public class Regen extends LittleRule {
    public Regen() {
        super("Regen", Arrays.asList(Lang.GUIS_PETITES_REGLES_REGEN.toString()), Material.POPPY);
    }
    @EventHandler
    public void onGameStart(final GameStartEvent event) {
        WorldUtils.getWorld().setGameRule(GameRule.NATURAL_REGENERATION, true);
        WorldUtils.getNether().setGameRule(GameRule.NATURAL_REGENERATION,true);
        WorldUtils.getEnd().setGameRule(GameRule.NATURAL_REGENERATION, true);
    }
}
