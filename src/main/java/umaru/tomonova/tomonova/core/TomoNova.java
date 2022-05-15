package umaru.tomonova.tomonova.core;

import org.bukkit.plugin.java.JavaPlugin;
import umaru.tomonova.tomonova.core.game.GameManager;
import umaru.tomonova.tomonova.core.game.GameStates;
import umaru.tomonova.tomonova.listeners.players.InteractEvent;
import umaru.tomonova.tomonova.listeners.players.Join;
import umaru.tomonova.tomonova.listeners.players.Quit;
import umaru.tomonova.tomonova.listeners.world.onWorldLoaded;


public final class TomoNova extends JavaPlugin {

    private static TomoNova plugin;
    public static GameManager gameManager;

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
    }

    public static TomoNova getPlugin() {
        return plugin;
    }
}
