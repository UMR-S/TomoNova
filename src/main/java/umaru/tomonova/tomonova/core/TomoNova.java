package umaru.tomonova.tomonova.core;



import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import umaru.tomonova.tomonova.core.game.GameManager;
import umaru.tomonova.tomonova.core.game.GameStates;

import umaru.tomonova.tomonova.lang.Lang;
import umaru.tomonova.tomonova.listeners.players.InteractEvent;
import umaru.tomonova.tomonova.listeners.players.Join;
import umaru.tomonova.tomonova.listeners.players.Quit;
import umaru.tomonova.tomonova.listeners.world.onWorldLoaded;
import umaru.tomonova.tomonova.utils.scoreboard.ScoreboardUtils;
import umaru.tomonova.tomonova.utils.teams.TeamUtils;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;


public final class TomoNova extends JavaPlugin {

    private Logger log;
    private static TomoNova plugin;
    public static GameManager gameManager;
    public  static ScoreboardUtils scoreboardUtils;
    public static TeamUtils teamUtils;
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
        listenersRegister();
        setupUtils();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void listenersRegister(){
        getServer().getPluginManager().registerEvents(new onWorldLoaded(), plugin);
        getServer().getPluginManager().registerEvents(new Join(), plugin);
        getServer().getPluginManager().registerEvents(new Quit(), plugin);
        getServer().getPluginManager().registerEvents(new InteractEvent(), plugin);

    }
    public void setupUtils(){
        gameManager = new GameManager();
        scoreboardUtils = new ScoreboardUtils();
        teamUtils = new TeamUtils();
    }

    public static TomoNova getPlugin() {
        return plugin;
    }

    //Lang
    public void loadLang()
    {
        File lang = new File(getDataFolder(), "lang.yml");
        YamlConfiguration langConfig = YamlConfiguration.loadConfiguration(lang);
        if (!lang.exists())
        {
            try
            {
                langConfig.save(lang);
            } catch (IOException e)
            {
                e.printStackTrace();
                log.info("Could not create language file.");
                log.info("Disabling plugin.");
                getServer().getPluginManager().disablePlugin(this);
            }
        }
        for (Lang item : Lang.values())
        {
            if (langConfig.getString(item.getPath()) == null)
            {
                langConfig.set(item.getPath(), item.getDefault());
            }
        }
        Lang.setFile(langConfig);
        try
        {
            langConfig.save(lang);
        } catch (IOException e)
        {
            log.info("Could not save language file.");
            log.info("Disabling plugin.");
            getServer().getPluginManager().disablePlugin(this);
        }
    }

}
