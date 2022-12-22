package umaru.tomonova.tomonova.gamemode.bleachUHC;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import umaru.tomonova.tomonova.lang.Lang;
import umaru.tomonova.tomonova.utils.customItems.CustomItems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GiveItem {
    //Hogyoku
    public static void spawnHogyokuInactifFragment(Location location){
        ItemStack hogyokuInactifFragment = CustomItems.createCustomItem(
                Material.NETHER_WART,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_HOGYOKU_FRAG_INACTIF_NAME.toString().toString(),
                Arrays.asList(Lang.BUHC_ITEM_HOGYOKU_FRAG_INACTIF_LORE.toString()),
                5149609);
        location.getWorld().dropItemNaturally(location,hogyokuInactifFragment);
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
    public static void spawnHogyokuCoeur(Location location){
        ItemStack hogyokuCoeur = CustomItems.createCustomItem(
                Material.NETHERITE_SCRAP,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_HOGYOKU_COEUR_NAME.toString().toString(),
                Arrays.asList(Lang.BUHC_ITEM_HOGYOKU_COEUR_LORE.toString()),
                5149611);
        location.getWorld().dropItemNaturally(location,hogyokuCoeur);
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
    public static void spawnRyujinJakka(Location location){
        ItemStack ryujinJakka = CustomItems.createCustomItem(
                Material.IRON_SWORD,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_RYUJIN_JAKKA_NAME.toString().toString(),
                Arrays.asList(Lang.BUHC_ITEM_RYUJIN_JAKKA_LORE.toString()),
                1011104);
        Map<Enchantment, Integer> enchantMap = new HashMap<Enchantment, Integer>() {{
            put(Enchantment.DAMAGE_ALL, 2);
            put(Enchantment.FIRE_ASPECT, 2);
        }};
        ryujinJakka = CustomItems.addEnchants(ryujinJakka,enchantMap);
        location.getWorld().dropItemNaturally(location,ryujinJakka);
    }
    public static void spawnArtDuHakuda(Location location){
        ItemStack artDuHakuda = CustomItems.createCustomItem(
                Material.CARROT_ON_A_STICK,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_ART_DU_HAKUDA_NAME.toString().toString(),
                Arrays.asList(Lang.BUHC_ITEM_ART_DU_HAKUDA_LORE.toString(), Lang.BUHC_ITEM_ART_DU_HAKUDA_LORE_TWO.toString()),
                4012602);
        location.getWorld().dropItemNaturally(location,artDuHakuda);
    }
    public static void spawnMinazuki(Location location){
        ItemStack minazuki = CustomItems.createCustomItem(
                Material.IRON_SWORD,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_MINAZUKI_NAME.toString().toString(),
                Arrays.asList(Lang.BUHC_ITEM_MINAZUKI_LORE.toString()),
                1041107);
        Map<Enchantment, Integer> enchantMap = new HashMap<Enchantment, Integer>() {{
            put(Enchantment.DAMAGE_ALL, 1);
        }};
        CustomItems.addEnchants(minazuki,enchantMap);
        location.getWorld().dropItemNaturally(location,minazuki);
    }
    public static void spawnSenbonzakura(Location location){
        ItemStack senbonzakura = CustomItems.createCustomItem(
                Material.IRON_SWORD,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_SENBONZAKURA_NAME.toString().toString(),
                Arrays.asList(Lang.BUHC_ITEM_SENBONZAKURA_LORE.toString(),Lang.BUHC_ITEM_SENBONZAKURA_LORE_TWO.toString(),Lang.BUHC_ITEM_SENBONZAKURA_LORE_THREE.toString()),
                1061109);
        Map<Enchantment, Integer> enchantMap = new HashMap<Enchantment, Integer>() {{
            put(Enchantment.DAMAGE_ALL, 1);
        }};
        CustomItems.addEnchants(senbonzakura,enchantMap);
        location.getWorld().dropItemNaturally(location, senbonzakura);
    }
    public static void spawnAshisogiJizo(Location location){
        ItemStack ashisogiJizo = CustomItems.createCustomItem(
                Material.IRON_SWORD,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_MINAZUKI_NAME.toString().toString(),
                Arrays.asList(Lang.BUHC_ITEM_MINAZUKI_LORE.toString()),
                1041107);
        Map<Enchantment, Integer> enchantMap = new HashMap<Enchantment, Integer>() {{
            put(Enchantment.DAMAGE_ALL, 1);
        }};
        CustomItems.addEnchants(ashisogiJizo,enchantMap);
        location.getWorld().dropItemNaturally(location, ashisogiJizo);
    }
    public static void spawnKenpachiSword(Location location){
        ItemStack kenpachiSword = CustomItems.createCustomItem(
                Material.IRON_SWORD,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_KENPACHI_SWORD_NAME.toString().toString(),
                Arrays.asList(Lang.BUHC_ITEM_KENPACHI_SWORD_LORE.toString()),
                1121115);
        Map<Enchantment, Integer> enchantMap = new HashMap<Enchantment, Integer>() {{
            put(Enchantment.DAMAGE_ALL, 3);
        }};
        CustomItems.addEnchants(kenpachiSword,enchantMap);
        location.getWorld().dropItemNaturally(location,kenpachiSword);
    }
    public static void spawnTengen(Location location){
        ItemStack tengen = CustomItems.createCustomItem(
                Material.IRON_SWORD,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_TENGEN_NAME.toString().toString(),
                Arrays.asList(Lang.BUHC_ITEM_TENGEN_LORE.toString(), Lang.BUHC_ITEM_TENGEN_LORE_TWO.toString()),
                1071110);
        Map<Enchantment, Integer> enchantMap = new HashMap<Enchantment, Integer>() {{
            put(Enchantment.DAMAGE_ALL, 1);
        }};
        CustomItems.addEnchants(tengen,enchantMap);
        location.getWorld().dropItemNaturally(location, tengen);
    }
    public static void spawnMedicament(Location location){
        ItemStack medicament = CustomItems.createCustomItem(
                Material.CARROT_ON_A_STICK,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_MEDICAMENT_NAME.toString().toString(),
                Arrays.asList(Lang.BUHC_ITEM_MEDICAMENT_LORE.toString(), Lang.BUHC_ITEM_MEDICAMENT_LORE_TWO.toString()),
                5132208);

        location.getWorld().dropItemNaturally(location, medicament);
    }
    public static void spawnSuzumebachi(Location location){
        ItemStack suzumebachi = CustomItems.createCustomItem(
                Material.IRON_SWORD,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_SUZUMEBACHI_NAME.toString().toString(),
                Arrays.asList(Lang.BUHC_ITEM_SUZUMEBACHI_LORE.toString()),
                1021105);
        Map<Enchantment, Integer> enchantMap = new HashMap<Enchantment, Integer>() {{
            put(Enchantment.DAMAGE_ALL, 1);
        }};
        CustomItems.addEnchants(suzumebachi,enchantMap);
        location.getWorld().dropItemNaturally(location, suzumebachi);
    }
}
