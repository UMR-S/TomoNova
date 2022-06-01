package umaru.tomonova.tomonova.gamemode;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.scoreboard.Team;

import java.util.List;

public class TeamsTLV {
    private final String name;
    private final String prefix;
    private final ChatColor baseColor;
    private final Material banner;
    private List<String> teamPlayers;
    private Integer numberPlayers;


    TeamsTLV(String name, String prefix, ChatColor baseColor, Material banner, Team team, List<String> teamPlayers, Integer numberPlayers) {
        this.name = name;
        this.prefix = prefix;
        this.baseColor = baseColor;
        this.banner = banner;
        this.teamPlayers = teamPlayers;
        this.numberPlayers = numberPlayers;
    }

    public List<String> getTeamPlayers() {
        return teamPlayers;
    }

    public void setTeamPlayers(List<String> teamPlayers) {
        this.teamPlayers = teamPlayers;
    }

    public Integer getNumberPlayers() {
        return numberPlayers;
    }

    public void setNumberPlayers(Integer numberPlayers) {
        this.numberPlayers = numberPlayers;
    }

    public String getPrefix() {
        return prefix;
    }

    public ChatColor getBaseColor() {
        return baseColor;
    }
}
