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
import umaru.tomonova.tomonova.core.task.bleachUHCTask.MedicamentUkitakeTask;
import umaru.tomonova.tomonova.core.task.bleachUHCTask.SenbonzakuraTask;
import umaru.tomonova.tomonova.gamemode.bleachUHC.GiveItem;
import umaru.tomonova.tomonova.gamemode.bleachUHC.items.Shinso;
import umaru.tomonova.tomonova.gamemode.bleachUHC.items.Tengen;

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
                        && !player.hasCooldown(Material.IRON_SWORD)
                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000101
                        && tomoNova.classesUtils.isPlayerClasse(player.getName(), "shinigami")) {

                    tomoNova.classesSpells.Dash(3, player.getName());
                    player.setCooldown(Material.IRON_SWORD, 300);

                }
                // Attaque arme de Gin
                if ((event.getAction() == Action.RIGHT_CLICK_AIR
                        || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1010101) {

                    Shinso.Shinso(player.getName());

                }
                // Attaque de l'arme d'Ukitake
                if ((event.getAction() == Action.RIGHT_CLICK_AIR
                        || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1131116) {
                    tomoNova.classesSpells.sogyoNoKotowari(player.getName());
                }
                // Attaque de l'arme de Byakuya (senbonzakura)
                if ((event.getAction() == Action.RIGHT_CLICK_AIR
                        || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1131116) {
                    BukkitTask senbonzakuraActive = new SenbonzakuraTask(tomoNova, player.getName()).runTaskTimer(tomoNova,0,20);
                }
                // Tengen (invocation samourai)
                if ((event.getAction() == Action.RIGHT_CLICK_AIR
                        || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1071110) {
                    Tengen.tengen(player.getName());
                }
                //Quincy
                //Dash quincy
                if ((event.getAction() == Action.RIGHT_CLICK_AIR
                        || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                        && !player.hasCooldown(Material.CARROT_ON_A_STICK)
                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2000301
                        && tomoNova.classesUtils.isPlayerClasse(player.getName(), "quincy")) {

                    tomoNova.classesSpells.Dash(5, player.getName());
                    player.setCooldown(Material.CARROT_ON_A_STICK, 300);
                }
                //
                //SSR
                // Ciel Unique
                if ((event.getAction() == Action.RIGHT_CLICK_AIR
                        || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                        && !player.hasCooldown(Material.CARROT_ON_A_STICK)
                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 3000301
                        && tomoNova.classesUtils.isPlayerClasse(player.getName(), "ssr")) {

                    tomoNova.classesSpells.cielUnique(player.getName());

                }
                // 2 Cieux
                if ((event.getAction() == Action.RIGHT_CLICK_AIR
                        || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                        && !player.hasCooldown(Material.NAUTILUS_SHELL)
                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 3000302
                        && tomoNova.classesUtils.isPlayerClasse(player.getName(), "ssr")) {

                    tomoNova.classesSpells.deuxCieux(player.getName());

                }
                // 3 Cieux
                if ((event.getAction() == Action.RIGHT_CLICK_AIR
                        || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                        && !player.hasCooldown(Material.PHANTOM_MEMBRANE)
                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 3000303
                        && tomoNova.classesUtils.isPlayerClasse(player.getName(), "ssr")) {

                    tomoNova.classesSpells.troisCieux(player.getName());

                }
                // 4 Cieux
                if ((event.getAction() == Action.RIGHT_CLICK_AIR
                        || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                        && !player.hasCooldown(Material.HEART_OF_THE_SEA)
                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 3000304
                        && tomoNova.classesUtils.isPlayerClasse(player.getName(), "ssr")) {

                    player.addPotionEffect(new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE, 100, 0, false, false, false));

                }
                if ((event.getAction() == Action.RIGHT_CLICK_AIR
                        || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                        && !player.hasCooldown(Material.HEART_OF_THE_SEA)
                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 3042605
                        && tomoNova.classesUtils.isPlayerClasse(player.getName(), "ssr")
                        && !BaveMinazukiTask.isBaveActive()
                        && BaveMinazukiTask.getUtilisationsBave() > 0){
                    BaveMinazukiTask.setBaveActive(true);
                    BaveMinazukiTask.setUtilisationsBave(BaveMinazukiTask.getUtilisationsBave()-1);
                    BukkitTask baveMinazuki = new BaveMinazukiTask(tomoNova, player.getName()).runTaskTimer(tomoNova,0,20);


                }
                //Brazo
                //Art du Hakuda
                if ((event.getAction() == Action.RIGHT_CLICK_AIR
                        || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                        && !player.hasCooldown(Material.CARROT_ON_A_STICK)
                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4012602
                        && tomoNova.classesUtils.isPlayerClasse(player.getName(), "brazo")) {
                    tomoNova.classesUtils.playerHakudaUpgrade(player.getName());
                    player.getInventory().remove(player.getInventory().getItemInMainHand());
                }
                //Toute classe
                //Activation fragment inactif du Hogyoku
                if ((event.getAction() == Action.RIGHT_CLICK_AIR
                        || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                        && !player.hasCooldown(Material.GLOWSTONE_DUST)
                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5149601) {
                    event.getPlayer().getInventory().remove(event.getPlayer().getInventory().getItemInMainHand());
                    GiveItem.giveHogyokuActifFragment(event.getPlayer().getName());
                    event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() - 10);
                }
                //Aveux de Gin
                if ((event.getAction() == Action.RIGHT_CLICK_AIR
                        || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                        && !player.hasCooldown(Material.CARROT_ON_A_STICK)
                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5032602) {
                    event.getPlayer().getInventory().remove(event.getPlayer().getInventory().getItemInMainHand());

                    for (ActiveMob boss : MythicBukkit.inst().getMobManager().getActiveMobs()) {
                        //Aizen est le seul à avoir la faction traitre
                        if (boss.getFaction().equals("traitre")) {
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
                    BukkitTask medicamentUkitake = new MedicamentUkitakeTask(tomoNova, player.getName()).runTaskTimer(tomoNova,0,10);
                }
                //Photo de Yoruichi
                if ((event.getAction() == Action.RIGHT_CLICK_AIR
                        || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                        && !player.hasCooldown(Material.CARROT_ON_A_STICK)
                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5132208) {
                    BukkitTask medicamentUkitake = new MedicamentUkitakeTask(tomoNova, player.getName()).runTaskTimer(tomoNova,0,10);
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
                    System.out.println(tomoNova.combatzoneUtils.isClosedShape());
                    if (tomoNova.combatzoneUtils.isClosedShape()) {
                        tomoNova.combatzoneUtils.sortingContours();
                    }
                }
            }
        }
    }
}
