package umaru.tomonova.tomonova.utils.bleachUHC.seireiteiChests;
import org.bukkit.inventory.ItemStack;

import org.bukkit.Material;
import java.util.ArrayList;
import java.util.List;

public enum ChestLootConstants {
    COMMON(85),
    RARE(10),
    EPIC(4),
    LEGENDARY(1);

    private final int dropRate;

    ChestLootConstants(int dropRate) {
        this.dropRate = dropRate;
    }

    public int getDropRate() {
        return dropRate;
    }

    // Define the pool for each rarity level
    public static List<ItemStack> getItems(ChestLootConstants rarity) {
        List<ItemStack> items = new ArrayList<>();
        switch (rarity) {
            case COMMON -> {
                items.add(new ItemStack(Material.IRON_INGOT, 3));
                items.add(new ItemStack(Material.COAL, 4));
                items.add(new ItemStack(Material.LAPIS_LAZULI, 6));
                items.add(new ItemStack(Material.WHEAT, 6));
                items.add(new ItemStack(Material.POTATO, 2));
            }
            case RARE -> {
                items.add(new ItemStack(Material.GOLD_INGOT, 3));
                items.add(new ItemStack(Material.APPLE, 1));
                items.add(new ItemStack(Material.MUSHROOM_STEW, 1));
                items.add(new ItemStack(Material.RABBIT_STEW, 1));
            }
            case EPIC -> {
                items.add(new ItemStack(Material.COOKED_BEEF, 3));
                items.add(new ItemStack(Material.GHAST_TEAR, 1));
                items.add(new ItemStack(Material.OBSIDIAN, 1));
            }
            case LEGENDARY -> {
                items.add(new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1));
                items.add(new ItemStack(Material.SHULKER_BOX, 1));
                items.add(new ItemStack(Material.ANVIL, 1));
                items.add(new ItemStack(Material.TNT, 2));
            }
        }
        return items;
    }
}
