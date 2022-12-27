package umaru.tomonova.tomonova.gui.classesgui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.gui.Gui;
import umaru.tomonova.tomonova.lang.Lang;
import umaru.tomonova.tomonova.utils.gui.ItemsCreator;

import java.util.Arrays;

public class ClassesGui extends Gui {
    public ClassesGui(Player player) {
        super(player, 9, ChatColor.RED + Lang.GUIS_CLASSES_NAME.toString());
        ItemsCreator ic = new ItemsCreator(Material.IRON_SWORD, ChatColor.RED + Lang.GUIS_CLASSES_SHINIGAMI_NAME.toString(), Arrays.asList(Lang.GUIS_CLASSES_SHINIGAMI_LORE.toString()));
        if (TomoNova.getPlugin().classesUtils.isPlayerClasse(player.getName(), "shinigami")) {
            this.inventory.setItem(7, ItemsCreator.create(ic,1000101));
        }
        this.inventory.setItem(0, ItemsCreator.create(ic,1000101));
        ic = new ItemsCreator(Material.BOW, ChatColor.RED + Lang.GUIS_CLASSES_QUINCY_NAME.toString(), Arrays.asList(Lang.GUIS_CLASSES_QUINCY_LORE.toString(), Lang.GUIS_CLASSES_QUINCY_LORE_TWO.toString()));
        if (TomoNova.getPlugin().classesUtils.isPlayerClasse(player.getName(), "quincy")) {
            this.inventory.setItem(7, ItemsCreator.create(ic,2000201));
        }
        this.inventory.setItem(1, ItemsCreator.create(ic,2000201));
        ic = new ItemsCreator(Material.CARROT_ON_A_STICK, ChatColor.RED + Lang.GUIS_CLASSES_SSR_NAME.toString(), Arrays.asList(Lang.GUIS_CLASSES_SSR_LORE.toString()));
        if (TomoNova.getPlugin().classesUtils.isPlayerClasse(player.getName(), "ssr")) {
            this.inventory.setItem(7, ItemsCreator.create(ic,3000301));
        }
        this.inventory.setItem(2, ItemsCreator.create(ic,3000301));
        ic = new ItemsCreator(Material.SHIELD, ChatColor.RED + Lang.GUIS_BRAZO_NAME.toString(), Arrays.asList(Lang.GUIS_CLASSES_BRAZO_LORE.toString()));
        if (TomoNova.getPlugin().classesUtils.isPlayerClasse(player.getName(), "brazo")) {
            this.inventory.setItem(7, ItemsCreator.create(ic,4000401));
        }
        this.inventory.setItem(3, ItemsCreator.create(ic,4000401));
        ic = new ItemsCreator(Material.BARRIER, Lang.GUIS_BACK.toString(), null);
        this.inventory.setItem(8, ItemsCreator.create(ic));
    }

    @EventHandler
    public void onClick(final InventoryClickEvent event) {
        if (event.getClickedInventory() == null) {
            return;
        }
        if (event.getClickedInventory().equals(this.inventory)) {
            final ItemStack is = event.getCurrentItem();
            if (is == null || is.getType() == Material.AIR) {
                return;
            }
            event.setCancelled(true);
            switch (is.getType()) {
                //Shinigami
                case IRON_SWORD: {
                    TomoNova.getPlugin().classesUtils.addPlayerToClassesMap(event.getWhoClicked().getName(),"shinigami");
                    event.getWhoClicked().closeInventory();
                    new ClassesGui(this.player).show();
                    break;
                }
                //Quincy
                case BOW: {
                    TomoNova.getPlugin().classesUtils.addPlayerToClassesMap(event.getWhoClicked().getName(),"quincy");
                    event.getWhoClicked().closeInventory();
                    new ClassesGui(this.player).show();
                    break;
                }
                //Shun Shun Rika
                case CARROT_ON_A_STICK: {
                    TomoNova.getPlugin().classesUtils.addPlayerToClassesMap(event.getWhoClicked().getName(),"ssr");
                    event.getWhoClicked().closeInventory();
                    new ClassesGui(this.player).show();
                    break;
                }
                //Brazo
                case SHIELD: {
                    TomoNova.getPlugin().classesUtils.addPlayerToClassesMap(event.getWhoClicked().getName(),"brazo");
                    event.getWhoClicked().closeInventory();
                    new ClassesGui(this.player).show();
                    break;
                }
                //Return
                case BARRIER: {
                    event.getWhoClicked().closeInventory();
                    break;
                }
            }
        }
    }

    @EventHandler
    public void onClick(final InventoryCloseEvent event) {
        if (event.getInventory().equals(this.inventory)) {
            HandlerList.unregisterAll((Listener) this);
        }
    }
}
