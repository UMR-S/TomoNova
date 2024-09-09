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

public class BossesGui extends Gui {
    public BossesGui(Player player) {
        super(player, 54, Lang.GUIS_BOSS_NAME.toString());
        ItemsCreator ic = new ItemsCreator(Material.ZOMBIE_HEAD, ChatColor.RED + Lang.GUIS_BOSS_YAMAMOTO_NAME.toString(), Arrays.asList(Lang.GUIS_BOSS_YAMAMOTO_LORE.toString()));
        if (TomoNova.getPlugin().bleachUHC.returnPlayerTargetName(player.getName()).equals(BleachUHCConstants.YAMAMOTO_NAME)) {
            this.inventory.setItem(40, ItemsCreator.create(ic, 6000001));
        }
        this.inventory.setItem(11, ItemsCreator.create(ic, 6000001));
        ic = new ItemsCreator(Material.ZOMBIE_HEAD, ChatColor.RED + Lang.GUIS_BOSS_SOI_FON_NAME.toString(), Arrays.asList(Lang.GUIS_BOSS_SOI_FON_LORE.toString()));
        if (TomoNova.getPlugin().bleachUHC.returnPlayerTargetName(player.getName()).equals(BleachUHCConstants.SOI_FON_NAME)) {
            this.inventory.setItem(40, ItemsCreator.create(ic, 6000002));
        }
        this.inventory.setItem(29, ItemsCreator.create(ic, 6000002));
        ic = new ItemsCreator(Material.ZOMBIE_HEAD, ChatColor.RED + Lang.GUIS_BOSS_GIN_NAME.toString(), Arrays.asList(Lang.GUIS_BOSS_GIN_LORE.toString()));
        if (TomoNova.getPlugin().bleachUHC.returnPlayerTargetName(player.getName()).equals(BleachUHCConstants.GIN_NAME)) {
            this.inventory.setItem(40, ItemsCreator.create(ic, 6000003));
        }
        this.inventory.setItem(13, ItemsCreator.create(ic, 6000003));
        ic = new ItemsCreator(Material.ZOMBIE_HEAD, ChatColor.RED + Lang.GUIS_BOSS_UNOHANA_NAME.toString(), Arrays.asList(Lang.GUIS_BOSS_UNOHANA_LORE.toString()));
        if (TomoNova.getPlugin().bleachUHC.returnPlayerTargetName(player.getName()).equals(BleachUHCConstants.UNOHANA_NAME)) {
            this.inventory.setItem(40, ItemsCreator.create(ic, 6000004));
        }
        this.inventory.setItem(14, ItemsCreator.create(ic, 6000004));
        ic = new ItemsCreator(Material.ZOMBIE_HEAD, ChatColor.RED + Lang.GUIS_BOSS_AIZEN_V1_NAME.toString(), Arrays.asList(Lang.GUIS_BOSS_AIZEN_V1_LORE.toString()));
        if (TomoNova.getPlugin().bleachUHC.returnPlayerTargetName(player.getName()).equals(BleachUHCConstants.AIZEN_V1_NAME)) {
            this.inventory.setItem(40, ItemsCreator.create(ic, 6000005));
        }
        this.inventory.setItem(15, ItemsCreator.create(ic, 6000005));
        ic = new ItemsCreator(Material.ZOMBIE_HEAD, ChatColor.RED + Lang.GUIS_BOSS_AIZEN_V2_NAME.toString(), Arrays.asList(Lang.GUIS_BOSS_AIZEN_V2_LORE.toString()));
        if (TomoNova.getPlugin().bleachUHC.returnPlayerTargetName(player.getName()).equals(BleachUHCConstants.AIZEN_V2_NAME)) {
            this.inventory.setItem(40, ItemsCreator.create(ic, 6000006));
        }
        this.inventory.setItem(20, ItemsCreator.create(ic, 6000006));
        ic = new ItemsCreator(Material.ZOMBIE_HEAD, ChatColor.RED + Lang.GUIS_BOSS_BYAKUYA_NAME.toString(), Arrays.asList(Lang.GUIS_BOSS_BYAKUYA_LORE.toString()));
        if (TomoNova.getPlugin().bleachUHC.returnPlayerTargetName(player.getName()).equals(BleachUHCConstants.BYAKUYA_NAME)) {
            this.inventory.setItem(40, ItemsCreator.create(ic, 6000007));
        }
        this.inventory.setItem(21, ItemsCreator.create(ic, 6000007));
        ic = new ItemsCreator(Material.ZOMBIE_HEAD, ChatColor.RED + Lang.GUIS_BOSS_KOMAMURA_NAME.toString(), Arrays.asList(Lang.GUIS_BOSS_KOMAMURA_LORE.toString()));
        if (TomoNova.getPlugin().bleachUHC.returnPlayerTargetName(player.getName()).equals(BleachUHCConstants.KOMAMURA_NAME)) {
            this.inventory.setItem(40, ItemsCreator.create(ic, 6000008));
        }
        this.inventory.setItem(22, ItemsCreator.create(ic, 6000008));
        ic = new ItemsCreator(Material.ZOMBIE_HEAD, ChatColor.RED + Lang.GUIS_BOSS_KYORAKU_NAME.toString(), Arrays.asList(Lang.GUIS_BOSS_KYORAKU_LORE.toString()));
        if (TomoNova.getPlugin().bleachUHC.returnPlayerTargetName(player.getName()).equals(BleachUHCConstants.KYORAKU_NAME)) {
            this.inventory.setItem(40, ItemsCreator.create(ic, 6000009));
        }
        this.inventory.setItem(23, ItemsCreator.create(ic, 6000009));
        ic = new ItemsCreator(Material.ZOMBIE_HEAD, ChatColor.RED + Lang.GUIS_BOSS_TOSEN_NAME.toString(), Arrays.asList(Lang.GUIS_BOSS_TOSEN_LORE.toString()));
        if (TomoNova.getPlugin().bleachUHC.returnPlayerTargetName(player.getName()).equals(BleachUHCConstants.TOSEN_NAME)) {
            this.inventory.setItem(40, ItemsCreator.create(ic, 6000010));
        }
        this.inventory.setItem(24, ItemsCreator.create(ic, 6000010));
        ic = new ItemsCreator(Material.ZOMBIE_HEAD, ChatColor.RED + Lang.GUIS_BOSS_TOSHIRO_NAME.toString(), Arrays.asList(Lang.GUIS_BOSS_TOSHIRO_LORE.toString()));
        if (TomoNova.getPlugin().bleachUHC.returnPlayerTargetName(player.getName()).equals(BleachUHCConstants.TOSHIRO_NAME)) {
            this.inventory.setItem(40, ItemsCreator.create(ic, 6000011));
        }
        this.inventory.setItem(12, ItemsCreator.create(ic, 6000011));
        ic = new ItemsCreator(Material.ZOMBIE_HEAD, ChatColor.RED + Lang.GUIS_BOSS_KENPACHI_NAME.toString(), Arrays.asList(Lang.GUIS_BOSS_KENPACHI_LORE.toString()));
        if (TomoNova.getPlugin().bleachUHC.returnPlayerTargetName(player.getName()).equals(BleachUHCConstants.KENPACHI_NAME)) {
            this.inventory.setItem(40, ItemsCreator.create(ic, 6000012));
        }
        this.inventory.setItem(30, ItemsCreator.create(ic, 6000012));
        ic = new ItemsCreator(Material.ZOMBIE_HEAD, ChatColor.RED + Lang.GUIS_BOSS_MAYURI_NAME.toString(), Arrays.asList(Lang.GUIS_BOSS_MAYURI_LORE.toString()));
        if (TomoNova.getPlugin().bleachUHC.returnPlayerTargetName(player.getName()).equals(BleachUHCConstants.MAYURI_NAME)) {
            this.inventory.setItem(40, ItemsCreator.create(ic, 6000013));
        }
        this.inventory.setItem(31, ItemsCreator.create(ic, 6000013));
        ic = new ItemsCreator(Material.ZOMBIE_HEAD, ChatColor.RED + Lang.GUIS_BOSS_UKITAKE_NAME.toString(), Arrays.asList(Lang.GUIS_BOSS_UKITAKE_LORE.toString()));
        if (TomoNova.getPlugin().bleachUHC.returnPlayerTargetName(player.getName()).equals(BleachUHCConstants.UKITAKE_NAME)) {
            this.inventory.setItem(40, ItemsCreator.create(ic, 6000014));
        }
        this.inventory.setItem(32, ItemsCreator.create(ic, 6000014));
        //Retour
        ic = new ItemsCreator(Material.BARRIER, Lang.GUIS_BACK.toString(), null);
        this.inventory.setItem(53, ItemsCreator.create(ic));
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
            if (is.getItemMeta().hasCustomModelData()) {
                switch (is.getItemMeta().getCustomModelData()) {
                    case 6000001: {
                        TomoNova.getPlugin().bleachUHC.playersBossTarget.put(event.getWhoClicked().getName(),BleachUHCConstants.YAMAMOTO_NAME);
                        event.getWhoClicked().closeInventory();
                        new BossesGui(this.player).show();
                        break;
                    }
                    case 6000002: {
                        TomoNova.getPlugin().bleachUHC.playersBossTarget.put(event.getWhoClicked().getName(),BleachUHCConstants.SOI_FON_NAME);
                        event.getWhoClicked().closeInventory();
                        new BossesGui(this.player).show();
                        break;
                    }
                    case 6000003: {
                        TomoNova.getPlugin().bleachUHC.playersBossTarget.put(event.getWhoClicked().getName(),BleachUHCConstants.GIN_NAME);
                        event.getWhoClicked().closeInventory();
                        new BossesGui(this.player).show();
                        break;
                    }
                    case 6000004: {
                        TomoNova.getPlugin().bleachUHC.playersBossTarget.put(event.getWhoClicked().getName(),BleachUHCConstants.UNOHANA_NAME);
                        event.getWhoClicked().closeInventory();
                        new BossesGui(this.player).show();
                        break;
                    }
                    case 6000005: {
                        TomoNova.getPlugin().bleachUHC.playersBossTarget.put(event.getWhoClicked().getName(),BleachUHCConstants.AIZEN_V1_NAME);
                        event.getWhoClicked().closeInventory();
                        new BossesGui(this.player).show();
                        break;
                    }
                    case 6000006: {
                        TomoNova.getPlugin().bleachUHC.playersBossTarget.put(event.getWhoClicked().getName(),BleachUHCConstants.AIZEN_V2_NAME);
                        event.getWhoClicked().closeInventory();
                        new BossesGui(this.player).show();
                        break;
                    }
                    case 6000007: {
                        TomoNova.getPlugin().bleachUHC.playersBossTarget.put(event.getWhoClicked().getName(),BleachUHCConstants.BYAKUYA_NAME);
                        event.getWhoClicked().closeInventory();
                        new BossesGui(this.player).show();
                        break;
                    }
                    case 6000008: {
                        TomoNova.getPlugin().bleachUHC.playersBossTarget.put(event.getWhoClicked().getName(),BleachUHCConstants.KOMAMURA_NAME);
                        event.getWhoClicked().closeInventory();
                        new BossesGui(this.player).show();
                        break;
                    }
                    case 6000009: {
                        TomoNova.getPlugin().bleachUHC.playersBossTarget.put(event.getWhoClicked().getName(),BleachUHCConstants.KYORAKU_NAME);
                        event.getWhoClicked().closeInventory();
                        new BossesGui(this.player).show();
                        break;
                    }
                    case 6000010: {
                        TomoNova.getPlugin().bleachUHC.playersBossTarget.put(event.getWhoClicked().getName(),BleachUHCConstants.TOSEN_NAME);
                        event.getWhoClicked().closeInventory();
                        new BossesGui(this.player).show();
                        break;
                    }
                    case 6000011: {
                        TomoNova.getPlugin().bleachUHC.playersBossTarget.put(event.getWhoClicked().getName(),BleachUHCConstants.TOSHIRO_NAME);
                        event.getWhoClicked().closeInventory();
                        new BossesGui(this.player).show();
                        break;
                    }
                    case 6000012: {
                        TomoNova.getPlugin().bleachUHC.playersBossTarget.put(event.getWhoClicked().getName(),BleachUHCConstants.KENPACHI_NAME);
                        event.getWhoClicked().closeInventory();
                        new BossesGui(this.player).show();
                        break;
                    }
                    case 6000013: {
                        TomoNova.getPlugin().bleachUHC.playersBossTarget.put(event.getWhoClicked().getName(),BleachUHCConstants.MAYURI_NAME);
                        event.getWhoClicked().closeInventory();
                        new BossesGui(this.player).show();
                        break;
                    }
                    case 6000014: {
                        TomoNova.getPlugin().bleachUHC.playersBossTarget.put(event.getWhoClicked().getName(),BleachUHCConstants.UKITAKE_NAME);
                        event.getWhoClicked().closeInventory();
                        new BossesGui(this.player).show();
                        break;
                    }
                }
            }
            else {
                event.getWhoClicked().closeInventory();
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
