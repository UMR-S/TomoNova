package umaru.tomonova.tomonova.listeners.bleachUHC;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.gamemode.bleachUHC.GiveItem;
import umaru.tomonova.tomonova.gamemode.bleachUHC.items.Shinso;

public class UseItemEvent {

    TomoNova tomoNova = TomoNova.getPlugin();

    @EventHandler
    public void onClickBleachUHC(PlayerInteractEvent event) {
        Player player = event.getPlayer();
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
            //Toute classe
            //Fragment inactif du Hogyoku
            if ((event.getAction() == Action.RIGHT_CLICK_AIR
                    || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                    && !player.hasCooldown(Material.NETHER_WART)
                    && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5149601) {
                event.getPlayer().getInventory().remove(event.getPlayer().getInventory().getItemInMainHand());
                GiveItem.giveHogyokuActifFragment(event.getPlayer().getName());
                event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() - 10);
            }
            // Hogyoku
//            if((event.getAction() == Action.RIGHT_CLICK_AIR
//                    || event.getAction() == Action.RIGHT_CLICK_BLOCK)
//                    && !player.hasCooldown(Material.NETHERITE_SCRAP)
//                    && !HogyokuInactifTask.isHogyokuActivated()
//                    && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5149604){
//                HogyokuFirstLevelTask.setPlayerName(player.getName());
//                HogyokuFirstLevelTask.setTimeActive(12000);
//                HogyokuInactifTask.setHogyokuActivated(true);
//                BukkitTask hogyokuFirstLevel = new HogyokuFirstLevelTask(BleachUHC.getPlugin()).runTaskTimer(BleachUHC.getPlugin(), 0, 20);
//            }
//        }
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
