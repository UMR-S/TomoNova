package umaru.tomonova.tomonova.listeners.littlerules.littleRules;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import umaru.tomonova.tomonova.lang.Lang;
import umaru.tomonova.tomonova.listeners.custom.GameStartEvent;
import umaru.tomonova.tomonova.listeners.littlerules.LittleRule;

import java.util.Arrays;

public class Fireless extends LittleRule {
    public Fireless() {
        super("Fireless", Arrays.asList(Lang.GUIS_PETITES_REGLES_FIRELESS.toString()), Material.FLINT_AND_STEEL);
    }

    @EventHandler
    public void onGameStart(final GameStartEvent event) {
        event.getPlayers().forEach(p -> p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 0)));
    }
}
