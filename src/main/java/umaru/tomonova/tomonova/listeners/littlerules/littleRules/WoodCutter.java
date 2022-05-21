package umaru.tomonova.tomonova.listeners.littlerules.littleRules;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.lang.Lang;
import umaru.tomonova.tomonova.listeners.littlerules.LittleRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WoodCutter extends LittleRule {
    public WoodCutter() {
        super("Wood Cutter", Arrays.asList(Lang.GUIS_PETITES_REGLES_WOODCUTTER.toString()), Material.OAK_LOG);
    }

    @EventHandler
    public void onBlockBreak(final BlockBreakEvent event) {
        final Block b = event.getBlock();
        if (b.getType() == Material.OAK_LOG || b.getType() == Material.ACACIA_LOG || b.getType() == Material.BIRCH_LOG || b.getType() == Material.DARK_OAK_LOG || b.getType() == Material.JUNGLE_LOG || b.getType() == Material.SPRUCE_LOG) {
            final List<Block> bList = new ArrayList<Block>();
            bList.add(b);
            new BukkitRunnable() {
                public void run() {
                    for (int i = 0; i < bList.size(); ++i) {
                        final Block block = bList.get(i);
                        if (b.getType() == Material.OAK_LOG || b.getType() == Material.ACACIA_LOG || b.getType() == Material.BIRCH_LOG || b.getType() == Material.DARK_OAK_LOG || b.getType() == Material.JUNGLE_LOG || b.getType() == Material.SPRUCE_LOG) {
                            for (final ItemStack item : block.getDrops()) {
                                block.getWorld().dropItemNaturally(block.getLocation(), item);
                            }
                            block.setType(Material.AIR);
                        }
                        for (final BlockFace face : BlockFace.values()) {
                            if (block.getRelative(face).getType() == Material.OAK_LOG || block.getRelative(face).getType() == Material.ACACIA_LOG || block.getRelative(face).getType() == Material.BIRCH_LOG || block.getRelative(face).getType() == Material.DARK_OAK_LOG || block.getRelative(face).getType() == Material.JUNGLE_LOG || block.getRelative(face).getType() == Material.SPRUCE_LOG) {
                                bList.add(block.getRelative(face));
                            }
                        }
                        bList.remove(block);
                    }
                    if (bList.size() == 0) {
                        this.cancel();
                    }
                }
            }.runTaskTimer((Plugin) TomoNova.getPlugin(), 1L, 1L);
        }
    }


}
