package umaru.tomonova.tomonova.utils.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.lang.Lang;

import java.util.List;

public class ScoreboardSign {


    public static Scoreboard create(Player player) {

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective("sidebar", "dummy", ChatColor.LIGHT_PURPLE + Lang.SB_PREFIX.toString());
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        String[] lines = setLines(player);
        Integer i = -1;
        for (String s : lines) {
            Score score = objective.getScore(s);
            score.setScore(i);
            i--;
        }
        return scoreboard;
    }

    public static String[] setLines(Player player) {
        final String[] lines = new String[16];
        lines[0] = ""; //Gamemode?
        lines[2] = Lang.SB_SPAWN.toString(); //Flèche vers le 0/0 à faire
        lines[3] = Lang.SB_KILLS.toString(); //Nb kills à faire
        lines[4] = Lang.SB_PLAYERS.toString() + TomoNova.getPlugin().gameManager.getNumberPlayer(); //Nombre de joueurs encore en vie
        if (TomoNova.getPlugin().gameManager.getPlayersPerTeam() > 1) {
            String teamName = TomoNova.getPlugin().teamUtils.getTeamNameFromPlayer(player);
            lines[5] = TomoNova.getPlugin().teamUtils.getTeamHashMap().get(teamName).getBaseColor() + teamName;
            String listJoueurs = " ";
            List<Player> listPlayers = TomoNova.getPlugin().teamUtils.getTeamHashMap().get(teamName).getTeamPlayers();
            for (Player p : listPlayers) {
                if (p.getName() != player.getName()) {
                    listJoueurs = listPlayers + p.getName() + " ";
                }
            }
            lines[6] = Lang.SB_TEAM.toString() + listJoueurs;
        }
        lines[8] = Lang.SB_TIME.toString(); //Task manager
        lines[9] = Lang.SB_BORDER.toString(); //Border utils à faire
        lines[10] = Lang.SB_BORDER.toString();
        return lines;
    }
}
