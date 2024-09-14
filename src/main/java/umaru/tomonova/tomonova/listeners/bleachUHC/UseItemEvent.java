package umaru.tomonova.tomonova.listeners.bleachUHC;

import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.mobs.ActiveMob;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.core.task.bleachUHCTask.*;
import umaru.tomonova.tomonova.gamemode.bleachUHC.GiveItem;
import umaru.tomonova.tomonova.gamemode.bleachUHC.items.*;
import umaru.tomonova.tomonova.gui.gamemodegui.bleachUHC.BossesGui;
import umaru.tomonova.tomonova.gui.gamemodegui.bleachUHC.YachiruGui;
import umaru.tomonova.tomonova.utils.bleachUHC.sounds.SoundsConstants;
import umaru.tomonova.tomonova.utils.bleachUHC.sounds.SoundsUtils;
import umaru.tomonova.tomonova.utils.constants.BleachUHCConstants;
import umaru.tomonova.tomonova.utils.cooldowns.CooldownManager;

import java.util.Objects;

public class UseItemEvent implements Listener {

    private final TomoNova tomoNova = TomoNova.getPlugin();
    private final CooldownManager cooldownManager = tomoNova.cooldownManager; // Initialize CooldownManager

    @EventHandler
    public void onClickBleachUHC(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (isInvalidItem(player)) return;

        ItemStack item = player.getInventory().getItemInMainHand();
        int customModelData = Objects.requireNonNull(item.getItemMeta()).getCustomModelData();

        // Check if the item is on cooldown
        if (cooldownManager.isCooldown(player, item)) {
            player.sendMessage("This item is on cooldown!");
            return; // Stop the execution if the item is on cooldown
        }

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
        ItemStack item = player.getInventory().getItemInMainHand();
        boolean removeItem = false;


        switch (customModelData) {
            case BleachUHCConstants.BOUSSOLE:
                if (isRightClick(action)) {
                    new BossesGui(player).show();
                }
                break;
            case BleachUHCConstants.BOUTIQUE_YACHIRU:
                if (isRightClick(action)) {
                    new YachiruGui(player).show();
                    player.sendMessage(BleachUHCConstants.BOUTIQUE_YACHIRU_NAME);
                }
                break;
            case BleachUHCConstants.DASH_SHINIGAMI:
                if (isRightClick(action) && tomoNova.classesUtils.isPlayerShinigami(player.getName())) {
                    tomoNova.classesSpells.dash(3, player.getName());
                    player.sendMessage(BleachUHCConstants.DASH_SHINIGAMI_NAME);
                    cooldownManager.startCooldown(player, item);
                    SoundsUtils.playSoundIfInRange(player.getLocation(),SoundsConstants.PLAYER_SHINIGAMI_SHUNPO,10);
                }
                break;
            case BleachUHCConstants.SHINSO:
                if (isRightClick(action) && tomoNova.classesUtils.isPlayerShinigami(player.getName())) {
                    Shinso.Shinso(player.getName());
                    player.sendMessage(BleachUHCConstants.SHINSO_NAME);
                    cooldownManager.startCooldown(player, item);
                }
                break;
            case BleachUHCConstants.SOGYO_NO_KOTOWARI:
                if (isRightClick(action) && tomoNova.classesUtils.isPlayerShinigami(player.getName())) {
                    tomoNova.classesSpells.sogyoNoKotowari(player.getName());
                    player.sendMessage(BleachUHCConstants.SOGYO_NO_KOTOWARI_NAME);
                    cooldownManager.startCooldown(player, item);
                }
                break;
            case BleachUHCConstants.SENBONZAKURA:
                if (isRightClick(action) && tomoNova.classesUtils.isPlayerShinigami(player.getName())) {
                    new SenbonzakuraTask(tomoNova, player.getName()).runTaskTimer(tomoNova, 0, 20);
                    player.sendMessage(BleachUHCConstants.SENBONZAKURA_NAME);
                    cooldownManager.startCooldown(player, item);
                }
                break;
            case BleachUHCConstants.TENGEN:
                if (isRightClick(action) && tomoNova.classesUtils.isPlayerShinigami(player.getName())) {
                    Tengen.tengen(player.getName());
                    player.sendMessage(BleachUHCConstants.TENGEN_NAME);
                    cooldownManager.startCooldown(player, item);
                }

                break;
            case BleachUHCConstants.HYORINMARU:
                if (isRightClick(action) && tomoNova.classesUtils.isPlayerShinigami(player.getName())) {
                    Hyorinmaru.hyorinmaru(player.getName());
                    player.sendMessage(BleachUHCConstants.HYORINMARU_NAME);
                    cooldownManager.startCooldown(player, item);
                }

                break;
            case BleachUHCConstants.CARQUOIS:
                if (isRightClick(action) && tomoNova.classesUtils.isPlayerQuincy(player.getName())) {
                    tomoNova.classesSpells.carquois(player.getName());
                    tomoNova.classesSpells.carquois(player.getName());
                    player.sendMessage(BleachUHCConstants.CARQUOIS_NAME);
                    cooldownManager.startCooldown(player, item);
                    SoundsUtils.playSoundIfInRange(player.getLocation(),SoundsConstants.PLAYER_QUINCY_CARQUOIS,10);
                }

                break;
            case BleachUHCConstants.DASH_QUINCY:
                if (isRightClick(action) && tomoNova.classesUtils.isPlayerQuincy(player.getName())) {
                    tomoNova.classesSpells.dash(5, player.getName());
                    player.sendMessage(BleachUHCConstants.DASH_QUINCY_NAME);
                    cooldownManager.startCooldown(player, item);
                    SoundsUtils.playSoundIfInRange(player.getLocation(),SoundsConstants.PLAYER_QUINCY_DASH,10);
                }
                break;
            case BleachUHCConstants.GANT_DE_SANREI:
                if (isRightClick(action) && tomoNova.classesUtils.isPlayerQuincy(player.getName())) {
                    GantDeSanrei.gantDeSanrei(player.getName());
                    player.sendMessage(BleachUHCConstants.GANT_DE_SANREI_NAME);
                    removeItem = true;
                }
                break;
            case BleachUHCConstants.CIEL_UNIQUE:
                if (isRightClick(action) && tomoNova.classesUtils.isPlayerSSR(player.getName())) {
                    tomoNova.classesSpells.cielUnique(player.getName());
                    player.sendMessage(BleachUHCConstants.CIEL_UNIQUE_NAME);
                    cooldownManager.startCooldown(player, item);
                    SoundsUtils.playSoundIfInRange(player.getLocation(), SoundsConstants.SSR_CIEL1,15);
                }
                break;
            case BleachUHCConstants.DEUX_CIEUX:
                if (isRightClick(action) && tomoNova.classesUtils.isPlayerSSR(player.getName())) {
                    tomoNova.classesSpells.deuxCieux(player.getName());
                    player.sendMessage(BleachUHCConstants.DEUX_CIEUX_NAME);
                    cooldownManager.startCooldown(player, item);
                    SoundsUtils.playSoundIfInRange(player.getLocation(), SoundsConstants.SSR_CIEL2,15);
                }
                break;
            case BleachUHCConstants.TROIS_CIEUX:
                if (isRightClick(action) && tomoNova.classesUtils.isPlayerSSR(player.getName())) {
                    tomoNova.classesSpells.troisCieux(player.getName());
                    player.sendMessage(BleachUHCConstants.TROIS_CIEUX_NAME);
                    cooldownManager.startCooldown(player, item);
                    SoundsUtils.playSoundIfInRange(player.getLocation(), SoundsConstants.SSR_CIEL3,15);
                }
                break;
            case BleachUHCConstants.QUATRE_CIEUX:
                if (isRightClick(action) && tomoNova.classesUtils.isPlayerSSR(player.getName())) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE, 100, 0, false, false, false));
                    player.sendMessage(BleachUHCConstants.QUATRE_CIEUX_NAME);
                    cooldownManager.startCooldown(player, item);
                    SoundsUtils.playSoundIfInRange(player.getLocation(), SoundsConstants.SSR_CIEL4,15);
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
                if (isRightClick(action) && tomoNova.classesUtils.isPlayerBrazo(player.getName())) {
                    tomoNova.classesUtils.playerHakudaUpgrade(player.getName());
                    removeItem = true;
                    player.sendMessage(BleachUHCConstants.ART_DU_HAKUDA_NAME);
                }
                break;
            case BleachUHCConstants.FRAGMENT_HOGYOKU_INACTIF:
                if (isRightClick(action)) {
                    removeItem = true;
                    GiveItem.giveHogyokuActifFragment(player.getName());
                    //Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() - 10);
                    player.sendMessage(BleachUHCConstants.FRAGMENT_HOGYOKU_INACTIF_NAME);
                }
                break;
            case BleachUHCConstants.AVEUX_DE_GIN:
                if (isRightClick(action)) {
                    handleAizenProximity(player);
                    player.sendMessage(BleachUHCConstants.AVEUX_DE_GIN_NAME);
                }
                break;
            case BleachUHCConstants.SAKE_DE_KYORAKU:
                if (isRightClick(action)) {
                    SakeKyoraku.sakeTeleport(player.getName(), tomoNova.classesSpells.getEntityInSight(player, 50).getName());
                    player.sendMessage(BleachUHCConstants.SAKE_DE_KYORAKU_NAME);
                    cooldownManager.startCooldown(player, item);
                }
                break;
            case BleachUHCConstants.MEDICAMENTS:
                if (isRightClick(action)) {
                    new MedicamentUkitakeTask(tomoNova, player.getName()).runTaskTimer(tomoNova, 0, 10);
                    player.sendMessage(BleachUHCConstants.MEDICAMENTS_NAME);
                    cooldownManager.startCooldown(player, item);
                }
                break;
            case BleachUHCConstants.PHOTO_DE_YORUICHI:
                if (isRightClick(action)) {
                    PhotoDeYoruichi.PhotoDeYoruichi(player.getName());
                    removeItem = true;
                    player.sendMessage(BleachUHCConstants.PHOTO_DE_YORUICHI_NAME);
                }
                break;
            case BleachUHCConstants.LYS_DES_NEIGES:
                if (isRightClick(action)) {
                    LysDesNeiges.lysDesNeiges(player.getName());
                    player.sendMessage(BleachUHCConstants.LYS_DES_NEIGES_NAME);
                    cooldownManager.startCooldown(player, item);
                }
                break;
            case BleachUHCConstants.LUNETTES_DE_TOSEN:
                if (isRightClick(action)) {
                    tomoNova.bleachUHC.initializeLunettesBoolean();
                    new LunetteDeTosenTask(TomoNova.getPlugin(), player.getName()).runTaskTimer(TomoNova.getPlugin(), 0, 20);
                    player.sendMessage(BleachUHCConstants.LUNETTES_DE_TOSEN_NAME);
                    cooldownManager.startCooldown(player, item);
                }
                break;
            case BleachUHCConstants.HOGYOKU_ACTIF:
                if (isRightClick(action)) {
                    new HogyokuActifTask(tomoNova, player.getName()).runTaskTimer(tomoNova, 0, 20);
                    player.sendMessage(BleachUHCConstants.HOGYOKU_ACTIF_NAME);
                }
                break;
            case BleachUHCConstants.CACHE_OEIL:
                if(isRightClick(action)) {
                    String playerWithMostKillsName = tomoNova.killCounter.getTopKiller(player.getName());
                    player.sendMessage(BleachUHCConstants.CACHE_OEIL_NAME);
                    tomoNova.bleachUHC.setCacheOeilBoolen(player.getName());
                    tomoNova.bleachUHC.setTrackedBoolen(playerWithMostKillsName);
                    removeItem = true;
                }
            case BleachUHCConstants.KYOKA_SUIGETSU:
                if(isRightClick(action)) {
                    KyokaSuigetsu.applyNauseaToEnemies(player.getName());
                    player.sendMessage(BleachUHCConstants.KYOKA_SUIGETSU_NAME);
                    cooldownManager.startCooldown(player, item);
                }
            case BleachUHCConstants.COEUR_HOGYOKU:
                if(isRightClick(action)){
                    handleCoeurHogyoku(player);
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

    private void handleCoeurHogyoku(Player player){
        Player nearestPlayer = player;
        int distanceMin = Integer.MAX_VALUE;
        for(Player players : Bukkit.getOnlinePlayers()){
            for (ItemStack items : player.getInventory().getContents()) {
                if (items != null && items.hasItemMeta()) {
                    ItemMeta meta = items.getItemMeta();
                    if (meta.hasCustomModelData() &&
                            (meta.getCustomModelData() == BleachUHCConstants.FRAGMENT_HOGYOKU_INACTIF
                                    || meta.getCustomModelData() == BleachUHCConstants.FRAGMENT_HOGYOKU_ACTIF)) {
                        if(player.getLocation().distance(players.getLocation()) < distanceMin){
                            nearestPlayer = players;
                            distanceMin = (int) player.getLocation().distance(players.getLocation());
                        }
                    }
                }
            }
        }
        if(!player.equals(nearestPlayer)){
            tomoNova.bleachUHC.setPlayerWithHogyokuHeart(player.getName());
            tomoNova.bleachUHC.setNearestPlayerWithHogyokuFragment(nearestPlayer.getName());
        } else {
            tomoNova.bleachUHC.setPlayerWithHogyokuHeart("None");
            tomoNova.bleachUHC.setNearestPlayerWithHogyokuFragment("None");
        }
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

        if (action == Action.RIGHT_CLICK_BLOCK && player.getInventory().getItemInMainHand().getType() == Material.STONE_AXE && !tomoNova.combatZoneCreationUtils.isFirstPoint()) {
            event.setCancelled(true);
            tomoNova.combatZoneCreationUtils.setFirstPoint(blockLoc.getBlockX(), blockLoc.getBlockZ());
            tomoNova.combatZoneCreationUtils.setActualPoint(blockLoc.getBlockX(), blockLoc.getBlockZ());
        }

        if (action == Action.LEFT_CLICK_BLOCK && player.getInventory().getItemInMainHand().getType() == Material.STONE_AXE && tomoNova.combatZoneCreationUtils.isFirstPoint()) {
            event.setCancelled(true);
            tomoNova.combatZoneCreationUtils.addLine(blockLoc.getBlockX(), blockLoc.getBlockZ());
            if (tomoNova.combatZoneCreationUtils.isClosedShape()) {
                tomoNova.combatZoneCreationUtils.sortingContours();
            }
        }
    }
}
