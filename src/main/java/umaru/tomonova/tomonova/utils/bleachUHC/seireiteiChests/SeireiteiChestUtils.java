package umaru.tomonova.tomonova.utils.bleachUHC.seireiteiChests;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SeireiteiChestUtils {
    private static final Random random = new Random();
    public static void populateChests(World world, int ymin, int ymax, int radius) {
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                if (x * x + z * z <= radius * radius) { // Check if inside the circle
                    for (int y = ymin; y <= ymax; y++) {
                        Block block = world.getBlockAt(x, y, z);
                        if (block.getType() == Material.CHEST) {
                            Chest chest = (Chest) block.getState();
                            fillChest(chest.getInventory());
                        }
                    }
                }
            }
        }
    }

    private static void fillChest(Inventory inventory) {
        inventory.clear();

        List<ItemStack> lootPool = new ArrayList<>();
        lootPool.addAll(ChestLootConstants.getItems(ChestLootConstants.COMMON));
        lootPool.addAll(ChestLootConstants.getItems(ChestLootConstants.RARE));
        lootPool.addAll(ChestLootConstants.getItems(ChestLootConstants.EPIC));
        lootPool.addAll(ChestLootConstants.getItems(ChestLootConstants.LEGENDARY));

        List<ItemStack> chestLoot = new ArrayList<>();

        List<ItemStack> rareItems = ChestLootConstants.getItems(ChestLootConstants.RARE);
        chestLoot.add(rareItems.get(random.nextInt(rareItems.size())));

        for (int i = 0; i < 4; i++) {
            ItemStack item = getRandomItem(lootPool);
            chestLoot.add(item);
        }

        for (ItemStack item : chestLoot) {
            inventory.addItem(item);
        }
    }

    private static ItemStack getRandomItem(List<ItemStack> lootPool) {
        int chance = random.nextInt(100) + 1;
        if (chance <= ChestLootConstants.COMMON.getDropRate()) {
            return getRandomFromList(ChestLootConstants.getItems(ChestLootConstants.COMMON));
        } else if (chance <= ChestLootConstants.COMMON.getDropRate() + ChestLootConstants.RARE.getDropRate()) {
            return getRandomFromList(ChestLootConstants.getItems(ChestLootConstants.RARE));
        } else if (chance <= ChestLootConstants.COMMON.getDropRate() + ChestLootConstants.RARE.getDropRate() + ChestLootConstants.EPIC.getDropRate()) {
            return getRandomFromList(ChestLootConstants.getItems(ChestLootConstants.EPIC));
        } else {
            return getRandomFromList(ChestLootConstants.getItems(ChestLootConstants.LEGENDARY));
        }
    }

    private static ItemStack getRandomFromList(List<ItemStack> items) {
        return items.get(random.nextInt(items.size()));
    }
}
