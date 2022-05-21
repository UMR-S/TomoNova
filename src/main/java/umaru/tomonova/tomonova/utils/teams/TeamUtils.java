package umaru.tomonova.tomonova.utils.teams;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.lang.Lang;

import java.util.*;

public class TeamUtils {

    private HashMap<String, Teams> listTeams = new HashMap<String, Teams>();

    public TeamUtils() {
        for (Teams team : Teams.values()) {
            listTeams.put(team.getName(), team);
        }
    }

    public Team registerTeam(String name) {
        Teams team = Teams.getTeamFromName(name);
        Team t = team.getTeam();
        t.setPrefix(team.getPrefix());
        t.setColor(team.getBaseColor());
        t.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.FOR_OTHER_TEAMS);
        t.setColor(team.getBaseColor());
        team.setTeam(t);
        team.setTeamPlayers(new ArrayList<Player>());
        return t;

    }

    public Teams unRegisterTeam(String name) {
        Teams team = listTeams.get(name);
        team.setTeamPlayers(null);
        team.setCustomName(null);
        team.setNumberPlayers(0);
        return team;

    }

    public void playerJoinTeam(String name, Player player) {
        Teams team = listTeams.get(name);
        List<Player> teamPlayers = new ArrayList<>();
        if (team.getTeam() == null) {
            team.setTeam(registerTeam(name));
        }
        if (TomoNova.getPlugin().gameManager.getPlayersPerTeam() == team.getNumberPlayers()) {
            player.sendMessage(ChatColor.RED + Lang.TEAM_FULL.toString());
        }
        if (team.getNumberPlayers() != 0) {
            teamPlayers = team.getTeamPlayers();
        }
        Team t = team.getTeam();
        teamPlayers.add(player);
        t.addEntry(player.getName());
        team.setTeam(t);
        team.setTeamPlayers(teamPlayers);
        team.setNumberPlayers(team.getNumberPlayers() + 1);
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        player.setScoreboard(scoreboard);
        listTeams.put(name,team);
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
            team = unRegisterTeam(name);

        } else {
            team.setTeam(t);
        }
        listTeams.put(name,team);
    }


    public void setFriendlyFire(String name, Boolean ff) {
        Teams team = listTeams.get(name);
        if (team == null) {
            return;
        }
        team.getTeam().setAllowFriendlyFire(ff);
    }

    public void addCustomName(String name, Player player) {
        Teams team = listTeams.get(name);
    }

    public HashMap<String, Teams> getTeamHashMap() {
        return this.listTeams;
    }

    public String getTeamNameFromPlayer(Player player) {
        String teamName = null;
        for (String name : this.listTeams.keySet()) {
            Teams team = this.listTeams.get(name);
            if (team.getTeamPlayers() == null) {
                return null;
            } else if (team.getTeamPlayers().contains(player)) {
                teamName = team.getName();
            }

        }
        return teamName;
    }

    public void getTeamToTeamlessPlayers(List<Player> players) {
        //On cherche les joueurs qui n'ont pas de team

        for (Player player : players) {
            for (String teamName : this.listTeams.keySet()) {
                if(listTeams.get(teamName).getTeamPlayers() != null){
                    System.out.println(this.listTeams.get(teamName).getTeamPlayers());
                    if (listTeams.get(teamName).getTeamPlayers().contains(player)) {
                        players.remove(player);
                    }
                }
            }
        }
        //Si tous les joueurs ont une team rien besoin de faire
        if (players.size() == 0) {
            return;
        }
        //On regarde les équipes non pleines et non vides et on les place dans une liste
        //On met également dans une autre liste les équipes vides
        //On définit la capacité de ces deux listes
        List<Teams> teamsNonPleines = new ArrayList<Teams>();
        List<Teams> teamsVides = new ArrayList<Teams>();
        Integer capaciteNonPleines = 0;
        Integer capacitéVides = 0;
        Integer playerPerTeam = TomoNova.getPlugin().gameManager.getPlayersPerTeam();
        for (String teamName : listTeams.keySet()) {
            Teams team = listTeams.get(teamName);
            if (team.getNumberPlayers() < playerPerTeam) {
                if (team.getNumberPlayers() == 0) {
                    teamsVides.add(team);
                    capacitéVides = capacitéVides + playerPerTeam;
                } else {
                    teamsNonPleines.add(team);
                    capaciteNonPleines = capaciteNonPleines + (playerPerTeam - team.getNumberPlayers());
                }
            }
        }

        //Création d'une liste avec assez de place pour placer tous les joueurs
        Collections.shuffle(teamsNonPleines);
        Collections.shuffle(teamsVides);
        if (players.size() > capaciteNonPleines + capacitéVides) {
            TomoNova.getPlugin().gameManager.stop();
            return;
        }
        while (capaciteNonPleines < players.size()) {
            teamsNonPleines.add(teamsVides.get(0));
            teamsVides.remove(0);
            capaciteNonPleines = capaciteNonPleines + playerPerTeam;
            capacitéVides = capacitéVides - playerPerTeam;
        }
        //Attribution des joueurs
        while (players.size() > 0) {
            //On recherche la team la moins pleine
            Teams teamWithLessPlayer = null;
            Integer lessPlayer = playerPerTeam;
            for (Teams team : teamsNonPleines) {
                if (team.getNumberPlayers() < lessPlayer) {
                    teamWithLessPlayer = team;
                }
            }
            //On y ajoute un joueur aléatoire
            Random rand = new Random();
            Player player = players.get(rand.nextInt(players.size()));
            playerJoinTeam(teamWithLessPlayer.getName(), player);
            //On supprime la team de la liste si elle est pleine
            if (teamWithLessPlayer.getNumberPlayers() == playerPerTeam) {
                teamsNonPleines.remove(teamWithLessPlayer);
            }
            Collections.shuffle(teamsNonPleines);
            players.remove(player);
        }
        return;
    }
}

