package umaru.tomonova.tomonova.gui.timergui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import umaru.tomonova.tomonova.gui.Gui;
import umaru.tomonova.tomonova.utils.gui.ItemsCreator;

import java.util.Arrays;

public class LargeTimerGui extends Gui {
    public LargeTimerGui(Player player, int size, String name) {
        super(player, size, name);
        ItemsCreator ic = new ItemsCreator(Material.RED_BANNER, ChatColor.RED + "-100", Arrays.asList(""));
        this.inventory.setItem(0, ItemsCreator.create(ic));
        ic = new ItemsCreator(Material.RED_BANNER, ChatColor.RED + "-50", Arrays.asList(""));
        this.inventory.setItem(1, ItemsCreator.create(ic));
        ic = new ItemsCreator(Material.RED_BANNER, ChatColor.RED + "-10", Arrays.asList(""));
        this.inventory.setItem(2, ItemsCreator.create(ic));
        ic = new ItemsCreator(Material.GREEN_BANNER, ChatColor.GREEN + "+10", Arrays.asList(""));
        this.inventory.setItem(6, ItemsCreator.create(ic));
        ic = new ItemsCreator(Material.GREEN_BANNER, ChatColor.GREEN + "+50", Arrays.asList(""));
        this.inventory.setItem(7, ItemsCreator.create(ic));
        ic = new ItemsCreator(Material.GREEN_BANNER, ChatColor.GREEN + "+100", Arrays.asList(""));
        this.inventory.setItem(8, ItemsCreator.create(ic));
    }
}
