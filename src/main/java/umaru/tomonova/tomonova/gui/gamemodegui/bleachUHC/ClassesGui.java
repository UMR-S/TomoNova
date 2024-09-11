package umaru.tomonova.tomonova.gui.gamemodegui.bleachUHC;

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
import umaru.tomonova.tomonova.utils.constants.BleachUHCConstants;
import umaru.tomonova.tomonova.utils.gui.ItemsCreator;

import java.util.Arrays;
import java.util.List;

public class ClassesGui extends Gui {
    public ClassesGui(Player player) {
        super(player, 9, ChatColor.RED + Lang.GUIS_CLASSES_NAME.toString());
        ItemsCreator ic = new ItemsCreator(Material.IRON_SWORD, ChatColor.RED + Lang.GUIS_CLASSES_SHINIGAMI_NAME.toString(), Arrays.asList(Lang.GUIS_CLASSES_SHINIGAMI_LORE.toString()));
        if (TomoNova.getPlugin().classesUtils.isPlayerShinigami(player.getName())) {
            this.inventory.setItem(7, ItemsCreator.create(ic, BleachUHCConstants.ZANPAKUTO));
        }
        this.inventory.setItem(0, ItemsCreator.create(ic, BleachUHCConstants.ZANPAKUTO));

        ic = new ItemsCreator(Material.BOW, ChatColor.RED + Lang.GUIS_CLASSES_QUINCY_NAME.toString(), Arrays.asList(Lang.GUIS_CLASSES_QUINCY_LORE.toString(), Lang.GUIS_CLASSES_QUINCY_LORE_TWO.toString()));
        if (TomoNova.getPlugin().classesUtils.isPlayerQuincy(player.getName())) {
            this.inventory.setItem(7, ItemsCreator.create(ic, BleachUHCConstants.ARC_QUINCY));
        }
        this.inventory.setItem(1, ItemsCreator.create(ic, BleachUHCConstants.ARC_QUINCY));

        ic = new ItemsCreator(Material.CARROT_ON_A_STICK, ChatColor.RED + Lang.GUIS_CLASSES_SSR_NAME.toString(), Arrays.asList(Lang.GUIS_CLASSES_SSR_LORE.toString()));
        if (TomoNova.getPlugin().classesUtils.isPlayerSSR(player.getName())) {
            this.inventory.setItem(7, ItemsCreator.create(ic, BleachUHCConstants.CIEL_UNIQUE));
        }
        this.inventory.setItem(2, ItemsCreator.create(ic, BleachUHCConstants.CIEL_UNIQUE));

        ic = new ItemsCreator(Material.SHIELD, ChatColor.RED + Lang.GUIS_BRAZO_NAME.toString(), Arrays.asList(Lang.GUIS_CLASSES_BRAZO_LORE.toString()));
        if (TomoNova.getPlugin().classesUtils.isPlayerBrazo(player.getName())) {
            this.inventory.setItem(7, ItemsCreator.create(ic, BleachUHCConstants.BOUCLIER_BRAZO));
        }
        this.inventory.setItem(3, ItemsCreator.create(ic, BleachUHCConstants.BOUCLIER_BRAZO));

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
            Player player = (Player) event.getWhoClicked();
            switch (is.getType()) {
                // Shinigami class selection
                case IRON_SWORD: {
                    TomoNova.getPlugin().classesUtils.addPlayerToClassesMap(player.getName(), BleachUHCConstants.SHINIGAMI);
                    player.closeInventory();
                    new ClassesGui(this.player).show();
                    break;
                }
                // Quincy class selection
                case BOW: {
                    if (isClassAvailableForTeam(player, BleachUHCConstants.QUINCY)) {
                        TomoNova.getPlugin().classesUtils.addPlayerToClassesMap(player.getName(), BleachUHCConstants.QUINCY);
                        player.closeInventory();
                        new ClassesGui(this.player).show();
                    } else {
                        player.sendMessage(ChatColor.RED + "The Quincy class is already taken by a teammate.");
                    }
                    break;
                }
                // Shun Shun Rika class selection
                case CARROT_ON_A_STICK: {
                    if (isClassAvailableForTeam(player, BleachUHCConstants.SHUN_SHUN_RIKA)) {
                        TomoNova.getPlugin().classesUtils.addPlayerToClassesMap(player.getName(), BleachUHCConstants.SHUN_SHUN_RIKA);
                        player.closeInventory();
                        new ClassesGui(this.player).show();
                    } else {
                        player.sendMessage(ChatColor.RED + "The Shun Shun Rika class is already taken by a teammate.");
                    }
                    break;
                }
                // Brazo class selection
                case SHIELD: {
                    if (isClassAvailableForTeam(player, BleachUHCConstants.BRAZO)) {
                        TomoNova.getPlugin().classesUtils.addPlayerToClassesMap(player.getName(), BleachUHCConstants.BRAZO);
                        player.closeInventory();
                        new ClassesGui(this.player).show();
                    } else {
                        player.sendMessage(ChatColor.RED + "The Brazo class is already taken by a teammate.");
                    }
                    break;
                }
                // Return
                case BARRIER: {
                    player.closeInventory();
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

    private boolean isClassAvailableForTeam(Player player, String className) {
        List<Player> onlinePlayers = (List<Player>) TomoNova.getPlugin().getServer().getOnlinePlayers();

        for (Player teammate : onlinePlayers) {
            if (TomoNova.getPlugin().teamUtils.arePlayersOnSameTeam(player.getName(), teammate.getName())) {
                if (className.equals(BleachUHCConstants.QUINCY) && TomoNova.getPlugin().classesUtils.isPlayerQuincy(teammate.getName())) {
                    return false;
                }
                if (className.equals(BleachUHCConstants.SHUN_SHUN_RIKA) && TomoNova.getPlugin().classesUtils.isPlayerSSR(teammate.getName())) {
                    return false;
                }
                if (className.equals(BleachUHCConstants.BRAZO) && TomoNova.getPlugin().classesUtils.isPlayerBrazo(teammate.getName())) {
                    return false;
                }
            }
        }
        return true;
    }
}
