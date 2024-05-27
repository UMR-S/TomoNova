package umaru.tomonova.tomonova.gamemode.bleachUHC;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import umaru.tomonova.tomonova.utils.constants.BleachUHCConstants;
import umaru.tomonova.tomonova.utils.constants.BleachUHCItemConstants;

public class GiveItem {

    private static void giveItem(Player player, ItemStack item) {
        player.getInventory().addItem(item);
    }

    public static void giveCarquois(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        if (player != null) {
            giveItem(player, BleachUHCItemConstants.CARQUOIS);
        }
    }

    public static void giveBow(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        if (player != null) {
            giveItem(player, BleachUHCItemConstants.QUINCY_BOW);
        }
    }

    public static void giveBowPowerFive(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        if (player != null) {
            giveItem(player, BleachUHCItemConstants.QUINCY_BOW_POWER_FIVE);
        }
    }

    public static void giveQuincyArrow(String playerName, int amount) {
        Player player = Bukkit.getPlayer(playerName);
        if (player != null) {
            ItemStack arrow = BleachUHCItemConstants.QUINCY_ARROW.clone();
            arrow.setAmount(amount);
            giveItem(player, arrow);
        }
    }

    public static void giveShield(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        if (player != null) {
            giveItem(player, BleachUHCItemConstants.SHIELD);
        }
    }

    public static void giveZanpakuto(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        if (player != null) {
            giveItem(player, BleachUHCItemConstants.ZANPAKUTO);
        }
    }

    public static void give1Cieux(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        if (player != null) {
            removeHogyokuInactif(player);
            giveItem(player, BleachUHCItemConstants.CIEL_UNIQUE);
        }
    }

    public static void give2Cieux(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        if (player != null) {
            removeHogyokuInactif(player);
            giveItem(player, BleachUHCItemConstants.DEUX_CIEUX);
        }
    }

    public static void give3Cieux(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        if (player != null) {
            removeHogyokuInactif(player);
            giveItem(player, BleachUHCItemConstants.TROIS_CIEUX);
        }
    }

    public static void give4Cieux(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        if (player != null) {
            removeHogyokuInactif(player);
            giveItem(player, BleachUHCItemConstants.QUATRE_CIEUX);
        }
    }

    public static void dashShinigami(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        if (player != null) {
            giveItem(player, BleachUHCItemConstants.DASH_SHINIGAMI);
        }
    }

    public static void dashQuincy(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        if (player != null) {
            giveItem(player, BleachUHCItemConstants.DASH_QUINCY);
        }
    }

    public static void spawnHogyokuInactifFragment(Location location) {
        location.getWorld().dropItemNaturally(location, BleachUHCItemConstants.FRAGMENT_HOGYOKU_INACTIF);
    }

    public static void giveHogyokuActifFragment(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        if (player != null) {
            player.getInventory().remove(player.getInventory().getItemInMainHand());
            giveItem(player, BleachUHCItemConstants.FRAGMENT_HOGYOKU_ACTIF);
        }
    }

    public static void spawnHogyokuCoeur(Location location) {
        location.getWorld().dropItemNaturally(location, BleachUHCItemConstants.COEUR_HOGYOKU);
    }

    public static void giveHogyokuInactif(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        if (player != null) {
            giveItem(player, BleachUHCItemConstants.HOGYOKU_INACTIF);
            player.setCooldown(BleachUHCItemConstants.HOGYOKU_INACTIF.getType(), 6000);
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
            giveItem(player, BleachUHCItemConstants.HOGYOKU_ACTIF);
        }
    }

    public static void spawnRyujinJakka(Location location) {
        location.getWorld().dropItemNaturally(location, BleachUHCItemConstants.RYUJIN_JAKKA);
    }

    public static void spawnArtDuHakuda(Location location) {
        location.getWorld().dropItemNaturally(location, BleachUHCItemConstants.ART_DU_HAKUDA);
    }

    public static void spawnMinazuki(Location location) {
        location.getWorld().dropItemNaturally(location, BleachUHCItemConstants.MINAZUKI);
    }

    public static void spawnSenbonzakura(Location location) {
        location.getWorld().dropItemNaturally(location, BleachUHCItemConstants.SENBONZAKURA);
    }

    public static void spawnAshisogiJizo(Location location) {
        location.getWorld().dropItemNaturally(location, BleachUHCItemConstants.ASHISOGI_JIZO);
    }

    public static void spawnKenpachiSword(Location location) {
        location.getWorld().dropItemNaturally(location, BleachUHCItemConstants.KENPACHI_SWORD);
    }

    public static void spawnTengen(Location location) {
        location.getWorld().dropItemNaturally(location, BleachUHCItemConstants.TENGEN);
    }

    public static void spawnMedicament(Location location) {
        location.getWorld().dropItemNaturally(location, BleachUHCItemConstants.MEDICAMENT);
    }

    public static void spawnSuzumebachi(Location location) {
        location.getWorld().dropItemNaturally(location, BleachUHCItemConstants.SUZUMEBACHI);
    }

    public static void spawnShinso(Location location) {
        location.getWorld().dropItemNaturally(location, BleachUHCItemConstants.SHINSO);
    }

    public static void spawnAveuxDeGin(Location location) {
        location.getWorld().dropItemNaturally(location, BleachUHCItemConstants.AVEUX);
    }

    public static void spawnBaveMinazuki(Location location) {
        location.getWorld().dropItemNaturally(location, BleachUHCItemConstants.BAVE_DE_MINAZUKI);
    }

    public static void spawnSuzumuchi(Location location) {
        location.getWorld().dropItemNaturally(location, BleachUHCItemConstants.SUZUMUCHI);
    }

    public static void spawnPhotoYoruichi(Location location) {
        location.getWorld().dropItemNaturally(location, BleachUHCItemConstants.PHOTO_YORUICHI);
    }

    public static void spawnLysDesNeiges(Location location) {
        location.getWorld().dropItemNaturally(location, BleachUHCItemConstants.LYS_DES_NEIGES);
    }

    public static void spawnHyorinmaru(Location location) {
        location.getWorld().dropItemNaturally(location, BleachUHCItemConstants.HYORINMARU);
    }

    public static void spawnLunettesTosen(Location location) {
        location.getWorld().dropItemNaturally(location, BleachUHCItemConstants.LUNETTES_TOSEN);
    }

    public static void spawnKatenKyokotsu(Location location) {
        location.getWorld().dropItemNaturally(location, BleachUHCItemConstants.KATEN_KYOKOTSU);
    }

    public static void spawnGantDeSanrei(Location location) {
        location.getWorld().dropItemNaturally(location, BleachUHCItemConstants.GANT_DE_SANREI);
    }
}
