package umaru.tomonova.tomonova.utils.world;

import org.bukkit.*;
import org.bukkit.block.Block;
import umaru.tomonova.tomonova.core.TomoNova;

import java.io.File;
import java.util.List;

public class WorldUtils {
    private static World overworld = Bukkit.getWorlds().get(0);
    private static World nether = Bukkit.getWorlds().get(1);
    private static World end = Bukkit.getWorlds().get(2);

    public static World getWorld() {
        return overworld;
    }

    public static World getNether() {
        return nether;
    }

    public static World getEnd() {
        return end;
    }
}
