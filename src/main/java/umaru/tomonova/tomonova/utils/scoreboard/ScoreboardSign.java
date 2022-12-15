package umaru.tomonova.tomonova.utils.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.lang.Lang;

public class ScoreboardSign {

    public static Scoreboard create(String playerName) {
        int count = 0;
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        scoreboard.getObjectivesByCriteria("dummy").forEach(s -> s.unregister());
        //Affichage de la vie
        String subname;
        if(playerName.toCharArray().length < 9){
            subname = playerName;
        }
        else{
            subname = playerName.substring(0,8);
        }
        Objective health = scoreboard.registerNewObjective( subname + "health", Criterias.HEALTH, "");
        health.setRenderType(RenderType.HEARTS);
        health.setDisplaySlot(DisplaySlot.PLAYER_LIST);
        //Teams


        //Création du panneau à droite
        Objective objective = scoreboard.registerNewObjective(subname + "sidebar", "dummy", ChatColor.LIGHT_PURPLE + Lang.SB_PREFIX.toString());
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        String[] lines = setLines(playerName, count);
        //Lignes à update : 2,3,4,5,8,10,11

        Score gamemode = objective.getScore(lines[0]);
        gamemode.setScore(-1);

        Score espace1 = objective.getScore(lines[1]);
        espace1.setScore(-2);

        Team flecheSpawn = scoreboard.registerNewTeam(playerName + "flecheSpawn");
        flecheSpawn.addEntry(ChatColor.RED + ""  + ChatColor.WHITE);
        flecheSpawn.setPrefix(lines[2]);
        objective.getScore(ChatColor.RED + ""  + ChatColor.WHITE).setScore(-3);

        Team kills = scoreboard.registerNewTeam(playerName + "kills");
        kills.addEntry(ChatColor.DARK_RED + ""  + ChatColor.WHITE);
        kills.setPrefix(lines[3]);
        objective.getScore(ChatColor.DARK_RED + ""  + ChatColor.WHITE).setScore(-4);

        Team nbJoueurs = scoreboard.registerNewTeam(playerName + "nbJoueurs");
        nbJoueurs.addEntry(ChatColor.GREEN + ""  + ChatColor.WHITE);
        nbJoueurs.setPrefix(lines[4]);
        objective.getScore(ChatColor.GREEN + ""  + ChatColor.WHITE).setScore(-5);

        Team teamName = scoreboard.registerNewTeam(playerName + "teamName");
        teamName.addEntry(ChatColor.DARK_GREEN + ""  + ChatColor.WHITE);
        teamName.setPrefix(lines[5]);
        objective.getScore(ChatColor.DARK_GREEN + ""  + ChatColor.WHITE).setScore(-6);

        Score espace2 = objective.getScore(lines[6]);
        espace1.setScore(-7);

        Score espace3 = objective.getScore(lines[7]);
        espace1.setScore(-8);

        Team time = scoreboard.registerNewTeam(playerName + "time");
        time.addEntry(ChatColor.LIGHT_PURPLE + ""  + ChatColor.WHITE);
        time.setPrefix(lines[8]);
        objective.getScore(ChatColor.LIGHT_PURPLE + ""  + ChatColor.WHITE).setScore(-9);

        Score espace4 = objective.getScore(lines[9]);
        espace1.setScore(-10);

        Team borderTime = scoreboard.registerNewTeam(playerName + "borderTime");
        borderTime.addEntry(ChatColor.DARK_PURPLE + ""  + ChatColor.WHITE);
        borderTime.setPrefix(lines[10]);
        objective.getScore(ChatColor.DARK_PURPLE + ""  + ChatColor.WHITE).setScore(-11);

        Team borderLength = scoreboard.registerNewTeam(playerName + "borderLength");
        borderLength.addEntry(ChatColor.GOLD + ""  + ChatColor.WHITE);
        borderLength.setPrefix(lines[11]);
        objective.getScore(ChatColor.GOLD + ""  + ChatColor.WHITE).setScore(-12);

        //Ajout à la hashmap des scoreboards
        TomoNova.getPlugin().scoreboardUtils.getScoreboardMap().put(playerName,scoreboard);
        return scoreboard;
    }
    public static void updateScoreboard(String playerName,int count){
        Player player  = Bukkit.getPlayer(playerName);
        if(player == null){
            return;
        }
        if(player.getScoreboard().getTeam(playerName + "flecheSpawn") == null){
            player.setScoreboard(TomoNova.getPlugin().scoreboardUtils.getScoreboardMap().get(playerName));
        }
        Scoreboard scoreboard = player.getScoreboard();
        String[] lines = setLines(playerName, count);

        scoreboard.getTeam(playerName + "flecheSpawn").setPrefix(lines[2]);
        scoreboard.getTeam(playerName + "kills").setPrefix(lines[3]);
        scoreboard.getTeam(playerName +"nbJoueurs").setPrefix(lines[4]);
        scoreboard.getTeam(playerName + "teamName").setPrefix(lines[5]);
        scoreboard.getTeam(playerName + "time").setPrefix(lines[8]);
        scoreboard.getTeam(playerName + "borderTime").setPrefix(lines[10]);
        scoreboard.getTeam(playerName + "borderLength").setPrefix(lines[11]);
    }

    public static String[] setLines(String playerName, int count) {
        final String[] lines = new String[12];
        lines[0] = "UHC classique"; //Gamemode?
        lines[1] = "";
        lines[2] = " ";
        //Flèche vers le spawn
        StringBuilder builder = new StringBuilder();
        if (Bukkit.getPlayer(playerName).getWorld() == TomoNova.getPlugin().worldUtils.getWorld()) {
            builder.append(TomoNova.getPlugin().scoreboardUtils.getColoredDirectionTo(Bukkit.getPlayer(playerName), TomoNova.getPlugin().worldUtils.getWorld().getWorldBorder().getCenter()));
            lines[2] = Lang.SB_SPAWN.toString() + builder + " §7(" + TomoNova.getPlugin().scoreboardUtils.getSpawnDistance(playerName) + ")   ";
        }
        if (Bukkit.getPlayer(playerName).getWorld() == TomoNova.getPlugin().worldUtils.getNether()) {
            builder.append(TomoNova.getPlugin().scoreboardUtils.getColoredDirectionTo(Bukkit.getPlayer(playerName), TomoNova.getPlugin().gameManager.getNetherSpawn(playerName)));
            lines[2] = Lang.SB_SPAWN.toString() + builder + " §7(" + (int) Bukkit.getPlayer(playerName).getLocation().distance(TomoNova.getPlugin().gameManager.getNetherSpawn(playerName)) + ")   ";

        }
        lines[3] = Lang.SB_KILLS.toString(); //Nb kills (à faire)
        lines[4] = Lang.SB_PLAYERS.toString() + TomoNova.getPlugin().gameManager.getNumberPlayer(); //Nombre de joueurs encore en vie
        lines[5] = "  ";
        if (TomoNova.getPlugin().gameManager.getPlayersPerTeam() > 1 && TomoNova.getPlugin().teamUtils.getTeamNameFromPlayer(playerName)!="None") {
            String teamName = TomoNova.getPlugin().teamUtils.getTeamNameFromPlayer(playerName);
            lines[5] = Lang.SB_TEAM.toString() + TomoNova.getPlugin().teamUtils.getTeamHashMap().get(teamName).getBaseColor() + teamName;
        }
        lines[6] = "   ";
        lines[7] = "    ";
        lines[8] = Lang.SB_TIME.toString() + (count - (count % 60)) / 60 + ":" + String.format("%02d", count % 60); //Task manager
        lines[9] = "    ";
        if (TomoNova.getPlugin().gameManager.getTimeBorder() * 60 < count) {
            lines[10] = Lang.SB_BORDER.toString() + "--:--";
        } else {
            int borderTime = TomoNova.getPlugin().gameManager.getActualBorderTime(count);
            lines[10] = Lang.SB_BORDER.toString() + (int) ((borderTime * 60 - count) / 60) + ":" + String.format("%02d", (borderTime * 60 - count) % 60);
        }
        lines[11] = Lang.SB_BORDER.toString() + (int) -TomoNova.getPlugin().worldUtils.getWorld().getWorldBorder().getSize() / 2 + "/" + (int) TomoNova.getPlugin().worldUtils.getWorld().getWorldBorder().getSize() / 2;
        return lines;
    }
}
