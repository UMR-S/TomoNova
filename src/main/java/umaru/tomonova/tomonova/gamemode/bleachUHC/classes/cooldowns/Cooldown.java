package umaru.tomonova.tomonova.gamemode.bleachUHC.classes.cooldowns;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import umaru.tomonova.tomonova.utils.cooldowns.TimeUtils;

import java.util.HashMap;
import java.util.Iterator;

public class Cooldown {
    public static HashMap<String, AbilityCooldown> cooldownPlayers = new HashMap<String, AbilityCooldown>();
    public static void add(String playerName, String ability, long seconds, long systime) {
        if(!cooldownPlayers.containsKey(playerName)) cooldownPlayers.put(playerName, new AbilityCooldown(playerName));
        if(isCooling(playerName, ability)) return;
        cooldownPlayers.get(playerName).cooldownMap.put(ability, new AbilityCooldown(playerName, seconds * 1000, System.currentTimeMillis()));
    }
    public static boolean isCooling(String playerName, String ability) {
        if(!cooldownPlayers.containsKey(playerName)) return false;
        if(!cooldownPlayers.get(playerName).cooldownMap.containsKey(ability)) return false;
        return true;
    }
    public static double getRemaining(String playerName, String ability) {
        if(!cooldownPlayers.containsKey(playerName)) return 0.0;
        if(!cooldownPlayers.get(playerName).cooldownMap.containsKey(ability)) return 0.0;
        return TimeUtils.convert((cooldownPlayers.get(playerName).cooldownMap.get(ability).seconds + cooldownPlayers.get(playerName).cooldownMap.get(ability).systime) - System.currentTimeMillis(), TimeUtils.TimeUnit.SECONDS, 1);
    }
    public static void coolDurMessage(String playerName, String ability) {
        Player player = Bukkit.getPlayer(playerName);
        if(player == null) {
            return;
        }
        if(!isCooling(playerName, ability)) {
            return;
        }
        player.sendMessage(ChatColor.GRAY + ability + " Cooldown " + ChatColor.AQUA + getRemaining(playerName, ability) + " Seconds");
    }
    public static void removeCooldown(String playerName, String ability) {
        if(!cooldownPlayers.containsKey(playerName)) {
            return;
        }
        if(!cooldownPlayers.get(playerName).cooldownMap.containsKey(ability)) {
            return;
        }
        cooldownPlayers.get(playerName).cooldownMap.remove(ability);
        Player player = Bukkit.getPlayer(playerName);
        if(playerName != null) {
            player.sendMessage(ChatColor.GRAY + "You can now use " + ChatColor.AQUA + ability);
        }
    }
    public static void handleCooldowns() {
        if(cooldownPlayers.isEmpty()) {
            return;
        }
        for(Iterator<String> it = cooldownPlayers.keySet().iterator(); it.hasNext();) {
            String key = it.next();
            for(Iterator<String> iter = cooldownPlayers.get(key).cooldownMap.keySet().iterator(); iter.hasNext();) {
                String name = iter.next();
                if(getRemaining(key, name) <= 0.0) {
                    removeCooldown(key, name);
                }
            }
        }
    }
}
