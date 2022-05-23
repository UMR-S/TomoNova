package umaru.tomonova.tomonova.utils.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.lang.Lang;

import java.util.List;

public class ScoreboardSign {


    public static Scoreboard create(String playerName,int count) {

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective("sidebar", "dummy", ChatColor.LIGHT_PURPLE + Lang.SB_PREFIX.toString());
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        String[] lines = setLines(playerName, count);
        Integer i = -1;
        for (String s : lines) {
            Score score = objective.getScore(s);
            score.setScore(i);
            i--;
        }
        return scoreboard;
    }

    public static String[] setLines(String playerName, int count) {
        final String[] lines = new String[12];
        lines[0] = "UHC classique"; //Gamemode?
        lines[1] = "";

        //Flèche vers le spawn
        StringBuilder builder = new StringBuilder();
        builder.append(TomoNova.getPlugin().scoreboardUtils.getColoredDirectionTo(Bukkit.getPlayer(playerName), TomoNova.getPlugin().worldUtils.getWorld().getWorldBorder().getCenter()));

        lines[2] = Lang.SB_SPAWN.toString() + builder + " §7(" + (int)Bukkit.getPlayer(playerName).getLocation().distance(TomoNova.getPlugin().worldUtils.getWorld().getWorldBorder().getCenter()) +")   " ; //Flèche vers le 0/0 à faire
        lines[3] = Lang.SB_KILLS.toString(); //Nb kills (à faire)
        lines[4] = Lang.SB_PLAYERS.toString() + TomoNova.getPlugin().gameManager.getNumberPlayer(); //Nombre de joueurs encore en vie
        lines[5] = " ";
        lines[6] =Lang.SB_TEAM.toString() + " - ";
        if (TomoNova.getPlugin().gameManager.getPlayersPerTeam() > 1) {
            String teamName = TomoNova.getPlugin().teamUtils.getTeamNameFromPlayer(playerName);
            lines[5] =Lang.SB_TEAM.toString() + TomoNova.getPlugin().teamUtils.getTeamHashMap().get(teamName).getBaseColor() + teamName;
            String listJoueurs = " ";
            List<String> listPlayers = TomoNova.getPlugin().teamUtils.getTeamHashMap().get(teamName).getTeamPlayers();
            for (String pName : listPlayers) {
                if (!playerName.equals(pName)) {
                    listJoueurs = listPlayers + pName + " ";
                }
            }
            lines[6] = listJoueurs.toString();
        }
        lines[7] = "   ";
        lines[8] = Lang.SB_TIME.toString() + (count-(count%60)) /60 + ":" + String.format("%02d", count%60); //Task manager
        lines[9] = "    ";
        lines[10] = Lang.SB_BORDER.toString() + ((TomoNova.getPlugin().taskManager.getBorderTime()-count)-((TomoNova.getPlugin().taskManager.getBorderTime()-count)%60))/60 + ":" + String.format("%02d",(TomoNova.getPlugin().taskManager.getBorderTime()-count)%60) ;
        lines[11] = Lang.SB_BORDER.toString() + (int) -TomoNova.getPlugin().worldUtils.getWorld().getWorldBorder().getSize()/2 + "/" + (int) TomoNova.getPlugin().worldUtils.getWorld().getWorldBorder().getSize()/2;
        return lines;
    }
}
