package umaru.tomonova.tomonova.gamemode.bleachUHC;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import umaru.tomonova.tomonova.lang.Lang;
import umaru.tomonova.tomonova.utils.customItems.CustomItems;

import java.util.Arrays;

public class GiveItem {
    //Hogyoku
    public static void giveHogyokuInactifFragment(String playerName){
        ItemStack hogyokuInactifFragment = CustomItems.createCustomItem(
                Material.NETHER_WART,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_HOGYOKU_FRAG_INACTIF_NAME.toString().toString(),
                Arrays.asList(Lang.BUHC_ITEM_HOGYOKU_FRAG_INACTIF_LORE.toString()),
                5149609);
    }
    public static void giveHogyokuActifFragment(String playerName){
        ItemStack hogyokuActifFragment = CustomItems.createCustomItem(
                Material.NETHER_WART,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_HOGYOKU_FRAG_ACTIF_NAME.toString().toString(),
                Arrays.asList(Lang.BUHC_ITEM_HOGYOKU_FRAG_ACTIF_LORE.toString()),
                5149610);
        Player player = Bukkit.getPlayer(playerName);
        player.getInventory().remove(player.getInventory().getItemInMainHand());
        player.getInventory().addItem(hogyokuActifFragment);
    }
    public static void giveHogyokuCoeur(String playerName){
        ItemStack hogyokuCoeur = CustomItems.createCustomItem(
                Material.NETHERITE_SCRAP,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_HOGYOKU_COEUR_NAME.toString().toString(),
                Arrays.asList(Lang.BUHC_ITEM_HOGYOKU_COEUR_LORE.toString()),
                5149611);
    }
    public static void giveHogyokuInactif(String playerName){
        ItemStack hogyokuInactif = CustomItems.createCustomItem(
                Material.NETHERITE_SCRAP,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_HOGYOKU_INACTIF_NAME.toString().toString(),
                Arrays.asList(Lang.BUHC_ITEM_HOGYOKU_INACTIF_LORE.toString()),
                5149612);
        Bukkit.getPlayer(playerName).getInventory().addItem(hogyokuInactif);
        Bukkit.getPlayer(playerName).setCooldown(Material.NETHERITE_SCRAP, 6000);
    }
    public static void removeHogyokuInactif(String playerName){
        Bukkit.getPlayer(playerName).getInventory().remove(Material.NETHERITE_SCRAP);
    }
    public static void giveHogyokuActif(String playerName){
        ItemStack hogyokuActif = CustomItems.createCustomItem(
                Material.TOTEM_OF_UNDYING,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_HOGYOKU_ACTIF_NAME.toString().toString(),
                Arrays.asList(Lang.BUHC_ITEM_HOGYOKU_ACTIF_LORE.toString(), Lang.BUHC_ITEM_HOGYOKU_ACTIF_LORE_TWO.toString()),
                5149613);
        removeHogyokuInactif(playerName);
        Bukkit.getPlayer(playerName).getInventory().addItem(hogyokuActif);
    }
}
