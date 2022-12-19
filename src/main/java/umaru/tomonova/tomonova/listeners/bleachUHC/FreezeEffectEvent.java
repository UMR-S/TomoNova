package umaru.tomonova.tomonova.listeners.bleachUHC;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FreezeEffectEvent implements Listener {
    @EventHandler
    public void PlayerOnFireEvent(EntityCombustEvent event) {

        if (event.getEntity() instanceof Player) {

            Player player = ((Player) event.getEntity()).getPlayer();
            Boolean isSlow = false;
            Boolean isWeak = false;
            for (PotionEffect potionEffect : player.getActivePotionEffects()) {

                if (potionEffect.getType().equals(PotionEffectType.SLOW) && potionEffect.getAmplifier() == 1) {

                    isSlow = true;

                }
                if (potionEffect.getType().equals(PotionEffectType.WEAKNESS) && potionEffect.getAmplifier() == 1){

                    isWeak = true;

                }

            }

            if (isSlow && isWeak) {

                player.removePotionEffect(PotionEffectType.SLOW);
                player.removePotionEffect(PotionEffectType.WEAKNESS);
            }

        }

    }
}
