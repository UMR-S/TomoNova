package umaru.tomonova.tomonova.utils.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.lang.Lang;

public class ScoreboardSign {

    public static Scoreboard create(String playerName) {
        try {
            Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
            clearExistingObjectives(scoreboard);

            String subname = getSubname(playerName);

            registerHealthObjective(scoreboard, subname);
            Objective sidebarObjective = registerSidebarObjective(scoreboard, subname);

            String[] lines = setLines(playerName, 0);
            setScores(sidebarObjective, lines, playerName, scoreboard);

            TomoNova.getPlugin().scoreboardUtils.getScoreboardMap().put(playerName, scoreboard);
            return scoreboard;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void updateScoreboard(String playerName, int count) {
        Player player = Bukkit.getPlayer(playerName);
        if (player == null) {
            return;
        }
        if (player.getScoreboard().getTeam(playerName + "flecheSpawn") == null) {
            player.setScoreboard(TomoNova.getPlugin().scoreboardUtils.getScoreboardMap().get(playerName));
        }
        try {
            Scoreboard scoreboard = player.getScoreboard();
            String[] lines = setLines(playerName, count);

            updateTeamPrefixes(scoreboard, playerName, lines);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void clearExistingObjectives(Scoreboard scoreboard) {
        scoreboard.getObjectivesByCriteria("dummy").forEach(Objective::unregister);
    }

    private static String getSubname(String playerName) {
        return playerName.length() < 9 ? playerName : playerName.substring(0, 8);
    }

    private static void registerHealthObjective(Scoreboard scoreboard, String subname) {
        Objective health = scoreboard.registerNewObjective(subname + "health", Criterias.HEALTH, "");
        health.setRenderType(RenderType.HEARTS);
        health.setDisplaySlot(DisplaySlot.PLAYER_LIST);
    }

    private static Objective registerSidebarObjective(Scoreboard scoreboard, String subname) {
        Objective objective = scoreboard.registerNewObjective(subname + "sidebar", "dummy", ChatColor.LIGHT_PURPLE + Lang.SB_PREFIX.toString());
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        return objective;
    }

    private static void setScores(Objective objective, String[] lines, String playerName, Scoreboard scoreboard) {
        setStaticScores(objective, lines);

        if (TomoNova.getPlugin().gameManager.isBleachUhc()) {
            registerTeamAndSetScore(scoreboard, objective, playerName + "flecheBoss", ChatColor.RED + "" + ChatColor.WHITE, lines[1], -2);
        }

        registerTeamAndSetScore(scoreboard, objective, playerName + "flecheSpawn", ChatColor.RED + "" + ChatColor.WHITE, lines[2], -3);
        registerTeamAndSetScore(scoreboard, objective, playerName + "kills", ChatColor.DARK_RED + "" + ChatColor.WHITE, lines[3], -4);
        registerTeamAndSetScore(scoreboard, objective, playerName + "nbJoueurs", ChatColor.GREEN + "" + ChatColor.WHITE, lines[4], -5);
        registerTeamAndSetScore(scoreboard, objective, playerName + "teamName", ChatColor.DARK_GREEN + "" + ChatColor.WHITE, lines[5], -6);
        registerTeamAndSetScore(scoreboard, objective, playerName + "time", ChatColor.LIGHT_PURPLE + "" + ChatColor.WHITE, lines[8], -9);
        registerTeamAndSetScore(scoreboard, objective, playerName + "borderTime", ChatColor.DARK_PURPLE + "" + ChatColor.WHITE, lines[10], -11);
        registerTeamAndSetScore(scoreboard, objective, playerName + "borderLength", ChatColor.GOLD + "" + ChatColor.WHITE, lines[11], -12);
    }

    private static void setStaticScores(Objective objective, String[] lines) {
        objective.getScore(lines[0]).setScore(-1);
        objective.getScore(lines[6]).setScore(-7);
        objective.getScore(lines[7]).setScore(-8);
        objective.getScore(lines[9]).setScore(-10);
    }

    private static void registerTeamAndSetScore(Scoreboard scoreboard, Objective objective, String teamName, String entry, String prefix, int score) {
        Team team = scoreboard.registerNewTeam(teamName);
        team.addEntry(entry);
        team.setPrefix(prefix);
        objective.getScore(entry).setScore(score);
    }

    private static void updateTeamPrefixes(Scoreboard scoreboard, String playerName, String[] lines) {
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
        lines[0] = gamemodeLine();
        lines[1] = TomoNova.getPlugin().gameManager.isBleachUhc() ? bossLineBleachUhc(playerName) : "";
        lines[2] = buildSpawnLine(playerName);
        lines[3] = Lang.SB_KILLS.toString();
        lines[4] = Lang.SB_PLAYERS.toString() + TomoNova.getPlugin().gameManager.getNumberPlayer();
        lines[5] = buildTeamNameLine(playerName);
        lines[6] = "   ";
        lines[7] = "    ";
        lines[8] = buildTimeLine(count);
        lines[9] = "    ";
        lines[10] = buildBorderTimeLine(count);
        lines[11] = buildBorderLengthLine();
        return lines;
    }

    public static String gamemodeLine() {
        if (TomoNova.getPlugin().gameManager.isUhc()) {
            return Lang.GUIS_GM_UHC_NAME.toString();
        }
        if (TomoNova.getPlugin().gameManager.isSwitch()) {
            return Lang.GUIS_GM_SWITCH_NAME.toString();
        }
        if (TomoNova.getPlugin().gameManager.isTaupe()) {
            return Lang.GUIS_GM_TAUPE_NAME.toString();
        }
        if (TomoNova.getPlugin().gameManager.isScarletMansion()) {
            return Lang.GUIS_GM_FSM_NAME.toString();
        }
        if (TomoNova.getPlugin().gameManager.isTomoLostVillage()) {
            return Lang.GUIS_GM_TLV_NAME.toString();
        }
        if (TomoNova.getPlugin().gameManager.isBleachUhc()) {
            return Lang.GUIS_GM_BLEACH_NAME.toString();
        }
        return "Nouveau gamemode";
    }

    public static String bossLineBleachUhc(String playerName) {
        if (TomoNova.getPlugin().gameManager.isBleachUhc()) {
            if (TomoNova.getPlugin().bleachUHC.playersBossTarget.containsKey(playerName)) {
                StringBuilder builder = new StringBuilder();
                if (Bukkit.getPlayer(playerName).getWorld() == TomoNova.getPlugin().worldUtils.getWorld()) {
                    String bossName = TomoNova.getPlugin().bleachUHC.returnPlayerTargetName(playerName);
                    Location bossLoc = TomoNova.getPlugin().bleachUHC.returnBossLoc(bossName);
                    builder.append(TomoNova.getPlugin().scoreboardUtils.getColoredDirectionTo(Bukkit.getPlayer(playerName), bossLoc));
                    return TomoNova.getPlugin().bleachUHC.playersBossTarget.get(playerName)
                            + builder + " §7("
                            + (int) bossLoc.distance(Bukkit.getPlayer(playerName).getLocation()) + ")   ";
                }
            }
        }
        return "";
    }

    private static String buildSpawnLine(String playerName) {
        StringBuilder builder = new StringBuilder();
        Player player = Bukkit.getPlayer(playerName);
        if (player == null) {
            return "";
        }

        if (player.getWorld() == TomoNova.getPlugin().worldUtils.getWorld()) {
            builder.append(TomoNova.getPlugin().scoreboardUtils.getColoredDirectionTo(player, TomoNova.getPlugin().worldUtils.getWorld().getWorldBorder().getCenter()));
            return Lang.SB_SPAWN.toString() + builder + " §7(" + TomoNova.getPlugin().scoreboardUtils.getSpawnDistance(playerName) + ")   ";
        } else if (player.getWorld() == TomoNova.getPlugin().worldUtils.getNether()) {
            builder.append(TomoNova.getPlugin().scoreboardUtils.getColoredDirectionTo(player, TomoNova.getPlugin().gameManager.getNetherSpawn(playerName)));
            return Lang.SB_SPAWN.toString() + builder + " §7(" + (int) player.getLocation().distance(TomoNova.getPlugin().gameManager.getNetherSpawn(playerName)) + ")   ";
        }
        return "";
    }

    private static String buildTeamNameLine(String playerName) {
        if (TomoNova.getPlugin().gameManager.getPlayersPerTeam() > 1 && !TomoNova.getPlugin().teamUtils.getTeamNameFromPlayer(playerName).equals("None")) {
            String teamName = TomoNova.getPlugin().teamUtils.getTeamNameFromPlayer(playerName);
            return Lang.SB_TEAM.toString() + TomoNova.getPlugin().teamUtils.getTeamHashMap().get(teamName).getBaseColor() + teamName;
        }
        return "  ";
    }

    private static String buildTimeLine(int count) {
        return Lang.SB_TIME.toString() + (count - (count % 60)) / 60 + ":" + String.format("%02d", count % 60);
    }

    private static String buildBorderTimeLine(int count) {
        if (TomoNova.getPlugin().gameManager.getTimeBorder() * 60 < count) {
            return Lang.SB_BORDER.toString() + "--:--";
        } else {
            int borderTime = TomoNova.getPlugin().gameManager.getActualBorderTime(count);
            return Lang.SB_BORDER.toString() + (int) ((borderTime * 60 - count) / 60) + ":" + String.format("%02d", (borderTime * 60 - count) % 60);
        }
    }

    private static String buildBorderLengthLine() {
        double borderSize = TomoNova.getPlugin().worldUtils.getWorld().getWorldBorder().getSize();
        return Lang.SB_BORDER.toString() + (int) -borderSize / 2 + "/" + (int) borderSize / 2;
    }
}
