package umaru.tomonova.tomonova.utils.customItems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;

public class CustomItems {

    public static ItemStack createCustomItem(Material material, ChatColor color, String name, List<String> lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        if (name != null) {
            itemMeta.setDisplayName(color + name);
        }
        if (lore != null) {
            itemMeta.setLore(lore);
        }
        item.setItemMeta(itemMeta);

        return item;
    }
    // Même fonction mais avec le custom model data
    public static ItemStack createCustomItem(Material material, ChatColor color, String name, List<String> lore, int customModelData) {
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        if (name != null) {
            itemMeta.setDisplayName(color + name);
        }
        if (lore != null) {
            itemMeta.setLore(lore);
        }
        itemMeta.setCustomModelData(customModelData);
        item.setItemMeta(itemMeta);

        return item;
    }
    public static ItemStack addEnchants(ItemStack item, Map<Enchantment, Integer> enchants) {
        if (enchants != null) {
            enchants.forEach((enchant, level) -> item.addEnchantment(enchant, level));
        }
        return item;
    }
}
