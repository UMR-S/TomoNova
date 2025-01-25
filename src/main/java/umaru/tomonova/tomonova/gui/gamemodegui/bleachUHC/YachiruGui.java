package umaru.tomonova.tomonova.gui.gamemodegui.bleachUHC;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.gui.Gui;
import umaru.tomonova.tomonova.lang.Lang;
import umaru.tomonova.tomonova.utils.gui.ItemsCreator;
import java.util.Arrays;

public class YachiruGui extends Gui {

    public YachiruGui(Player player) {
        super(player, 54, "Boutique de Yachiru : " + TomoNova.getPlugin().bleachUHC.getPlayerPoints(player.getName()));

        // Sharpness Books
        int[] sharpnessSlots = {0, 1, 2, 3, 4}; // Row 1
        populateEnchantments(sharpnessSlots, "Sharpness", YachiruPrices.values());

        // Power Books
        int[] powerSlots = {9, 10, 11, 12, 13}; // Row 2
        populateEnchantments(powerSlots, "Power", YachiruPrices.values());

        // Protection Books
        int[] protectionSlots = {18, 19, 20, 21}; // Row 3
        populateEnchantments(protectionSlots, "Protection", YachiruPrices.values());

        // Protection Projectile Books
        int[] protectionProjectileSlots = {27, 28, 29, 30}; // Row 4
        populateEnchantments(protectionProjectileSlots, "Projectile protection", YachiruPrices.values());

        // Blast protection Books
        int[] blastProtectionSlots = {36, 37, 38, 39}; // Row 5
        populateEnchantments(blastProtectionSlots, "Blast protection", YachiruPrices.values());

        // Fire protection Books
        int[] fireProtectionSlots = {45, 46, 47, 38}; // Row 6
        populateEnchantments(fireProtectionSlots, "Fire protection", YachiruPrices.values());

//        // Fire Aspect Books
//        int[] fireAspectSlots = {27, 28}; // Row 4
//        populateEnchantments(fireAspectSlots, "Fire Aspect", YachiruPrices.values());

        // Thorns Books
        int[] thornsSlots = {24, 25, 26}; // Row 3
        populateEnchantments(thornsSlots, "Thorns", YachiruPrices.values());

        // Back button at the bottom right
        ItemsCreator ic = new ItemsCreator(Material.BARRIER, Lang.GUIS_BACK.toString(), null);
        this.inventory.setItem(53, ItemsCreator.create(ic));
    }

    // Helper method to populate books for a specific enchantment group
    private void populateEnchantments(int[] slots, String enchantmentType, YachiruPrices[] books) {
        int index = 0;
        for (YachiruPrices enchantBook : books) {
            if (enchantBook.getName().contains(enchantmentType) && index < slots.length) {
                ItemStack book = enchantBook.getEnchantement();
                ItemMeta bookMeta = book.getItemMeta();
                bookMeta.setDisplayName(enchantBook.getName() + " : " + enchantBook.getPrice());
                bookMeta.setLore(Arrays.asList("§7Prix: §6" + enchantBook.getPrice() + " points"));
                book.setItemMeta(bookMeta);
                this.inventory.setItem(slots[index], book);
                index++;
            }
        }
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
            if (is.getType() == Material.BARRIER) {
                event.getWhoClicked().closeInventory();
            } else {
                String[] split = is.getItemMeta().getDisplayName().split(":");
                String price = split[1].replace(" ", "");
                int priceInt = Integer.parseInt(price);
                if (TomoNova.getPlugin().bleachUHC.getPlayerPoints(player.getName()) >= priceInt) {
                    this.player.getInventory().addItem(event.getCurrentItem());
                    TomoNova.getPlugin().bleachUHC.addPoints(player.getName(), -priceInt);
                    new YachiruGui(this.player).show();
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
