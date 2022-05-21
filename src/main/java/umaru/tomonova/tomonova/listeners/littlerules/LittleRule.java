package umaru.tomonova.tomonova.listeners.littlerules;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.gui.Gui;

import java.util.List;

public abstract class LittleRule implements Listener {
    private String name;
    private List<String> descritpion;
    private Material type;
    private Class<? extends Gui> gui;

    public LittleRule(final String name, final List<String> descritpion, final Material type) {
        this.name = name;
        this.descritpion = descritpion;
        this.type = type;
        Bukkit.getPluginManager().registerEvents((Listener) this, TomoNova.getPlugin());
    }

    public LittleRule(final String name, final List<String> descritpion, final Material type, final Class<? extends Gui> gui) {
        this.name = name;
        this.descritpion = descritpion;
        this.type = type;
        this.gui = gui;
        Bukkit.getPluginManager().registerEvents((Listener) this, TomoNova.getPlugin());
    }

    public String getName() {
        return this.name;
    }

    public List<String> getDescritpion() {
        return this.descritpion;
    }

    public Material getType() {
        return this.type;
    }

    public Class<? extends Gui> getGui() {
        return this.gui;
    }
}
