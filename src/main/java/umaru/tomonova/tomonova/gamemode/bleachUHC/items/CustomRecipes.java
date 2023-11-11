package umaru.tomonova.tomonova.gamemode.bleachUHC.items;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.lang.Lang;
import umaru.tomonova.tomonova.utils.customItems.CustomItems;

import java.util.Arrays;

public class CustomRecipes {
    public static void addHogyokuRecipe(){
        ItemStack hogyokuActif = CustomItems.createCustomItem(
                Material.TOTEM_OF_UNDYING,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_HOGYOKU_ACTIF_NAME.toString().toString(),
                Arrays.asList(Lang.BUHC_ITEM_HOGYOKU_ACTIF_LORE.toString(), Lang.BUHC_ITEM_HOGYOKU_ACTIF_LORE_TWO.toString()),
                5149613);

        ShapedRecipe hogyokuRecipe = new ShapedRecipe(new NamespacedKey(TomoNova.getPlugin(),"hogyoku"),hogyokuActif);
        hogyokuRecipe.shape("XYX");
        hogyokuRecipe.setIngredient('X', Material.NETHER_WART);
        hogyokuRecipe.setIngredient('Y', Material.NETHERITE_SCRAP);
        Bukkit.addRecipe(hogyokuRecipe);
    }
}
