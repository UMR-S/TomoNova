package umaru.tomonova.tomonova.listeners.others;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.core.game.GameStates;

public class FoodLevelChange implements Listener {
    @EventHandler
    public void onFoodLevelChange(final FoodLevelChangeEvent e) {
        if (GameStates.isState(GameStates.LOBBY) || GameStates.isState(GameStates.LOBBY_END) || GameStates.isState(GameStates.PREGAME)) {
            e.setCancelled(true);
        }
        if (GameStates.isState(GameStates.GAME) && TomoNova.getPlugin().gameManager.isTomoLostVillage()) {
            if (!TomoNova.getPlugin().tomoLostVillage.isHunger(e.getEntity().getName())) {
                e.setCancelled(true);
            }
        }
    }
}
