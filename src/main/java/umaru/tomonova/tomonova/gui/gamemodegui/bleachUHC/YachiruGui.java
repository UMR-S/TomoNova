package umaru.tomonova.tomonova.gui.gamemodegui.bleachUHC;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.gui.Gui;
import umaru.tomonova.tomonova.lang.Lang;
import umaru.tomonova.tomonova.utils.gui.ItemsCreator;

public class YachiruGui extends Gui {
    public YachiruGui(Player player) {
        super(player, 54, "Boutique de Yachiru : " + Integer.toString(TomoNova.getPlugin().bleachUHC.getPlayerPoints(player.getName())));
            int i = 0;
            for(YachiruPrices enchantBook : YachiruPrices.values()){
                ItemStack book = enchantBook.getEnchantement();
                ItemMeta bookMeta = book.getItemMeta();
                bookMeta.setDisplayName(enchantBook.getName() + " : " + Integer.toString(enchantBook.getPrice()));
                book.setItemMeta(bookMeta);
                this.inventory.setItem(i,book);
                i++;
            }
        ItemsCreator ic = new ItemsCreator(Material.BARRIER, Lang.GUIS_BACK.toString(), null);
        this.inventory.setItem(53, ItemsCreator.create(ic));
    }

    @EventHandler
    public void onClick(final InventoryClickEvent event) {
        if (event.getClickedInventory() == null) {
            return;
        }
        if (event.getClickedInventory().equals(this.inventory)) {
            final ItemStack is = event.getCurrentItem();
            if (is == null || is.getType() == Material.AIR) {
                return;
            }
            event.setCancelled(true);
            if(is.getType() == Material.BARRIER){
                event.getWhoClicked().closeInventory();
            } else {
                String[] split = is.getItemMeta().getDisplayName().split(":");
                String price = split[1].replace(" ","");
                int priceInt = Integer.parseInt(price);
                if(TomoNova.getPlugin().bleachUHC.getPlayerPoints(player.getName()) >= priceInt){
                    this.player.getInventory().addItem(event.getCurrentItem());
                    TomoNova.getPlugin().bleachUHC.addPoints(player.getName(), -priceInt);
                }

            }
        }
    }

    @EventHandler
    public void onClick(final InventoryCloseEvent event) {
        if (event.getInventory().equals(this.inventory)) {
            HandlerList.unregisterAll((Listener) this);
        }
    }
}
