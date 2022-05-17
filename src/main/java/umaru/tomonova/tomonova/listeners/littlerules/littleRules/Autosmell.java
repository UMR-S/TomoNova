package umaru.tomonova.tomonova.listeners.littlerules.littleRules;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.inventory.ItemStack;
import umaru.tomonova.tomonova.lang.Lang;
import umaru.tomonova.tomonova.listeners.littlerules.LittleRule;

import java.util.Arrays;
import java.util.List;

public class Autosmell extends LittleRule {

    public Autosmell() {
        super("Autosmell", Arrays.asList(Lang.GUIS_PETITES_REGLES_AUTOSMELL.toString()), Material.IRON_INGOT);
    }

    @EventHandler
    public void onItemSpawn(final ItemSpawnEvent event) {
        final ItemStack is = event.getEntity().getItemStack();
        if (is == null) {
            return;
        }
        ItemStack replace = null;
        switch (is.getType()) {
            case RAW_IRON: {
                replace = new ItemStack(Material.IRON_INGOT);
                break;
            }
            case RAW_GOLD: {
                replace = new ItemStack(Material.GOLD_INGOT);
                break;
            }
            case RAW_COPPER: {
                replace = new ItemStack(Material.COPPER_INGOT);
            }
        }
        if (replace != null) {
            event.getEntity().setItemStack(replace);
        }
    }

    @EventHandler
    public void onEntityDeath(final EntityDeathEvent event) {
        final EntityType entity = event.getEntityType();
        final List<ItemStack> loots = (List<ItemStack>)event.getDrops();
        for (int i = loots.size() - 1; i >= 0; --i) {
            final ItemStack is = loots.get(i);
            if (is == null) {
                return;
            }
            switch (is.getType()) {
                case BEEF: {
                    loots.remove(i);
                    loots.add(new ItemStack(Material.COOKED_BEEF));
                    break;
                }
                case PORKCHOP: {
                    loots.remove(i);
                    loots.add(new ItemStack(Material.COOKED_PORKCHOP));
                    break;
                }
                case CHICKEN: {
                    loots.remove(i);
                    loots.add(new ItemStack(Material.COOKED_CHICKEN));
                    break;
                }
                case MUTTON: {
                    loots.remove(i);
                    loots.add(new ItemStack(Material.COOKED_MUTTON));
                    break;
                }
                case RABBIT: {
                    loots.remove(i);
                    loots.add(new ItemStack(Material.COOKED_RABBIT));
                    break;
                }
            }
        }
    }
}
