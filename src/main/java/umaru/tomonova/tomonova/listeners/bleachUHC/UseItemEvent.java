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
import umaru.tomonova.tomonova.core.task.bleachUHCTask.BaveMinazukiTask;
import umaru.tomonova.tomonova.core.task.bleachUHCTask.LunetteDeTosenTask;
import umaru.tomonova.tomonova.core.task.bleachUHCTask.MedicamentUkitakeTask;
import umaru.tomonova.tomonova.core.task.bleachUHCTask.SenbonzakuraTask;
import umaru.tomonova.tomonova.gamemode.bleachUHC.GiveItem;
import umaru.tomonova.tomonova.gamemode.bleachUHC.classes.cooldowns.Cooldown;
import umaru.tomonova.tomonova.gamemode.bleachUHC.items.*;

public class UseItemEvent implements Listener {

    TomoNova tomoNova = TomoNova.getPlugin();

    @EventHandler
    public void onClickBleachUHC(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().getItemInMainHand() != null) {
            if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {

                //Shinigami
                // Dash shinigami

                if ((event.getAction() == Action.RIGHT_CLICK_AIR
                        || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000603
                        && tomoNova.classesUtils.isPlayerClasse(player.getName(), "shinigami")) {
                    if(Cooldown.isCooling(player.getName(),"shinigamiDash")){
                        Cooldown.coolDurMessage(player.getName(),"shinigamiDash");
                    }
                    else {
                        tomoNova.classesSpells.Dash(3, player.getName());
                        Cooldown.add(player.getName(), "shinigamiDash", 15, System.currentTimeMillis());
                    }

                }
                // Attaque arme de Gin
                if ((event.getAction() == Action.RIGHT_CLICK_AIR
                        || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1010101
                        && tomoNova.classesUtils.isPlayerClasse(player.getName(), "shinigami")) {
                    if(Cooldown.isCooling(player.getName(),"shinso")){
                        Cooldown.coolDurMessage(player.getName(),"shinso");
                    }
                    else {
                        Shinso.Shinso(player.getName());
                        Cooldown.add(player.getName(), "shinso", 7, System.currentTimeMillis());
                    }
                }
                // Attaque de l'arme d'Ukitake
                if ((event.getAction() == Action.RIGHT_CLICK_AIR
                        || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1131116
                        && tomoNova.classesUtils.isPlayerClasse(player.getName(), "shinigami")) {
                    if(Cooldown.isCooling(player.getName(),"ukitakeWeapon")){
                        Cooldown.coolDurMessage(player.getName(),"ukitakeWeapon");
                    }
                    else {
                        tomoNova.classesSpells.sogyoNoKotowari(player.getName());
                        Cooldown.add(player.getName(), "ukitakeWeapon", 45, System.currentTimeMillis());
                    }
                }
                // Attaque de l'arme de Byakuya (senbonzakura)
                if ((event.getAction() == Action.RIGHT_CLICK_AIR
                        || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1131116
                        && tomoNova.classesUtils.isPlayerClasse(player.getName(), "shinigami")) {
                    if(Cooldown.isCooling(player.getName(),"senbonzakura")){
                        Cooldown.coolDurMessage(player.getName(),"senbonzakura");
                    }
                    else {
                        BukkitTask senbonzakuraActive = new SenbonzakuraTask(tomoNova, player.getName()).runTaskTimer(tomoNova,0,20);
                        Cooldown.add(player.getName(), "senbonzakura", 20, System.currentTimeMillis());
                    }
                }
                // Tengen (invocation samourai)
                if ((event.getAction() == Action.RIGHT_CLICK_AIR
                        || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1071110
                        && tomoNova.classesUtils.isPlayerClasse(player.getName(), "shinigami")) {
                    if(Cooldown.isCooling(player.getName(),"tengen")){
                        Cooldown.coolDurMessage(player.getName(),"tengen");
                    }
                    else {
                        Tengen.tengen(player.getName());
                        Cooldown.add(player.getName(), "tengen", 60, System.currentTimeMillis());
                    }
                }
                //Hyorinmaru
                if ((event.getAction() == Action.RIGHT_CLICK_AIR
                        || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1101113
                        && tomoNova.classesUtils.isPlayerClasse(player.getName(), "shinigami")) {
                    if(Cooldown.isCooling(player.getName(),"hyorinmaru")){
                        Cooldown.coolDurMessage(player.getName(),"hyorinmaru");
                    }
                    else {
                        Hyorinmaru.hyorinmaru(player.getName());
                        Cooldown.add(player.getName(), "hyorinmaru", 600, System.currentTimeMillis());
                    }
                }
                //Quincy
                //Dash quincy
                if ((event.getAction() == Action.RIGHT_CLICK_AIR
                        || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2000301
                        && tomoNova.classesUtils.isPlayerClasse(player.getName(), "quincy")) {
                    if(Cooldown.isCooling(player.getName(),"dashQuincy")){
                        Cooldown.coolDurMessage(player.getName(),"dashQuincy");
                    }
                    else {
                        tomoNova.classesSpells.Dash(5, player.getName());
                        Cooldown.add(player.getName(), "dashQuincy", 15, System.currentTimeMillis());
                    }
                }
                //Carquois
                if ((event.getAction() == Action.RIGHT_CLICK_AIR
                        || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2000602
                        && tomoNova.classesUtils.isPlayerClasse(player.getName(), "quincy")) {
                    if(Cooldown.isCooling(player.getName(),"carquois")){
                        Cooldown.coolDurMessage(player.getName(),"carquois");
                    }
                    else {
                        tomoNova.classesSpells.Carquois(player.getName());
                        Cooldown.add(player.getName(), "carquois", 15, System.currentTimeMillis());
                    }
                }
                //Gant de Sanrei (utilisation unique)
                if ((event.getAction() == Action.RIGHT_CLICK_AIR
                        || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2000301
                        && tomoNova.classesUtils.isPlayerClasse(player.getName(), "quincy")) {
                    player.getInventory().remove(player.getInventory().getItemInMainHand());
                    GantDeSanrei.gantDeSanrei(player.getName());
                }
                //SSR
                // Ciel Unique
                if ((event.getAction() == Action.RIGHT_CLICK_AIR
                        || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 3000301
                        && tomoNova.classesUtils.isPlayerClasse(player.getName(), "ssr")) {
                    if(Cooldown.isCooling(player.getName(),"cielUnique")){
                        Cooldown.coolDurMessage(player.getName(),"cielUnique");
                    }
                    else {
                        tomoNova.classesSpells.cielUnique(player.getName());
                        Cooldown.add(player.getName(), "cielUnique", 25, System.currentTimeMillis());
                    }
                }
                // 2 Cieux
                if ((event.getAction() == Action.RIGHT_CLICK_AIR
                        || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 3000302
                        && tomoNova.classesUtils.isPlayerClasse(player.getName(), "ssr")) {
                    if(Cooldown.isCooling(player.getName(),"deuxCieux")){
                        Cooldown.coolDurMessage(player.getName(),"deuxCieux");
                    }
                    else {
                        tomoNova.classesSpells.deuxCieux(player.getName());
                        Cooldown.add(player.getName(), "deuxCieux", 180, System.currentTimeMillis());
                    }

                }
                // 3 Cieux
                if ((event.getAction() == Action.RIGHT_CLICK_AIR
                        || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 3000303
                        && tomoNova.classesUtils.isPlayerClasse(player.getName(), "ssr")) {
                    if(Cooldown.isCooling(player.getName(),"troisCieux")){
                        Cooldown.coolDurMessage(player.getName(),"troisCieux");
                    }
                    else {
                        tomoNova.classesSpells.troisCieux(player.getName());
                        Cooldown.add(player.getName(), "troisCieux", 3, System.currentTimeMillis());
                    }
                }
                // 4 Cieux
                if ((event.getAction() == Action.RIGHT_CLICK_AIR
                        || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 3000304
                        && tomoNova.classesUtils.isPlayerClasse(player.getName(), "ssr")) {
                    if(Cooldown.isCooling(player.getName(),"quatreCieux")){
                        Cooldown.coolDurMessage(player.getName(),"quatreCieux");
                    }
                    else {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE, 100, 0, false, false, false));
                        Cooldown.add(player.getName(), "quatreCieux", 3, System.currentTimeMillis());
                    }
                }
                //Bave de minazuki
                if ((event.getAction() == Action.RIGHT_CLICK_AIR
                        || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 3042605
                        && tomoNova.classesUtils.isPlayerClasse(player.getName(), "ssr")
                        && !tomoNova.classesSpells.isMinazukiActive()
                        && tomoNova.classesSpells.getMinazukiLeftUses() > 0){
                    tomoNova.classesSpells.setMinazukiActive(true);
                    tomoNova.classesSpells.setMinazukiLeftUses(tomoNova.classesSpells.getMinazukiLeftUses()-1);
                    BukkitTask baveMinazuki = new BaveMinazukiTask(tomoNova, player.getName()).runTaskTimer(tomoNova,0,20);
                }
                //Brazo
                //Art du Hakuda (utilisation unique)
                if ((event.getAction() == Action.RIGHT_CLICK_AIR
                        || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                        && !player.hasCooldown(Material.CARROT_ON_A_STICK)
                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4012602
                        && tomoNova.classesUtils.isPlayerClasse(player.getName(), "brazo")) {
                    tomoNova.classesUtils.playerHakudaUpgrade(player.getName());
                    player.getInventory().remove(player.getInventory().getItemInMainHand());
                }
                //Toute classe
                //Activation fragment inactif du Hogyoku (utilisation unique)
                if ((event.getAction() == Action.RIGHT_CLICK_AIR
                        || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                        && !player.hasCooldown(Material.GLOWSTONE_DUST)
                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5149601) {
                    event.getPlayer().getInventory().remove(event.getPlayer().getInventory().getItemInMainHand());
                    GiveItem.giveHogyokuActifFragment(event.getPlayer().getName());
                    event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() - 10);
                }
                //Aveux de Gin (utilisation unique)
                if ((event.getAction() == Action.RIGHT_CLICK_AIR
                        || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                        && !player.hasCooldown(Material.CARROT_ON_A_STICK)
                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5032602) {
                    event.getPlayer().getInventory().remove(event.getPlayer().getInventory().getItemInMainHand());

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
                                player.getInventory().remove(player.getInventory().getItemInMainHand());
                            }
                        }
                    }
                }
                //Medicament de Ukitake
                if ((event.getAction() == Action.RIGHT_CLICK_AIR
                        || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                        && !player.hasCooldown(Material.CARROT_ON_A_STICK)
                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5132208) {
                    if(Cooldown.isCooling(player.getName(),"medicaments")){
                        Cooldown.coolDurMessage(player.getName(),"medicaments");
                    }
                    else {
                        BukkitTask medicamentUkitake = new MedicamentUkitakeTask(tomoNova, player.getName()).runTaskTimer(tomoNova,0,10);
                        Cooldown.add(player.getName(), "medicaments", 30, System.currentTimeMillis());
                    }
                }
                //Photo de Yoruichi (utilisation unique)
                if ((event.getAction() == Action.RIGHT_CLICK_AIR
                        || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                        && !player.hasCooldown(Material.CARROT_ON_A_STICK)
                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5022601) {
                    PhotoDeYoruichi.PhotoDeYoruichi(player.getName());
                    player.getInventory().remove(player.getInventory().getItemInMainHand());
                }
                //Lys des neiges
                if ((event.getAction() == Action.RIGHT_CLICK_AIR
                        || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                        && !player.hasCooldown(Material.CARROT_ON_A_STICK)
                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5102605) {
                    if(Cooldown.isCooling(player.getName(),"lysDesNeiges")){
                        Cooldown.coolDurMessage(player.getName(),"lysDesNeiges");
                    }
                    else {
                        LysDesNeiges.lysDesNeiges(player.getName());
                        Cooldown.add(player.getName(), "lysDesNeiges", 1200, System.currentTimeMillis());
                    }
                }
                //Lunettes de Tosen
                if ((event.getAction() == Action.RIGHT_CLICK_AIR
                        || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                        && !player.hasCooldown(Material.CARROT_ON_A_STICK)
                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5092605) {
                    TomoNova.getPlugin().bleachUHC.initializeLunettesBoolean();
                    if(Cooldown.isCooling(player.getName(),"lunettesTosen")){
                        Cooldown.coolDurMessage(player.getName(),"lunettesTosen");
                    }
                    else {
                        BukkitTask lunettesDeTosen = new LunetteDeTosenTask(TomoNova.getPlugin(), player.getName()).runTaskTimer(TomoNova.getPlugin(), 0, 20);
                        Cooldown.add(player.getName(), "lunettesTosen", 1800, System.currentTimeMillis());
                    }
                }
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
}
