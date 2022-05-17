package umaru.tomonova.tomonova.listeners.littlerules.littleRules;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import umaru.tomonova.tomonova.lang.Lang;
import umaru.tomonova.tomonova.listeners.littlerules.LittleRule;
import umaru.tomonova.tomonova.utils.gui.ItemsCreator;

import java.util.Arrays;
public class Rodless extends LittleRule {
    public Rodless() {
        super("Rodless", Arrays.asList(Lang.GUIS_PETITES_REGLES_RODLESS.toString()), Material.FISHING_ROD);
    }
    @EventHandler
    public void onPrepareItemCraft(final PrepareItemCraftEvent event) {
        if (event.getInventory().getResult().getType() == Material.FISHING_ROD) {
            ItemsCreator ic = new ItemsCreator(Material.AIR, null, null);
            event.getInventory().setResult(ItemsCreator.create(ic));
        }
    }

    @EventHandler
    public void onProjectileLaunch(final ProjectileLaunchEvent event) {
        if (event.getEntityType() == EntityType.EGG || event.getEntityType() == EntityType.SNOWBALL) {
            event.setCancelled(true);
        }
    }
}
