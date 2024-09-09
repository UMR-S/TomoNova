package umaru.tomonova.tomonova.utils.players;

import umaru.tomonova.tomonova.core.TomoNova;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class KillCounter {

    private final HashMap<String, Integer> killCounts = new HashMap<>();

    public void incrementKillCount(String playerName) {
        int newKillCount = killCounts.getOrDefault(playerName, 0) + 1;
        killCounts.put(playerName, newKillCount);
    }

    public int getKillCount(String playerName) {
        return killCounts.getOrDefault(playerName, 0);
    }

    public void resetKillCount(String playerName) {
        killCounts.put(playerName, 0);
    }

    public String getTopKiller(String playerToExclude) {
        String topKiller = null;
        int highestKills = 0;
        for (String playerName : killCounts.keySet()) {
            int kills = killCounts.get(playerName);
            if (kills > highestKills && !TomoNova.getPlugin().teamUtils.arePlayersOnSameTeam(playerToExclude,playerName)) {
                highestKills = kills;
                topKiller = playerName;
            }
        }

        return topKiller;
    }

    public HashMap<String, Integer> getAllKillCounts() {
        return new HashMap<>(killCounts);
    }

    public void saveKillCountsToFile(String filePath) {
        File file = new File(filePath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Map.Entry<String, Integer> entry : killCounts.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearAllKillCounts() {
        killCounts.clear();
    }
}