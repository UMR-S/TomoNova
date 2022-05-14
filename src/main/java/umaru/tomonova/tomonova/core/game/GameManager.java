package umaru.tomonova.tomonova.core.game;

import org.bukkit.entity.Player;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.utils.scoreboard.Teams;

import java.util.*;

public class GameManager {
    private TomoNova plugin;
    private static List<Player> players;
    private static List<Player> deadPlayers;
    public static List<Teams> teams;

    public GameManager(){
        TomoNova.getPlugin();
        players = new ArrayList<Player>();
    }
    public static void addPlayer(Player player){
        players.add(player);
    }
    public static void removePlayer(Player player){
        if(players.contains(player)){
            players.remove(player);
        }
    }
    public static List<Player> getPlayers(){
        return players;
    }
    public static List<String> getPlayersName(){
        List<String> names = new ArrayList<>();
        for(Player p : players){
            names.add(p.getName());
        }
        return names;
    }
    public static int getNumberPlayer(){
        return players.size();
    }
}
