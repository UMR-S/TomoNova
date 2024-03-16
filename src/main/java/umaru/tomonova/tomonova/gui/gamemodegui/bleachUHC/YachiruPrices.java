package umaru.tomonova.tomonova.gui.gamemodegui.bleachUHC;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

public enum YachiruPrices {

    SHARPNESS_1("Sharpness 1", getBook(Enchantment.DAMAGE_ALL,1),100),
    SHARPNESS_2("Sharpness 2", getBook(Enchantment.DAMAGE_ALL,2),100),
    SHARPNESS_3("Sharpness 3", getBook(Enchantment.DAMAGE_ALL,3),100),
    SHARPNESS_4("Sharpness 4", getBook(Enchantment.DAMAGE_ALL,4),100),

    ;

    public String name;

    public ItemStack enchantement;
    public int price;

    YachiruPrices(String name, ItemStack enchantement,int price) {
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

    public ItemStack getEnchantement(){
        return enchantement;
    }

    public static ItemStack getBook(Enchantment enchantment, int level) {
        ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta)book.getItemMeta();
        assert meta != null;
        meta.addStoredEnchant(enchantment,level ,true);
        book.setItemMeta(meta);
        return book;
    }
}
