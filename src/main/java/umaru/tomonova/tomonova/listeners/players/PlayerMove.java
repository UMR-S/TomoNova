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
            return;
        }

        if (GameStates.isState(GameStates.GAME)) {
            Player player = event.getPlayer();

            if (!TomoNova.getPlugin().gameManager.isTomoLostVillage()) {
                handleGameMove(player);
            } else {
                handleLostVillageMove(player);
            }
        }
    }

    private void handleGameMove(Player player) {
        String teamName = TomoNova.getPlugin().teamUtils.getTeamNameFromPlayer(player.getName());
        if (!teamName.equals("None")) {
            Teams team = TomoNova.getPlugin().teamUtils.getTeamHashMap().get(teamName);
            if (team.getNumberPlayers() != 1) {
                String message = buildTeamMessage(player, team);
                player.spigot().sendMessage(net.md_5.bungee.api.ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
            }
        }
    }

    private void handleLostVillageMove(Player player) {
        if (TomoNova.getPlugin().tomoLostVillage.playerTeamNumberPlayer(player.getName()) > 1) {
            String message = buildLostVillageMessage(player);
            player.spigot().sendMessage(net.md_5.bungee.api.ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
        }
    }

    private String buildTeamMessage(Player player, Teams team) {
        StringBuilder builder = new StringBuilder();
        for (String targetName : team.getTeamPlayers()) {
            Player target = Bukkit.getPlayer(targetName);
            if (target != null && !player.getName().equals(targetName)) {
                builder.append(team.getPrefix()).append(target.getName())
                        .append(" §7: ")
                        .append(TomoNova.getPlugin().scoreboardUtils.getColoredDirectionTo(player, target.getLocation()))
                        .append(" §7(").append((int) player.getLocation().distance(target.getLocation())).append(")   ");
            }
        }
        return builder.toString();
    }

    private String buildLostVillageMessage(Player player) {
        StringBuilder builder = new StringBuilder();
        for (String targetName : TomoNova.getPlugin().tomoLostVillage.playersInTeamOfPlayer(player.getName())) {
            Player target = Bukkit.getPlayer(targetName);
            if (target != null && !player.getName().equals(targetName)) {
                builder.append(TomoNova.getPlugin().tomoLostVillage.getChatColor(player.getName()))
                        .append(target.getName()).append(" §7: ")
                        .append(TomoNova.getPlugin().scoreboardUtils.getColoredDirectionTo(player, target.getLocation()))
                        .append(" §7(").append((int) player.getLocation().distance(target.getLocation())).append(")   ");
            }
        }
        return builder.toString();
    }
}
