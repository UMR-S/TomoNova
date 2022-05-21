package umaru.tomonova.tomonova.listeners.littlerules.littleRules;

import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.lang.Lang;
import umaru.tomonova.tomonova.listeners.custom.GameStartEvent;
import umaru.tomonova.tomonova.listeners.littlerules.LittleRule;

import java.util.Arrays;

public class Regen extends LittleRule {
    public Regen() {
        super("Regen", Arrays.asList(Lang.GUIS_PETITES_REGLES_REGEN.toString()), Material.POPPY);
    }

    @EventHandler
    public void onGameStart(final GameStartEvent event) {
        TomoNova.getPlugin().worldUtils.getWorld().setGameRule(GameRule.NATURAL_REGENERATION, true);
        TomoNova.getPlugin().worldUtils.getNether().setGameRule(GameRule.NATURAL_REGENERATION, true);
        TomoNova.getPlugin().worldUtils.getEnd().setGameRule(GameRule.NATURAL_REGENERATION, true);
    }
}
