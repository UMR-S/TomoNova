package umaru.tomonova.tomonova.listeners.custom;

import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.event.*;
import java.util.*;
import org.bukkit.*;

public class GameStartEvent extends Event implements Cancellable
{
    private List<Player> players;
    private Inventory inventory;
    private World world;
    private static final HandlerList handlers;

    public GameStartEvent(final List<Player> players, final Inventory inventory, final World world) {
        this.players = new ArrayList<Player>();
        this.inventory = inventory;
        this.setCancelled(false);
        this.world = world;
    }

    public boolean isCancelled() {
        return false;
    }

    public void setCancelled(final boolean b) {
    }

    public HandlerList getHandlers() {
        return GameStartEvent.handlers;
    }

    public static HandlerList getHandlerList() {
        return GameStartEvent.handlers;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public void setPlayers(final List<Player> players) {
        this.players = players;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void setInventory(final Inventory inventory) {
        this.inventory = inventory;
    }

    public World getWorld() {
        return this.world;
    }

    public void setWorld(final World world) {
        this.world = world;
    }

    static {
        handlers = new HandlerList();
    }
}