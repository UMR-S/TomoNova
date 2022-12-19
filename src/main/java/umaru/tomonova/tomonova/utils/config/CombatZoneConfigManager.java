package umaru.tomonova.tomonova.utils.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import umaru.tomonova.tomonova.core.TomoNova;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

public class CombatZoneConfigManager {
    private TomoNova tomoNova;
    private FileConfiguration dataCombatZoneConfig = null;
    private File fileCombatZoneConfig = null;

    public CombatZoneConfigManager(TomoNova tomoNova) {
        this.tomoNova = tomoNova;
        saveCombatZoneDefaultConfig();
    }

    public void reloadCombatZoneConfig() {
        if (this.fileCombatZoneConfig == null) {
            this.fileCombatZoneConfig = new File(this.tomoNova.getDataFolder(), "combatZoneConfig.yml");
        }
        this.dataCombatZoneConfig = YamlConfiguration.loadConfiguration(this.fileCombatZoneConfig);
        InputStream defaultStream = this.tomoNova.getResource("combatZoneConfig.yml");
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.dataCombatZoneConfig.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getCombatZoneConfig() {
        if (this.dataCombatZoneConfig == null) {
            reloadCombatZoneConfig();
        }
        return this.dataCombatZoneConfig;
    }

    public void saveCombatZoneConfig() {
        if (this.fileCombatZoneConfig == null || this.dataCombatZoneConfig == null) {
            return;
        }
        try {
            this.getCombatZoneConfig().save(this.fileCombatZoneConfig);
        } catch (IOException e) {
            tomoNova.getLogger().log(Level.SEVERE, "Could not save config to " + this.fileCombatZoneConfig, e);
        }
    }

    public void saveCombatZoneDefaultConfig() {
        if (this.fileCombatZoneConfig == null) {
            this.fileCombatZoneConfig = new File(this.tomoNova.getDataFolder(), "combatZoneConfig.yml");
        }
        if (!this.fileCombatZoneConfig.exists()) {
            this.tomoNova.saveResource("combatZoneConfig.yml", false);
        }
    }

    public void addCombatZone(String zoneName, HashMap<Integer, List<Integer>> finalZone, int ymin, int ymax) {

        getCombatZoneConfig().set("combatZone." + zoneName + ".ymin", ymin);
        getCombatZoneConfig().set("combatZone." + zoneName + ".ymax", ymax);
        System.out.println(finalZone.size());
        for (Integer x : finalZone.keySet()) {

            getCombatZoneConfig().set("combatZone." + zoneName + ".contours." + x, finalZone.get(x));

        }
        saveCombatZoneConfig();
        restoreCombatZone();
    }

    public void restoreCombatZone() {

        List<List> allCombatZones = new ArrayList();

        for (String name : getCombatZoneConfig().getConfigurationSection("combatZone").getKeys(false)) {

            List tempList = new ArrayList();
            HashMap<Integer, List<Integer>> tempHashMap = new HashMap<>();
            tempList.add(this.getCombatZoneConfig().get("combatZone." + name + ".ymin"));
            tempList.add(this.getCombatZoneConfig().get("combatZone." + name + ".ymax"));
            for (String x : getCombatZoneConfig().getConfigurationSection("combatZone." + name + ".contours").getKeys(false)) {
                tempHashMap.put(Integer.parseInt(x), this.getCombatZoneConfig().getIntegerList("combatZone." + name + ".contours." + x));
            }
            tempList.add(tempHashMap);
            allCombatZones.add(tempList);
        }
        tomoNova.combatzoneUtils.setAllZones(allCombatZones);
    }
}
