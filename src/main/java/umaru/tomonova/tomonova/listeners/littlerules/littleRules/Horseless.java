package umaru.tomonova.tomonova.listeners.littlerules.littleRules;

import org.bukkit.Material;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.spigotmc.event.entity.EntityMountEvent;
import umaru.tomonova.tomonova.lang.Lang;
import umaru.tomonova.tomonova.listeners.littlerules.LittleRule;

import java.util.Arrays;

public class Horseless extends LittleRule {
    public Horseless() {
        super("Horseless", Arrays.asList(Lang.GUIS_PETITES_REGLES_HORSELESS.toString()), Material.SADDLE);
    }

    @EventHandler
    public void onEntityMount(final EntityMountEvent event) {
        if (event.getEntity() != null && event.getEntity() instanceof Player && event.getMount() != null && event.getMount() instanceof Horse) {
            event.setCancelled(true);
        }
    }
}
