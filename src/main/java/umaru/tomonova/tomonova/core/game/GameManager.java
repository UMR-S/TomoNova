package umaru.tomonova.tomonova.core.game;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitTask;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.core.task.TaskCountdown;
import umaru.tomonova.tomonova.core.task.TaskFinalCountdown;
import umaru.tomonova.tomonova.core.task.TaskManager;
import umaru.tomonova.tomonova.listeners.littlerules.LittleRule;
import umaru.tomonova.tomonova.listeners.littlerules.LittleRules;
import umaru.tomonova.tomonova.utils.teams.Teams;

import java.util.*;

import static java.lang.Math.sqrt;

public class GameManager {
    private TomoNova tomoNova = TomoNova.getPlugin();
    private List<Player> players;
    private List<Player> deadPlayers;
    private int playersPerTeam = 3;
    private Inventory gameInventory;

    //Config
    private int maxPlayers = 33;
    private int minPlayers = 30;
    private boolean nether = true;
    private boolean uhc = true;
    private boolean Switch = false;
    private boolean taupe = false;
    private boolean scarletMansion = false;
    private boolean tomoLostVillage = false;
    private int timeBorder = 120; //En min

    private int netherEndTime = 120; //En min
    private int pvpTime = 20; //En min
    private int suddenDeathTime = 150; //En min
    private int betweenSwitches = 20;
    private int numberSwitches = 3;
    private int beforeTaupe = 20;
    private HashMap<Teams, Location> locationTeams;
    BukkitTask preGame;
    public List<LittleRule> littleRulesList = new ArrayList<LittleRule>();
    private boolean isDamage;

    private int actualSubborderFinalSize;
    private int actualSubborderTime;
    private List<Integer> listSubborderFinalSize = new ArrayList<Integer>();
    private List<Integer> listSubborderTime = new ArrayList<Integer>();

    public GameManager() {
        TomoNova.getPlugin();
        players = new ArrayList<Player>();

    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        if (players.contains(player)) {
            players.remove(player);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<String> getPlayersName() {
        List<String> names = new ArrayList<>();
        for (Player p : players) {
            names.add(p.getName());
        }
        return names;
    }

    //Start game
    public void preStart() {
        //Remplissage des teams automatique
        //tomoNova.teamUtils.getTeamToTeamlessPlayers(getPlayers());
        //Liste des location et des teams
        GameStates.setCurrentState(GameStates.LOBBY_END);
        HashMap<String, Teams> teams = tomoNova.teamUtils.getTeamHashMap();
        double r = 0.5 * tomoNova.worldBorderUtils.getStartSize() / sqrt(teams.size());
        locationTeams = new HashMap<Teams, Location>();

        for (String teamName : teams.keySet()) {
            Teams team = teams.get(teamName);
            Location teamLoc = getRandomLocation();
            if (locationTeams.size() > 0) {
                List<Double> dist = new ArrayList<Double>();
                for (Teams otherTeam : locationTeams.keySet()) {
                    dist.add(teamLoc.distance(locationTeams.get(otherTeam)));
                }
                Integer i = 0;
                while (Collections.max(dist) > 2 * r) {
                    teamLoc = getRandomLocation();
                    dist = new ArrayList<Double>();
                    for (Teams otherTeam : locationTeams.keySet()) {
                        dist.add(teamLoc.distance(locationTeams.get(otherTeam)));
                    }
                    if (i == 10) {
                        i = 0;
                        r = r * 0.95;
                    }
                }
            }
            locationTeams.put(team, teamLoc);
        }

        //Bordure
        tomoNova.worldBorderUtils.change(tomoNova.worldBorderUtils.getStartSize());
        //Timer
        TaskCountdown.setPreStartTime(10);
        preGame = new TaskCountdown(tomoNova).runTaskTimer(tomoNova, 0, 20);

    }

    public void start() {
        GameStates.setCurrentState(GameStates.PREGAME);
        tomoNova.worldUtils.getWorld().setPVP(false);
        for (final LittleRules littleRule : LittleRules.values()) {
            if (!hasLittleRule(littleRule.getLittleRule())) {
                HandlerList.unregisterAll((Listener) littleRule.getLittleRule());
            }
        }
        //Tp les joueurs
        for (Teams team : locationTeams.keySet()) {
            spawnPreGameLobby(locationTeams.get(team));
            if (team.getNumberPlayers() != 0) {
                for (String playerName : team.getTeamPlayers()) {
                    Player player = Bukkit.getPlayer(playerName);
                    Location loc = locationTeams.get(team);
                    try {
                        loc.setY(loc.getY() + 1);
                        loc.setX(loc.getX() - 0.5);
                        loc.setZ(loc.getZ() - 0.5);
                        player.teleport(loc);
                    } catch (NullPointerException nullPointerException) {
                        break;
                    }
                }
            }
        }
        setDamage(false);
        TaskFinalCountdown.setPreStartTime(10);
        Bukkit.getOnlinePlayers().forEach(p -> p.getInventory().clear());
        BukkitTask countdown = new TaskFinalCountdown(tomoNova).runTaskTimer(tomoNova, 0, 20);
        BukkitTask TaskManager = new TaskManager(tomoNova).runTaskTimer(tomoNova, 200, 20);

    }

    public void stop() {
        GameStates.setCurrentState(GameStates.LOBBY);
        preGame.cancel();
    }

    //Location
    public Location getRandomLocation() {
        final Random r = new Random();
        double x = r.nextInt(tomoNova.worldBorderUtils.getStartSize() / 2);
        double z = r.nextInt(tomoNova.worldBorderUtils.getStartSize() / 2);
        Location loc = new Location(tomoNova.worldUtils.getWorld(), x, 250.0, z);
        return loc;
    }

    //Création des petites plateformes
    public void spawnPreGameLobby(Location loc) {
        loc.getChunk().load(true);
        double x = loc.getX();
        double z = loc.getZ();
        loc.setY(200.0);
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                loc.setX(x + i);
                loc.setZ(z + j);
                loc.getWorld().getBlockAt(loc).setType(Material.WHITE_STAINED_GLASS);
            }
        }
    }

    public void deletePreGameLobby() {
        for (Teams team : locationTeams.keySet()) {
            Location loc = locationTeams.get(team);
            loc.getChunk().load(true);
            double x = loc.getX();
            double z = loc.getZ();
            loc.setY(200.0);
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    loc.setX(x + i);
                    loc.setZ(z + j);
                    loc.getWorld().getBlockAt(loc).setType(Material.AIR);
                }
            }

        }
    }
    //Getter et setter


    public int getActualSubborderFinalSize() {
        return actualSubborderFinalSize;
    }

    public void setActualSubborderFinalSize(int actualSubborderFinalSize) {
        this.actualSubborderFinalSize = actualSubborderFinalSize;
    }

    public int getActualSubborderTime() {
        return actualSubborderTime;
    }

    public void setActualSubborderTime(int actualSubborderTime) {
        this.actualSubborderTime = actualSubborderTime;
    }

    public List<Integer> getListSubborderFinalSize() {
        return this.listSubborderFinalSize;
    }

    public void addListSubborderFinalSize(Integer SubborderFinalSize) {
        this.listSubborderFinalSize.add(SubborderFinalSize);
    }

    public void removeLastListSubborderFinalSize() {
        if (!this.listSubborderFinalSize.isEmpty()) {
            this.listSubborderFinalSize.remove(this.listSubborderFinalSize.size() - 1);
        }

    }

    public List<Integer> getListSubborderTime() {
        return this.listSubborderTime;
    }

    public void addListSubborderTime(Integer SubborderTime) {
        this.listSubborderTime.add(SubborderTime);
    }

    public void removeLastListSubborderTime() {
        if (!this.listSubborderTime.isEmpty()) {
            this.listSubborderTime.remove(this.listSubborderTime.size() - 1);
        }
    }

    public int getActualBorderTime(int count) {
        int borderTime = getTimeBorder();
        if (!(listSubborderTime.isEmpty() || listSubborderTime.get(listSubborderTime.size() - 1) * 60 < count)) {
            for (int time : listSubborderTime) {
                if (time * 60 > count) {
                    borderTime = time;
                    return borderTime;
                }
            }
        }
        return borderTime;
    }

    public boolean isDamage() {
        return isDamage;
    }

    public void setDamage(boolean damage) {
        isDamage = damage;
    }

    public List<LittleRule> getLittleRulesList() {
        return littleRulesList;
    }

    public void setLittleRulesList(List<LittleRule> littleRulesList) {
        this.littleRulesList = littleRulesList;
    }

    public void addLittleRule(final LittleRule littleRule) {
        if (!this.littleRulesList.contains(littleRule)) {
            this.littleRulesList.add(littleRule);
        }
    }

    public void removeLittleRule(final LittleRule littleRule) {
        if (this.littleRulesList.contains(littleRule)) {
            this.littleRulesList.remove(littleRule);
        }
    }

    public boolean hasLittleRule(LittleRule littleRule) {
        return this.littleRulesList.contains(littleRule);
    }

    public int getNumberPlayer() {
        return players.size();
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Player> getDeadPlayers() {
        return deadPlayers;
    }

    public void setDeadPlayers(List<Player> deadPlayers) {
        this.deadPlayers = deadPlayers;
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

    public boolean isScarletMansion() {
        return scarletMansion;
    }

    public void setScarletMansion(boolean scarletMansion) {
        this.scarletMansion = scarletMansion;
    }

    public boolean isTomoLostVillage() {
        return tomoLostVillage;
    }

    public void setTomoLostVillage(boolean tomoLostVillage) {
        this.tomoLostVillage = tomoLostVillage;
    }

    public int getBetweenSwitches() {
        return betweenSwitches;
    }

    public void setBetweenSwitches(int betweenSwitches) {
        this.betweenSwitches = betweenSwitches;
    }

    public int getNumberSwitches() {
        return numberSwitches;
    }

    public void setNumberSwitches(int numberSwitches) {
        this.numberSwitches = numberSwitches;
    }

    public int getBeforeTaupe() {
        return beforeTaupe;
    }

    public void setBeforeTaupe(int beforeTaupe) {
        this.beforeTaupe = beforeTaupe;
    }

    public int getTimeBorder() {
        return timeBorder;
    }

    public void setTimeBorder(int timeBorder) {
        this.timeBorder = timeBorder;
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
