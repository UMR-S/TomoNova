package umaru.tomonova.tomonova.gui.teamsgui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.gui.Gui;
import umaru.tomonova.tomonova.gui.MainGui;
import umaru.tomonova.tomonova.lang.Lang;
import umaru.tomonova.tomonova.utils.gui.ItemsCreator;

import java.util.Arrays;

public class ToGui extends Gui {
    private int[] possibilities;
    private static int select;

    public ToGui(Player player) {
        super(player, 9, ChatColor.RED + Lang.GUIS_TEAMSOF_NAME.toString());
        this.possibilities = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 15, 20, 25, 50};
        Integer i = TomoNova.getPlugin().gameManager.getPlayersPerTeam();
        String ppt = i.toString();
        final ItemsCreator ic = new ItemsCreator(Material.BLAZE_ROD,ChatColor.AQUA + ppt, Arrays.asList(Lang.GUIS_TEAMSOF_LORE.toString()));
        ToGui.inventory.setItem(4, ItemsCreator.create(ic));
        ItemsCreator ic2 = new ItemsCreator(Material.RED_BANNER, Lang.GUIS_TIMER_PREVIOUS.toString(), Arrays.asList(""));
        ToGui.inventory.setItem(0, ItemsCreator.create(ic2));
        ic2 = new ItemsCreator(Material.GREEN_BANNER, Lang.GUIS_TIMER_NEXT.toString(), Arrays.asList(""));
        ToGui.inventory.setItem(8, ItemsCreator.create(ic2));
    }

    @EventHandler
    public void onClick(final InventoryClickEvent event) {
        if (event.getClickedInventory() == null) {
            return;
        }
        if (event.getClickedInventory().equals(ToGui.inventory)) {
            final ItemStack is = event.getCurrentItem();
            if (is == null || is.getType() == Material.AIR) {
                return;
            }
            event.setCancelled(true);
            switch (is.getType()) {
                case BLAZE_ROD: {
                    this.player.closeInventory();
                    new MainGui(this.player).show();
                    break;
                }
                case RED_BANNER: {
                    final String name = is.getItemMeta().getDisplayName();
                    int i = 0;
                    while (i < this.possibilities.length) {
                        if (this.possibilities[i] == TomoNova.getPlugin().gameManager.getPlayersPerTeam()) {
                            if (i <= 0) {
                                TomoNova.getPlugin().gameManager.setPlayersPerTeam(this.possibilities[this.possibilities.length - 1]);
                                break;
                            }
                            TomoNova.getPlugin().gameManager.setPlayersPerTeam(this.possibilities[i - 1]);
                            break;
                        } else {
                            ++i;
                        }
                    }
                    Integer i1 = TomoNova.getPlugin().gameManager.getPlayersPerTeam();
                    String ppt = i1.toString();
                    final ItemsCreator ic = new ItemsCreator(Material.BLAZE_ROD,ChatColor.AQUA + ppt, Arrays.asList(Lang.GUIS_TEAMSOF_LORE.toString()));
                    ToGui.inventory.setItem(4, ItemsCreator.create(ic));
                    break;
                }
                case GREEN_BANNER: {
                    int i = 0;
                    while (i < this.possibilities.length) {
                        if (this.possibilities[i] == TomoNova.getPlugin().gameManager.getPlayersPerTeam()) {
                            if (i + 1 >= this.possibilities.length) {
                                TomoNova.getPlugin().gameManager.setPlayersPerTeam((this.possibilities[0]));
                                break;
                            }
                            TomoNova.getPlugin().gameManager.setPlayersPerTeam((this.possibilities[i + 1]));
                            break;
                        } else {
                            ++i;
                        }
                    }
                    Integer i2 = TomoNova.getPlugin().gameManager.getPlayersPerTeam();
                    String ppt = i2.toString();
                    final ItemsCreator ic = new ItemsCreator(Material.BLAZE_ROD,ChatColor.AQUA + ppt, Arrays.asList(Lang.GUIS_TEAMSOF_LORE.toString()));
                    ToGui.inventory.setItem(4, ItemsCreator.create(ic));
                    break;
                }
            }
        }

    }

    @EventHandler
    public void onClick(final InventoryCloseEvent event) {
        if (event.getInventory().equals(ToGui.inventory)) {
            HandlerList.unregisterAll((Listener) this);
        }
    }

    static {
        ToGui.select = 1;
    }
}

