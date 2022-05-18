package umaru.tomonova.tomonova.utils.teams;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.lang.Lang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamUtils {

    public HashMap<String,Teams> listTeams = new HashMap<String,Teams>();
    public TeamUtils(){
        for(Teams team : Teams.values()){
            listTeams.put(team.getName(),team);
        }
    }
    public Team registerTeam(String name) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        Teams team = listTeams.get(name);
        Team t = scoreboard.registerNewTeam(name);
        t.setPrefix(team.getPrefix());
        t.setColor(team.getBaseColor());
        t.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.FOR_OTHER_TEAMS);
        t.setColor(team.getBaseColor());
        team.setTeam(t);
        team.setTeamPlayers(new ArrayList<Player>());
        return t;

    }

    public void unRegisterTeam(String name) {
        Teams team = listTeams.get(name);
        team.getTeam().unregister();
        team.setTeam(null);
        team.setTeamPlayers(null);
        team.setCustomName(null);
        team.setNumberPlayers(0);

    }

    public void playerJoinTeam(String name, Player player) {
        Teams team = listTeams.get(name);
        List<Player> teamPlayers = new ArrayList<>();
        if (team.getTeam() == null){
            team.setTeam(registerTeam(name));
        }
        if(TomoNova.getPlugin().gameManager.getPlayersPerTeam() == team.getNumberPlayers() ){
            player.sendMessage(ChatColor.RED + Lang.TEAM_FULL.toString());
        }
        if(team.getNumberPlayers() != 0){
            teamPlayers = team.getTeamPlayers();
        }
        Team t = team.getTeam();
        teamPlayers.add(player);
        t.addEntry(player.getName());
        team.setTeam(t);
        team.setTeamPlayers(teamPlayers);
        team.setNumberPlayers(team.getNumberPlayers() +1 );
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        player.setScoreboard(scoreboard);
    }

    public void playerQuitTeam(String name, Player player) {
        Teams team = listTeams.get(name);
        if (team == null) {
            return;
        }
        List<Player> teamPlayers = team.getTeamPlayers();
        Team t = team.getTeam();
        if (teamPlayers.contains(player)) {
            teamPlayers.remove(player);
            t.removeEntry(player.getName());
            team.setTeamPlayers(teamPlayers);
            team.setNumberPlayers(team.getNumberPlayers() - 1);
        }
        if (team.getNumberPlayers() == 0) {
            unRegisterTeam(name);

        }
        else {
            team.setTeam(t);
        }
    }


    public void setFriendlyFire(String name,Boolean ff) {
        Teams team = listTeams.get(name);
        if (team == null) {
            return;
        }
        team.getTeam().setAllowFriendlyFire(ff);
    }
    public void addCustomName(String name, Player player){
        Teams team = listTeams.get(name);
    }
}
