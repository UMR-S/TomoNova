package umaru.tomonova.tomonova.gamemode;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Team;
import umaru.tomonova.tomonova.core.TomoNova;

import java.util.*;

public class TomoLostVillage {
    private HashMap<String, TeamsTLV> mapPlayerTeam;
    private List<ChatColor> listColors;
    private int numeroTeam;

    public TomoLostVillage() {
        this.numeroTeam = 1;
        this.listColors = Arrays.asList(ChatColor.RED, ChatColor.YELLOW, ChatColor.GOLD, ChatColor.DARK_GREEN, ChatColor.GREEN, ChatColor.AQUA, ChatColor.BLUE, ChatColor.LIGHT_PURPLE, ChatColor.DARK_PURPLE, ChatColor.WHITE, ChatColor.GRAY);
        this.mapPlayerTeam = new HashMap<String, TeamsTLV>();
    }

    public void createNewTeam(String killerName, String killedName) {
        Random rand = new Random();

        List<String> playerList = new ArrayList<>();
        playerList.add(killedName);
        playerList.add(killedName);

        Team team = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam(killerName);
        team.addEntry(killerName);
        team.addEntry(killedName);

        TeamsTLV teamTLV = new TeamsTLV(killerName, "[" + numeroTeam + "]", listColors.get(rand.nextInt(listColors.size())), null, team, playerList, 2);

        team.setPrefix(teamTLV.getPrefix());
        team.setColor(teamTLV.getBaseColor());
        team.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
        team.setAllowFriendlyFire(false);

        mapPlayerTeam.put(killerName, teamTLV);
        mapPlayerTeam.put(killedName, teamTLV);
        numeroTeam++;
    }

    public void addPlayerToTeam(String killerName, String killedName) {
        TeamsTLV team = mapPlayerTeam.get(killerName);
        team.getTeam().addEntry(killedName);

        List<String> listPlayers = team.getTeamPlayers();
        listPlayers.add(killedName);
        team.setTeamPlayers(listPlayers);

        team.setNumberPlayers(team.getNumberPlayers() + 1);
        if (team.getNumberPlayers() >= 3) {
            nightVisionOff(listPlayers);
        }
        if (team.getNumberPlayers() == 5) {
            bonusHearthOff(listPlayers);
        }

    }

    public void removePlayerToTeam(String killedName) {
        TeamsTLV team = mapPlayerTeam.get(killedName);
        team.getTeam().removeEntry(killedName);

        List<String> listPlayers = team.getTeamPlayers();
        listPlayers.remove(killedName);
        team.setTeamPlayers(listPlayers);

        team.setNumberPlayers(team.getNumberPlayers() - 1);

        if (team.getNumberPlayers() < 3) {
            nightVisionOn(listPlayers);
        }
        if (team.getNumberPlayers() < 5) {
            bonusHearthOn(listPlayers);
        }
    }

    public boolean isKilledInTeam(String killedName) {
        for (String playerName : mapPlayerTeam.keySet()) {
            if (killedName.equals(playerName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isHunger(String playerName) {
        for (String pName : mapPlayerTeam.keySet()) {
            if (playerName.equals(pName)) {
                return true;
            }
        }
        return false;
    }

    public void nightVisionOn(List<String> playersInTeam) {
        for (String player : playersInTeam) {
            Bukkit.getPlayer(player).addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 72000, 1));
        }
    }

    public void nightVisionOff(List<String> playersInTeam) {
        for (String player : playersInTeam) {
            Bukkit.getPlayer(player).removePotionEffect(PotionEffectType.NIGHT_VISION);
        }
    }

    public boolean isCompass(String playerName) {
        for (String pName : mapPlayerTeam.keySet()) {
            if (playerName.equals(pName)) {
                if (mapPlayerTeam.get(playerName).getNumberPlayers() >= 4) {
                    return false;
                }
            }
        }
        return true;
    }

    public void bonusHearthOn(List<String> playersName) {
        for (String pName : playersName) {
            Bukkit.getPlayer(pName).getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(24.0);
        }
    }

    public void bonusHearthOff(List<String> playersName) {
        for (String pName : playersName) {
            Bukkit.getPlayer(pName).getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20.0);
        }
    }

    public int killerTeamNumberPlayer(String killerName) {
        for (String pName : mapPlayerTeam.keySet()) {
            if (killerName.equals(pName)) {
                return mapPlayerTeam.get(killerName).getNumberPlayers();
            }
        }
        return 0;
    }

    public List<String> playersInTeamOfPlayer(String playerName) {
        return mapPlayerTeam.get(playerName).getTeamPlayers();
    }

    public ChatColor getChatColor(String playerName) {
        return mapPlayerTeam.get(playerName).getBaseColor();
    }

    public void tomoLostVillageSettings() {
        TomoNova.getPlugin().gameManager.setUhc(false);
        TomoNova.getPlugin().gameManager.setSwitch(false);
        TomoNova.getPlugin().gameManager.setTaupe(false);
        TomoNova.getPlugin().gameManager.setScarletMansion(false);
        TomoNova.getPlugin().gameManager.setTomoLostVillage(true);
        TomoNova.getPlugin().gameManager.setPlayersPerTeam(1);
    }
}
