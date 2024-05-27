package umaru.tomonova.tomonova.utils.constants;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import umaru.tomonova.tomonova.lang.Lang;
import umaru.tomonova.tomonova.utils.customItems.CustomItems;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class BleachUHCItemConstants {

    public static final ItemStack CARQUOIS = CustomItems.createCustomItem(
            Material.CARROT_ON_A_STICK,
            ChatColor.AQUA,
            BleachUHCConstants.CARQUOIS_NAME,
            Collections.singletonList(Lang.BUHC_ITEM_CARQUOIS_LORE.toString()),
            BleachUHCConstants.CARQUOIS
    );

    public static final ItemStack QUINCY_BOW = CustomItems.createCustomItem(
            Material.BOW,
            ChatColor.AQUA,
            BleachUHCConstants.ARC_QUINCY_NAME,
            Collections.singletonList(Lang.BUHC_ITEM_QUINCY_BOW_LORE.toString()),
            BleachUHCConstants.ARC_QUINCY
    );

    public static final ItemStack QUINCY_BOW_POWER_FIVE;
    static {
        ItemStack bow = CustomItems.createCustomItem(
                Material.BOW,
                ChatColor.AQUA,
                BleachUHCConstants.ARC_QUINCY_NAME,
                Collections.singletonList(Lang.BUHC_ITEM_QUINCY_BOW_LORE.toString()),
                BleachUHCConstants.ARC_QUINCY
        );
        Map<Enchantment, Integer> enchantMap = Collections.singletonMap(Enchantment.ARROW_DAMAGE, 5);
        QUINCY_BOW_POWER_FIVE = CustomItems.addEnchants(bow, enchantMap);
    }

    public static final ItemStack QUINCY_ARROW = CustomItems.createCustomItem(
            Material.ARROW,
            ChatColor.AQUA,
            BleachUHCConstants.ARC_QUINCY_NAME,
            Collections.singletonList(Lang.BUHC_ITEM_QUINCY_ARROW_LORE.toString()),
            BleachUHCConstants.ARC_QUINCY
    );

    public static final ItemStack SHIELD = CustomItems.createCustomItem(
            Material.SHIELD,
            ChatColor.AQUA,
            BleachUHCConstants.BOUCLIER_BRAZO_NAME,
            Collections.singletonList(Lang.BUHC_ITEM_SHIELD_LORE.toString()),
            BleachUHCConstants.BOUCLIER_BRAZO
    );

    public static final ItemStack ZANPAKUTO = CustomItems.createCustomItem(
            Material.IRON_SWORD,
            ChatColor.AQUA,
            BleachUHCConstants.ZANPAKUTO_NAME,
            Collections.singletonList(Lang.BUHC_ITEM_ZANPAKUTO_LORE.toString()),
            BleachUHCConstants.ZANPAKUTO
    );

    public static final ItemStack CIEL_UNIQUE = CustomItems.createCustomItem(
            Material.CARROT_ON_A_STICK,
            ChatColor.AQUA,
            BleachUHCConstants.CIEL_UNIQUE_NAME,
            Arrays.asList(Lang.BUHC_ITEM_1CIEUX_LORE.toString(), Lang.BUHC_ITEM_1CIEUX_LORE_TWO.toString()),
            BleachUHCConstants.CIEL_UNIQUE
    );

    public static final ItemStack DEUX_CIEUX = CustomItems.createCustomItem(
            Material.CARROT_ON_A_STICK,
            ChatColor.AQUA,
            BleachUHCConstants.DEUX_CIEUX_NAME,
            Arrays.asList(Lang.BUHC_ITEM_2CIEUX_LORE.toString(), Lang.BUHC_ITEM_2CIEUX_LORE_TWO.toString()),
            BleachUHCConstants.DEUX_CIEUX
    );

    public static final ItemStack TROIS_CIEUX = CustomItems.createCustomItem(
            Material.CARROT_ON_A_STICK,
            ChatColor.AQUA,
            BleachUHCConstants.TROIS_CIEUX_NAME,
            Arrays.asList(Lang.BUHC_ITEM_3CIEUX_LORE.toString(), Lang.BUHC_ITEM_3CIEUX_LORE_TWO.toString()),
            BleachUHCConstants.TROIS_CIEUX
    );

    public static final ItemStack QUATRE_CIEUX = CustomItems.createCustomItem(
            Material.CARROT_ON_A_STICK,
            ChatColor.AQUA,
            BleachUHCConstants.QUATRE_CIEUX_NAME,
            Arrays.asList(Lang.BUHC_ITEM_4CIEUX_LORE.toString(), Lang.BUHC_ITEM_4CIEUX_LORE_TWO.toString()),
            BleachUHCConstants.QUATRE_CIEUX
    );

    public static final ItemStack DASH_SHINIGAMI = CustomItems.createCustomItem(
            Material.CARROT_ON_A_STICK,
            ChatColor.AQUA,
            BleachUHCConstants.DASH_SHINIGAMI_NAME,
            Collections.singletonList(Lang.BUHC_ITEM_DASH_LORE.toString()),
            BleachUHCConstants.DASH_SHINIGAMI
    );

    public static final ItemStack DASH_QUINCY = CustomItems.createCustomItem(
            Material.CARROT_ON_A_STICK,
            ChatColor.AQUA,
            BleachUHCConstants.DASH_QUINCY_NAME,
            Collections.singletonList(Lang.BUHC_ITEM_DASH_LORE.toString()),
            BleachUHCConstants.DASH_QUINCY
    );

    public static final ItemStack FRAGMENT_HOGYOKU_INACTIF = CustomItems.createCustomItem(
            Material.NETHER_WART,
            ChatColor.AQUA,
            BleachUHCConstants.FRAGMENT_HOGYOKU_INACTIF_NAME,
            Collections.singletonList(Lang.BUHC_ITEM_HOGYOKU_FRAG_INACTIF_LORE.toString()),
            BleachUHCConstants.FRAGMENT_HOGYOKU_INACTIF
    );

    public static final ItemStack FRAGMENT_HOGYOKU_ACTIF = CustomItems.createCustomItem(
            Material.NETHER_WART,
            ChatColor.AQUA,
            BleachUHCConstants.FRAGMENT_HOGYOKU_ACTIF_NAME,
            Collections.singletonList(Lang.BUHC_ITEM_HOGYOKU_FRAG_ACTIF_LORE.toString()),
            BleachUHCConstants.FRAGMENT_HOGYOKU_ACTIF
    );

    public static final ItemStack COEUR_HOGYOKU = CustomItems.createCustomItem(
            Material.NETHERITE_SCRAP,
            ChatColor.AQUA,
            BleachUHCConstants.COEUR_HOGYOKU_NAME,
            Collections.singletonList(Lang.BUHC_ITEM_HOGYOKU_COEUR_LORE.toString()),
            BleachUHCConstants.COEUR_HOGYOKU
    );

    public static final ItemStack HOGYOKU_INACTIF = CustomItems.createCustomItem(
            Material.NETHERITE_SCRAP,
            ChatColor.AQUA,
            BleachUHCConstants.HOGYOKU_INACTIF_NAME,
            Collections.singletonList(Lang.BUHC_ITEM_HOGYOKU_INACTIF_LORE.toString()),
            BleachUHCConstants.HOGYOKU_INACTIF
    );

    public static final ItemStack HOGYOKU_ACTIF = CustomItems.createCustomItem(
            Material.TOTEM_OF_UNDYING,
            ChatColor.AQUA,
            BleachUHCConstants.HOGYOKU_ACTIF_NAME,
            Arrays.asList(Lang.BUHC_ITEM_HOGYOKU_ACTIF_LORE.toString(), Lang.BUHC_ITEM_HOGYOKU_ACTIF_LORE_TWO.toString()),
            BleachUHCConstants.HOGYOKU_ACTIF
    );
    public static final ItemStack RYUJIN_JAKKA;
    static {
        ItemStack item = CustomItems.createCustomItem(
                Material.IRON_SWORD,
                ChatColor.AQUA,
                BleachUHCConstants.RYUJIN_JAKKA_NAME,
                Collections.singletonList(Lang.BUHC_ITEM_RYUJIN_JAKKA_LORE.toString()),
                BleachUHCConstants.RYUJIN_JAKKA
        );
        Map<Enchantment, Integer> enchantMap = Map.of(
                Enchantment.DAMAGE_ALL, 2,
                Enchantment.FIRE_ASPECT, 2
        );
        RYUJIN_JAKKA = CustomItems.addEnchants(item, enchantMap);
    }

    public static final ItemStack ART_DU_HAKUDA = CustomItems.createCustomItem(
            Material.CARROT_ON_A_STICK,
            ChatColor.AQUA,
            BleachUHCConstants.ART_DU_HAKUDA_NAME,
            Arrays.asList(Lang.BUHC_ITEM_ART_DU_HAKUDA_LORE.toString(), Lang.BUHC_ITEM_ART_DU_HAKUDA_LORE_TWO.toString()),
            BleachUHCConstants.ART_DU_HAKUDA
    );

    public static final ItemStack MINAZUKI;
    static {
        ItemStack item = CustomItems.createCustomItem(
                Material.IRON_SWORD,
                ChatColor.AQUA,
                BleachUHCConstants.MINAZUKI_NAME,
                Collections.singletonList(Lang.BUHC_ITEM_MINAZUKI_LORE.toString()),
                BleachUHCConstants.MINAZUKI
        );
        Map<Enchantment, Integer> enchantMap = Collections.singletonMap(Enchantment.DAMAGE_ALL, 1);
        MINAZUKI = CustomItems.addEnchants(item, enchantMap);
    }

    public static final ItemStack SENBONZAKURA;
    static {
        ItemStack item = CustomItems.createCustomItem(
                Material.IRON_SWORD,
                ChatColor.AQUA,
                BleachUHCConstants.SENBONZAKURA_NAME,
                Arrays.asList(Lang.BUHC_ITEM_SENBONZAKURA_LORE.toString(), Lang.BUHC_ITEM_SENBONZAKURA_LORE_TWO.toString(), Lang.BUHC_ITEM_SENBONZAKURA_LORE_THREE.toString()),
                BleachUHCConstants.SENBONZAKURA
        );
        Map<Enchantment, Integer> enchantMap = Collections.singletonMap(Enchantment.DAMAGE_ALL, 1);
        SENBONZAKURA = CustomItems.addEnchants(item, enchantMap);
    }

    public static final ItemStack ASHISOGI_JIZO;
    static {
        ItemStack item = CustomItems.createCustomItem(
                Material.IRON_SWORD,
                ChatColor.AQUA,
                BleachUHCConstants.ASHISOGI_JIZO_NAME,
                Collections.singletonList(Lang.BUHC_ITEM_ASHISOGI_JIZO_LORE.toString()),
                BleachUHCConstants.ASHISOGI_JIZO
        );
        Map<Enchantment, Integer> enchantMap = Collections.singletonMap(Enchantment.DAMAGE_ALL, 1);
        ASHISOGI_JIZO = CustomItems.addEnchants(item, enchantMap);
    }

    public static final ItemStack KENPACHI_SWORD;
    static {
        ItemStack item = CustomItems.createCustomItem(
                Material.IRON_SWORD,
                ChatColor.AQUA,
                BleachUHCConstants.EPEE_DE_KENPACHI_NAME,
                Collections.singletonList(Lang.BUHC_ITEM_KENPACHI_SWORD_LORE.toString()),
                BleachUHCConstants.EPEE_DE_KENPACHI
        );
        Map<Enchantment, Integer> enchantMap = Collections.singletonMap(Enchantment.DAMAGE_ALL, 3);
        KENPACHI_SWORD = CustomItems.addEnchants(item, enchantMap);
    }

    public static final ItemStack TENGEN;
    static {
        ItemStack item = CustomItems.createCustomItem(
                Material.IRON_SWORD,
                ChatColor.AQUA,
                BleachUHCConstants.TENGEN_NAME,
                Arrays.asList(Lang.BUHC_ITEM_TENGEN_LORE.toString(), Lang.BUHC_ITEM_TENGEN_LORE_TWO.toString()),
                BleachUHCConstants.TENGEN
        );
        Map<Enchantment, Integer> enchantMap = Collections.singletonMap(Enchantment.DAMAGE_ALL, 1);
        TENGEN = CustomItems.addEnchants(item, enchantMap);
    }

    public static final ItemStack MEDICAMENT = CustomItems.createCustomItem(
            Material.CARROT_ON_A_STICK,
            ChatColor.AQUA,
            BleachUHCConstants.MEDICAMENTS_NAME,
            Arrays.asList(Lang.BUHC_ITEM_MEDICAMENT_LORE.toString(), Lang.BUHC_ITEM_MEDICAMENT_LORE_TWO.toString()),
            BleachUHCConstants.MEDICAMENTS
    );

    public static final ItemStack SUZUMEBACHI;
    static {
        ItemStack item = CustomItems.createCustomItem(
                Material.IRON_SWORD,
                ChatColor.AQUA,
                BleachUHCConstants.SUZUMEBACHI_NAME,
                Collections.singletonList(Lang.BUHC_ITEM_SUZUMEBACHI_LORE.toString()),
                BleachUHCConstants.SUZUMEBACHI
        );
        Map<Enchantment, Integer> enchantMap = Collections.singletonMap(Enchantment.DAMAGE_ALL, 1);
        SUZUMEBACHI = CustomItems.addEnchants(item, enchantMap);
    }

    public static final ItemStack SHINSO;
    static {
        ItemStack item = CustomItems.createCustomItem(
                Material.IRON_SWORD,
                ChatColor.AQUA,
                BleachUHCConstants.SHINSO_NAME,
                Arrays.asList(Lang.BUHC_ITEM_SHINSO_LORE.toString(), Lang.BUHC_ITEM_SHINSO_LORE_TWO.toString()),
                BleachUHCConstants.SHINSO
        );
        Map<Enchantment, Integer> enchantMap = Collections.singletonMap(Enchantment.DAMAGE_ALL, 1);
        SHINSO = CustomItems.addEnchants(item, enchantMap);
    }

    public static final ItemStack AVEUX = CustomItems.createCustomItem(
            Material.CARROT_ON_A_STICK,
            ChatColor.AQUA,
            BleachUHCConstants.AVEUX_DE_GIN_NAME,
            Collections.singletonList(Lang.BUHC_ITEM_AVEUX_LORE.toString()),
            BleachUHCConstants.AVEUX_DE_GIN
    );

    public static final ItemStack BAVE_DE_MINAZUKI = CustomItems.createCustomItem(
            Material.SHIELD,
            ChatColor.AQUA,
            BleachUHCConstants.BAVE_DE_MINAZUKI_NAME,
            Collections.singletonList(Lang.BUHC_ITEM_BAVE_LORE.toString()),
            BleachUHCConstants.BAVE_DE_MINAZUKI
    );

    public static final ItemStack SUZUMUCHI;
    static {
        ItemStack item = CustomItems.createCustomItem(
                Material.IRON_SWORD,
                ChatColor.AQUA,
                BleachUHCConstants.SUZUMUSHI_NAME,
                Collections.singletonList(Lang.BUHC_ITEM_SUZUMUCHI_LORE.toString()),
                BleachUHCConstants.SUZUMUSHI
        );
        Map<Enchantment, Integer> enchantMap = Collections.singletonMap(Enchantment.DAMAGE_ALL, 1);
        SUZUMUCHI = CustomItems.addEnchants(item, enchantMap);
    }

    public static final ItemStack PHOTO_YORUICHI = CustomItems.createCustomItem(
            Material.CARROT_ON_A_STICK,
            ChatColor.AQUA,
            BleachUHCConstants.PHOTO_DE_YORUICHI_NAME,
            Arrays.asList(Lang.BUHC_ITEM_PHOTO_YORUICHI_LORE.toString(), Lang.BUHC_ITEM_PHOTO_YORUICHI_LORE_TWO.toString()),
            BleachUHCConstants.PHOTO_DE_YORUICHI
    );

    public static final ItemStack LYS_DES_NEIGES = CustomItems.createCustomItem(
            Material.CARROT_ON_A_STICK,
            ChatColor.AQUA,
            BleachUHCConstants.LYS_DES_NEIGES_NAME,
            Arrays.asList(Lang.BUHC_ITEM_LYS_DES_NEIGES_LORE.toString(), Lang.BUHC_ITEM_LYS_DES_NEIGES_LORE_TWO.toString()),
            BleachUHCConstants.LYS_DES_NEIGES
    );

    public static final ItemStack HYORINMARU = CustomItems.createCustomItem(
            Material.IRON_SWORD,
            ChatColor.AQUA,
            BleachUHCConstants.HYORINMARU_NAME,
            Arrays.asList(Lang.BUHC_ITEM_HYORINMARU_LORE.toString(), Lang.BUHC_ITEM_HYORINMARU_LORE_TWO.toString()),
            BleachUHCConstants.HYORINMARU
    );

    public static final ItemStack LUNETTES_TOSEN = CustomItems.createCustomItem(
            Material.CARROT_ON_A_STICK,
            ChatColor.AQUA,
            BleachUHCConstants.LUNETTES_DE_TOSEN_NAME,
            Arrays.asList(Lang.BUHC_ITEM_LUNETTES_TOSEN_LORE.toString(), Lang.BUHC_ITEM_LUNETTES_TOSEN_LORE_TWO.toString()),
            BleachUHCConstants.LUNETTES_DE_TOSEN
    );

    public static final ItemStack KATEN_KYOKOTSU;
    static {
        ItemStack item = CustomItems.createCustomItem(
                Material.CARROT_ON_A_STICK,
                ChatColor.AQUA,
                BleachUHCConstants.KATEN_KYOKOTSU_NAME,
                Arrays.asList(Lang.BUHC_ITEM_KATEN_KYOKOTSU_LORE.toString(), Lang.BUHC_ITEM_KATEN_KYOKOTSU_LORE_TWO.toString()),
                BleachUHCConstants.KATEN_KYOKOTSU
        );
        Map<Enchantment, Integer> enchantMap = Collections.singletonMap(Enchantment.DAMAGE_ALL, 1);
        KATEN_KYOKOTSU = CustomItems.addEnchants(item, enchantMap);
    }

    public static final ItemStack GANT_DE_SANREI = CustomItems.createCustomItem(
            Material.CARROT_ON_A_STICK,
            ChatColor.AQUA,
            BleachUHCConstants.GANT_DE_SANREI_NAME,
            Arrays.asList(Lang.BUHC_ITEM_GANT_SANREI_LORE.toString(), Lang.BUHC_ITEM_GANT_SANREI_LORE_TWO.toString()),
            BleachUHCConstants.GANT_DE_SANREI
    );
}
