package umaru.tomonova.tomonova.listeners.bleachUHC;

import io.lumine.mythic.bukkit.MythicBukkit;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.core.task.bleachUHCTask.ReturnDamageTask;
import umaru.tomonova.tomonova.gamemode.bleachUHC.items.IceCage;

import java.util.HashMap;
import java.util.UUID;

public class AbilitiesEvents {

    //Compteurs
    @EventHandler
    public void PlayerGetPotionEffect(EntityPotionEffectEvent event){

        //Cage de glace de Toshiro

        if(event.getEntity() instanceof Player){

            Player player = ((Player) event.getEntity()).getPlayer();
            if(event.getNewEffect() != null){

                if(event.getNewEffect().getType().equals(PotionEffectType.SLOW) && event.getNewEffect().getAmplifier() == 3){

                    IceCage.iceCage(player.getLocation().getBlock().getLocation().clone());

                }

            }
        }

        // 4 cieux

        if(event.getEntity() instanceof Player){

            if(event.getNewEffect() != null){

                if(event.getNewEffect().getType().equals(PotionEffectType.HERO_OF_THE_VILLAGE)
                        && event.getNewEffect().getAmplifier() == 0){

                    ReturnDamageTask.setReturnTime(5);
                    HashMap<UUID,Double> playerHashMap = new HashMap<UUID,Double>();
                    event.getEntity().getWorld().getLivingEntities().forEach(e -> playerHashMap.put(e.getUniqueId(),0.0));
                    ReturnDamageTask.setHashMapReturnDamage(playerHashMap);
                    BukkitTask playerReturnsDamage = new ReturnDamageTask(TomoNova.getPlugin()).runTaskTimer(TomoNova.getPlugin(), 0, 20);
                }
            }
        }

        //Fusion poison Mayuri
        //Ajouter une condition : mayuriVivant (sinon problème avec Ashisogi Jizo) ou avec la cause
        if(event.getEntity() instanceof Player){

            if(event.getNewEffect() != null){

                if(event.getOldEffect() != null){

                    if(event.getNewEffect().getType().equals(PotionEffectType.POISON) && event.getOldEffect().getType().equals(PotionEffectType.POISON)){

                        Player player = ((Player) event.getEntity()).getPlayer();
                        int duration = event.getNewEffect().getDuration() + event.getOldEffect().getDuration();
                        int amplifier = event.getNewEffect().getAmplifier() + event.getOldEffect().getAmplifier() + 1;
                        player.removePotionEffect(PotionEffectType.POISON);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, duration, amplifier));

                    }
                }
            }
        }

    }
    @EventHandler
    public void EntityGetPotionEffect(EntityPotionEffectEvent event){

        //Ukitake send damage back

        if(event.getEntity() instanceof LivingEntity){

            if(event.getNewEffect() != null){

                if(event.getNewEffect().getType().equals(PotionEffectType.NIGHT_VISION) && event.getNewEffect().getAmplifier() == 10){

                    ReturnDamageTask.setReturnTime(5);
                    HashMap<UUID,Double> playerHashMapUkitake = new HashMap<UUID,Double>();
                    Bukkit.getOnlinePlayers().forEach(p -> playerHashMapUkitake.put(p.getUniqueId(),0.0));
                    ReturnDamageTask.setHashMapReturnDamage(playerHashMapUkitake);
                    BukkitTask ukitakeReturnsDamage = new ReturnDamageTask(TomoNova.getPlugin()).runTaskTimer(TomoNova.getPlugin(), 0, 20);

                }
            }
        }

        if(event.getEntity() instanceof Player){
            if(event.getNewEffect() != null){
                Player player = (Player) event.getEntity();
                if(player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()){
                    if(player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1131116){
                        TomoNova.getPlugin().bleachUHC.addPotionKotowari(event.getNewEffect());
                    }
                }
            }
        }
    }
    //Stack des dégâts/effets+attaque/réduction du brazo
    @EventHandler(priority = EventPriority.HIGHEST)
    public void EntityHitByEntity(EntityDamageByEntityEvent event){


        if(event.getDamager() instanceof Player){
            //Stack des dégâts/effets
            //Pour Ukitake

            if(event.getEntity() instanceof LivingEntity){

                Player player = (Player) event.getDamager();
                LivingEntity entity = (LivingEntity) event.getEntity();
                Boolean isUkitakeThors = false;
                for(PotionEffect potionEffect : entity.getActivePotionEffects()){
                    if(potionEffect.getType().equals(PotionEffectType.NIGHT_VISION) && potionEffect.getAmplifier() == 10){
                        isUkitakeThors = true;
                    }
                }
                if(isUkitakeThors){
                    ReturnDamageTask.addDamageToPlayer(event.getDamage(), player.getUniqueId());
                }

            }

            //Pour 4 cieux

            if(event.getEntity() instanceof Player){

                Player damager = (Player) event.getDamager();
                Player player = ((Player) event.getEntity()).getPlayer();
                Boolean isPlayerThors = false;
                for(PotionEffect potionEffect : player.getActivePotionEffects()){
                    if(potionEffect.getType().equals(PotionEffectType.HERO_OF_THE_VILLAGE) && potionEffect.getAmplifier() == 0){
                        isPlayerThors = true;
                    }
                }
                if(isPlayerThors){
                    ReturnDamageTask.addDamageToPlayer(event.getDamage(), damager.getUniqueId());
                    event.setCancelled(true);
                }

            }
            if(event.getEntity() instanceof LivingEntity){
                Player player = (Player) event.getDamager();
                LivingEntity damaged = (LivingEntity) event.getEntity();
                //Brazo
                //Attaque du Brazo
                if(player.getInventory().getItemInMainHand().getType().equals(Material.SHIELD)
                        && TomoNova.classesUtils.isPlayerClasse(player.getName(), "brazo")){
                    boolean isCooldown = false;
                    for(PotionEffect potionEffect : player.getActivePotionEffects()){
                        if(potionEffect.getType().equals(PotionEffectType.HERO_OF_THE_VILLAGE) && potionEffect.getAmplifier() == 0){
                            isCooldown = true;
                        }
                    }
                    if(!isCooldown){
                        int hakudaModifier = TomoNova.getPlugin().classesUtils.getPlayerHakudaUpgrade(player.getName());
                        event.setDamage(0);
                        damaged.setHealth((damaged.getHealth() - 4)*hakudaModifier);
                        damaged.setVelocity(damaged.getLocation().add(0.0,1.0,0.0).clone().toVector().subtract(player.getLocation().clone().toVector()).normalize().multiply(10*hakudaModifier));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE, 100, 0, false, false, false));
                    }
                }
                //Shinigami
                //Minazuki
                if(player.getInventory().getItemInMainHand().getType().equals(Material.IRON_SWORD)
                    && TomoNova.classesUtils.isPlayerClasse(player.getName(), "shinigami")){
                    if(player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()){
                        if(player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1041107){
                            damaged.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60,0));
                        }
                    }
                }
                //Ashisogi Jizo
                if(player.getInventory().getItemInMainHand().getType().equals(Material.IRON_SWORD)
                        && TomoNova.classesUtils.isPlayerClasse(player.getName(), "shinigami")){
                    if(player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()){
                        if(player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1121115){
                            damaged.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100 ,0));
                        }
                    }
                }
                //Suzumichi
                if(player.getInventory().getItemInMainHand().getType().equals(Material.IRON_SWORD)
                        && TomoNova.classesUtils.isPlayerClasse(player.getName(), "shinigami")){
                    if(player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()){
                        if(player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1091112){
                            damaged.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 ,0));
                        }
                    }
                }
                //Toute classe
                //Lunettes de Tosen (rajouter la condition de mort de Tosen)
            }
        }

        if(event.getEntity() instanceof Player){
            Player player = ((Player) event.getEntity()).getPlayer();
            // Dégats mobs au Brazo
            if(TomoNova.classesUtils.isPlayerClasse(player.getName(),"brazo")
                    && !event.getDamager().getType().equals(EntityType.PLAYER)
                    && event.getDamage() >= 1.0){
                if(MythicBukkit.inst().getAPIHelper().isMythicMob(event.getDamager())) {
                    event.setDamage(event.getDamage()*0.43); //0.43 = 0.65/1.5
                    event.setCancelled(true);
                    player.damage(event.getDamage(), event.getEntity());
                } else {
                    event.setDamage(event.getDamage()*0.65);
                }
            }
        }
    }
}
