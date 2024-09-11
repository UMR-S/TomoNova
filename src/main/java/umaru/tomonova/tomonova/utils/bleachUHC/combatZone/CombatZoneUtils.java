package umaru.tomonova.tomonova.utils.bleachUHC.combatZone;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import umaru.tomonova.tomonova.utils.config.CombatZoneConfigManager;

import java.util.HashMap;
import java.util.List;

public class CombatZoneUtils {
    private List<List<Object>> allZones;
    private CombatZoneConfigManager configManager;

    public CombatZoneUtils(CombatZoneConfigManager configManager) {
        this.configManager = configManager;
        loadAllZonesFromConfig();
    }

    // Method to load allZones from the configuration file
    private void loadAllZonesFromConfig() {
        this.allZones = this.configManager.restoreCombatZone();  // Restore and load the processed zones
    }

    public boolean isPlayerInZone(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        int xPlayer = player.getLocation().getBlockX();
        int yPlayer = player.getLocation().getBlockY();
        int zPlayer = player.getLocation().getBlockZ();

        for (List combatZone : this.allZones) {

            HashMap<Integer, List<Integer>> tempHashMap = (HashMap<Integer, List<Integer>>) combatZone.get(2);
            int ymin = (int) combatZone.get(0);
            int ymax = (int) combatZone.get(1);
            for (Integer x : tempHashMap.keySet()) {

                if (x.intValue() == xPlayer) {
                    if (zPlayer >= tempHashMap.get(x).get(0) && zPlayer <= tempHashMap.get(x).get(1)) {
                        if (yPlayer >= ymin && yPlayer <= ymax) {
                            return true;
                        }
                    }
                }

            }
        }
        return false;
    }

    public boolean isBlockInZone(int xBlock, int yBlock, int zBlock) {

        for (List combatZone : this.allZones) {

            HashMap<Integer, List<Integer>> tempHashMap = (HashMap<Integer, List<Integer>>) combatZone.get(2);
            int ymin = (int) combatZone.get(0);
            int ymax = (int) combatZone.get(1);
            for (Integer x : tempHashMap.keySet()) {

                if (x.intValue() == xBlock) {
                    if (zBlock >= tempHashMap.get(x).get(0) && zBlock <= tempHashMap.get(x).get(1)) {
                        if (yBlock >= ymin && yBlock <= ymax) {
                            return true;
                        }
                    }
                }

            }
        }
        return false;
    }
}
