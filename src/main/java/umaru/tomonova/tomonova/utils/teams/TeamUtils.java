package umaru.tomonova.tomonova.utils.teams;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Scoreboard;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.lang.Lang;

import java.util.*;


public class TeamUtils {

    private HashMap<String, Teams> listTeams = new HashMap<String, Teams>();
    private Scoreboard scoreBoardTeams;

    public TeamUtils() {
        Bukkit.getScoreboardManager().getMainScoreboard().getTeams().forEach(t -> t.unregister());
        this.scoreBoardTeams = Bukkit.getScoreboardManager().getMainScoreboard();
        for (Teams team : Teams.values()) {
            team = registerTeam(team);
            listTeams.put(team.getName(), team);
        }
    }

    public Teams registerTeam(Teams team) {
        team.setTeamPlayers(new ArrayList<String>());
        return team;
    }

    public void playerJoinTeam(String name, String playerName) {
        Teams team = listTeams.get(name);
        List<String> teamPlayers = new ArrayList<>();
        if (TomoNova.getPlugin().gameManager.getPlayersPerTeam() <= team.getNumberPlayers()) {
            Bukkit.getPlayer(playerName).sendMessage(ChatColor.RED + Lang.TEAM_FULL.toString());
            return;
        }
        teamPlayers = team.getTeamPlayers();
        teamPlayers.add(playerName);

        team.setTeamPlayers(teamPlayers);
        team.setNumberPlayers(team.getNumberPlayers() + 1);

        Bukkit.getOnlinePlayers().forEach(p -> p.setScoreboard(this.scoreBoardTeams));
        Bukkit.getPlayer(playerName).setDisplayName(team.getBaseColor() + playerName);
        Bukkit.getPlayer(playerName).setPlayerListName(team.getBaseColor() + playerName);
        listTeams.put(name, team);
    }

    public void playerQuitTeam(String playerName) {
        if (getTeamNameFromPlayer(playerName).equals("None")) {
            return;
        }
        String name = getTeamNameFromPlayer(playerName);
        Teams team = listTeams.get(name);
        List<String> teamPlayers = team.getTeamPlayers();
        if (teamPlayers.contains(playerName)) {
            teamPlayers.remove(playerName);
            team.setTeamPlayers(teamPlayers);
            team.setNumberPlayers(team.getNumberPlayers() - 1);
        }

        listTeams.put(name, team);
    }

    public void resetTeams() {

        for (String playerName : TomoNova.getPlugin().gameManager.getPlayers()) {
            playerQuitTeam(playerName);
        }

    }

    public HashMap<String, Teams> getTeamHashMap() {
        return listTeams;
    }

    public String getTeamNameFromPlayer(String playerName) {
        String teamName = "None";
        for (String name : this.listTeams.keySet()) {
            Teams team = this.listTeams.get(name);
            if (team.getTeamPlayers().contains(playerName)) {
                teamName = team.getName();
            }
        }
        return teamName;
    }

    public void getTeamToTeamlessPlayers(List<String> players) {
        //On cherche les joueurs qui n'ont pas de team
        List<String> teamlessPlayers = new ArrayList<>();
        for (String player : players) {
            for (String teamName : this.listTeams.keySet()) {
                if (listTeams.get(teamName).getTeamPlayers() != null) {
                    if (listTeams.get(teamName).getTeamPlayers().contains(player)) {
                        teamlessPlayers.add(player);
                    }
                }
            }
        }
        //Si tous les joueurs ont une team rien besoin de faire
        if (teamlessPlayers.size() == 0) {
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
        while (capaciteNonPleines < teamlessPlayers.size()) {
            teamsNonPleines.add(teamsVides.get(0));
            teamsVides.remove(0);
            capaciteNonPleines = capaciteNonPleines + playerPerTeam;
            capacitéVides = capacitéVides - playerPerTeam;
        }
        //Attribution des joueurs
        while (teamlessPlayers.size() > 0) {
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
            String player = teamlessPlayers.get(rand.nextInt(players.size()));
            playerJoinTeam(teamWithLessPlayer.getName(), player);
            //On supprime la team de la liste si elle est pleine
            if (teamWithLessPlayer.getNumberPlayers() == playerPerTeam) {
                teamsNonPleines.remove(teamWithLessPlayer);
            }
            Collections.shuffle(teamsNonPleines);
            teamlessPlayers.remove(player);
        }
        return;
    }

    public Scoreboard getScoreBoardTeams() {
        return scoreBoardTeams;
    }
}

