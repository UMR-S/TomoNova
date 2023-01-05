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
import org.bukkit.scheduler.BukkitTask;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.core.task.bleachUHCTask.*;
import umaru.tomonova.tomonova.gamemode.bleachUHC.GiveItem;
import umaru.tomonova.tomonova.gamemode.bleachUHC.items.*;

public class UseItemEvent implements Listener {

    TomoNova tomoNova = TomoNova.getPlugin();

    @EventHandler
    public void onClickBleachUHC(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        boolean removeItem = false;
        if (!(player.getInventory().getItemInMainHand() == null || player.getInventory().getItemInMainHand().getType().equals(Material.AIR))) {
            if (player.getInventory().getItemInMainHand().hasItemMeta()) {
                if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {

                    //Shinigami
                    // Dash shinigami

                    if ((event.getAction() == Action.RIGHT_CLICK_AIR
                            || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                            && !player.hasCooldown(Material.CARROT_ON_A_STICK)
                            && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000603
                            && tomoNova.classesUtils.isPlayerClasse(player.getName(), "shinigami")) {

                        tomoNova.classesSpells.Dash(3, player.getName());
                        player.sendMessage("Dash Shinigami");

                    }
                    // Attaque arme de Gin
                    if ((event.getAction() == Action.RIGHT_CLICK_AIR
                            || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                            && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1031106
                            && tomoNova.classesUtils.isPlayerClasse(player.getName(), "shinigami")) {

                        Shinso.Shinso(player.getName());
                        player.sendMessage("Shinso");

                    }
                    // Attaque de l'arme d'Ukitake
                    if ((event.getAction() == Action.RIGHT_CLICK_AIR
                            || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                            && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1131116
                            && tomoNova.classesUtils.isPlayerClasse(player.getName(), "shinigami")) {
                        tomoNova.classesSpells.sogyoNoKotowari(player.getName());
                        player.sendMessage("Sogyo no kotawari");
                    }
                    // Attaque de l'arme de Byakuya (senbonzakura)
                    if ((event.getAction() == Action.RIGHT_CLICK_AIR
                            || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                            && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1061109
                            && tomoNova.classesUtils.isPlayerClasse(player.getName(), "shinigami")) {
                        BukkitTask senbonzakuraActive = new SenbonzakuraTask(tomoNova, player.getName()).runTaskTimer(tomoNova, 0, 20);
                        player.sendMessage("Senbonzakura");
                    }
                    // Tengen (invocation samourai)
                    if ((event.getAction() == Action.RIGHT_CLICK_AIR
                            || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                            && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1071110
                            && tomoNova.classesUtils.isPlayerClasse(player.getName(), "shinigami")) {
                        Tengen.tengen(player.getName());
                        player.sendMessage("Tengen");
                    }
                    //Hyorinmaru
                    if ((event.getAction() == Action.RIGHT_CLICK_AIR
                            || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                            && !player.hasCooldown(Material.IRON_SWORD)
                            && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1101113
                            && tomoNova.classesUtils.isPlayerClasse(player.getName(), "shinigami")) {
                        Hyorinmaru.hyorinmaru(player.getName());
                        player.sendMessage("Hyorinmaru");
                    }
                    //Quincy
                    //Dash quincy
                    if ((event.getAction() == Action.RIGHT_CLICK_AIR
                            || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                            && !player.hasCooldown(Material.CARROT_ON_A_STICK)
                            && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2000301
                            && tomoNova.classesUtils.isPlayerClasse(player.getName(), "quincy")) {

                        tomoNova.classesSpells.Dash(5, player.getName());
                        player.sendMessage("Dash Quincy");
                    }
                    //Gant de Sanrei
                    if ((event.getAction() == Action.RIGHT_CLICK_AIR
                            || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                            && !player.hasCooldown(Material.CARROT_ON_A_STICK)
                            && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2122605
                            && tomoNova.classesUtils.isPlayerClasse(player.getName(), "quincy")) {

                        GantDeSanrei.gantDeSanrei(player.getName());
                        player.sendMessage("Gant de Sanrei");
                    }
                    //SSR
                    // Ciel Unique
                    if ((event.getAction() == Action.RIGHT_CLICK_AIR
                            || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                            && !player.hasCooldown(Material.CARROT_ON_A_STICK)
                            && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 3000301
                            && tomoNova.classesUtils.isPlayerClasse(player.getName(), "ssr")) {

                        tomoNova.classesSpells.cielUnique(player.getName());
                        player.sendMessage("Ciel unique");

                    }
                    // 2 Cieux
                    if ((event.getAction() == Action.RIGHT_CLICK_AIR
                            || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                            && !player.hasCooldown(Material.NAUTILUS_SHELL)
                            && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 3000302
                            && tomoNova.classesUtils.isPlayerClasse(player.getName(), "ssr")) {

                        tomoNova.classesSpells.deuxCieux(player.getName());
                        player.sendMessage("Deux Cieux");

                    }
                    // 3 Cieux
                    if ((event.getAction() == Action.RIGHT_CLICK_AIR
                            || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                            && !player.hasCooldown(Material.PHANTOM_MEMBRANE)
                            && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 3000303
                            && tomoNova.classesUtils.isPlayerClasse(player.getName(), "ssr")) {

                        tomoNova.classesSpells.troisCieux(player.getName());
                        player.sendMessage("Trois cieux");

                    }
                    // 4 Cieux
                    if ((event.getAction() == Action.RIGHT_CLICK_AIR
                            || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                            && !player.hasCooldown(Material.HEART_OF_THE_SEA)
                            && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 3000304
                            && tomoNova.classesUtils.isPlayerClasse(player.getName(), "ssr")) {

                        player.addPotionEffect(new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE, 100, 0, false, false, false));
                        player.sendMessage("Quatre cieux");
                    }
                    //Bave de minazuki
                    if ((event.getAction() == Action.RIGHT_CLICK_AIR
                            || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                            && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 3042405
                            && tomoNova.classesUtils.isPlayerClasse(player.getName(), "ssr")
                            && !tomoNova.classesSpells.isMinazukiActive()
                            && tomoNova.classesSpells.getMinazukiCharges() > 0) {
                        tomoNova.classesSpells.setMinazukiActive(true);
                        tomoNova.classesSpells.setMinazukiCharges(tomoNova.classesSpells.getMinazukiCharges() - 1);
                        BukkitTask baveMinazuki = new BaveMinazukiTask(tomoNova, player.getName()).runTaskTimer(tomoNova, 0, 20);
                        player.sendMessage("Bave de minazuki");

                    }
                    //Brazo
                    //Art du Hakuda
                    if ((event.getAction() == Action.RIGHT_CLICK_AIR
                            || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                            && !player.hasCooldown(Material.CARROT_ON_A_STICK)
                            && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4012602
                            && tomoNova.classesUtils.isPlayerClasse(player.getName(), "brazo")) {
                        tomoNova.classesUtils.playerHakudaUpgrade(player.getName());
                        removeItem = true;
                        player.sendMessage("Art Du Hakuda");
                    }
                    //Toute classe
                    //Activation fragment inactif du Hogyoku
                    if ((event.getAction() == Action.RIGHT_CLICK_AIR
                            || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                            && !player.hasCooldown(Material.GLOWSTONE_DUST)
                            && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5149609) {
                        removeItem = true;
                        GiveItem.giveHogyokuActifFragment(event.getPlayer().getName());
                        event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() - 10);
                        player.sendMessage("Hogyoku Inactif");
                    }
                    //Aveux de Gin
                    if ((event.getAction() == Action.RIGHT_CLICK_AIR
                            || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                            && !player.hasCooldown(Material.CARROT_ON_A_STICK)
                            && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5032602) {
                        for (ActiveMob boss : MythicBukkit.inst().getMobManager().getActiveMobs()) {
                            if (boss.getName().equals("aizenv2")) {
                                //Creation d'une loc normale
                                Location bossLoc = new Location(tomoNova.worldUtils.getWorld(),
                                        boss.getLocation().clone().getX(),
                                        boss.getLocation().clone().getY(),
                                        boss.getLocation().clone().getZ());
                                //Activer si Aizen est proche
                                if (player.getLocation().distance(bossLoc) <= 30.0) {
                                    boss.getEntity().setHealth(boss.getEntity().getHealth() - boss.getEntity().getMaxHealth() * 0.5);
                                    removeItem = true;
                                }
                            }
                        }
                        player.sendMessage("Aveux de Gin");
                    }
                    //Sake
                    if ((event.getAction() == Action.RIGHT_CLICK_AIR
                            || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                            && !player.hasCooldown(Material.CARROT_ON_A_STICK)
                            && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5082604) {
                        SakeKyoraku.sakeTeleport(player.getName(), tomoNova.classesSpells.getEntityInSight(player, 50).getName());
                        player.sendMessage("Sake");
                    }
                    //Medicament de Ukitake
                    if ((event.getAction() == Action.RIGHT_CLICK_AIR
                            || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                            && !player.hasCooldown(Material.CARROT_ON_A_STICK)
                            && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5132208) {
                        BukkitTask medicamentUkitake = new MedicamentUkitakeTask(tomoNova, player.getName()).runTaskTimer(tomoNova, 0, 10);
                        player.sendMessage("Medicaments");
                    }
                    //Photo de Yoruichi
                    if ((event.getAction() == Action.RIGHT_CLICK_AIR
                            || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                            && !player.hasCooldown(Material.CARROT_ON_A_STICK)
                            && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5022601) {
                        PhotoDeYoruichi.PhotoDeYoruichi(player.getName());
                        removeItem = true;
                        player.sendMessage("Photo de Yoruichi");
                    }
                    //Lys des neiges
                    if ((event.getAction() == Action.RIGHT_CLICK_AIR
                            || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                            && !player.hasCooldown(Material.CARROT_ON_A_STICK)
                            && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5102605) {
                        LysDesNeiges.lysDesNeiges(player.getName());
                        player.sendMessage("Lys des neiges");
                    }
                    //Lunettes de Tosen
                    if ((event.getAction() == Action.RIGHT_CLICK_AIR
                            || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                            && !player.hasCooldown(Material.CARROT_ON_A_STICK)
                            && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5092605) {
                        tomoNova.bleachUHC.initializeLunettesBoolean();
                        BukkitTask lunettesDeTosen = new LunetteDeTosenTask(TomoNova.getPlugin(), player.getName()).runTaskTimer(TomoNova.getPlugin(), 0, 20);
                        player.sendMessage("Lunettes de Tosen");
                    }
                    if ((event.getAction() == Action.RIGHT_CLICK_AIR
                            || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                            && !player.hasCooldown(Material.TOTEM_OF_UNDYING)
                            && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5149613) {
                        BukkitTask hogyokuActif = new HogyokuActifTask(tomoNova, player.getName()).runTaskTimer(tomoNova, 0, 20);
                        player.sendMessage("Hogyoku");
                    }
                    //Activer Hogyoku
                    //Operator
                    // Wand combat zone
                    if (event.getAction() == Action.RIGHT_CLICK_BLOCK
                            && player.getInventory().getItemInMainHand().getType() == Material.STONE_AXE
                            && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000
                            && (!tomoNova.combatzoneUtils.isFirstPoint())) {
                        event.setCancelled(true);
                        Location blockLoc = event.getClickedBlock().getLocation();
                        tomoNova.combatzoneUtils.setFirstPoint(blockLoc.getBlockX(), blockLoc.getBlockZ());
                        tomoNova.combatzoneUtils.setActualPoint(blockLoc.getBlockX(), blockLoc.getBlockZ());
                    }
                    if (event.getAction() == Action.LEFT_CLICK_BLOCK
                            && player.getInventory().getItemInMainHand().getType() == Material.STONE_AXE
                            && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000
                            && tomoNova.combatzoneUtils.isFirstPoint()) {
                        event.setCancelled(true);
                        Location blockLoc = event.getClickedBlock().getLocation();
                        tomoNova.combatzoneUtils.addLine(blockLoc.getBlockX(), blockLoc.getBlockZ());
                        if (tomoNova.combatzoneUtils.isClosedShape()) {
                            tomoNova.combatzoneUtils.sortingContours();
                        }
                    }
                }
            }
        }
        if(removeItem){
            player.getInventory().remove(player.getInventory().getItemInMainHand());
        }
    }
}
