package umaru.tomonova.tomonova.core.game;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.utils.scoreboard.Teams;

import java.util.*;

public class GameManager {
    private TomoNova plugin = TomoNova.getPlugin();
    private static List<Player> players;
    private static List<Player> deadPlayers;
    private static List<Teams> teams;
    private int playersPerTeam = 3;
    private Inventory gameInventory;

    //Config
    private int maxPlayers = 50;
    private int minPlayers = 30;
    private boolean nether = true;
    private boolean uhc = true;
    private boolean Switch = false;
    private boolean taupe = false;
    private int timeBorder = 120; //En min

    private int subBorders = 0;
    private int netherEndTime = 120; //En min
    private int pvpTime = 20; //En min
    private int suddenDeathTime = 150;


    public GameManager() {
        TomoNova.getPlugin();
        players = new ArrayList<Player>();

    }

    public static void addPlayer(Player player) {
        players.add(player);
    }

    public static void removePlayer(Player player) {
        if (players.contains(player)) {
            players.remove(player);
        }
    }

    public static List<Player> getPlayers() {
        return players;
    }

    public static List<String> getPlayersName() {
        List<String> names = new ArrayList<>();
        for (Player p : players) {
            names.add(p.getName());
        }
        return names;
    }

    public static int getNumberPlayer() {
        return players.size();
    }

    public static void setPlayers(List<Player> players) {
        GameManager.players = players;
    }

    public static List<Player> getDeadPlayers() {
        return deadPlayers;
    }

    public static void setDeadPlayers(List<Player> deadPlayers) {
        GameManager.deadPlayers = deadPlayers;
    }

    public static List<Teams> getTeams() {
        return teams;
    }

    public static void setTeams(List<Teams> teams) {
        GameManager.teams = teams;
    }

    public int getPlayersPerTeam() {
        return playersPerTeam;
    }

    public void setPlayersPerTeam(int playersPerTeam) {
        this.playersPerTeam = playersPerTeam;
    }

    public Inventory getGameInventory() {
        return gameInventory;
    }

    public void setGameInventory(Inventory gameInventory) {
        this.gameInventory = gameInventory;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public boolean isNether() {
        return nether;
    }

    public void setNether(boolean nether) {
        this.nether = nether;
    }

    public boolean isUhc() {
        return uhc;
    }

    public void setUhc(boolean uhc) {
        this.uhc = uhc;
    }

    public boolean isSwitch() {
        return Switch;
    }

    public void setSwitch(boolean aSwitch) {
        Switch = aSwitch;
    }

    public boolean isTaupe() {
        return taupe;
    }

    public void setTaupe(boolean taupe) {
        this.taupe = taupe;
    }

    public int getTimeBorder() {
        return timeBorder;
    }

    public void setTimeBorder(int timeBorder) {
        this.timeBorder = timeBorder;
    }

    public int getSubBorders() {
        return subBorders;
    }

    public void setSubBorders(int subBorders) {
        this.subBorders = subBorders;
    }

    public int getNetherEndTime() {
        return netherEndTime;
    }

    public void setNetherEndTime(int netherEndTime) {
        this.netherEndTime = netherEndTime;
    }

    public int getPvpTime() {
        return pvpTime;
    }

    public void setPvpTime(int pvpTime) {
        this.pvpTime = pvpTime;
    }

    public int getSuddenDeathTime() {
        return suddenDeathTime;
    }

    public void setSuddenDeathTime(int suddenDeathTime) {
        this.suddenDeathTime = suddenDeathTime;
    }
}
