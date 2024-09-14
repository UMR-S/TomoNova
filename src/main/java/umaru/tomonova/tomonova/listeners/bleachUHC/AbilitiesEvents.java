package umaru.tomonova.tomonova.listeners.bleachUHC;

import io.lumine.mythic.bukkit.MythicBukkit;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.core.task.bleachUHCTask.ReturnDamageTask;
import umaru.tomonova.tomonova.gamemode.bleachUHC.items.IceCage;
import umaru.tomonova.tomonova.utils.bleachUHC.sounds.SoundsConstants;
import umaru.tomonova.tomonova.utils.bleachUHC.sounds.SoundsUtils;
import umaru.tomonova.tomonova.utils.constants.BleachUHCConstants;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class AbilitiesEvents implements Listener {

    private final TomoNova tomoNova = TomoNova.getPlugin();
    @EventHandler
    public void onPlayerGetPotionEffect(EntityPotionEffectEvent event) {
        if (!(event.getEntity() instanceof Player)) return;

        Player player = (Player) event.getEntity();
        PotionEffect newEffect = event.getNewEffect();
        if (newEffect == null) return;

        handleIceCage(player, newEffect);
        handleReturnDamageForFourHeavens(player, newEffect);
        handlePoisonFusion(player, event.getOldEffect(), newEffect);
    }

    private void handleIceCage(Player player, PotionEffect newEffect) {
        if (newEffect.getType().equals(PotionEffectType.SLOW) && newEffect.getAmplifier() == 3) {
            IceCage.iceCage(player.getLocation().getBlock().getLocation().clone());
        }
    }

    private void handleReturnDamageForFourHeavens(Player player, PotionEffect newEffect) {
        if (newEffect.getType().equals(PotionEffectType.HERO_OF_THE_VILLAGE) && newEffect.getAmplifier() == 0) {
            ReturnDamageTask.setReturnTime(5);
            HashMap<UUID, Double> playerHashMap = new HashMap<>();
            player.getWorld().getLivingEntities().forEach(e -> playerHashMap.put(e.getUniqueId(), 0.0));
            ReturnDamageTask.setHashMapReturnDamage(playerHashMap);
            new ReturnDamageTask(TomoNova.getPlugin()).runTaskTimer(TomoNova.getPlugin(), 0, 20);
        }
    }

    private void handlePoisonFusion(Player player, PotionEffect oldEffect, PotionEffect newEffect) {
        if (oldEffect == null) return;
        if (newEffect.getType().equals(PotionEffectType.POISON) && oldEffect.getType().equals(PotionEffectType.POISON)) {
            int duration = newEffect.getDuration() + oldEffect.getDuration();
            int amplifier = newEffect.getAmplifier() + oldEffect.getAmplifier() + 1;
            player.removePotionEffect(PotionEffectType.POISON);
            player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, duration, amplifier));
        }
    }

    @EventHandler
    public void onEntityGetPotionEffect(EntityPotionEffectEvent event) {
        LivingEntity entity = (LivingEntity) event.getEntity();
        PotionEffect newEffect = event.getNewEffect();
        if (newEffect == null) return;

        if (newEffect.getType().equals(PotionEffectType.NIGHT_VISION) && newEffect.getAmplifier() == 10) {
            handleUkitakeSendDamageBack();
        }

        if (entity instanceof Player) {
            handlePlayerSpecialEffects((Player) entity, newEffect);
        }
    }

    private void handleUkitakeSendDamageBack() {
        ReturnDamageTask.setReturnTime(5);
        HashMap<UUID, Double> playerHashMapUkitake = new HashMap<>();
        Bukkit.getOnlinePlayers().forEach(p -> playerHashMapUkitake.put(p.getUniqueId(), 0.0));
        ReturnDamageTask.setHashMapReturnDamage(playerHashMapUkitake);
        new ReturnDamageTask(TomoNova.getPlugin()).runTaskTimer(TomoNova.getPlugin(), 0, 20);
    }

    private void handlePlayerSpecialEffects(Player player, PotionEffect newEffect) {
        if (player.getInventory().getItemInMainHand().hasItemMeta() &&
                Objects.requireNonNull(player.getInventory().getItemInMainHand().getItemMeta()).hasCustomModelData() &&
                player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == BleachUHCConstants.SOGYO_NO_KOTOWARI) {
            TomoNova.getPlugin().bleachUHC.addPotionKotowari(newEffect);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityHitByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            handlePlayerHitEntity((Player) event.getDamager(), event);
        }

        if (event.getEntity() instanceof Player) {
            handlePlayerHitByEntity((Player) event.getEntity(), event);
        }
    }

    private void handlePlayerHitEntity(Player player, EntityDamageByEntityEvent event) {
        LivingEntity entity = (LivingEntity) event.getEntity();

        if (entity instanceof Player) {
            handleFourHeavensHit((Player) entity, player, event);
        }

        handleBrazoAndShinigamiHits(player, entity, event);
        handleUkitakeThorsDamage(entity, player, event);
    }

    private void handleFourHeavensHit(Player target, Player attacker, EntityDamageByEntityEvent event) {
        boolean isTargetThors = target.getActivePotionEffects().stream()
                .anyMatch(effect -> effect.getType().equals(PotionEffectType.HERO_OF_THE_VILLAGE) && effect.getAmplifier() == 0);

        if (isTargetThors) {
            ReturnDamageTask.addDamageToPlayer(event.getDamage(), attacker.getUniqueId());
            event.setCancelled(true);
        }
    }

    private void handleBrazoAndShinigamiHits(Player player, LivingEntity entity, EntityDamageByEntityEvent event) {
        if (player.getInventory().getItemInMainHand().getType().equals(Material.AIR)) return;
        if (!player.getInventory().getItemInMainHand().hasItemMeta()) return;
        if (!Objects.requireNonNull(player.getInventory().getItemInMainHand().getItemMeta()).hasCustomModelData()) return;

        int customModelData = player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData();

        switch (customModelData) {
            case BleachUHCConstants.BOUCLIER_BRAZO:
                if (tomoNova.classesUtils.isPlayerBrazo(player.getName())) {
                    handleBrazoAttack(player, entity, event);
                }
                break;
            case BleachUHCConstants.MINAZUKI:
                if (tomoNova.classesUtils.isPlayerShinigami(player.getName())) {
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60, 0));
                }
                break;
            case BleachUHCConstants.ASHISOGI_JIZO:
                if (tomoNova.classesUtils.isPlayerShinigami(player.getName())) {
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 0));
                }
                break;
            case BleachUHCConstants.SUZUMUSHI:
                if (tomoNova.classesUtils.isPlayerShinigami(player.getName())) {
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 0));
                }
                break;
            case BleachUHCConstants.KATEN_KYOKOTSU:
                if (tomoNova.classesUtils.isPlayerShinigami(player.getName())) {
                    handleKatenKyokotsuAttack(player, entity, event);
                }
                break;
            default:
                break;
        }
    }

    private void handleBrazoAttack(Player player, LivingEntity entity, EntityDamageByEntityEvent event) {
        if(!tomoNova.cooldownManager.isCooldown(player,player.getInventory().getItemInMainHand())){
            int hakudaModifier = TomoNova.getPlugin().classesUtils.getPlayerHakudaUpgrade(player.getName());
            event.setDamage(0);
            entity.setHealth((entity.getHealth() - 4) * hakudaModifier);
            entity.setVelocity(entity.getLocation().add(0.0, 1.0, 0.0).clone().toVector().subtract(player.getLocation().clone().toVector()).normalize().multiply(10 * hakudaModifier));
            tomoNova.cooldownManager.startCooldown(player, player.getInventory().getItemInMainHand());
            SoundsUtils.playSoundIfInRange(player.getLocation(), SoundsConstants.PLAYER_BRAZO_ATK,15);
        }
    }

    private void handleKatenKyokotsuAttack(Player player, LivingEntity entity, EntityDamageByEntityEvent event) {
        double heightDifference = player.getLocation().getY() - entity.getLocation().getY();
        if (heightDifference < -2.5) {
            heightDifference = -2.5;
        }
        event.setDamage(event.getDamage() * (1 + 0.4 * heightDifference));
    }

    private void handleUkitakeThorsDamage(LivingEntity entity, Player player, EntityDamageByEntityEvent event) {
        boolean isUkitakeThors = entity.getActivePotionEffects().stream()
                .anyMatch(effect -> effect.getType().equals(PotionEffectType.NIGHT_VISION) && effect.getAmplifier() == 10);

        if (isUkitakeThors) {
            ReturnDamageTask.addDamageToPlayer(event.getDamage(), player.getUniqueId());
        }
    }

    private void handlePlayerHitByEntity(Player player, EntityDamageByEntityEvent event) {
        if (tomoNova.classesUtils.isPlayerBrazo(player.getName())
                && !event.getDamager().getType().equals(EntityType.PLAYER)
                && event.getDamage() >= 1.0) {
            if (MythicBukkit.inst().getAPIHelper().isMythicMob(event.getDamager())) {
                event.setDamage(event.getDamage() * 0.43); //0.43 = 0.65/1.5
                event.setCancelled(true);
                player.damage(event.getDamage(), event.getEntity());
            } else {
                event.setDamage(event.getDamage() * 0.65);
            }
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) return;

        Player player = (Player) event.getEntity();
        if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
            event.setCancelled(true);
        }

        boolean isBlind = player.getActivePotionEffects().stream()
                .anyMatch(effect -> effect.getType().equals(PotionEffectType.BLINDNESS) && effect.getDuration() > 21);

        if (isBlind) {
            player.removePotionEffect(PotionEffectType.BLINDNESS);
            TomoNova.getPlugin().bleachUHC.setLunettesBooleanTruePlayer(player.getName());
        }
    }
}
