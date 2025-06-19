package umaru.tomonova.tomonova.utils.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.lang.Lang;

import java.awt.*;

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
        Objective health = scoreboard.registerNewObjective( subname + "health", Criterias.HEALTH, "hap");
        health.setRenderType(RenderType.HEARTS);
        health.setDisplaySlot(DisplaySlot.PLAYER_LIST);
        //Teams


        //Création du panneau à droite
        Objective objective = scoreboard.registerNewObjective(subname + "sidebar", "dummy",  ChatColor.LIGHT_PURPLE + Lang.SB_PREFIX.toString());
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        String[] lines = setLines(playerName, count);
        //Lignes à update : 1(en bleach UHC),2,3,4,5,8,10,11

        Score gamemode = objective.getScore(lines[0]);
        gamemode.setScore(-1);

        if(!TomoNova.getPlugin().gameManager.isBleachUhc()) {
            Score espace1 = objective.getScore(lines[1]);
            espace1.setScore(-2);
        } else {
            Team flecheBoss = scoreboard.registerNewTeam(playerName + "flecheBoss");
            flecheBoss.addEntry(ChatColor.BLUE + ""  + ChatColor.WHITE);
            flecheBoss.setPrefix(lines[1]);
            objective.getScore(ChatColor.BLUE + ""  + ChatColor.WHITE).setScore(-2);
        }
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

        if(!TomoNova.getPlugin().gameManager.isBleachUhc()){
            Score espace2 = objective.getScore(lines[6]);
            espace2.setScore(-7);
        } else {
            Team flecheOeil = scoreboard.registerNewTeam(playerName + "flecheOeil");
            flecheOeil.addEntry(ChatColor.DARK_BLUE + ""  + ChatColor.WHITE);
            flecheOeil.setPrefix(lines[6]);
            objective.getScore(ChatColor.DARK_BLUE + ""  + ChatColor.WHITE).setScore(-7);
        }

        Score espace3 = objective.getScore(lines[7]);
        espace3.setScore(-8);


        Team time = scoreboard.registerNewTeam(playerName + "time");
        time.addEntry(ChatColor.LIGHT_PURPLE + ""  + ChatColor.WHITE);
        time.setPrefix(lines[8]);
        objective.getScore(ChatColor.LIGHT_PURPLE + ""  + ChatColor.WHITE).setScore(-9);

        Score espace4 = objective.getScore(lines[9]);
        espace4.setScore(-10);

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

        if(TomoNova.getPlugin().gameManager.isBleachUhc()){
            scoreboard.getTeam(playerName + "flecheBoss").setPrefix(lines[1]);
            scoreboard.getTeam(playerName + "flecheOeil").setPrefix(lines[6]);
        }
        scoreboard.getTeam(playerName + "flecheSpawn").setPrefix(lines[2]);
        scoreboard.getTeam(playerName + "kills").setPrefix(lines[3]);
        scoreboard.getTeam(playerName + "nbJoueurs").setPrefix(lines[4]);
        scoreboard.getTeam(playerName + "teamName").setPrefix(lines[5]);
        scoreboard.getTeam(playerName + "time").setPrefix(lines[8]);
        scoreboard.getTeam(playerName + "borderTime").setPrefix(lines[10]);
        scoreboard.getTeam(playerName + "borderLength").setPrefix(lines[11]);
    }

    public static String[] setLines(String playerName, int count) {
        final String[] lines = new String[12];
        //Gamemode ligne 0
        try {
            lines[0] = gamemodeLine();
        } catch (Exception e) {
            System.out.println("Error in line 0: " + e.getMessage());
        }
        //Boss flèche pour BleachUHC, vide sinon
        lines[1] = "";
        try {
            if(TomoNova.getPlugin().gameManager.isBleachUhc()){
                StringBuilder builder = new StringBuilder();
                String bossName = TomoNova.getPlugin().bleachUHC.returnPlayerTargetName(playerName);
                if(!bossName.equals("None")){
                    Location playerLoc = Bukkit.getPlayer(playerName).getLocation();
                    playerLoc.setY(0.0);
                    Location bossLoc = TomoNova.getPlugin().bleachUHC.returnBossLoc(bossName);
                    bossLoc.setY(0.0);
                    int distance = (int) playerLoc.distance(bossLoc);
                    builder.append(TomoNova.getPlugin().scoreboardUtils.getColoredDirectionTo(Bukkit.getPlayer(playerName), bossLoc));
                    lines[1] = bossName +  " : " + builder  + " §7(" + distance + ")   ";
                }
            }
        } catch (Exception e) {
            System.out.println("Error in line 1: " + e.getMessage());
        }
        // Flèche vers le spawn
        lines[2] = " ";
        try {
            StringBuilder builder = new StringBuilder();
            if (Bukkit.getPlayer(playerName).getWorld() == TomoNova.getPlugin().worldUtils.getWorld()) {
                builder.append(TomoNova.getPlugin().scoreboardUtils.getColoredDirectionTo(Bukkit.getPlayer(playerName), TomoNova.getPlugin().worldUtils.getWorld().getWorldBorder().getCenter()));
                lines[2] = Lang.SB_SPAWN.toString() + builder + " §7(" + TomoNova.getPlugin().scoreboardUtils.getSpawnDistance(playerName) + ")   ";
            }
            if (Bukkit.getPlayer(playerName).getWorld() == TomoNova.getPlugin().worldUtils.getNether()) {
                builder.append(TomoNova.getPlugin().scoreboardUtils.getColoredDirectionTo(Bukkit.getPlayer(playerName), TomoNova.getPlugin().gameManager.getNetherSpawn(playerName)));
                lines[2] = Lang.SB_SPAWN.toString() + builder + " §7(" + (int) Bukkit.getPlayer(playerName).getLocation().distance(TomoNova.getPlugin().gameManager.getNetherSpawn(playerName)) + ")   ";
            }
        } catch (Exception e) {
            System.out.println("Error in line 2: " + e.getMessage());
        }
        //Nombre de kills
        lines[3] = "   ";
        try {
            lines[3] = Lang.SB_KILLS.toString() + TomoNova.getPlugin().killCounter.getKillCount(playerName);
        } catch (Exception e) {
            System.out.println("Error in line 3: " + e.getMessage());
        }
        //Nombre de joueurs encore en vie
        lines[4] = "    ";
        try {
            lines[4] = Lang.SB_PLAYERS.toString() + TomoNova.getPlugin().gameManager.getNumberPlayer();
        } catch (Exception e) {
            System.out.println("Error in line 4: " + e.getMessage());
        }
        //Nom de la team
        lines[5] = "     ";
        try {
            if (TomoNova.getPlugin().gameManager.getPlayersPerTeam() > 1 && !TomoNova.getPlugin().teamUtils.getTeamNameFromPlayer(playerName).equals("None")) {
                String teamName = TomoNova.getPlugin().teamUtils.getTeamNameFromPlayer(playerName);
                lines[5] = Lang.SB_TEAM.toString() + TomoNova.getPlugin().teamUtils.getTeamHashMap().get(teamName).getBaseColor() + teamName;
            }
        } catch (Exception e) {
            System.out.println("Error in line 5: " + e.getMessage());
        }
        //BleachUHC Cache Oeil, sinon rien
        lines[6] = "      ";
        try {
            if(TomoNova.getPlugin().gameManager.isBleachUhc()){
                if(TomoNova.getPlugin().bleachUHC.getCacheOeilBoolean(playerName)){
                    String playerTracked = TomoNova.getPlugin().bleachUHC.getTrackedName();
                    StringBuilder builder = new StringBuilder();
                    if (Bukkit.getPlayer(playerName).getWorld() == Bukkit.getPlayer(playerTracked).getWorld()) {
                        builder.append(TomoNova.getPlugin().scoreboardUtils.getColoredDirectionTo(Bukkit.getPlayer(playerName), Bukkit.getPlayer(playerTracked).getLocation()));
                        Location playerCacheOeilLoc = Bukkit.getPlayer(playerName).getLocation();
                        playerCacheOeilLoc.setY(0.0);
                        Location playerTraqueLoc = Bukkit.getPlayer(playerTracked).getLocation();
                        int distance = (int) playerCacheOeilLoc.distance(playerTraqueLoc);
                        lines[6] = playerTracked + builder + " §7(" + distance + ")   ";
                    }
                }
                if(TomoNova.getPlugin().bleachUHC.getTrackedBoolean(playerName)){
                    String playerCacheOeil = TomoNova.getPlugin().bleachUHC.getCacheOeilName();
                    StringBuilder builder = new StringBuilder();
                    if (Bukkit.getPlayer(playerName).getWorld() == Bukkit.getPlayer(playerCacheOeil).getWorld()) {
                        Location playerTraqueLoc = Bukkit.getPlayer(playerName).getLocation();
                        playerTraqueLoc.setY(0.0);
                        Location playerCacheOeilLoc = Bukkit.getPlayer(playerCacheOeil).getLocation();
                        int distance = (int) playerCacheOeilLoc.distance(playerTraqueLoc);
                        if(distance <= 80) {
                            builder.append(TomoNova.getPlugin().scoreboardUtils.getColoredDirectionTo(Bukkit.getPlayer(playerName), Bukkit.getPlayer(playerCacheOeil).getLocation()));
                            lines[6] = playerCacheOeil + builder + " §7(" + distance + ")   ";
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error in line 6: " + e.getMessage());
        }
        // BleachUHC fragment Hogyoku sinon vide
        lines[7] = "       ";
        try {
            if(TomoNova.getPlugin().gameManager.isBleachUhc()){
                if(playerName.equals(TomoNova.getPlugin().bleachUHC.getPlayerWithHogyokuHeart())){
                    StringBuilder builder = new StringBuilder();
                    Location hogyokuLoc = Bukkit.getPlayer(TomoNova.getPlugin().bleachUHC.getNearestPlayerWithHogyokuFragment()).getLocation();
                    builder.append(TomoNova.getPlugin().scoreboardUtils.getColoredDirectionTo(Bukkit.getPlayer(playerName),hogyokuLoc));
                    int distance = (int) Bukkit.getPlayer(playerName).getLocation().distance(hogyokuLoc);
                    lines[7] = Lang.SB_HOGYOKU.toString() + builder + " §7(" + TomoNova.getPlugin().scoreboardUtils.getSpawnDistance(playerName) + ")   ";
                }
            }
        } catch (Exception e) {
            System.out.println("Error in line 7: " + e.getMessage());
        }
        lines[8] = "        ";
        try {
            lines[8] = Lang.SB_TIME.toString() + (count - (count % 60)) / 60 + ":" + String.format("%02d", count % 60); // Task manager
        } catch (Exception e) {
            System.out.println("Error in line 8: " + e.getMessage());
        }
        lines[9] = "         ";
        try {
            lines[9] = "         ";
        } catch (Exception e) {
            System.out.println("Error in line 9: " + e.getMessage());
        }
        lines[10] = "          ";
        try {
            if (TomoNova.getPlugin().gameManager.getTimeBorder() * 60 < count) {
                lines[10] = Lang.SB_BORDER.toString() + "--:--";
            } else {
                int borderTime = TomoNova.getPlugin().gameManager.getActualBorderTime(count);
                lines[10] = Lang.SB_BORDER.toString() + (int) ((borderTime * 60 - count) / 60) + ":" + String.format("%02d", (borderTime * 60 - count) % 60);
            }
        } catch (Exception e) {
            System.out.println("Error in line 10: " + e.getMessage());
        }
        lines[11] = "           ";
        try {
            lines[11] = Lang.SB_BORDER.toString() + (int) -TomoNova.getPlugin().worldUtils.getWorld().getWorldBorder().getSize() / 2 + "/" + (int) TomoNova.getPlugin().worldUtils.getWorld().getWorldBorder().getSize() / 2;
        } catch (Exception e) {
            System.out.println("Error in line 11: " + e.getMessage());
        }

        return lines;
    }

    public static String gamemodeLine(){
        if(TomoNova.getPlugin().gameManager.isUhc()){
            return Lang.GUIS_GM_UHC_NAME.toString();
        }
        if(TomoNova.getPlugin().gameManager.isSwitch()){
            return Lang.GUIS_GM_SWITCH_NAME.toString();
        }
        if(TomoNova.getPlugin().gameManager.isTaupe()){
            return Lang.GUIS_GM_TAUPE_NAME.toString();
        }
        if(TomoNova.getPlugin().gameManager.isScarletMansion()){
            return Lang.GUIS_GM_FSM_NAME.toString();
        }
        if(TomoNova.getPlugin().gameManager.isTomoLostVillage()){
            return Lang.GUIS_GM_TLV_NAME.toString();
        }
        if(TomoNova.getPlugin().gameManager.isBleachUhc()){
            return Lang.GUIS_GM_BLEACH_NAME.toString();
        }
        return "Nouveau gamemode";
    }
}