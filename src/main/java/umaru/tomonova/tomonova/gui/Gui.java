package umaru.tomonova.tomonova.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import umaru.tomonova.tomonova.core.TomoNova;

public abstract class Gui implements Listener {
    Plugin tomoNova = TomoNova.getPlugin();
    public Player player;
    public int size;
    public InventoryType type;
    public String name;
    public Inventory inventory;

    public Gui(final Player player, final int size, final String name) {
        this.player = player;
        this.size = size;
        this.name = name;
        this.inventory = Bukkit.createInventory(player, size, name);
        Bukkit.getPluginManager().registerEvents((Listener) this, tomoNova);
    }

    public Gui(final Player player, final InventoryType type, final String name) {
        this.player = player;
        this.type = type;
        this.name = name;
        this.inventory = Bukkit.createInventory(player, type, name);
        Bukkit.getPluginManager().registerEvents((Listener) this, tomoNova);
    }

    public void show() {
        this.player.openInventory(this.inventory);
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
        return this.inventory;
    }

    public void setInventory(final Inventory inventory) {
        this.inventory = inventory;
    }
}