package umaru.tomonova.tomonova.utils.teams;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.lang.Lang;

import java.util.*;

public class TeamUtils {

    private final HashMap<String, Teams> listTeams = new HashMap<>();

    public TeamUtils() {
        for (Teams team : Teams.values()) {
            team = registerTeam(team);
            listTeams.put(team.getName(), team);
        }
    }

    public Teams registerTeam(Teams team) {
        team.setTeamPlayers(new ArrayList<>());
        return team;
    }

    public void playerJoinTeam(String name, String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        if (player == null) {
            return;
        }

        Teams team = listTeams.get(name);
        if (team == null) {
            player.sendMessage(ChatColor.RED + Lang.TEAM_NOT_FOUND.toString());
            return;
        }

        if (TomoNova.getPlugin().gameManager.getPlayersPerTeam() <= team.getNumberPlayers()) {
            player.sendMessage(ChatColor.RED + Lang.TEAM_FULL.toString());
            return;
        }

        List<String> teamPlayers = team.getTeamPlayers();
        teamPlayers.add(playerName);
        team.setTeamPlayers(teamPlayers);
        team.setNumberPlayers(team.getNumberPlayers() + 1);

        player.setDisplayName(team.getBaseColor() + playerName);
        player.setPlayerListName(team.getBaseColor() + playerName);
        listTeams.put(name, team);
    }

    public void playerQuitTeam(String playerName) {
        String teamName = getTeamNameFromPlayer(playerName);
        if ("None".equals(teamName)) {
            return;
        }

        Teams team = listTeams.get(teamName);
        if (team != null) {
            List<String> teamPlayers = team.getTeamPlayers();
            teamPlayers.remove(playerName);
            team.setTeamPlayers(teamPlayers);
            team.setNumberPlayers(team.getNumberPlayers() - 1);
            listTeams.put(teamName, team);
        }
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
        return listTeams.values().stream()
                .filter(team -> team.getTeamPlayers().contains(playerName))
                .map(Teams::getName)
                .findFirst()
                .orElse("None");
    }

    public boolean arePlayersOnSameTeam(String firstPlayer, String secondPlayer) {
        return getTeamNameFromPlayer(firstPlayer).equals(getTeamNameFromPlayer(secondPlayer));
    }

    public void assignTeamToTeamlessPlayers(List<String> players) {
        List<String> teamlessPlayers = getTeamlessPlayers(players);

        if (teamlessPlayers.isEmpty()) {
            return;
        }

        List<Teams> availableTeams = getAvailableTeams();
        int playerPerTeam = TomoNova.getPlugin().gameManager.getPlayersPerTeam();

        if (teamlessPlayers.size() > getAvailableCapacity(availableTeams)) {
            TomoNova.getPlugin().gameManager.stop();
            return;
        }

        balanceTeamCapacity(teamlessPlayers, availableTeams, playerPerTeam);
        assignPlayersToTeams(teamlessPlayers, availableTeams, playerPerTeam);
    }

    private List<String> getTeamlessPlayers(List<String> players) {
        List<String> teamlessPlayers = new ArrayList<>();
        for (String player : players) {
            if ("None".equals(getTeamNameFromPlayer(player))) {
                teamlessPlayers.add(player);
            }
        }
        return teamlessPlayers;
    }

    private List<Teams> getAvailableTeams() {
        List<Teams> availableTeams = new ArrayList<>();
        int playerPerTeam = TomoNova.getPlugin().gameManager.getPlayersPerTeam();
        for (Teams team : listTeams.values()) {
            if (team.getNumberPlayers() < playerPerTeam) {
                availableTeams.add(team);
            }
        }
        return availableTeams;
    }

    private int getAvailableCapacity(List<Teams> availableTeams) {
        int capacity = 0;
        int playerPerTeam = TomoNova.getPlugin().gameManager.getPlayersPerTeam();
        for (Teams team : availableTeams) {
            if (team.getNumberPlayers() == 0) {
                capacity += playerPerTeam;
            } else {
                capacity += (playerPerTeam - team.getNumberPlayers());
            }
        }
        return capacity;
    }

    private void balanceTeamCapacity(List<String> teamlessPlayers, List<Teams> availableTeams, int playerPerTeam) {
        int teamlessCount = teamlessPlayers.size();
        int nonEmptyTeamsCapacity = getNonEmptyTeamsCapacity(availableTeams, playerPerTeam);
        int emptyTeamsCapacity = getEmptyTeamsCapacity(availableTeams, playerPerTeam);

        while (nonEmptyTeamsCapacity < teamlessCount) {
            Teams emptyTeam = getFirstEmptyTeam(availableTeams);
            if (emptyTeam != null) {
                availableTeams.add(emptyTeam);
                nonEmptyTeamsCapacity += playerPerTeam;
                emptyTeamsCapacity -= playerPerTeam;
            }
        }
    }

    private int getNonEmptyTeamsCapacity(List<Teams> availableTeams, int playerPerTeam) {
        int capacity = 0;
        for (Teams team : availableTeams) {
            if (team.getNumberPlayers() > 0) {
                capacity += (playerPerTeam - team.getNumberPlayers());
            }
        }
        return capacity;
    }

    private int getEmptyTeamsCapacity(List<Teams> availableTeams, int playerPerTeam) {
        int capacity = 0;
        for (Teams team : availableTeams) {
            if (team.getNumberPlayers() == 0) {
                capacity += playerPerTeam;
            }
        }
        return capacity;
    }

    private Teams getFirstEmptyTeam(List<Teams> availableTeams) {
        for (Teams team : availableTeams) {
            if (team.getNumberPlayers() == 0) {
                availableTeams.remove(team);
                return team;
            }
        }
        return null;
    }

    private void assignPlayersToTeams(List<String> teamlessPlayers, List<Teams> availableTeams, int playerPerTeam) {
        Collections.shuffle(availableTeams);
        Random rand = new Random();
        while (!teamlessPlayers.isEmpty()) {
            Teams teamWithLessPlayers = findTeamWithLessPlayers(availableTeams, playerPerTeam);
            if (teamWithLessPlayers != null) {
                String player = teamlessPlayers.remove(rand.nextInt(teamlessPlayers.size()));
                playerJoinTeam(teamWithLessPlayers.getName(), player);
                if (teamWithLessPlayers.getNumberPlayers() == playerPerTeam) {
                    availableTeams.remove(teamWithLessPlayers);
                }
            }
        }
    }

    private Teams findTeamWithLessPlayers(List<Teams> availableTeams, int playerPerTeam) {
        return availableTeams.stream()
                .filter(team -> team.getNumberPlayers() < playerPerTeam)
                .min(Comparator.comparingInt(Teams::getNumberPlayers))
                .orElse(null);
    }
}
