package umaru.tomonova.tomonova.gamemode.bleachUHC;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import umaru.tomonova.tomonova.lang.Lang;
import umaru.tomonova.tomonova.utils.constants.BleachUHCConstants;
import umaru.tomonova.tomonova.utils.customItems.CustomItems;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class GiveItem {

    private static void giveItem(Player player, ItemStack item) {
        player.getInventory().addItem(item);
    }

    public static void giveCarquois(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        if (player != null) {
            ItemStack carquois = CustomItems.createCustomItem(
                    Material.CARROT_ON_A_STICK,
                    ChatColor.AQUA,
                    BleachUHCConstants.CARQUOIS_NAME,
                    Collections.singletonList(Lang.BUHC_ITEM_CARQUOIS_LORE.toString()),
                    BleachUHCConstants.CARQUOIS
            );
            giveItem(player, carquois);
        }
    }

    public static void giveBow(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        if (player != null) {
            ItemStack quincyBow = CustomItems.createCustomItem(
                    Material.BOW,
                    ChatColor.AQUA,
                    BleachUHCConstants.ARC_QUINCY_NAME,
                    Collections.singletonList(Lang.BUHC_ITEM_QUINCY_BOW_LORE.toString()),
                    BleachUHCConstants.ARC_QUINCY
            );
            giveItem(player, quincyBow);
        }
    }

    public static void giveBowPowerFive(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        if (player != null) {
            ItemStack bow = CustomItems.createCustomItem(
                    Material.BOW,
                    ChatColor.AQUA,
                    BleachUHCConstants.ARC_QUINCY_NAME,
                    Collections.singletonList(Lang.BUHC_ITEM_QUINCY_BOW_LORE.toString()),
                    BleachUHCConstants.ARC_QUINCY
            );
            Map<Enchantment, Integer> enchantMap = Collections.singletonMap(Enchantment.ARROW_DAMAGE, 5);
            ItemStack quincyPowerFive = CustomItems.addEnchants(bow, enchantMap);
            giveItem(player, quincyPowerFive);
        }
    }

    public static void giveQuincyArrow(String playerName, int amount) {
        Player player = Bukkit.getPlayer(playerName);
        if (player != null) {
            ItemStack arrow = CustomItems.createCustomItem(
                    Material.ARROW,
                    ChatColor.AQUA,
                    BleachUHCConstants.FLECHES_NAME,
                    Collections.singletonList(Lang.BUHC_ITEM_QUINCY_ARROW_LORE.toString()),
                    BleachUHCConstants.FLECHES
            );
            arrow.setAmount(amount);
            giveItem(player, arrow);
        }
    }

    public static void giveShield(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        if (player != null) {
            ItemStack shield = CustomItems.createCustomItem(
                    Material.SHIELD,
                    ChatColor.AQUA,
                    BleachUHCConstants.BOUCLIER_BRAZO_NAME,
                    Collections.singletonList(Lang.BUHC_ITEM_SHIELD_LORE.toString()),
                    BleachUHCConstants.BOUCLIER_BRAZO
            );
            giveItem(player, shield);
        }
    }

    public static void giveZanpakuto(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        if (player != null) {
            ItemStack zanpakuto = CustomItems.createCustomItem(
                    Material.IRON_SWORD,
                    ChatColor.AQUA,
                    BleachUHCConstants.ZANPAKUTO_NAME,
                    Collections.singletonList(Lang.BUHC_ITEM_ZANPAKUTO_LORE.toString()),
                    BleachUHCConstants.ZANPAKUTO
            );
            giveItem(player, zanpakuto);
        }
    }

    public static void give1Cieux(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        if (player != null) {
            ItemStack cielUnique = CustomItems.createCustomItem(
                    Material.CARROT_ON_A_STICK,
                    ChatColor.AQUA,
                    BleachUHCConstants.CIEL_UNIQUE_NAME,
                    Arrays.asList(Lang.BUHC_ITEM_1CIEUX_LORE.toString(), Lang.BUHC_ITEM_1CIEUX_LORE_TWO.toString()),
                    BleachUHCConstants.CIEL_UNIQUE
            );
            giveItem(player, cielUnique);
        }
    }

    public static void give2Cieux(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        if (player != null) {
            ItemStack deuxCieux = CustomItems.createCustomItem(
                    Material.CARROT_ON_A_STICK,
                    ChatColor.AQUA,
                    BleachUHCConstants.DEUX_CIEUX_NAME,
                    Arrays.asList(Lang.BUHC_ITEM_2CIEUX_LORE.toString(), Lang.BUHC_ITEM_2CIEUX_LORE_TWO.toString()),
                    BleachUHCConstants.DEUX_CIEUX
            );
            giveItem(player, deuxCieux);
        }
    }

    public static void give3Cieux(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        if (player != null) {
            ItemStack troisCieux = CustomItems.createCustomItem(
                    Material.CARROT_ON_A_STICK,
                    ChatColor.AQUA,
                    BleachUHCConstants.TROIS_CIEUX_NAME,
                    Arrays.asList(Lang.BUHC_ITEM_3CIEUX_LORE.toString(), Lang.BUHC_ITEM_3CIEUX_LORE_TWO.toString()),
                    BleachUHCConstants.TROIS_CIEUX
            );
            giveItem(player, troisCieux);
        }
    }

    public static void give4Cieux(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        if (player != null) {
            ItemStack quatreCieux = CustomItems.createCustomItem(
                    Material.CARROT_ON_A_STICK,
                    ChatColor.AQUA,
                    BleachUHCConstants.QUATRE_CIEUX_NAME,
                    Arrays.asList(Lang.BUHC_ITEM_4CIEUX_LORE.toString(), Lang.BUHC_ITEM_4CIEUX_LORE_TWO.toString()),
                    BleachUHCConstants.QUATRE_CIEUX
            );
            giveItem(player, quatreCieux);
        }
    }

    public static void dashShinigami(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        if (player != null) {
            ItemStack dashShinigami = CustomItems.createCustomItem(
                    Material.CARROT_ON_A_STICK,
                    ChatColor.AQUA,
                    BleachUHCConstants.DASH_SHINIGAMI_NAME,
                    Collections.singletonList(Lang.BUHC_ITEM_DASH_LORE.toString()),
                    BleachUHCConstants.DASH_SHINIGAMI
            );
            giveItem(player, dashShinigami);
        }
    }

    public static void dashQuincy(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        if (player != null) {
            ItemStack dashQuincy = CustomItems.createCustomItem(
                    Material.CARROT_ON_A_STICK,
                    ChatColor.AQUA,
                    BleachUHCConstants.DASH_QUINCY_NAME,
                    Collections.singletonList(Lang.BUHC_ITEM_DASH_LORE.toString()),
                    BleachUHCConstants.DASH_QUINCY
            );
            giveItem(player, dashQuincy);
        }
    }

    public static void spawnHogyokuInactifFragment(Location location) {
        ItemStack fragmentHogyokuInactif = CustomItems.createCustomItem(
                Material.HEART_OF_THE_SEA,
                ChatColor.AQUA,
                BleachUHCConstants.FRAGMENT_HOGYOKU_INACTIF_NAME,
                Collections.singletonList(Lang.BUHC_ITEM_HOGYOKU_FRAG_INACTIF_LORE.toString()),
                BleachUHCConstants.FRAGMENT_HOGYOKU_INACTIF
        );
        location.getWorld().dropItemNaturally(location, fragmentHogyokuInactif);
    }

    public static void giveHogyokuActifFragment(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        if (player != null) {
            ItemStack fragmentHogyokuActif = CustomItems.createCustomItem(
                    Material.NETHER_WART,
                    ChatColor.AQUA,
                    BleachUHCConstants.FRAGMENT_HOGYOKU_ACTIF_NAME,
                    Collections.singletonList(Lang.BUHC_ITEM_HOGYOKU_FRAG_ACTIF_LORE.toString()),
                    BleachUHCConstants.FRAGMENT_HOGYOKU_ACTIF
            );
            giveItem(player, fragmentHogyokuActif);
        }
    }

    public static void spawnHogyokuCoeur(Location location) {
        ItemStack coeurHogyoku = CustomItems.createCustomItem(
                Material.NETHERITE_SCRAP,
                ChatColor.AQUA,
                BleachUHCConstants.COEUR_HOGYOKU_NAME,
                Collections.singletonList(Lang.BUHC_ITEM_HOGYOKU_COEUR_LORE.toString()),
                BleachUHCConstants.COEUR_HOGYOKU
        );
        location.getWorld().dropItemNaturally(location, coeurHogyoku);
    }

    public static void giveHogyokuInactif(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        if (player != null) {
            ItemStack hogyokuInactif = CustomItems.createCustomItem(
                    Material.NETHERITE_SCRAP,
                    ChatColor.AQUA,
                    BleachUHCConstants.HOGYOKU_INACTIF_NAME,
                    Collections.singletonList(Lang.BUHC_ITEM_HOGYOKU_INACTIF_LORE.toString()),
                    BleachUHCConstants.HOGYOKU_INACTIF
            );
            removeHogyokuActif(player);
            giveItem(player, hogyokuInactif);
        }
    }

    public static void removeHogyokuInactif(Player player) {
        player.getInventory().forEach(itemStack -> {
            if (itemStack != null && itemStack.getType() != Material.AIR) {
                if (itemStack.getItemMeta().hasCustomModelData() && itemStack.getItemMeta().getCustomModelData() == BleachUHCConstants.HOGYOKU_INACTIF) {
                    player.getInventory().remove(itemStack);
                }
            }
        });
    }

    public static void giveHogyokuActif(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        if (player != null) {
            removeHogyokuInactif(player);
            ItemStack hogyokuActif = CustomItems.createCustomItem(
                    Material.TOTEM_OF_UNDYING,
                    ChatColor.AQUA,
                    BleachUHCConstants.HOGYOKU_ACTIF_NAME,
                    Arrays.asList(Lang.BUHC_ITEM_HOGYOKU_ACTIF_LORE.toString(), Lang.BUHC_ITEM_HOGYOKU_ACTIF_LORE_TWO.toString()),
                    BleachUHCConstants.HOGYOKU_ACTIF
            );
            giveItem(player, hogyokuActif);
        }
    }

    public static void removeHogyokuActif(Player player) {
        player.getInventory().forEach(itemStack -> {
            if (itemStack != null && itemStack.getType() != Material.AIR) {
                if (itemStack.getItemMeta().hasCustomModelData() && itemStack.getItemMeta().getCustomModelData() == BleachUHCConstants.HOGYOKU_ACTIF) {
                    player.getInventory().remove(itemStack);
                }
            }
        });
    }

    public static void spawnRyujinJakka(Location location) {
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
        ItemStack ryujinJakka = CustomItems.addEnchants(item, enchantMap);
        location.getWorld().dropItemNaturally(location, ryujinJakka);
    }

    public static void spawnArtDuHakuda(Location location) {
        ItemStack artDuHakuda = CustomItems.createCustomItem(
                Material.CARROT_ON_A_STICK,
                ChatColor.AQUA,
                BleachUHCConstants.ART_DU_HAKUDA_NAME,
                Arrays.asList(Lang.BUHC_ITEM_ART_DU_HAKUDA_LORE.toString(), Lang.BUHC_ITEM_ART_DU_HAKUDA_LORE_TWO.toString()),
                BleachUHCConstants.ART_DU_HAKUDA
        );
        location.getWorld().dropItemNaturally(location, artDuHakuda);
    }

    public static void spawnMinazuki(Location location) {
        ItemStack item = CustomItems.createCustomItem(
                Material.IRON_SWORD,
                ChatColor.AQUA,
                BleachUHCConstants.MINAZUKI_NAME,
                Collections.singletonList(Lang.BUHC_ITEM_MINAZUKI_LORE.toString()),
                BleachUHCConstants.MINAZUKI
        );
        Map<Enchantment, Integer> enchantMap = Collections.singletonMap(Enchantment.DAMAGE_ALL, 1);
        ItemStack minazuki = CustomItems.addEnchants(item, enchantMap);
        location.getWorld().dropItemNaturally(location, minazuki);
    }

    public static void spawnSenbonzakura(Location location) {
        ItemStack item = CustomItems.createCustomItem(
                Material.IRON_SWORD,
                ChatColor.AQUA,
                BleachUHCConstants.SENBONZAKURA_NAME,
                Arrays.asList(Lang.BUHC_ITEM_SENBONZAKURA_LORE.toString(), Lang.BUHC_ITEM_SENBONZAKURA_LORE_TWO.toString(), Lang.BUHC_ITEM_SENBONZAKURA_LORE_THREE.toString()),
                BleachUHCConstants.SENBONZAKURA
        );
        Map<Enchantment, Integer> enchantMap = Collections.singletonMap(Enchantment.DAMAGE_ALL, 1);
        ItemStack senbonzakura = CustomItems.addEnchants(item, enchantMap);
        location.getWorld().dropItemNaturally(location, senbonzakura);
    }

    public static void spawnAshisogiJizo(Location location) {
        ItemStack item = CustomItems.createCustomItem(
                Material.IRON_SWORD,
                ChatColor.AQUA,
                BleachUHCConstants.ASHISOGI_JIZO_NAME,
                Collections.singletonList(Lang.BUHC_ITEM_ASHISOGI_JIZO_LORE.toString()),
                BleachUHCConstants.ASHISOGI_JIZO
        );
        Map<Enchantment, Integer> enchantMap = Collections.singletonMap(Enchantment.DAMAGE_ALL, 1);
        ItemStack ashisogiJizo = CustomItems.addEnchants(item, enchantMap);
        location.getWorld().dropItemNaturally(location, ashisogiJizo);
    }

    public static void spawnKenpachiSword(Location location) {
        ItemStack item = CustomItems.createCustomItem(
                Material.IRON_SWORD,
                ChatColor.AQUA,
                BleachUHCConstants.EPEE_DE_KENPACHI_NAME,
                Collections.singletonList(Lang.BUHC_ITEM_KENPACHI_SWORD_LORE.toString()),
                BleachUHCConstants.EPEE_DE_KENPACHI
        );
        Map<Enchantment, Integer> enchantMap = Collections.singletonMap(Enchantment.DAMAGE_ALL, 3);
        ItemStack kenpachiSword = CustomItems.addEnchants(item, enchantMap);
        location.getWorld().dropItemNaturally(location, kenpachiSword);
    }

    public static void spawnTengen(Location location) {
        ItemStack item = CustomItems.createCustomItem(
                Material.IRON_SWORD,
                ChatColor.AQUA,
                BleachUHCConstants.TENGEN_NAME,
                Arrays.asList(Lang.BUHC_ITEM_TENGEN_LORE.toString(), Lang.BUHC_ITEM_TENGEN_LORE_TWO.toString()),
                BleachUHCConstants.TENGEN
        );
        Map<Enchantment, Integer> enchantMap = Collections.singletonMap(Enchantment.DAMAGE_ALL, 1);
        ItemStack tengen = CustomItems.addEnchants(item, enchantMap);
        location.getWorld().dropItemNaturally(location, tengen);
    }

    public static void spawnMedicament(Location location) {
        ItemStack medicament = CustomItems.createCustomItem(
                Material.CARROT_ON_A_STICK,
                ChatColor.AQUA,
                BleachUHCConstants.MEDICAMENTS_NAME,
                Arrays.asList(Lang.BUHC_ITEM_MEDICAMENT_LORE.toString(), Lang.BUHC_ITEM_MEDICAMENT_LORE_TWO.toString()),
                BleachUHCConstants.MEDICAMENTS
        );

        location.getWorld().dropItemNaturally(location, medicament);
    }

    public static void spawnSuzumebachi(Location location) {
        ItemStack item = CustomItems.createCustomItem(
                Material.IRON_SWORD,
                ChatColor.AQUA,
                BleachUHCConstants.SUZUMEBACHI_NAME,
                Collections.singletonList(Lang.BUHC_ITEM_SUZUMEBACHI_LORE.toString()),
                BleachUHCConstants.SUZUMEBACHI
        );
        Map<Enchantment, Integer> enchantMap = Collections.singletonMap(Enchantment.DAMAGE_ALL, 1);
        ItemStack suzumebachi = CustomItems.addEnchants(item, enchantMap);
        location.getWorld().dropItemNaturally(location, suzumebachi);
    }

    public static void spawnShinso(Location location) {
        ItemStack item = CustomItems.createCustomItem(
                Material.IRON_SWORD,
                ChatColor.AQUA,
                BleachUHCConstants.SHINSO_NAME,
                Arrays.asList(Lang.BUHC_ITEM_SHINSO_LORE.toString(), Lang.BUHC_ITEM_SHINSO_LORE_TWO.toString()),
                BleachUHCConstants.SHINSO
        );
        Map<Enchantment, Integer> enchantMap = Collections.singletonMap(Enchantment.DAMAGE_ALL, 1);
        ItemStack shinso = CustomItems.addEnchants(item, enchantMap);
        location.getWorld().dropItemNaturally(location, shinso);
    }

    public static void spawnAveuxDeGin(Location location) {
        ItemStack aveux = CustomItems.createCustomItem(
                Material.CARROT_ON_A_STICK,
                ChatColor.AQUA,
                BleachUHCConstants.AVEUX_DE_GIN_NAME,
                Collections.singletonList(Lang.BUHC_ITEM_AVEUX_LORE.toString()),
                BleachUHCConstants.AVEUX_DE_GIN
        );
        location.getWorld().dropItemNaturally(location, aveux);
    }

    public static void spawnBaveMinazuki(Location location) {
        ItemStack baveDeMinazuki = CustomItems.createCustomItem(
                Material.SHIELD,
                ChatColor.AQUA,
                BleachUHCConstants.BAVE_DE_MINAZUKI_NAME,
                Collections.singletonList(Lang.BUHC_ITEM_BAVE_LORE.toString()),
                BleachUHCConstants.BAVE_DE_MINAZUKI
        );
        location.getWorld().dropItemNaturally(location, baveDeMinazuki);
    }

    public static void spawnSuzumuchi(Location location) {
        ItemStack item = CustomItems.createCustomItem(
                Material.IRON_SWORD,
                ChatColor.AQUA,
                BleachUHCConstants.SUZUMUSHI_NAME,
                Collections.singletonList(Lang.BUHC_ITEM_SUZUMUCHI_LORE.toString()),
                BleachUHCConstants.SUZUMUSHI
        );
        Map<Enchantment, Integer> enchantMap = Collections.singletonMap(Enchantment.DAMAGE_ALL, 1);
        ItemStack suzumichi = CustomItems.addEnchants(item, enchantMap);
        location.getWorld().dropItemNaturally(location, suzumichi);
    }

    public static void spawnPhotoYoruichi(Location location) {
        ItemStack photoYoruichi = CustomItems.createCustomItem(
                Material.CARROT_ON_A_STICK,
                ChatColor.AQUA,
                BleachUHCConstants.PHOTO_DE_YORUICHI_NAME,
                Arrays.asList(Lang.BUHC_ITEM_PHOTO_YORUICHI_LORE.toString(), Lang.BUHC_ITEM_PHOTO_YORUICHI_LORE_TWO.toString()),
                BleachUHCConstants.PHOTO_DE_YORUICHI
        );
        location.getWorld().dropItemNaturally(location, photoYoruichi);
    }

    public static void spawnLysDesNeiges(Location location) {
        ItemStack lysDesNeiges = CustomItems.createCustomItem(
                Material.CARROT_ON_A_STICK,
                ChatColor.AQUA,
                BleachUHCConstants.LYS_DES_NEIGES_NAME,
                Arrays.asList(Lang.BUHC_ITEM_LYS_DES_NEIGES_LORE.toString(), Lang.BUHC_ITEM_LYS_DES_NEIGES_LORE_TWO.toString()),
                BleachUHCConstants.LYS_DES_NEIGES
        );
        location.getWorld().dropItemNaturally(location, lysDesNeiges);
    }

    public static void spawnHyorinmaru(Location location) {
        ItemStack hyorinmaru = CustomItems.createCustomItem(
                Material.IRON_SWORD,
                ChatColor.AQUA,
                BleachUHCConstants.HYORINMARU_NAME,
                Arrays.asList(Lang.BUHC_ITEM_HYORINMARU_LORE.toString(), Lang.BUHC_ITEM_HYORINMARU_LORE_TWO.toString()),
                BleachUHCConstants.HYORINMARU
        );
        location.getWorld().dropItemNaturally(location, hyorinmaru);
    }

    public static void spawnLunettesTosen(Location location) {
        ItemStack lunettesTosen = CustomItems.createCustomItem(
                Material.CARROT_ON_A_STICK,
                ChatColor.AQUA,
                BleachUHCConstants.LUNETTES_DE_TOSEN_NAME,
                Arrays.asList(Lang.BUHC_ITEM_LUNETTES_TOSEN_LORE.toString(), Lang.BUHC_ITEM_LUNETTES_TOSEN_LORE_TWO.toString()),
                BleachUHCConstants.LUNETTES_DE_TOSEN
        );
        location.getWorld().dropItemNaturally(location, lunettesTosen);
    }

    public static void spawnKatenKyokotsu(Location location) {
        ItemStack item = CustomItems.createCustomItem(
                Material.IRON_SWORD,
                ChatColor.AQUA,
                BleachUHCConstants.KATEN_KYOKOTSU_NAME,
                Arrays.asList(Lang.BUHC_ITEM_KATEN_KYOKOTSU_LORE.toString(), Lang.BUHC_ITEM_KATEN_KYOKOTSU_LORE_TWO.toString()),
                BleachUHCConstants.KATEN_KYOKOTSU
        );
        Map<Enchantment, Integer> enchantMap = Collections.singletonMap(Enchantment.DAMAGE_ALL, 1);
        ItemStack katenKyokotsu = CustomItems.addEnchants(item, enchantMap);
        location.getWorld().dropItemNaturally(location, katenKyokotsu);
    }

    public static void spawnGantDeSanrei(Location location) {
        ItemStack gantDeSeirei = CustomItems.createCustomItem(
                Material.CARROT_ON_A_STICK,
                ChatColor.AQUA,
                BleachUHCConstants.GANT_DE_SANREI_NAME,
                Arrays.asList(Lang.BUHC_ITEM_GANT_SANREI_LORE.toString(), Lang.BUHC_ITEM_GANT_SANREI_LORE_TWO.toString()),
                BleachUHCConstants.GANT_DE_SANREI
        );
        location.getWorld().dropItemNaturally(location, gantDeSeirei);
    }

    public static void spawnInsigne(Location location) {
        ItemStack gantDeSeirei = CustomItems.createCustomItem(
                Material.CARROT_ON_A_STICK,
                ChatColor.AQUA,
                BleachUHCConstants.INSIGNE_CENTRAL_NAME,
                Arrays.asList(Lang.BUHC_ITEM_INSIGNE_CENTRAL_LORE.toString()),
                BleachUHCConstants.INSIGNE_CENTRAL
        );
        location.getWorld().dropItemNaturally(location, gantDeSeirei);
    }

    public static void spawnKyokaSuigetsu(Location location) {
        ItemStack item = CustomItems.createCustomItem(
                Material.IRON_SWORD,
                ChatColor.AQUA,
                BleachUHCConstants.KYOKA_SUIGETSU_NAME,
                Arrays.asList(Lang.BUHC_ITEM_KYOKA_SUIGETSU_LORE.toString(), Lang.BUHC_ITEM_KYOKA_SUIGETSU_LORE_TWO.toString()),
                BleachUHCConstants.KYOKA_SUIGETSU
        );
        Map<Enchantment, Integer> enchantMap = Collections.singletonMap(Enchantment.DAMAGE_ALL, 1);
        ItemStack kyokaSuigetsu = CustomItems.addEnchants(item, enchantMap);
        location.getWorld().dropItemNaturally(location, kyokaSuigetsu);
    }

    public static void spawnKenseikan(Location location) {
        ItemStack kenseikan = CustomItems.createCustomItem(
                Material.CARROT_ON_A_STICK,
                ChatColor.AQUA,
                BleachUHCConstants.KENSEIKAN_NAME,
                Collections.singletonList(Lang.BUHC_ITEM_DASH_LORE.toString()),
                BleachUHCConstants.DASH_SHINIGAMI
        );
        location.getWorld().dropItemNaturally(location, kenseikan);
    }

    public static void spawnCasqueKomamura(Location location) {
        ItemStack casqueKomamura = CustomItems.createCustomItem(
                Material.DIAMOND_HELMET,
                ChatColor.AQUA,
                BleachUHCConstants.CASQUE_DE_KOMAMURA_NAME,
                Collections.singletonList(Lang.BUHC_ITEM_CASQUE_KOMAMURA_LORE.toString()),
                BleachUHCConstants.CASQUE_DE_KOMAMURA
        );
        location.getWorld().dropItemNaturally(location, casqueKomamura);
    }

    public static void spawnSakeKyoraku(Location location) {
        ItemStack kenseikan = CustomItems.createCustomItem(
                Material.CARROT_ON_A_STICK,
                ChatColor.AQUA,
                BleachUHCConstants.SAKE_DE_KYORAKU_NAME,
                Arrays.asList(Lang.BUHC_ITEM_SAKE_LORE.toString(), Lang.BUHC_ITEM_SAKE_LORE_TWO.toString()),
                BleachUHCConstants.SAKE_DE_KYORAKU
        );
        location.getWorld().dropItemNaturally(location, kenseikan);
    }

    public static void spawnCacheOeilKenpachi(Location location) {
        ItemStack cacheOeil = CustomItems.createCustomItem(
                Material.CARROT_ON_A_STICK,
                ChatColor.AQUA,
                BleachUHCConstants.CACHE_OEIL_NAME,
                Arrays.asList(Lang.BUHC_ITEM_CACHE_OEIL_LORE.toString(), Lang.BUHC_ITEM_CACHE_OEIL_LORE_TWO.toString()),
                BleachUHCConstants.CACHE_OEIL
        );
        location.getWorld().dropItemNaturally(location, cacheOeil);
    }

    public static void spawnSogyoNoKotowari(Location location) {
        ItemStack item = CustomItems.createCustomItem(
                Material.IRON_SWORD,
                ChatColor.AQUA,
                BleachUHCConstants.SOGYO_NO_KOTOWARI_NAME,
                Arrays.asList(Lang.BUHC_ITEM_SOGYO_NO_KOTOWARI_LORE.toString(), Lang.BUHC_ITEM_SOGYO_NO_KOTOWARI_LORE_TWO.toString()),
                BleachUHCConstants.SOGYO_NO_KOTOWARI
        );
        Map<Enchantment, Integer> enchantMap = Collections.singletonMap(Enchantment.DAMAGE_ALL, 1);
        ItemStack sogyoNoKotowari = CustomItems.addEnchants(item, enchantMap);
        location.getWorld().dropItemNaturally(location, sogyoNoKotowari);
    }
}
