package umaru.tomonova.tomonova.utils.scoreboard;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.List;

public enum Teams {
    RED("Red", "§c", ChatColor.RED, null, null),
    YELLOW("Yellow", "§e",ChatColor.YELLOW , null, null),
    ORANGE("Orange", "§6",ChatColor.GOLD , null, null), //Gold
    DARK_GREEN("Green", "§2",ChatColor.DARK_GREEN , null, null),
    GREEN("Green", "§a",ChatColor.GREEN , null, null),
    AQUA("AQUA", "§b",ChatColor.AQUA , null, null),
    BLUE("Blue", "§1",ChatColor.BLUE , null, null),
    PINK("Pink", "§d",ChatColor.LIGHT_PURPLE , null, null), //Light purple
    MAGENTA("Magenta", "§5",ChatColor.DARK_PURPLE , null, null), //Dark purple
    WHITE("White", "§f",ChatColor.WHITE , null, null),
    GRAY("Gray", "§7",ChatColor.GRAY , null, null);
    private final String name;
    private final String prefix;
    private final ChatColor baseColor;
    private Team team;
    private List<Player> teamPlayers;

    Teams(String name, String prefix, ChatColor baseColor, Team team, List<Player> teamPlayers) {
        this.name = name;
        this.prefix = prefix;
        this.baseColor = baseColor;
        this.team = team;
        this.teamPlayers = teamPlayers;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public ChatColor getBaseColor() {
        return baseColor;
    }

    public Team getTeam() {
        return team;
    }

    public List<Player> getTeamPlayers() {
        return teamPlayers;
    }


    public void setTeam(Team team) {
        this.team = team;
    }

    public void setTeamPlayer(List<Player> teamPlayers) {
        this.teamPlayers = teamPlayers;
    }

    public static Teams getTeamFromName(String name) {
        for (Teams t : Teams.values()) {
            if (t.getName().equals(name)) {
                return t;
            }
        }
        return null;
    }

}

