package umaru.tomonova.tomonova.gui;

import org.bukkit.event.*;
import org.bukkit.entity.*;
import org.bukkit.event.inventory.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.plugin.*;
import umaru.tomonova.tomonova.core.TomoNova;

public abstract class Gui implements Listener
{
    Plugin tomoNova = TomoNova.getPlugin();
    public Player player;
    public int size;
    public InventoryType type;
    public String name;
    public static Inventory inventory;

    public Gui(final Player player, final int size, final String name) {
        this.player = player;
        this.size = size;
        this.name = name;
        Gui.inventory = Bukkit.createInventory((InventoryHolder)null, size, name);
        Bukkit.getPluginManager().registerEvents((Listener)this, tomoNova);
    }

    public Gui(final Player player, final InventoryType type, final String name) {
        this.player = player;
        this.type = type;
        this.name = name;
        Gui.inventory = Bukkit.createInventory((InventoryHolder)null, type, name);
        Bukkit.getPluginManager().registerEvents((Listener)this, tomoNova);
    }

    public void show() {
        this.player.openInventory(Gui.inventory);
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(final Player player) {
        this.player = player;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(final int size) {
        this.size = size;
    }

    public InventoryType getType() {
        return this.type;
    }

    public void setType(final InventoryType type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Inventory getInventory() {
        return Gui.inventory;
    }

    public void setInventory(final Inventory inventory) {
        Gui.inventory = inventory;
    }
}