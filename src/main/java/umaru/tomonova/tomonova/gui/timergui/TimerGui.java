package umaru.tomonova.tomonova.gui.timergui;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import umaru.tomonova.tomonova.gui.Gui;
import umaru.tomonova.tomonova.utils.gui.ItemsCreator;

import java.util.Arrays;

public class TimerGui extends Gui {
    public TimerGui(Player player,Integer size, String name) {
        super(player, size, name);
        ItemsCreator ic = new ItemsCreator(Material.RED_BANNER, "-10", Arrays.asList(""));
        TimerGui.inventory.setItem(0, ItemsCreator.create(ic));
        ic = new ItemsCreator(Material.RED_BANNER, "-5", Arrays.asList(""));
        TimerGui.inventory.setItem(1, ItemsCreator.create(ic));
        ic = new ItemsCreator(Material.RED_BANNER, "-1", Arrays.asList(""));
        TimerGui.inventory.setItem(2, ItemsCreator.create(ic));
        ic = new ItemsCreator(Material.GREEN_BANNER, "+1", Arrays.asList(""));
        TimerGui.inventory.setItem(6, ItemsCreator.create(ic));
        ic = new ItemsCreator(Material.GREEN_BANNER, "+5", Arrays.asList(""));
        TimerGui.inventory.setItem(7, ItemsCreator.create(ic));
        ic = new ItemsCreator(Material.GREEN_BANNER, "+10", Arrays.asList(""));
        TimerGui.inventory.setItem(8, ItemsCreator.create(ic));
    }
}

