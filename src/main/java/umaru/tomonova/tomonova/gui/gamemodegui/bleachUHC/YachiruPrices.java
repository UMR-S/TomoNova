package umaru.tomonova.tomonova.gui.gamemodegui.bleachUHC;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

public enum YachiruPrices {

    // Sharpness (Sword damage)
    SHARPNESS_1("Sharpness 1", getBook(Enchantment.DAMAGE_ALL, 1), 100),
    SHARPNESS_2("Sharpness 2", getBook(Enchantment.DAMAGE_ALL, 2), 200),
    SHARPNESS_3("Sharpness 3", getBook(Enchantment.DAMAGE_ALL, 3), 400),
    SHARPNESS_4("Sharpness 4", getBook(Enchantment.DAMAGE_ALL, 4), 800),
    SHARPNESS_5("Sharpness 5", getBook(Enchantment.DAMAGE_ALL, 5), 1600),

    // Protection (Armor protection)
    PROTECTION_1("Protection 1", getBook(Enchantment.PROTECTION_ENVIRONMENTAL, 1), 150),
    PROTECTION_2("Protection 2", getBook(Enchantment.PROTECTION_ENVIRONMENTAL, 2), 300),
    PROTECTION_3("Protection 3", getBook(Enchantment.PROTECTION_ENVIRONMENTAL, 3), 600),
    PROTECTION_4("Protection 4", getBook(Enchantment.PROTECTION_ENVIRONMENTAL, 4), 1200),

    // Power (Bow damage)
    POWER_1("Power 1", getBook(Enchantment.ARROW_DAMAGE, 1), 100),
    POWER_2("Power 2", getBook(Enchantment.ARROW_DAMAGE, 2), 200),
    POWER_3("Power 3", getBook(Enchantment.ARROW_DAMAGE, 3), 400),
    POWER_4("Power 4", getBook(Enchantment.ARROW_DAMAGE, 4), 800),
    POWER_5("Power 5", getBook(Enchantment.ARROW_DAMAGE, 5), 1600),

    // Fire Aspect (Set targets on fire)
    FIRE_ASPECT_1("Fire Aspect 1", getBook(Enchantment.FIRE_ASPECT, 1), 300),
    FIRE_ASPECT_2("Fire Aspect 2", getBook(Enchantment.FIRE_ASPECT, 2), 600),

    // Thorns (Damages attackers when hit)
    THORNS_1("Thorns 1", getBook(Enchantment.THORNS, 1), 500),
    THORNS_2("Thorns 2", getBook(Enchantment.THORNS, 2), 1000),
    THORNS_3("Thorns 3", getBook(Enchantment.THORNS, 3), 2000);

    public String name;
    public ItemStack enchantement;
    public int price;

    YachiruPrices(String name, ItemStack enchantement, int price) {
        this.name = name;
        this.enchantement = enchantement;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public ItemStack getEnchantement() {
        return enchantement;
    }

    public static ItemStack getBook(Enchantment enchantment, int level) {
        ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) book.getItemMeta();
        assert meta != null;
        meta.addStoredEnchant(enchantment, level, true);
        book.setItemMeta(meta);
        return book;
    }
}
