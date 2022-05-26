package umaru.tomonova.tomonova.listeners.players;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.core.game.GameStates;
import umaru.tomonova.tomonova.utils.teams.Teams;


public class PlayerMove implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (GameStates.isState(GameStates.PREGAME)) {
            event.setCancelled(true);
        }
        if (GameStates.isState(GameStates.GAME)) {
            String teamName = TomoNova.getPlugin().teamUtils.getTeamNameFromPlayer(event.getPlayer().getName());
            Player player = event.getPlayer();
            if (teamName != "None") {
                Teams team = TomoNova.getPlugin().teamUtils.getTeamHashMap().get(teamName);
                if (team.getNumberPlayers() != 1) {
                    final StringBuilder builder = new StringBuilder();
                    for (String targetName : team.getTeamPlayers()) {
                        Player target = Bukkit.getPlayer(targetName);
                        if (!player.getName().equals(targetName)) {
                            if (Bukkit.getPlayer(targetName) != null) {
                                builder.append(String.valueOf(String.valueOf(team.getPrefix())) + target.getName() + " §7: " + TomoNova.getPlugin().scoreboardUtils.getColoredDirectionTo(player, target.getLocation()) + " §7(" + (int) player.getLocation().distance(target.getLocation()) + ")   ");
                            }
                        }
                    }
                    player.spigot().sendMessage(net.md_5.bungee.api.ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(builder.toString()));
                }
            }
        }
    }
}
