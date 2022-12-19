package umaru.tomonova.tomonova.gui.gamemodegui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionType;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.gui.Gui;
import umaru.tomonova.tomonova.gui.MainGui;
import umaru.tomonova.tomonova.lang.Lang;
import umaru.tomonova.tomonova.utils.gui.ItemsCreator;

import java.util.Arrays;

public class GamemodeGui extends Gui {
    public GamemodeGui(Player player) {
        super(player, 9, Lang.GUIS_GAMEMODE_NAME.toString());
        ItemsCreator ic = new ItemsCreator(Material.DIAMOND_SWORD, ChatColor.RED + Lang.GUIS_GM_UHC_NAME.toString(), Arrays.asList(Lang.GUIS_GM_UHC_LORE.toString()));
        if (TomoNova.getPlugin().gameManager.isUhc()) {
            this.inventory.setItem(7, ItemsCreator.create(ic));
        }
        this.inventory.setItem(0, ItemsCreator.create(ic));
        ic = new ItemsCreator(Material.LEVER, ChatColor.RED + Lang.GUIS_GM_SWITCH_NAME.toString(), Arrays.asList(Lang.GUIS_GM_SWITCH_LORE.toString()));
        if (TomoNova.getPlugin().gameManager.isSwitch()) {
            this.inventory.setItem(7, ItemsCreator.create(ic));
        }
        this.inventory.setItem(1, ItemsCreator.create(ic));
        ic = new ItemsCreator(Material.DIAMOND_SHOVEL, ChatColor.RED + Lang.GUIS_GM_TAUPE_NAME.toString(), Arrays.asList(Lang.GUIS_GM_TAUPE_LORE.toString()));
        if (TomoNova.getPlugin().gameManager.isTaupe()) {
            this.inventory.setItem(7, ItemsCreator.create(ic));
        }
        this.inventory.setItem(2, ItemsCreator.create(ic));
        ic = new ItemsCreator(Material.POTION, ChatColor.RED + Lang.GUIS_GM_FSM_NAME.toString(), Arrays.asList(Lang.GUIS_GM_FSM_LORE.toString()));
        if (TomoNova.getPlugin().gameManager.isScarletMansion()) {
            this.inventory.setItem(7, ItemsCreator.create(ic));
        }
        this.inventory.setItem(3, ItemsCreator.create(ic, PotionType.INSTANT_HEAL));
        ic = new ItemsCreator(Material.MOSSY_COBBLESTONE, ChatColor.RED + Lang.GUIS_GM_TLV_NAME.toString(), Arrays.asList(Lang.GUIS_GM_TLV_LORE.toString()));
        if (TomoNova.getPlugin().gameManager.isTomoLostVillage()) {
            this.inventory.setItem(7, ItemsCreator.create(ic));
        }
        this.inventory.setItem(4, ItemsCreator.create(ic));
        ic = new ItemsCreator(Material.HEART_OF_THE_SEA, ChatColor.RED + Lang.GUIS_GM_BLEACHUHC_NAME.toString(), Arrays.asList(Lang.GUIS_GM_BLEACH_LORE.toString()));
        if (TomoNova.getPlugin().gameManager.isBleachUhc()) {
            this.inventory.setItem(7, ItemsCreator.create(ic));
        }
        this.inventory.setItem(5, ItemsCreator.create(ic));
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
                //UHC
                case DIAMOND_SWORD: {
                    TomoNova.getPlugin().gameManager.setUhc(true);
                    TomoNova.getPlugin().gameManager.setSwitch(false);
                    TomoNova.getPlugin().gameManager.setTaupe(false);
                    TomoNova.getPlugin().gameManager.setScarletMansion(false);
                    TomoNova.getPlugin().gameManager.setTomoLostVillage(false);
                    TomoNova.getPlugin().gameManager.setBleachUhc(false);
                    event.getWhoClicked().closeInventory();
                    new GamemodeGui(this.player).show();
                    break;
                }
                //Switch
                case LEVER: {
                    TomoNova.getPlugin().gameManager.setUhc(false);
                    TomoNova.getPlugin().gameManager.setSwitch(true);
                    TomoNova.getPlugin().gameManager.setTaupe(false);
                    TomoNova.getPlugin().gameManager.setScarletMansion(false);
                    TomoNova.getPlugin().gameManager.setTomoLostVillage(false);
                    TomoNova.getPlugin().gameManager.setBleachUhc(false);
                    event.getWhoClicked().closeInventory();
                    new GamemodeGui(this.player).show();
                    break;
                }
                //Taupe gun
                case DIAMOND_SHOVEL: {
                    TomoNova.getPlugin().gameManager.setUhc(false);
                    TomoNova.getPlugin().gameManager.setSwitch(false);
                    TomoNova.getPlugin().gameManager.setTaupe(true);
                    TomoNova.getPlugin().gameManager.setScarletMansion(false);
                    TomoNova.getPlugin().gameManager.setTomoLostVillage(false);
                    TomoNova.getPlugin().gameManager.setBleachUhc(false);
                    event.getWhoClicked().closeInventory();
                    new GamemodeGui(this.player).show();
                    break;
                }
                //Scarlett Mansion
                case POTION: {
                    TomoNova.getPlugin().gameManager.setUhc(false);
                    TomoNova.getPlugin().gameManager.setSwitch(false);
                    TomoNova.getPlugin().gameManager.setTaupe(false);
                    TomoNova.getPlugin().gameManager.setScarletMansion(true);
                    TomoNova.getPlugin().gameManager.setTomoLostVillage(false);
                    TomoNova.getPlugin().gameManager.setBleachUhc(false);
                    event.getWhoClicked().closeInventory();
                    new GamemodeGui(this.player).show();
                    break;
                }
                //Tomo lost village
                case MOSSY_COBBLESTONE: {
                    TomoNova.getPlugin().tomoLostVillage.tomoLostVillageSettings();
                    event.getWhoClicked().closeInventory();
                    new GamemodeGui(this.player).show();
                    break;
                }
                //BleachUHC
                case HEART_OF_THE_SEA: {
                    TomoNova.getPlugin().bleachUHC.bleachUhcSettings();
                    event.getWhoClicked().closeInventory();
                    new GamemodeGui(this.player).show();
                    break;
                }
                //Return
                case BARRIER: {
                    event.getWhoClicked().closeInventory();
                    new MainGui(this.player).show();
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
