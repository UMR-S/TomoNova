package umaru.tomonova.tomonova.listeners.players;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Team;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.core.game.GameManager;
import umaru.tomonova.tomonova.core.game.GameStates;
import umaru.tomonova.tomonova.utils.teams.TeamUtils;
import umaru.tomonova.tomonova.utils.teams.Teams;

import java.util.Arrays;

public class Quit implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onQuit(PlayerQuitEvent event) {
        if (GameStates.isState(GameStates.LOBBY)) {
            Player player = event.getPlayer();
            TomoNova.getPlugin().gameManager.removePlayer(player);
            String teamName;
            for(Teams team : Teams.values()){
                if(team.getTeamPlayers() == null){

                } else if (team.getTeamPlayers().contains(player)) {
                    teamName = team.getName();
                    System.out.println(teamName);
                    TomoNova.getPlugin().teamUtils.playerQuitTeam(teamName, player);
                }
            }
        }
    }
}

