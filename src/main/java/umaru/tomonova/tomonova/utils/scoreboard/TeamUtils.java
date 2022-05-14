package umaru.tomonova.tomonova.utils.scoreboard;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.List;

public class TeamUtils {

    public static void registerTeam(String name) {
        Scoreboard scoreboard = ScoreboardUtils.getScoreboard();
        Teams team = Teams.getTeamFromName(name);
        if (team == null) {
            return;
        }
        Team t = scoreboard.registerNewTeam(name);
        t.setPrefix(team.getPrefix());
        t.setColor(team.getBaseColor());
        t.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.FOR_OTHER_TEAMS);
        team.setTeam(t);
    }

    public static void unRegisterTeam(String name) {
        Teams team = Teams.getTeamFromName(name);
        if (team == null) {
            return;
        }
        team.setTeam(null);
        team.getTeam().unregister();

    }

    public static void playerJoinTeam(String name, Player player) {
        Teams team = Teams.getTeamFromName(name);
        if (team == null) {
            return;
        }
        List<Player> teamPlayers = team.getTeamPlayers();
        Team t = team.getTeam();
        teamPlayers.add(player);
        t.addEntry(player.getName());
        team.setTeam(t);
        team.setTeamPlayer(teamPlayers);

    }

    public static void playerQuitTeam(String name, Player player) {
        Teams team = Teams.getTeamFromName(name);
        if (team == null) {
            return;
        }
        List<Player> teamPlayers = team.getTeamPlayers();
        Team t = team.getTeam();
        if (teamPlayers.contains(player)) {
            teamPlayers.remove(player);
            t.removeEntry(player.getName());
        }
        if (teamPlayers.size() == 0) {
            unRegisterTeam(name);

        }
        else {
            team.setTeam(t);
            team.setTeamPlayer(teamPlayers);
        }
    }

    public static int intPlayerInTeam(String name){
        Teams team = Teams.getTeamFromName(name);
        if (team == null) {
            return 0;
        }
        return team.getTeamPlayers().size();
    }
    public static void setFriendlyFire(String name,Boolean ff) {
        Teams team = Teams.getTeamFromName(name);
        if (team == null) {
            return;
        }
        team.getTeam().setAllowFriendlyFire(ff);
    }
}
