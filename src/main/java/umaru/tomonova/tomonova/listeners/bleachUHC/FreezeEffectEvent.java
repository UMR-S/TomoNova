package umaru.tomonova.tomonova.listeners.bleachUHC;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FreezeEffectEvent implements Listener {

    @EventHandler
    public void onPlayerCombust(EntityCombustEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (hasFreezeEffects(player)) {
                removeFreezeEffects(player);
            }
        }
    }

    private boolean hasFreezeEffects(Player player) {
        boolean isSlow = false;
        boolean isWeak = false;

        for (PotionEffect potionEffect : player.getActivePotionEffects()) {
            if (potionEffect.getType().equals(PotionEffectType.SLOW) && potionEffect.getAmplifier() == 1) {
                isSlow = true;
            }
            if (potionEffect.getType().equals(PotionEffectType.WEAKNESS) && potionEffect.getAmplifier() == 1) {
                isWeak = true;
            }
        }

        return isSlow && isWeak;
    }

    private void removeFreezeEffects(Player player) {
        player.removePotionEffect(PotionEffectType.SLOW);
        player.removePotionEffect(PotionEffectType.WEAKNESS);
    }
}
