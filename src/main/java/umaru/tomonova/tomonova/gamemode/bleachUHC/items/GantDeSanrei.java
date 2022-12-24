package umaru.tomonova.tomonova.gamemode.bleachUHC.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.gamemode.bleachUHC.GiveItem;

public class GantDeSanrei {
    public static void gantDeSanrei(String playerName){
        Player player = Bukkit.getPlayer(playerName);
        //Remplir de flèches
        TomoNova.getPlugin().classesSpells.Carquois(playerName);
        TomoNova.getPlugin().classesSpells.Carquois(playerName);
        //Remplacer l'arc par un power 5 et enlever le carquois
        for(ItemStack itemStack : player.getInventory()){
            if(!(itemStack == null || itemStack.getType().equals(Material.AIR))) {
                if (itemStack.getType().equals(Material.BOW)) {
                    player.getInventory().remove(itemStack);
                    GiveItem.giveBowPowerFive(playerName);
                }
                if (itemStack.getItemMeta().hasCustomModelData()) {
                    if (itemStack.getItemMeta().getCustomModelData() == 2000602) {
                        player.getInventory().remove(itemStack);
                    }
                }
            }
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,2400,1));
    }
}
