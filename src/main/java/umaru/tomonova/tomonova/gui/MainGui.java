package umaru.tomonova.tomonova.gui;

import org.bukkit.entity.*;

import org.bukkit.*;

import java.util.*;

import org.bukkit.inventory.*;

import org.bukkit.event.inventory.*;
import org.bukkit.event.*;
import umaru.tomonova.tomonova.gui.bordergui.BorderGui;
import umaru.tomonova.tomonova.gui.gamemodegui.GamemodeGui;
import umaru.tomonova.tomonova.gui.itemsgui.CustomInventoryGui;
import umaru.tomonova.tomonova.gui.netherGui.NetherGui;
import umaru.tomonova.tomonova.gui.teamsgui.ToGui;
import umaru.tomonova.tomonova.gui.timergui.MaxPlayersGui;
import umaru.tomonova.tomonova.gui.timergui.MinPlayersGui;
import umaru.tomonova.tomonova.gui.timergui.PvPTimeGui;
import umaru.tomonova.tomonova.gui.timergui.SuddenDeathTimeGui;
import umaru.tomonova.tomonova.lang.Lang;
import umaru.tomonova.tomonova.utils.gui.ItemsCreator;

public class MainGui extends Gui {
    public MainGui(final Player player) {
        super(player, 54, Lang.GUIS_MAIN_NAME.toString());

        //Relatifs aux configurations de base

        ItemsCreator ic = new ItemsCreator(Material.MINECART,ChatColor.LIGHT_PURPLE  + Lang.GUIS_MAIN_MAX_PLAYERS.toString(), Arrays.asList(Lang.GUIS_MAIN_MAX_PLAYERS_LORE.toString()));
        MainGui.inventory.setItem(0,ItemsCreator.create(ic));

        ic = new ItemsCreator(Material.OAK_BOAT,ChatColor.LIGHT_PURPLE  + Lang.GUIS_MAIN_MIN_PLAYERS.toString(), Arrays.asList(Lang.GUIS_MAIN_MIN_PLAYERS_LORE.toString(),Lang.GUIS_MAIN_MIN_PLAYERS_LORE1.toString()));
        MainGui.inventory.setItem(1,ItemsCreator.create(ic));

        ic = new ItemsCreator(Material.DIAMOND_SWORD,ChatColor.LIGHT_PURPLE  +Lang.GUIS_MAIN_PVP.toString(), Arrays.asList(Lang.GUIS_MAIN_PVP_LORE.toString()));
        MainGui.inventory.setItem(2,ItemsCreator.create(ic));

        ic = new ItemsCreator(Material.BONE,ChatColor.LIGHT_PURPLE  + Lang.GUIS_MAIN_SUDDEN_DEATH.toString(), Arrays.asList(Lang.GUIS_MAIN_SUDDEN_DEATH_LORE.toString()));
        MainGui.inventory.setItem(3,ItemsCreator.create(ic));

        ic = new ItemsCreator(Material.WHITE_STAINED_GLASS_PANE,ChatColor.LIGHT_PURPLE  + Lang.GUIS_MAIN_BORDER.toString(), Arrays.asList(Lang.GUIS_MAIN_BORDER_LORE.toString()));
        MainGui.inventory.setItem(4,ItemsCreator.create(ic));

        ic = new ItemsCreator(Material.WRITABLE_BOOK,ChatColor.LIGHT_PURPLE  + Lang.GUIS_MAIN_LITTLE_RULES.toString(), Arrays.asList(Lang.GUIS_MAIN_LITTLE_RULES_LORE.toString()));
        MainGui.inventory.setItem(5,ItemsCreator.create(ic));

        ic = new ItemsCreator(Material.CHEST,ChatColor.LIGHT_PURPLE  + Lang.GUIS_MAIN_START_STUFF.toString(), Arrays.asList(Lang.GUIS_MAIN_START_STUFF_LORE.toString()));
        MainGui.inventory.setItem(6,ItemsCreator.create(ic));

        //Relatifs au monde

        ic = new ItemsCreator(Material.OBSIDIAN,ChatColor.DARK_PURPLE + Lang.GUIS_MAIN_NETHER.toString(), Arrays.asList(Lang.GUIS_MAIN_NETHER_LORE.toString()));
        MainGui.inventory.setItem(9,ItemsCreator.create(ic));

        //Relatifs aux teams

        ic = new ItemsCreator(Material.BLAZE_ROD,ChatColor.GOLD + Lang.GUIS_MAIN_TEAMS.toString(), Arrays.asList(Lang.GUIS_MAIN_TEAMS_LORE.toString()));
        MainGui.inventory.setItem(18,ItemsCreator.create(ic));

        //Configuration du gamemode

        ic = new ItemsCreator(Material.REDSTONE,ChatColor.RED + Lang.GUIS_MAIN_GAMEMODE.toString(), Arrays.asList(Lang.GUIS_MAIN_GAMEMODE_LORE.toString()));
        MainGui.inventory.setItem(27,ItemsCreator.create(ic));

        //Configs

        ic = new ItemsCreator(Material.PAPER,ChatColor.GRAY + Lang.GUIS_MAIN_LOAD.toString(), Arrays.asList(Lang.GUIS_MAIN_LOAD_LORE.toString()));
        MainGui.inventory.setItem(36,ItemsCreator.create(ic));

        ic = new ItemsCreator(Material.ANVIL,ChatColor.GRAY + Lang.GUIS_MAIN_SAVE.toString(), Arrays.asList(Lang.GUIS_MAIN_SAVE_LORE.toString()));
        MainGui.inventory.setItem(37,ItemsCreator.create(ic));

        //Lancer ou fermer

        ic = new ItemsCreator(Material.BEACON, Lang.GUIS_MAIN_START.toString(), Arrays.asList(Lang.GUIS_MAIN_START_LORE.toString()));
        MainGui.inventory.setItem(45,ItemsCreator.create(ic));

        ic = new ItemsCreator(Material.BARRIER, Lang.GUIS_MAIN_CLOSE.toString(), Arrays.asList(""));
        MainGui.inventory.setItem(53,ItemsCreator.create(ic));

    }

    @EventHandler
    public void onClick(final InventoryClickEvent event) {
        if (event.getClickedInventory() == null) {
            return;
        }
        if (event.getClickedInventory().equals(MainGui.inventory)) {
            final ItemStack is = event.getCurrentItem();
            if (is == null || is.getType() == Material.AIR) {
                return;
            }
            event.setCancelled(true);
            switch (is.getType()) {
                case MINECART: {
                    this.player.closeInventory();
                    new MaxPlayersGui(this.player).show();
                    break;
                }
                case OAK_BOAT: {
                    this.player.closeInventory();
                    new MinPlayersGui(this.player).show();
                    break;
                }
                case DIAMOND_SWORD: {
                    this.player.closeInventory();
                    new PvPTimeGui(this.player).show();
                    break;
                }
                case WHITE_STAINED_GLASS_PANE: {
                    this.player.closeInventory();
                    new BorderGui(this.player).show();
                    break;
                }
                case CHEST: {
                    this.player.closeInventory();
                    new CustomInventoryGui(this.player).show();
                    break;
                }
                case WRITABLE_BOOK: {
                    this.player.closeInventory();
                    new LittleRules(this.player).show();
                    break;
                }
                case BLAZE_ROD: {
                    this.player.closeInventory();
                    new ToGui(this.player).show();
                    break;
                }
                case BONE: {
                    this.player.closeInventory();
                    new SuddenDeathTimeGui(this.player).show();
                    break;
                }
                case OBSIDIAN: {
                    this.player.closeInventory();
                    new NetherGui(this.player).show();
                    break;
                }
                case REDSTONE: {
                    this.player.closeInventory();
                    new GamemodeGui(this.player).show();
                    break;
                }
                case BEACON: {
                    this.player.closeInventory(); //Lancer le jeu
                    break;
                }
                case PAPER: {
                    this.player.closeInventory();
                    new LoadConfigsGui(this.player).show();
                    break;
                }
                case ANVIL: {
                    this.player.closeInventory();
                    //new ConfigRenameGui(this.player,); //à ajouter
                    break;
                }
                case BARRIER: {
                    event.getWhoClicked().closeInventory();
                    break;
                }
            }
        }
    }

    @EventHandler
    public void onClick(final InventoryCloseEvent event) {
        if (event.getInventory().equals(MainGui.inventory)) {
            HandlerList.unregisterAll((Listener) this);
        }
    }
}

