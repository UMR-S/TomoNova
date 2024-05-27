package umaru.tomonova.tomonova.listeners.bleachUHC;

import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.mobs.ActiveMob;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.core.task.bleachUHCTask.*;
import umaru.tomonova.tomonova.gamemode.bleachUHC.GiveItem;
import umaru.tomonova.tomonova.gamemode.bleachUHC.items.*;
import umaru.tomonova.tomonova.gui.gamemodegui.bleachUHC.YachiruGui;
import umaru.tomonova.tomonova.utils.bleachUHC.classes.ClassesUtils;
import umaru.tomonova.tomonova.utils.constants.BleachUHCConstants;

import java.util.Objects;

public class UseItemEvent implements Listener {

    private final TomoNova tomoNova = TomoNova.getPlugin();

    @EventHandler
    public void onClickBleachUHC(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (isInvalidItem(player)) return;

        int customModelData = Objects.requireNonNull(player.getInventory().getItemInMainHand().getItemMeta()).getCustomModelData();
        boolean removeItem = handlePlayerActions(event, player, customModelData);

        if (removeItem) {
            player.getInventory().remove(player.getInventory().getItemInMainHand());
        }
    }

    private boolean isInvalidItem(Player player) {
        return player.getInventory().getItemInMainHand() == null
                || player.getInventory().getItemInMainHand().getType().equals(Material.AIR)
                || !player.getInventory().getItemInMainHand().hasItemMeta()
                || !Objects.requireNonNull(player.getInventory().getItemInMainHand().getItemMeta()).hasCustomModelData();
    }

    private boolean handlePlayerActions(PlayerInteractEvent event, Player player, int customModelData) {
        Action action = event.getAction();
        boolean removeItem = false;

        switch (customModelData) {
            case BleachUHCConstants.BOUTIQUE_YACHIRU:
                if (isRightClick(action) && !player.hasCooldown(Material.CARROT_ON_A_STICK)) {
                    new YachiruGui(player).show();
                    player.sendMessage(BleachUHCConstants.BOUTIQUE_YACHIRU_NAME);
                }
                break;
            case BleachUHCConstants.DASH_SHINIGAMI:
                if (isRightClick(action) && !player.hasCooldown(Material.CARROT_ON_A_STICK) && tomoNova.classesUtils.isPlayerShinigami(player.getName())) {
                    tomoNova.classesSpells.dash(3, player.getName());
                    player.sendMessage(BleachUHCConstants.DASH_SHINIGAMI_NAME);
                }
                break;
            case BleachUHCConstants.SHINSO:
                if (isRightClick(action) && tomoNova.classesUtils.isPlayerShinigami(player.getName())) {
                    Shinso.Shinso(player.getName());
                    player.sendMessage(BleachUHCConstants.SHINSO_NAME);
                }
                break;
            case BleachUHCConstants.SOGYO_NO_KOTOWARI:
                if (isRightClick(action) && tomoNova.classesUtils.isPlayerShinigami(player.getName())) {
                    tomoNova.classesSpells.sogyoNoKotowari(player.getName());
                    player.sendMessage(BleachUHCConstants.SOGYO_NO_KOTOWARI_NAME);
                }
                break;
            case BleachUHCConstants.SENBONZAKURA:
                if (isRightClick(action) && tomoNova.classesUtils.isPlayerShinigami(player.getName())) {
                    new SenbonzakuraTask(tomoNova, player.getName()).runTaskTimer(tomoNova, 0, 20);
                    player.sendMessage(BleachUHCConstants.SENBONZAKURA_NAME);
                }
                break;
            case BleachUHCConstants.TENGEN:
                if (isRightClick(action) && tomoNova.classesUtils.isPlayerShinigami(player.getName())) {
                    Tengen.tengen(player.getName());
                    player.sendMessage(BleachUHCConstants.TENGEN_NAME);
                }
                break;
            case BleachUHCConstants.HYORINMARU:
                if (isRightClick(action) && !player.hasCooldown(Material.IRON_SWORD) && tomoNova.classesUtils.isPlayerShinigami(player.getName())) {
                    Hyorinmaru.hyorinmaru(player.getName());
                    player.sendMessage(BleachUHCConstants.HYORINMARU_NAME);
                }
                break;
            case BleachUHCConstants.CARQUOIS:
                if (isRightClick(action) && !player.hasCooldown(Material.CARROT_ON_A_STICK) && tomoNova.classesUtils.isPlayerQuincy(player.getName())) {
                    tomoNova.classesSpells.carquois(player.getName());
                    player.sendMessage(BleachUHCConstants.CARQUOIS_NAME);
                }
                break;
            case BleachUHCConstants.DASH_QUINCY:
                if (isRightClick(action) && !player.hasCooldown(Material.CARROT_ON_A_STICK) && tomoNova.classesUtils.isPlayerQuincy(player.getName())) {
                    tomoNova.classesSpells.dash(5, player.getName());
                    player.sendMessage(BleachUHCConstants.DASH_QUINCY_NAME);
                }
                break;
            case BleachUHCConstants.GANT_DE_SANREI:
                if (isRightClick(action) && !player.hasCooldown(Material.CARROT_ON_A_STICK) && tomoNova.classesUtils.isPlayerQuincy(player.getName())) {
                    GantDeSanrei.gantDeSanrei(player.getName());
                    player.sendMessage(BleachUHCConstants.GANT_DE_SANREI_NAME);
                }
                break;
            case BleachUHCConstants.CIEL_UNIQUE:
                if (isRightClick(action) && !player.hasCooldown(Material.CARROT_ON_A_STICK) && tomoNova.classesUtils.isPlayerSSR(player.getName())) {
                    tomoNova.classesSpells.cielUnique(player.getName());
                    player.sendMessage(BleachUHCConstants.CIEL_UNIQUE_NAME);
                }
                break;
            case BleachUHCConstants.DEUX_CIEUX:
                if (isRightClick(action) && !player.hasCooldown(Material.NAUTILUS_SHELL) && tomoNova.classesUtils.isPlayerSSR(player.getName())) {
                    tomoNova.classesSpells.deuxCieux(player.getName());
                    player.sendMessage(BleachUHCConstants.DEUX_CIEUX_NAME);
                }
                break;
            case BleachUHCConstants.TROIS_CIEUX:
                if (isRightClick(action) && !player.hasCooldown(Material.PHANTOM_MEMBRANE) && tomoNova.classesUtils.isPlayerSSR(player.getName())) {
                    tomoNova.classesSpells.troisCieux(player.getName());
                    player.sendMessage(BleachUHCConstants.TROIS_CIEUX_NAME);
                }
                break;
            case BleachUHCConstants.QUATRE_CIEUX:
                if (isRightClick(action) && !player.hasCooldown(Material.HEART_OF_THE_SEA) && tomoNova.classesUtils.isPlayerSSR(player.getName())) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE, 100, 0, false, false, false));
                    player.sendMessage(BleachUHCConstants.QUATRE_CIEUX_NAME);
                }
                break;
            case BleachUHCConstants.BAVE_DE_MINAZUKI:
                if (isRightClick(action) && tomoNova.classesUtils.isPlayerSSR(player.getName()) && !tomoNova.classesSpells.isMinazukiActive() && tomoNova.classesSpells.getMinazukiCharges() > 0) {
                    tomoNova.classesSpells.setMinazukiActive(true);
                    tomoNova.classesSpells.setMinazukiCharges(tomoNova.classesSpells.getMinazukiCharges() - 1);
                    new BaveMinazukiTask(tomoNova, player.getName()).runTaskTimer(tomoNova, 0, 20);
                    player.sendMessage(BleachUHCConstants.BAVE_DE_MINAZUKI_NAME);
                }
                break;
            case BleachUHCConstants.ART_DU_HAKUDA:
                if (isRightClick(action) && !player.hasCooldown(Material.CARROT_ON_A_STICK) && tomoNova.classesUtils.isPlayerBrazo(player.getName())) {
                    tomoNova.classesUtils.playerHakudaUpgrade(player.getName());
                    removeItem = true;
                    player.sendMessage(BleachUHCConstants.ART_DU_HAKUDA_NAME);
                }
                break;
            case BleachUHCConstants.FRAGMENT_HOGYOKU_INACTIF:
                if (isRightClick(action) && !player.hasCooldown(Material.GLOWSTONE_DUST)) {
                    removeItem = true;
                    GiveItem.giveHogyokuActifFragment(player.getName());
                    Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() - 10);
                    player.sendMessage(BleachUHCConstants.FRAGMENT_HOGYOKU_INACTIF_NAME);
                }
                break;
            case BleachUHCConstants.AVEUX_DE_GIN:
                if (isRightClick(action) && !player.hasCooldown(Material.CARROT_ON_A_STICK)) {
                    handleAizenProximity(player);
                    player.sendMessage(BleachUHCConstants.AVEUX_DE_GIN_NAME);
                }
                break;
            case BleachUHCConstants.SAKE_DE_KYORAKU:
                if (isRightClick(action) && !player.hasCooldown(Material.CARROT_ON_A_STICK)) {
                    SakeKyoraku.sakeTeleport(player.getName(), tomoNova.classesSpells.getEntityInSight(player, 50).getName());
                    player.sendMessage(BleachUHCConstants.SAKE_DE_KYORAKU_NAME);
                }
                break;
            case BleachUHCConstants.MEDICAMENTS:
                if (isRightClick(action) && !player.hasCooldown(Material.CARROT_ON_A_STICK)) {
                    new MedicamentUkitakeTask(tomoNova, player.getName()).runTaskTimer(tomoNova, 0, 10);
                    player.sendMessage(BleachUHCConstants.MEDICAMENTS_NAME);
                }
                break;
            case BleachUHCConstants.PHOTO_DE_YORUICHI:
                if (isRightClick(action) && !player.hasCooldown(Material.CARROT_ON_A_STICK)) {
                    PhotoDeYoruichi.PhotoDeYoruichiUse(player.getName());
                    removeItem = true;
                    player.sendMessage(BleachUHCConstants.PHOTO_DE_YORUICHI_NAME);
                }
                break;
            case BleachUHCConstants.LYS_DES_NEIGES:
                if (isRightClick(action) && !player.hasCooldown(Material.CARROT_ON_A_STICK)) {
                    LysDesNeiges.lysDesNeiges(player.getName());
                    player.sendMessage(BleachUHCConstants.LYS_DES_NEIGES_NAME);
                }
                break;
            case BleachUHCConstants.LUNETTES_DE_TOSEN:
                if (isRightClick(action) && !player.hasCooldown(Material.CARROT_ON_A_STICK)) {
                    tomoNova.bleachUHC.initializeLunettesBoolean();
                    new LunetteDeTosenTask(TomoNova.getPlugin(), player.getName()).runTaskTimer(TomoNova.getPlugin(), 0, 20);
                    player.sendMessage(BleachUHCConstants.LUNETTES_DE_TOSEN_NAME);
                }
                break;
            case BleachUHCConstants.HOGYOKU_ACTIF:
                if (isRightClick(action) && !player.hasCooldown(Material.TOTEM_OF_UNDYING)) {
                    new HogyokuActifTask(tomoNova, player.getName()).runTaskTimer(tomoNova, 0, 20);
                    player.sendMessage(BleachUHCConstants.HOGYOKU_ACTIF_NAME);
                }
                break;
            case 1000:
                handleCombatZone(event, player);
                break;
            default:
                break;
        }
        return removeItem;
    }

    private boolean isRightClick(Action action) {
        return action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK;
    }

    private void handleAizenProximity(Player player) {
        for (ActiveMob boss : MythicBukkit.inst().getMobManager().getActiveMobs()) {
            if (boss.getName().equals(BleachUHCConstants.AIZEN_V2_NAME) && player.getLocation().distance(boss.getLocation().toPosition().toLocation()) <= 30.0) {
                boss.getEntity().setHealth(boss.getEntity().getHealth() - boss.getEntity().getMaxHealth() * 0.5);
            }
        }
    }

    private void handleCombatZone(PlayerInteractEvent event, Player player) {
        Action action = event.getAction();
        Location blockLoc = event.getClickedBlock().getLocation();

        if (action == Action.RIGHT_CLICK_BLOCK && player.getInventory().getItemInMainHand().getType() == Material.STONE_AXE && !tomoNova.combatzoneUtils.isFirstPoint()) {
            event.setCancelled(true);
            tomoNova.combatzoneUtils.setFirstPoint(blockLoc.getBlockX(), blockLoc.getBlockZ());
            tomoNova.combatzoneUtils.setActualPoint(blockLoc.getBlockX(), blockLoc.getBlockZ());
        }

        if (action == Action.LEFT_CLICK_BLOCK && player.getInventory().getItemInMainHand().getType() == Material.STONE_AXE && tomoNova.combatzoneUtils.isFirstPoint()) {
            event.setCancelled(true);
            tomoNova.combatzoneUtils.addLine(blockLoc.getBlockX(), blockLoc.getBlockZ());
            if (tomoNova.combatzoneUtils.isClosedShape()) {
                tomoNova.combatzoneUtils.sortingContours();
            }
        }
    }
}
