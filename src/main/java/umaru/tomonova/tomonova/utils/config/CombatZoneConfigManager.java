package umaru.tomonova.tomonova.utils.config;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import umaru.tomonova.tomonova.core.TomoNova;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.logging.Level;

public class CombatZoneConfigManager {
    private TomoNova tomoNova;  // Reference to the main plugin instance
    private FileConfiguration dataCombatZoneConfig = null;  // Holds the combat zone configuration data
    private File fileCombatZoneConfig = null;  // File reference to the combat zone config file
    private boolean replaceBlocksWithRedGlass = false;  // Flag to control if blocks in the combat zone should be replaced with red stained glass
    private List<List<Object>> allZones;  // Stores all the combat zones after being restored

    /**
     * Constructor that initializes the CombatZoneConfigManager.
     * It sets up the default configuration for the combat zones.
     *
     * @param tomoNova The main plugin instance.
     */
    public CombatZoneConfigManager(TomoNova tomoNova) {
        this.tomoNova = tomoNova;
        this.allZones = new ArrayList<>();
        saveCombatZoneDefaultConfig();  // Save the default configuration if the file doesn't exist
    }

    /**
     * Reloads the combat zone configuration from the config file.
     * If the file does not exist, it creates a new one with the default values.
     */
    public void reloadCombatZoneConfig() {
        if (this.fileCombatZoneConfig == null) {
            this.fileCombatZoneConfig = new File(this.tomoNova.getDataFolder(), "combatZoneConfig.yml");
        }
        this.dataCombatZoneConfig = YamlConfiguration.loadConfiguration(this.fileCombatZoneConfig);
        InputStream defaultStream = this.tomoNova.getResource("combatZoneConfig.yml");
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.dataCombatZoneConfig.setDefaults(defaultConfig);  // Load default values if available
        }
    }

    /**
     * Returns the combat zone configuration. If not loaded, it will reload it first.
     *
     * @return FileConfiguration containing combat zone settings.
     */
    public FileConfiguration getCombatZoneConfig() {
        if (this.dataCombatZoneConfig == null) {
            reloadCombatZoneConfig();  // Reload if the config is not already loaded
        }
        return this.dataCombatZoneConfig;
    }

    /**
     * Saves the current state of the combat zone configuration to the file.
     */
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

    /**
     * Saves the default combat zone configuration if it does not already exist.
     */
    public void saveCombatZoneDefaultConfig() {
        if (this.fileCombatZoneConfig == null) {
            this.fileCombatZoneConfig = new File(this.tomoNova.getDataFolder(), "combatZoneConfig.yml");
        }
        if (!this.fileCombatZoneConfig.exists()) {
            this.tomoNova.saveResource("combatZoneConfig.yml", false);  // Copy default resource if file doesn't exist
        }
    }

    /**
     * Adds a new combat zone to the configuration and saves it.
     *
     * @param zoneName Name of the combat zone.
     * @param finalZone HashMap containing x-coordinates and their corresponding z-ranges.
     * @param ymin Minimum y-level of the combat zone.
     * @param ymax Maximum y-level of the combat zone.
     */
    public void addCombatZone(String zoneName, HashMap<Integer, List<Integer>> finalZone, int ymin, int ymax) {
        getCombatZoneConfig().set("combatZone." + zoneName + ".ymin", ymin);
        getCombatZoneConfig().set("combatZone." + zoneName + ".ymax", ymax);
        for (Map.Entry<Integer, List<Integer>> entry : finalZone.entrySet()) {
            getCombatZoneConfig().set("combatZone." + zoneName + ".contours." + entry.getKey(), entry.getValue());
        }
        saveCombatZoneConfig();  // Save the updated configuration
        restoreCombatZone();  // Reload the zones to reflect changes
    }

    /**
     * Restores all combat zones from the configuration file and, if enabled, replaces the blocks in those zones with red stained glass.
     *
     * @return List of all combat zones.
     */
    public List<List<Object>> restoreCombatZone() {
        allZones.clear();  // Clear the previous zone data
        FileConfiguration config = getCombatZoneConfig();
        World world = Bukkit.getWorld("world");  // Default world, adjust if necessary

        if (config.contains("combatZone")) {
            // Iterate through each combat zone in the config
            for (String zoneName : config.getConfigurationSection("combatZone").getKeys(false)) {
                List<Object> tempList = new ArrayList<>();
                HashMap<Integer, List<Integer>> tempHashMap = new HashMap<>();

                int ymin = config.getInt("combatZone." + zoneName + ".ymin");
                int ymax = config.getInt("combatZone." + zoneName + ".ymax");
                tempList.add(ymin);
                tempList.add(ymax);

                // Loop through all x-coordinates and their corresponding z-ranges
                for (String x : config.getConfigurationSection("combatZone." + zoneName + ".contours").getKeys(false)) {
                    int xCoord = Integer.parseInt(x);
                    List<Integer> zRange = config.getIntegerList("combatZone." + zoneName + ".contours." + x);
                    tempHashMap.put(xCoord, zRange);

                    // If the flag is enabled, replace blocks in the combat zone with red stained glass
                    if (replaceBlocksWithRedGlass && world != null) {
                        replaceZoneBlocksWithRedGlass(world, xCoord, zRange, ymin, ymax);
                    }
                }

                tempList.add(tempHashMap);
                allZones.add(tempList);
            }
        }
        return allZones;  // Return the list of combat zones
    }

    /**
     * Replaces all blocks within the specified x and z coordinates in the given combat zone with red stained glass.
     *
     * @param world The world where the combat zone is located.
     * @param x The x-coordinate of the zone.
     * @param zRange List of two integers representing the minimum and maximum z-coordinates of the zone.
     * @param ymin The minimum y-coordinate of the zone.
     * @param ymax The maximum y-coordinate of the zone.
     */
    private void replaceZoneBlocksWithRedGlass(World world, int x, List<Integer> zRange, int ymin, int ymax) {
        int zMin = zRange.get(0);
        int zMax = zRange.get(1);

        // Loop through all blocks within the specified range and replace them with red stained glass
        for (int z = zMin; z <= zMax; z++) {
            for (int y = ymin; y <= ymax; y++) {
                Block block = world.getBlockAt(x, y, z);
                block.setType(Material.RED_STAINED_GLASS);
            }
        }
    }
}
