package umaru.tomonova.tomonova.utils.world;

import org.bukkit.World;

public class WorldUtils {
    private World overworld;
    private World nether;
    private World end;
    public WorldUtils(World overworld, World nether, World end) {
        this.overworld = overworld;
        this.nether = nether;
        this.end = end;
    }


    public World getWorld() {
        return this.overworld;
    }

    public World getNether() {
        return this.nether;
    }

    public World getEnd() {
        return this.end;
    }
}
