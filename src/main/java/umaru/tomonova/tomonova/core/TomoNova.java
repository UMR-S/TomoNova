package umaru.tomonova.tomonova.core;


import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import umaru.tomonova.tomonova.core.game.GameManager;
import umaru.tomonova.tomonova.core.game.GameStates;
import umaru.tomonova.tomonova.core.task.TaskManager;
import umaru.tomonova.tomonova.gamemode.BleachUHC;
import umaru.tomonova.tomonova.gamemode.TomoLostVillage;
import umaru.tomonova.tomonova.gamemode.bleachUHC.classes.ClassesSpells;
import umaru.tomonova.tomonova.gamemode.bleachUHC.items.CustomRecipes;
import umaru.tomonova.tomonova.lang.Lang;
import umaru.tomonova.tomonova.listeners.bleachUHC.*;
import umaru.tomonova.tomonova.listeners.entities.EntityDamage;
import umaru.tomonova.tomonova.listeners.entities.EntityDamageByEntity;
import umaru.tomonova.tomonova.listeners.entities.EntitySpawn;
import umaru.tomonova.tomonova.listeners.others.FoodLevelChange;
import umaru.tomonova.tomonova.listeners.others.PortalCreate;
import umaru.tomonova.tomonova.listeners.players.*;
import umaru.tomonova.tomonova.utils.bleachUHC.classes.ClassesUtils;
import umaru.tomonova.tomonova.utils.bleachUHC.combatZone.CombatZoneCreationUtils;
import umaru.tomonova.tomonova.utils.bleachUHC.combatZone.CombatZoneUtils;
import umaru.tomonova.tomonova.utils.config.CombatZoneConfigManager;
import umaru.tomonova.tomonova.utils.cooldowns.CooldownManager;
import umaru.tomonova.tomonova.utils.lobby.LobbyUtils;
import umaru.tomonova.tomonova.utils.players.KillCounter;
import umaru.tomonova.tomonova.utils.rules.SettingRulesUtils;
import umaru.tomonova.tomonova.utils.scoreboard.ScoreboardUtils;
import umaru.tomonova.tomonova.utils.teams.TeamUtils;
import umaru.tomonova.tomonova.utils.world.WorldUtils;
import umaru.tomonova.tomonova.utils.worldborder.WorldBorderUtils;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;


public final class TomoNova extends JavaPlugin {
    public static boolean test = false;
    private Logger log;
    private static TomoNova plugin;
    public GameManager gameManager;
    public ScoreboardUtils scoreboardUtils;
    public TeamUtils teamUtils;
    public WorldUtils worldUtils;
    public WorldBorderUtils worldBorderUtils;
    public TaskManager taskManager;
    public TomoLostVillage tomoLostVillage;
    public BleachUHC bleachUHC;
    public ClassesSpells classesSpells;
    public CombatZoneCreationUtils combatZoneCreationUtils;
    public CombatZoneUtils combatZoneUtils;
    public ClassesUtils classesUtils;
    public KillCounter killCounter;
    public CooldownManager cooldownManager;

    @Override
    public void onLoad() {
        //Génération du monde
        GameStates.setCurrentState(GameStates.GEN);
//        getConfig().options().copyDefaults();
//        saveDefaultConfig();
//        FileConfiguration config = getConfig();
//        String regen = config.getString("isCustomMap");

    }

    @Override
    public void onEnable() {
        // Plugin startup login
        plugin = this;
        log = getLogger();
        loadLang();
        setupUtils();
        listenersRegister();
        LobbyUtils.spawnLobby();
        SettingRulesUtils.setGamerules();
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        CustomRecipes.addHogyokuRecipe();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void listenersRegister() {

        //Commun
        getServer().getPluginManager().registerEvents(new InteractEvent(), plugin);
        getServer().getPluginManager().registerEvents(new Join(), plugin);
        getServer().getPluginManager().registerEvents(new PlayerMove(), plugin);
        getServer().getPluginManager().registerEvents(new Quit(), plugin);
        getServer().getPluginManager().registerEvents(new PlayerChangeWorld(), plugin);
        getServer().getPluginManager().registerEvents(new PortalCreate(), plugin);
        getServer().getPluginManager().registerEvents(new FoodLevelChange(), plugin);
        getServer().getPluginManager().registerEvents(new EntitySpawn(), plugin);
//        getServer().getPluginManager().registerEvents(new Autosmell(), plugin);
//        getServer().getPluginManager().registerEvents(new Collisions(), plugin);
//        getServer().getPluginManager().registerEvents(new EternalDay(), plugin);
//        getServer().getPluginManager().registerEvents(new Fireless(), plugin);
//        getServer().getPluginManager().registerEvents(new FriendlyFire(), plugin);
//        getServer().getPluginManager().registerEvents(new Horseless(), plugin);
//        getServer().getPluginManager().registerEvents(new Regen(), plugin);
//        getServer().getPluginManager().registerEvents(new Rodless(), plugin);
//        getServer().getPluginManager().registerEvents(new WoodCutter(), plugin);
    }

    public void listenerBleachUHCRegister(){
        getServer().getPluginManager().registerEvents(new PlayerDeath(), plugin);
        getServer().getPluginManager().registerEvents(new PlayerDropItem(), plugin);
        getServer().getPluginManager().registerEvents(new EntityDamage(), plugin);
        getServer().getPluginManager().registerEvents(new EntityDamageByEntity(), plugin);
        getServer().getPluginManager().registerEvents(new AbilitiesEvents(), plugin);
        getServer().getPluginManager().registerEvents(new BannedItemForClasses(), plugin);
        getServer().getPluginManager().registerEvents(new CombatZoneEvent(), plugin);
        getServer().getPluginManager().registerEvents(new FreezeEffectEvent(), plugin);
        getServer().getPluginManager().registerEvents(new HogyokuTriggerEvent(), plugin);
        getServer().getPluginManager().registerEvents(new HeldEvent(), plugin);
        getServer().getPluginManager().registerEvents(new UseItemEvent(), plugin);
        getServer().getPluginManager().registerEvents(new MobDeathEvent(), plugin);
        getServer().getPluginManager().registerEvents(new EndermanSpawnEvent(), plugin);
        getServer().getPluginManager().registerEvents(new ItemDurabilityEvent(), plugin);
    }

    public void setupUtils() {
        gameManager = new GameManager();
        scoreboardUtils = new ScoreboardUtils();
        teamUtils = new TeamUtils();
        worldUtils = new WorldUtils(Bukkit.getWorld("world"), Bukkit.getWorld("world_nether"), Bukkit.getWorld("world_the_end"));
        worldBorderUtils = new WorldBorderUtils();
        taskManager = new TaskManager(this);
        tomoLostVillage = new TomoLostVillage();
        bleachUHC = new BleachUHC();
        classesSpells =new ClassesSpells();
        combatZoneCreationUtils = new CombatZoneCreationUtils();
        combatZoneUtils = new CombatZoneUtils(new CombatZoneConfigManager(this));
        classesUtils = new ClassesUtils();
        killCounter = new KillCounter();
        cooldownManager = new CooldownManager();
    }

    public static TomoNova getPlugin() {
        return plugin;
    }

    //Lang
    public void loadLang() {
        File lang = new File(getDataFolder(), "lang.yml");
        YamlConfiguration langConfig = YamlConfiguration.loadConfiguration(lang);
        if (!lang.exists()) {
            try {
                langConfig.save(lang);
            } catch (IOException e) {
                e.printStackTrace();
                log.info("Could not create language file.");
                log.info("Disabling plugin.");
                getServer().getPluginManager().disablePlugin(this);
            }
        }
        for (Lang item : Lang.values()) {
            if (langConfig.getString(item.getPath()) == null) {
                langConfig.set(item.getPath(), item.getDefault());
            }
        }
        Lang.setFile(langConfig);
        try {
            langConfig.save(lang);
        } catch (IOException e) {
            log.info("Could not save language file.");
            log.info("Disabling plugin.");
            getServer().getPluginManager().disablePlugin(this);
        }
    }

}
