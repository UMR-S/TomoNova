package umaru.tomonova.tomonova.core.game;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.core.task.TaskCountdown;
import umaru.tomonova.tomonova.core.task.TaskFinalCountdown;
import umaru.tomonova.tomonova.core.task.TaskManager;
import umaru.tomonova.tomonova.listeners.littlerules.LittleRule;
import umaru.tomonova.tomonova.listeners.littlerules.LittleRules;
import umaru.tomonova.tomonova.utils.customItems.CustomItems;
import umaru.tomonova.tomonova.utils.teams.Teams;

import java.util.*;

import static java.lang.Math.sqrt;

public class GameManager {
    private final TomoNova tomoNova = TomoNova.getPlugin();
    private final List<String> players;
    private final List<String> deadPlayers;
    private int playersPerTeam = 3;
    private Inventory gameInventory;

    // Config
    private int maxPlayers = 33;
    private int minPlayers = 30;
    private boolean nether = true;
    private boolean uhc = true;
    private boolean Switch = false;
    private boolean taupe = false;
    private boolean scarletMansion = false;
    private boolean tomoLostVillage = false;
    private boolean bleachUhc = false;
    private int timeBorder = 120;

    private int netherEndTime = 120;
    private int pvpTime = 20;
    private int suddenDeathTime = 150;
    private int betweenSwitches = 20;
    private int numberSwitches = 3;
    private int beforeTaupe = 20;
    private int numberTaupes = 1;
    private BukkitTask preGame;
    public List<LittleRule> littleRulesList;
    private boolean isDamage;

    private int actualSubborderFinalSize;
    private int actualSubborderTime;
    private final List<Integer> listSubborderFinalSize;
    private final List<Integer> listSubborderTime;
    private final Map<Teams, Location> locationTeams;
    private final Map<String, Location> locationSolo;
    private final Map<String, Location> playerNetherSpawn;

    public GameManager() {
        players = new ArrayList<>();
        deadPlayers = new ArrayList<>();
        littleRulesList = new ArrayList<>();
        locationTeams = new HashMap<>();
        locationSolo = new HashMap<>();
        playerNetherSpawn = new HashMap<>();
        listSubborderFinalSize = new ArrayList<>();
        listSubborderTime = new ArrayList<>();
    }

    // Start game
    public void preStart() {
        GameStates.setCurrentState(GameStates.LOBBY_END);
        if (playersPerTeam > 1) {
            assignTeamLocation();
        } else if (playersPerTeam == 1) {
            assignSoloLocations();
        }
        tomoNova.worldBorderUtils.change(tomoNova.worldBorderUtils.getStartSize());
        TaskCountdown.setPreStartTime(10);
        preGame = new TaskCountdown(tomoNova).runTaskTimer(tomoNova, 0, 20);
    }

    private void assignTeamLocation() {
        Map<String, Teams> teams = tomoNova.teamUtils.getTeamHashMap();
        double r = 0.5 * tomoNova.worldBorderUtils.getStartSize() / sqrt(teams.size());
        for (String teamName : teams.keySet()) {
            Teams team = teams.get(teamName);
            Location teamLoc = getValidLocation(locationTeams, r);
            locationTeams.put(team, teamLoc);
        }
    }

    private void assignSoloLocations() {
        double r = 0.5 * tomoNova.worldBorderUtils.getStartSize() / sqrt(players.size());
        for (String player : players) {
            Location playerLoc = getValidLocation(locationSolo, r);
            locationSolo.put(player, playerLoc);
        }
    }

    private <T> Location getValidLocation(Map<T, Location> existingLocations, double r) {
        Location location;
        int attempt = 0;
        do {
            location = getRandomLocation();
            attempt++;
            if (attempt == 10) {
                r *= 0.95;
                attempt = 0;
            }
        } while (!isValidLocation(existingLocations, location, r));
        return location;
    }

    private <T> boolean isValidLocation(Map<T, Location> existingLocations, Location location, double r) {
        if (existingLocations.isEmpty()) return true;

        for (Location existingLocation : existingLocations.values()) {
            if (location.distance(existingLocation) <= 2 * r) {
                return false;
            }
        }
        return true;
    }

    public void start() {
        GameStates.setCurrentState(GameStates.PREGAME);
        tomoNova.worldUtils.getWorld().setPVP(false);
        unregisterUnusedLittleRules();
        if (!TomoNova.test) {
            teleportPlayers();
        }
        initializeGameSettings();
        startGameTasks();
    }

    private void unregisterUnusedLittleRules() {
        for (LittleRules littleRule : LittleRules.values()) {
            if (!hasLittleRule(littleRule.getLittleRule())) {
                HandlerList.unregisterAll((Listener) littleRule.getLittleRule());
            }
        }
    }

    private void teleportPlayers() {
        if (playersPerTeam > 1) {
            teleportTeamPlayers();
        } else if (playersPerTeam == 1) {
            teleportSoloPlayers();
        }
    }

    private void teleportTeamPlayers() {
        for (Teams team : locationTeams.keySet()) {
            spawnPreGameLobby(locationTeams.get(team));
            for (String playerName : team.getTeamPlayers()) {
                teleportPlayer(locationTeams.get(team).add(0.5,0,0.5), playerName);
            }
        }
    }

    private void teleportSoloPlayers() {
        for (String playerName : locationSolo.keySet()) {
            spawnPreGameLobby(locationSolo.get(playerName));
            teleportPlayer(locationSolo.get(playerName).add(0.5,0,0.5), playerName);
        }
    }

    private void teleportPlayer(Location location, String playerName) {
        Location loc = new Location(location.getWorld(), location.getX(), 201.0, location.getZ());
        Player player = Bukkit.getPlayer(playerName);
        if (player != null) {
            player.teleport(loc);
        }
    }

    private void initializeGameSettings() {
        isDamage = false;
        TaskFinalCountdown.setPreStartTime(10);
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.getInventory().clear();
            player.setGameMode(GameMode.SURVIVAL);
        });
        if (tomoLostVillage) {
            initializeTomoLostVillage();
        }
        if(bleachUhc){
            tomoNova.bleachUHC.removeCraft();
        }
    }

    private void initializeTomoLostVillage() {
        Bukkit.getScoreboardManager().getMainScoreboard().getTeams().forEach(team -> team.unregister());
        tomoNova.tomoLostVillage.nightVisionOn(players);
        tomoNova.tomoLostVillage.bonusHearthOn(players);
        players.forEach(playerName -> Bukkit.getPlayer(playerName).setHealth(24.0));
    }

    private void startGameTasks() {
        new TaskFinalCountdown(tomoNova).runTaskTimer(tomoNova, 0, 20);
        new TaskManager(tomoNova).runTaskTimer(tomoNova, 200, 20);
    }

    public void stop() {
        GameStates.setCurrentState(GameStates.LOBBY);
        if (preGame != null) {
            preGame.cancel();
        }
    }

    public Location getRandomLocation() {
        final Random r = new Random();
        double x = r.nextInt(tomoNova.worldBorderUtils.getStartSize() / 2);
        double z = r.nextInt(tomoNova.worldBorderUtils.getStartSize() / 2);
        return new Location(tomoNova.worldUtils.getWorld(), x, 250.0, z);
    }

    public void spawnPreGameLobby(Location locPlateform) {
        Location loc = new Location(locPlateform.getWorld(), locPlateform.getX(), 200.0, locPlateform.getZ());
        loc.getChunk().load(true);
        double x = loc.getX();
        double z = loc.getZ();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                loc.setX(x + i);
                loc.setZ(z + j);
                loc.getWorld().getBlockAt(loc).setType(Material.WHITE_STAINED_GLASS);
            }
        }
    }

    public void deletePreGameLobby() {
        clearLocations(locationTeams);
        clearLocations(locationSolo);
    }

    private <T> void clearLocations(Map<T, Location> locations) {
        for (Location locPlateform : locations.values()) {
            Location loc = new Location(locPlateform.getWorld(), locPlateform.getX(), 200.0, locPlateform.getZ());
            loc.getChunk().load(true);
            double x = loc.getX();
            double z = loc.getZ();
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    loc.setX(x + i);
                    loc.setZ(z + j);
                    loc.getWorld().getBlockAt(loc).setType(Material.AIR);
                }
            }
        }
    }

    //Getter et setter
    public void addNetherSpawn(String pName) {
        Player player = Bukkit.getPlayer(pName);
        Location locNether = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
        playerNetherSpawn.put(pName, locNether);
    }

    public Location getNetherSpawn(String pName) {
        return playerNetherSpawn.get(pName);
    }

    public void addPlayer(String player) {
        players.add(player);
    }

    public void removePlayer(String player) {
        if (players.contains(player)) {
            players.remove(player);
        }
    }

    public List<String> getPlayers() {
        return players;
    }

    public void addDeadPlayer(String player) {
        deadPlayers.add(player);
    }

    public void removeDeadPlayer(String player) {
        if (deadPlayers.contains(player)) {
            deadPlayers.remove(player);
        }
    }

    public List<String> getDeadPlayers() {
        return deadPlayers;
    }

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


    public int getPlayersPerTeam() {
        return playersPerTeam;
    }

    public void setPlayersPerTeam(int playersPerTeam) {
        tomoNova.teamUtils.resetTeams();
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

    public boolean isBleachUhc() {
        return bleachUhc;
    }

    public void setBleachUhc(boolean bleachUhc) {
        if(bleachUhc){
            List<String> loreClasses = new ArrayList<>();
            loreClasses.add("Sasageyo");
            ItemStack choixClasses;
            choixClasses = CustomItems.createCustomItem(Material.NETHERITE_SCRAP, ChatColor.AQUA, "Choissisez votre classe", loreClasses);
            Bukkit.getOnlinePlayers().forEach(p -> p.getInventory().addItem(choixClasses));
        }
        else {
            Bukkit.getOnlinePlayers().forEach(p-> p.getInventory().remove(Material.NETHERITE_SCRAP));
        }
        this.bleachUhc = bleachUhc;
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

    public int getNumberTaupes() {
        return numberTaupes;
    }

    public void setNumberTaupes(int numberTaupes) {
        this.numberTaupes = numberTaupes;
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
