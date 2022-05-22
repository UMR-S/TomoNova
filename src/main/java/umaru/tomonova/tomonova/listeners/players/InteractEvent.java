package umaru.tomonova.tomonova.listeners.players;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import umaru.tomonova.tomonova.core.game.GameStates;
import umaru.tomonova.tomonova.gui.MainGui;
import umaru.tomonova.tomonova.gui.teamsgui.TeamsGui;


public class InteractEvent implements Listener {

    @EventHandler
    public void onLeftClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if ((event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && player.getInventory().getItemInMainHand().getType() == Material.BLACK_BANNER && GameStates.isState(GameStates.LOBBY)) {
            new TeamsGui(player).show();
        }
        if ((event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && player.getInventory().getItemInMainHand().getType() == Material.PAPER && (GameStates.isState(GameStates.LOBBY) || GameStates.isState(GameStates.LOBBY_END))) {
            new MainGui(player).show();
        }

    }
}
