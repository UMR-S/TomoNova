package umaru.tomonova.tomonova.utils.teams;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.List;

public enum Teams {
    RED("Red", "§c", ChatColor.RED, Material.RED_BANNER, null, null, 0),
    YELLOW("Yellow", "§e", ChatColor.YELLOW, Material.YELLOW_BANNER, null, null, 0),
    ORANGE("Orange", "§6", ChatColor.GOLD, Material.ORANGE_BANNER, null, null, 0), //Gold
    DARK_GREEN("Green", "§2", ChatColor.DARK_GREEN, Material.GREEN_BANNER, null, null, 0),
    GREEN("Lime", "§a", ChatColor.GREEN, Material.LIME_BANNER, null, null, 0),
    AQUA("Aqua", "§b", ChatColor.AQUA, Material.LIGHT_BLUE_BANNER, null, null, 0),
    BLUE("Blue", "§1", ChatColor.BLUE, Material.BLUE_BANNER, null, null, 0),
    PINK("Pink", "§d", ChatColor.LIGHT_PURPLE, Material.PINK_BANNER, null, null, 0), //Light purple
    MAGENTA("Magenta", "§5", ChatColor.DARK_PURPLE, Material.MAGENTA_BANNER, null, null, 0), //Dark purple
    WHITE("White", "§f", ChatColor.WHITE, Material.WHITE_BANNER, null, null, 0),
    GRAY("Gray", "§7", ChatColor.GRAY, Material.GRAY_BANNER, null, null, 0);
    private final String name;
    private final String prefix;
    private final ChatColor baseColor;
    private final Material banner;
    private Team team;
    private List<Player> teamPlayers;
    private Integer numberPlayers;


    Teams(String name, String prefix, ChatColor baseColor, Material banner, Team team, List<Player> teamPlayers, Integer numberPlayers) {
        this.name = name;
        this.prefix = prefix;
        this.baseColor = baseColor;
        this.banner = banner;
        this.team = team;
        this.teamPlayers = teamPlayers;
        this.numberPlayers = numberPlayers;
    }

    public Material getBanner() {
        return banner;
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

    public void setTeamPlayers(List<Player> teamPlayers) {
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

    public Integer getNumberPlayers() {
        return numberPlayers;
    }

    public void setNumberPlayers(Integer numberPlayers) {
        this.numberPlayers = numberPlayers;
    }
}

