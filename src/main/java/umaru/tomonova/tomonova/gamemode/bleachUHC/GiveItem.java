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

import java.util.*;

public class GiveItem {
    //Items de base des classes
    public static void giveCarquois(String playerName){
        ItemStack carquois = CustomItems.createCustomItem(
                Material.CARROT_ON_A_STICK,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_CARQUOIS_NAME.toString(),
                Collections.singletonList(Lang.BUHC_ITEM_CARQUOIS_LORE.toString()),
                2000602);
        Objects.requireNonNull(Bukkit.getPlayer(playerName)).getInventory().addItem(carquois);

    }
    public static void giveBow(String playerName){
        ItemStack quincyBow = CustomItems.createCustomItem(
                Material.BOW,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_QUINCY_BOW_NAME.toString(),
                Collections.singletonList(Lang.BUHC_ITEM_QUINCY_BOW_LORE.toString()),
                2000201);
        Objects.requireNonNull(Bukkit.getPlayer(playerName)).getInventory().addItem(quincyBow);

    }
    public static void giveBowPowerFive(String playerName){
        ItemStack quincyBowPowerFive = CustomItems.createCustomItem(
                Material.BOW,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_QUINCY_BOW_NAME.toString(),
                Collections.singletonList(Lang.BUHC_ITEM_QUINCY_BOW_LORE.toString()),
                2000201);
        Map<Enchantment, Integer> enchantMap = new HashMap<Enchantment, Integer>() {{
            put(Enchantment.ARROW_DAMAGE, 5);
        }};
        quincyBowPowerFive = CustomItems.addEnchants(quincyBowPowerFive,enchantMap);
        Objects.requireNonNull(Bukkit.getPlayer(playerName)).getInventory().addItem(quincyBowPowerFive);

    }
    public static void giveQuincyArrow(String playerName, int amount){
        ItemStack quincyArrow =  CustomItems.createCustomItem(
                Material.ARROW,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_QUINCY_ARROW_NAME.toString(),
                Collections.singletonList(Lang.BUHC_ITEM_QUINCY_ARROW_LORE.toString()),
                2000501);
        quincyArrow.setAmount(amount);
        Player player = Bukkit.getPlayer(playerName);
        assert player != null;
        Objects.requireNonNull(player.getInventory().addItem(quincyArrow));
    }

    public static void giveShield(String playerName){
        ItemStack brazoShield = CustomItems.createCustomItem(
                Material.SHIELD,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_SHIELD_NAME.toString(),
                Collections.singletonList(Lang.BUHC_ITEM_SHIELD_LORE.toString()),
                4000401);
        Player player = Bukkit.getPlayer(playerName);
        assert player != null;
        player.getInventory().addItem(brazoShield);
    }

    public static void giveZanpakuto(String playerName){
        ItemStack zanpakuto = CustomItems.createCustomItem(
                Material.IRON_SWORD,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_ZANPAKUTO_NAME.toString(),
                Collections.singletonList(Lang.BUHC_ITEM_ZANPAKUTO_LORE.toString()),
                1000101);
        Player player = Bukkit.getPlayer(playerName);
        assert player != null;
        player.getInventory().addItem(zanpakuto);
    }
    public static void give1Cieux(String playerName){
        ItemStack firstCieux = CustomItems.createCustomItem(
                Material.CARROT_ON_A_STICK,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_1CIEUX_NAME.toString(),
                Arrays.asList(Lang.BUHC_ITEM_1CIEUX_LORE.toString(), Lang.BUHC_ITEM_1CIEUX_LORE_TWO.toString()),
                3000301);
        removeHogyokuInactif(playerName);
        Objects.requireNonNull(Bukkit.getPlayer(playerName)).getInventory().addItem(firstCieux);
    }
    public static void give2Cieux(String playerName){
        ItemStack secondCieux = CustomItems.createCustomItem(
                Material.CARROT_ON_A_STICK,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_2CIEUX_NAME.toString(),
                Arrays.asList(Lang.BUHC_ITEM_2CIEUX_LORE.toString(), Lang.BUHC_ITEM_2CIEUX_LORE_TWO.toString()),
                3000302);
        removeHogyokuInactif(playerName);
        Objects.requireNonNull(Bukkit.getPlayer(playerName)).getInventory().addItem(secondCieux);
    }
    public static void give3Cieux(String playerName){
        ItemStack thirsdCieux = CustomItems.createCustomItem(
                Material.CARROT_ON_A_STICK,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_3CIEUX_NAME.toString(),
                Arrays.asList(Lang.BUHC_ITEM_3CIEUX_LORE.toString(), Lang.BUHC_ITEM_3CIEUX_LORE_TWO.toString()),
                3000303);
        removeHogyokuInactif(playerName);
        Objects.requireNonNull(Bukkit.getPlayer(playerName)).getInventory().addItem(thirsdCieux);
    }
    public static void give4Cieux(String playerName){
        ItemStack forthCieux = CustomItems.createCustomItem(
                Material.CARROT_ON_A_STICK,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_4CIEUX_NAME.toString(),
                Arrays.asList(Lang.BUHC_ITEM_4CIEUX_LORE.toString(), Lang.BUHC_ITEM_4CIEUX_LORE_TWO.toString()),
                3000304);
        removeHogyokuInactif(playerName);
        Objects.requireNonNull(Bukkit.getPlayer(playerName)).getInventory().addItem(forthCieux);
    }

    public static void dashShinigami(String playerName){
        ItemStack dashShinigami = CustomItems.createCustomItem(
                Material.CARROT_ON_A_STICK,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_DASH_NAME.toString(),
                Collections.singletonList(Lang.BUHC_ITEM_DASH_LORE.toString()),
                1000603);
        Player player = Bukkit.getPlayer(playerName);
        assert player != null;
        player.getInventory().addItem(dashShinigami);
    }
    public static void dashQuincy(String playerName){
        ItemStack dashQuincy = CustomItems.createCustomItem(
                Material.CARROT_ON_A_STICK,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_DASH_NAME.toString(),
                Collections.singletonList(Lang.BUHC_ITEM_DASH_LORE.toString()),
                2000604);
        Player player = Bukkit.getPlayer(playerName);
        assert player != null;
        player.getInventory().addItem(dashQuincy);
    }
    //Items des boss/zanpakuto
    public static void spawnHogyokuInactifFragment(Location location){
        ItemStack hogyokuInactifFragment = CustomItems.createCustomItem(
                Material.NETHER_WART,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_HOGYOKU_FRAG_INACTIF_NAME.toString(),
                Collections.singletonList(Lang.BUHC_ITEM_HOGYOKU_FRAG_INACTIF_LORE.toString()),
                5149609);
        Objects.requireNonNull(location.getWorld()).dropItemNaturally(location,hogyokuInactifFragment);
    }
    public static void giveHogyokuActifFragment(String playerName){
        ItemStack hogyokuActifFragment = CustomItems.createCustomItem(
                Material.NETHER_WART,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_HOGYOKU_FRAG_ACTIF_NAME.toString(),
                Collections.singletonList(Lang.BUHC_ITEM_HOGYOKU_FRAG_ACTIF_LORE.toString()),
                5149610);
        Player player = Bukkit.getPlayer(playerName);
        assert player != null;
        player.getInventory().remove(player.getInventory().getItemInMainHand());
        player.getInventory().addItem(hogyokuActifFragment);
    }
    public static void spawnHogyokuCoeur(Location location){
        ItemStack hogyokuCoeur = CustomItems.createCustomItem(
                Material.NETHERITE_SCRAP,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_HOGYOKU_COEUR_NAME.toString(),
                Collections.singletonList(Lang.BUHC_ITEM_HOGYOKU_COEUR_LORE.toString()),
                5149611);
        Objects.requireNonNull(location.getWorld()).dropItemNaturally(location,hogyokuCoeur);
    }
    public static void giveHogyokuInactif(String playerName){
        ItemStack hogyokuInactif = CustomItems.createCustomItem(
                Material.NETHERITE_SCRAP,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_HOGYOKU_INACTIF_NAME.toString(),
                Collections.singletonList(Lang.BUHC_ITEM_HOGYOKU_INACTIF_LORE.toString()),
                5149612);
        Objects.requireNonNull(Bukkit.getPlayer(playerName)).getInventory().addItem(hogyokuInactif);
        Objects.requireNonNull(Bukkit.getPlayer(playerName)).setCooldown(Material.NETHERITE_SCRAP, 6000);
    }
    public static void removeHogyokuInactif(String playerName){
        for(ItemStack itemStack : Objects.requireNonNull(Bukkit.getPlayer(playerName)).getInventory()){
            if(!(itemStack == null || itemStack.getType().equals(Material.AIR))){
                if(Objects.requireNonNull(itemStack.getItemMeta()).hasCustomModelData()){
                    if(itemStack.getItemMeta().getCustomModelData() == 5149612){
                        Objects.requireNonNull(Bukkit.getPlayer(playerName)).getInventory().remove(itemStack);
                    }
                }
            }
        }
    }
    public static void giveHogyokuActif(String playerName){
        ItemStack hogyokuActif = CustomItems.createCustomItem(
                Material.TOTEM_OF_UNDYING,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_HOGYOKU_ACTIF_NAME.toString(),
                Arrays.asList(Lang.BUHC_ITEM_HOGYOKU_ACTIF_LORE.toString(), Lang.BUHC_ITEM_HOGYOKU_ACTIF_LORE_TWO.toString()),
                5149613);
        removeHogyokuInactif(playerName);
        Objects.requireNonNull(Bukkit.getPlayer(playerName)).getInventory().addItem(hogyokuActif);
    }
    public static void spawnRyujinJakka(Location location){
        ItemStack ryujinJakka = CustomItems.createCustomItem(
                Material.IRON_SWORD,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_RYUJIN_JAKKA_NAME.toString(),
                Collections.singletonList(Lang.BUHC_ITEM_RYUJIN_JAKKA_LORE.toString()),
                1011104);
        Map<Enchantment, Integer> enchantMap = new HashMap<Enchantment, Integer>() {{
            put(Enchantment.DAMAGE_ALL, 2);
            put(Enchantment.FIRE_ASPECT, 2);
        }};
        ryujinJakka = CustomItems.addEnchants(ryujinJakka,enchantMap);
        Objects.requireNonNull(location.getWorld()).dropItemNaturally(location,ryujinJakka);
    }
    public static void spawnArtDuHakuda(Location location){
        ItemStack artDuHakuda = CustomItems.createCustomItem(
                Material.CARROT_ON_A_STICK,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_ART_DU_HAKUDA_NAME.toString(),
                Arrays.asList(Lang.BUHC_ITEM_ART_DU_HAKUDA_LORE.toString(), Lang.BUHC_ITEM_ART_DU_HAKUDA_LORE_TWO.toString()),
                4012602);
        Objects.requireNonNull(location.getWorld()).dropItemNaturally(location,artDuHakuda);
    }
    public static void spawnMinazuki(Location location){
        ItemStack minazuki = CustomItems.createCustomItem(
                Material.IRON_SWORD,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_MINAZUKI_NAME.toString(),
                Collections.singletonList(Lang.BUHC_ITEM_MINAZUKI_LORE.toString()),
                1041107);
        Map<Enchantment, Integer> enchantMap = new HashMap<Enchantment, Integer>() {{
            put(Enchantment.DAMAGE_ALL, 1);
        }};
        CustomItems.addEnchants(minazuki,enchantMap);
        Objects.requireNonNull(location.getWorld()).dropItemNaturally(location,minazuki);
    }
    public static void spawnSenbonzakura(Location location){
        ItemStack senbonzakura = CustomItems.createCustomItem(
                Material.IRON_SWORD,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_SENBONZAKURA_NAME.toString(),
                Arrays.asList(Lang.BUHC_ITEM_SENBONZAKURA_LORE.toString(),Lang.BUHC_ITEM_SENBONZAKURA_LORE_TWO.toString(),Lang.BUHC_ITEM_SENBONZAKURA_LORE_THREE.toString()),
                1061109);
        Map<Enchantment, Integer> enchantMap = new HashMap<Enchantment, Integer>() {{
            put(Enchantment.DAMAGE_ALL, 1);
        }};
        CustomItems.addEnchants(senbonzakura,enchantMap);
        Objects.requireNonNull(location.getWorld()).dropItemNaturally(location, senbonzakura);
    }
    public static void spawnAshisogiJizo(Location location){
        ItemStack ashisogiJizo = CustomItems.createCustomItem(
                Material.IRON_SWORD,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_ASHISOGI_JIZO_NAME.toString(),
                Arrays.asList(Lang.BUHC_ITEM_ASHISOGI_JIZO_LORE.toString()),
                1121115);
        Map<Enchantment, Integer> enchantMap = new HashMap<Enchantment, Integer>() {{
            put(Enchantment.DAMAGE_ALL, 1);
        }};
        CustomItems.addEnchants(ashisogiJizo,enchantMap);
        Objects.requireNonNull(location.getWorld()).dropItemNaturally(location, ashisogiJizo);
    }
    public static void spawnKenpachiSword(Location location){
        ItemStack kenpachiSword = CustomItems.createCustomItem(
                Material.IRON_SWORD,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_KENPACHI_SWORD_NAME.toString(),
                Collections.singletonList(Lang.BUHC_ITEM_KENPACHI_SWORD_LORE.toString()),
                1121115);
        Map<Enchantment, Integer> enchantMap = new HashMap<Enchantment, Integer>() {{
            put(Enchantment.DAMAGE_ALL, 3);
        }};
        CustomItems.addEnchants(kenpachiSword,enchantMap);
        Objects.requireNonNull(location.getWorld()).dropItemNaturally(location,kenpachiSword);
    }
    public static void spawnTengen(Location location){
        ItemStack tengen = CustomItems.createCustomItem(
                Material.IRON_SWORD,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_TENGEN_NAME.toString(),
                Arrays.asList(Lang.BUHC_ITEM_TENGEN_LORE.toString(), Lang.BUHC_ITEM_TENGEN_LORE_TWO.toString()),
                1071110);
        Map<Enchantment, Integer> enchantMap = new HashMap<Enchantment, Integer>() {{
            put(Enchantment.DAMAGE_ALL, 1);
        }};
        CustomItems.addEnchants(tengen,enchantMap);
        Objects.requireNonNull(location.getWorld()).dropItemNaturally(location, tengen);
    }
    public static void spawnMedicament(Location location){
        ItemStack medicament = CustomItems.createCustomItem(
                Material.CARROT_ON_A_STICK,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_MEDICAMENT_NAME.toString(),
                Arrays.asList(Lang.BUHC_ITEM_MEDICAMENT_LORE.toString(), Lang.BUHC_ITEM_MEDICAMENT_LORE_TWO.toString()),
                5132208);

        Objects.requireNonNull(location.getWorld()).dropItemNaturally(location, medicament);
    }
    public static void spawnSuzumebachi(Location location){
        ItemStack suzumebachi = CustomItems.createCustomItem(
                Material.IRON_SWORD,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_SUZUMEBACHI_NAME.toString(),
                Collections.singletonList(Lang.BUHC_ITEM_SUZUMEBACHI_LORE.toString()),
                1021105);
        Map<Enchantment, Integer> enchantMap = new HashMap<Enchantment, Integer>() {{
            put(Enchantment.DAMAGE_ALL, 1);
        }};
        CustomItems.addEnchants(suzumebachi,enchantMap);
        Objects.requireNonNull(location.getWorld()).dropItemNaturally(location, suzumebachi);
    }
    public static void spawnShinso(Location location){
        ItemStack shinso = CustomItems.createCustomItem(
                Material.IRON_SWORD,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_SHINSO_NAME.toString(),
                Arrays.asList(Lang.BUHC_ITEM_SHINSO_LORE.toString(),Lang.BUHC_ITEM_SHINSO_LORE_TWO.toString()),
                1031106);
        Map<Enchantment, Integer> enchantMap = new HashMap<Enchantment, Integer>() {{
            put(Enchantment.DAMAGE_ALL, 1);
        }};
        CustomItems.addEnchants(shinso,enchantMap);
        Objects.requireNonNull(location.getWorld()).dropItemNaturally(location, shinso);
    }
    public static void spawnAveuxDeGin(Location location){
        ItemStack aveux = CustomItems.createCustomItem(
                Material.CARROT_ON_A_STICK,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_AVEUX_NAME.toString(),
                Collections.singletonList(Lang.BUHC_ITEM_AVEUX_LORE.toString()),
                5032602);

        Objects.requireNonNull(location.getWorld()).dropItemNaturally(location, aveux);
    }
    public static void spawnBaveMinazuki(Location location){
        ItemStack aveux = CustomItems.createCustomItem(
                Material.SHIELD,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_BAVE_NAME.toString(),
                Collections.singletonList(Lang.BUHC_ITEM_BAVE_LORE.toString()),
                5032602);

        Objects.requireNonNull(location.getWorld()).dropItemNaturally(location, aveux);
    }
    public static void spawnSuzumuchi(Location location){
        ItemStack suzumuchi = CustomItems.createCustomItem(
                Material.IRON_SWORD,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_SUZUMUCHI_NAME.toString(),
                Collections.singletonList(Lang.BUHC_ITEM_SUZUMUCHI_LORE.toString()),
                1091112);
        Map<Enchantment, Integer> enchantMap = new HashMap<Enchantment, Integer>() {{
            put(Enchantment.DAMAGE_ALL, 1);
        }};
        CustomItems.addEnchants(suzumuchi,enchantMap);
        Objects.requireNonNull(location.getWorld()).dropItemNaturally(location, suzumuchi);
    }
    public static void spawnPhotoYoruichi(Location location){
        ItemStack photoYoruichi = CustomItems.createCustomItem(
                Material.CARROT_ON_A_STICK,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_PHOTO_YORUICHI_NAME.toString(),
                Arrays.asList(Lang.BUHC_ITEM_PHOTO_YORUICHI_LORE.toString(), Lang.BUHC_ITEM_PHOTO_YORUICHI_LORE_TWO.toString()),
                5022601);
        Objects.requireNonNull(location.getWorld()).dropItemNaturally(location, photoYoruichi);
    }
    public static void spawnLysDesNeiges(Location location){
        ItemStack lysDesNeiges = CustomItems.createCustomItem(
                Material.CARROT_ON_A_STICK,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_LYS_DES_NEIGES_NAME.toString(),
                Arrays.asList(Lang.BUHC_ITEM_LYS_DES_NEIGES_LORE.toString(), Lang.BUHC_ITEM_LYS_DES_NEIGES_LORE_TWO.toString()),
                5102605);
        Objects.requireNonNull(location.getWorld()).dropItemNaturally(location, lysDesNeiges);
    }
    public static void spawnHyorinmaru(Location location){
        ItemStack hyorinmaru = CustomItems.createCustomItem(
                Material.IRON_SWORD,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_HYORINMARU_NAME.toString(),
                Arrays.asList(Lang.BUHC_ITEM_HYORINMARU_LORE.toString(), Lang.BUHC_ITEM_HYORINMARU_LORE_TWO.toString()),
                1101113);
        Objects.requireNonNull(location.getWorld()).dropItemNaturally(location, hyorinmaru);
    }
    public static void spawnLunettesTosen(Location location){
        ItemStack lunettesTosen = CustomItems.createCustomItem(
                Material.CARROT_ON_A_STICK,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_LUNETTES_TOSEN_NAME.toString(),
                Arrays.asList(Lang.BUHC_ITEM_LUNETTES_TOSEN_LORE.toString(), Lang.BUHC_ITEM_LUNETTES_TOSEN_LORE_TWO.toString()),
                5092605);
        Objects.requireNonNull(location.getWorld()).dropItemNaturally(location, lunettesTosen);
    }
    public static void spawnKatenKyokotsu(Location location){
        ItemStack katenKyokotsu = CustomItems.createCustomItem(
                Material.CARROT_ON_A_STICK,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_KATEN_KYOKOTSU_NAME.toString(),
                Arrays.asList(Lang.BUHC_ITEM_KATEN_KYOKOTSU_LORE.toString(), Lang.BUHC_ITEM_KATEN_KYOKOTSU_LORE_TWO.toString()),
                1081111);
        Map<Enchantment, Integer> enchantMap = new HashMap<Enchantment, Integer>() {{
            put(Enchantment.DAMAGE_ALL, 1);
        }};
        CustomItems.addEnchants(katenKyokotsu,enchantMap);
        Objects.requireNonNull(location.getWorld()).dropItemNaturally(location, katenKyokotsu);
    }
    public static void spawnGantDeSanrei(Location location){
        ItemStack gantDeSanrei = CustomItems.createCustomItem(
                Material.CARROT_ON_A_STICK,
                ChatColor.AQUA,
                Lang.BUHC_ITEM_GANT_SANREI_NAME.toString(),
                Arrays.asList(Lang.BUHC_ITEM_GANT_SANREI_LORE.toString(), Lang.BUHC_ITEM_GANT_SANREI_LORE_TWO.toString()),
                2122605);
        Objects.requireNonNull(location.getWorld()).dropItemNaturally(location, gantDeSanrei);
    }
}
