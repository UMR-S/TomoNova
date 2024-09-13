package umaru.tomonova.tomonova.listeners.bleachUHC;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.utils.constants.BleachUHCConstants;

public class BannedItemForClasses implements Listener {
    //Item Interdit par classes

    private final TomoNova tomoNova = TomoNova.getPlugin();

    @EventHandler
    public void PlayerPickEvent(EntityPickupItemEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = ((Player) event.getEntity()).getPlayer();
            //Shinigami
            if (tomoNova.classesUtils.isPlayerShinigami(player.getName())) {
                if (isItemBannedForShinigami(event.getItem().getItemStack().getType())) {
                    event.setCancelled(true);
                }
            }
            //Quincy
            if (tomoNova.classesUtils.isPlayerQuincy(player.getName())) {
                if (isItemBannedForQuincy(event.getItem().getItemStack().getType())) {
                    event.setCancelled(true);
                }
            }
            //Shun shun rika
            if (tomoNova.classesUtils.isPlayerSSR(player.getName())) {
                if (isItemBannedforSSR(event.getItem().getItemStack().getType())) {
                    event.setCancelled(true);
                }
            }
            //Brazo
            if (TomoNova.getPlugin().classesUtils.isPlayerBrazo(player.getName())) {
                if (isItemBannedForBrazo(event.getItem().getItemStack().getType())) {
                    event.setCancelled(true);
                }
            }
        }
    }

    //Impossibilité de jeter le Hogyoku
    @EventHandler
    public void PlayerDropEvent(PlayerDropItemEvent event) {

        if (event.getItemDrop().getItemStack().hasItemMeta()) {
            if (event.getItemDrop().getItemStack().getItemMeta().hasCustomModelData()) {
                if (event.getItemDrop().getItemStack().getItemMeta().getCustomModelData() == BleachUHCConstants.HOGYOKU_INACTIF
                        || event.getItemDrop().getItemStack().getItemMeta().getCustomModelData() == BleachUHCConstants.HOGYOKU_ACTIF) {
                    event.setCancelled(true);
                }
            }
        }
    }

    // Handle InventoryClickEvent
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        ItemStack currentItem = event.getCurrentItem();
        ItemStack cursorItem = event.getCursor();
        Player player = (Player) event.getWhoClicked();
        assert currentItem != null;
        // Check if currentItem is banned
        if (currentItem.getType() != Material.AIR) {
            if (isItemBannedForPlayer(player, currentItem.getType())) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.RED + "You cannot use this item.");
            }
        }

        // Block Shift+Click
        if (event.getClick() == ClickType.SHIFT_LEFT || event.getClick() == ClickType.SHIFT_RIGHT) {
            if (currentItem.hasItemMeta()) {
                if (currentItem.getItemMeta().hasCustomModelData()) {
                    if (currentItem.getItemMeta().getCustomModelData() == BleachUHCConstants.HOGYOKU_INACTIF
                            || currentItem.getItemMeta().getCustomModelData() == BleachUHCConstants.HOGYOKU_ACTIF) {
                        if (isContainer(event.getInventory())) {
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }
        assert cursorItem != null;
        if (cursorItem.getType() != Material.AIR) {
            if (isItemBannedForPlayer(player, cursorItem.getType())) {
                event.setCancelled(true);
            }
        }
        if (cursorItem.hasItemMeta()) {
            if (cursorItem.getItemMeta().hasCustomModelData()) {
                if (cursorItem.getItemMeta().getCustomModelData() == BleachUHCConstants.HOGYOKU_INACTIF
                        || cursorItem.getItemMeta().getCustomModelData() == BleachUHCConstants.HOGYOKU_ACTIF) {
                    if (isContainer(event.getInventory())) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    // Handle InventoryDragEvent
    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        Player player = (Player) event.getWhoClicked();

        for (ItemStack item : event.getNewItems().values()) {
            if (item.getType() != Material.AIR) {
                if (isItemBannedForPlayer(player, item.getType())) {
                    event.setCancelled(true);
                    break;
                }
            }

            if (item.hasItemMeta()) {
                if (item.getItemMeta().hasCustomModelData()) {
                    if (item.getItemMeta().getCustomModelData() == BleachUHCConstants.HOGYOKU_INACTIF
                            || item.getItemMeta().getCustomModelData() == BleachUHCConstants.HOGYOKU_ACTIF) {
                        if (isContainer(event.getInventory())) {
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }
    }

    private boolean isItemBannedForPlayer(Player player, Material item) {
        // Check for Shinigami class
        if (tomoNova.classesUtils.isPlayerShinigami(player.getName())) {
            return isItemBannedForShinigami(item);
        }
        // Check for Quincy class
        if (tomoNova.classesUtils.isPlayerQuincy(player.getName())) {
            return isItemBannedForQuincy(item);
        }
        // Check for Shun Shun Rika class
        if (tomoNova.classesUtils.isPlayerSSR(player.getName())) {
            return isItemBannedforSSR(item);
        }
        // Check for Brazo class
        if (tomoNova.classesUtils.isPlayerBrazo(player.getName())) {
            return isItemBannedForBrazo(item);
        }
        return false;
    }


    private boolean isItemBannedForQuincy(Material item) {
        if (item.equals(Material.WOODEN_SWORD)
                || item.equals(Material.STONE_SWORD)
                || item.equals(Material.IRON_SWORD)
                || item.equals(Material.GOLDEN_SWORD)
                || item.equals(Material.DIAMOND_SWORD)
                || item.equals(Material.NETHERITE_SWORD)
                || item.equals(Material.CROSSBOW)
                || item.equals(Material.SHIELD)) {
            return true;
        }
        return false;
    }

    private boolean isItemBannedForBrazo(Material item) {
        if (item.equals(Material.WOODEN_SWORD)
                || item.equals(Material.STONE_SWORD)
                || item.equals(Material.IRON_SWORD)
                || item.equals(Material.GOLDEN_SWORD)
                || item.equals(Material.DIAMOND_SWORD)
                || item.equals(Material.NETHERITE_SWORD)
                || item.equals(Material.CROSSBOW)
                || item.equals(Material.BOW)) {
            return true;
        }
        return false;
    }

    private boolean isItemBannedforSSR(Material item) {
        if (item.equals(Material.WOODEN_SWORD)
                || item.equals(Material.STONE_SWORD)
                || item.equals(Material.IRON_SWORD)
                || item.equals(Material.GOLDEN_SWORD)
                || item.equals(Material.DIAMOND_SWORD)
                || item.equals(Material.NETHERITE_SWORD)
                || item.equals(Material.CROSSBOW)
                || item.equals(Material.BOW)
                || item.equals(Material.SHIELD)) {
            return true;
        }
        return false;
    }

    private boolean isItemBannedForShinigami(Material item) {
        if (item.equals(Material.BOW)
                || item.equals(Material.CROSSBOW)
                || item.equals(Material.SHIELD)) {
            return true;
        }
        return false;
    }

    private boolean isContainer(Inventory inventory) {
        switch (inventory.getType()) {
            case CHEST:
            case HOPPER:
            case SHULKER_BOX:
            case BARREL:
            case DISPENSER:
            case DROPPER:
                return true;
            default:
                return false;
        }
    }

}
