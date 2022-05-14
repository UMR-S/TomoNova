package umaru.tomonova.tomonova.listeners.players;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.Permission;
import umaru.tomonova.tomonova.core.game.GameManager;
import umaru.tomonova.tomonova.core.game.GameStates;

import java.util.ArrayList;
import java.util.List;


public class onJoin implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event){
        if(GameStates.isState(GameStates.LOBBY)){
            Player player = event.getPlayer();
            GameManager.addPlayer(player);
            ItemStack banner = new ItemStack(Material.BLACK_BANNER);
            ItemMeta bannerMeta = banner.getItemMeta();
            bannerMeta.setDisplayName("Choissisez votre équipe");
            List<String> lore = new ArrayList<>();
            lore.add("(sauf toi Shanto)");
            bannerMeta.setLore(lore);
            banner.setItemMeta(bannerMeta);
            player.getInventory().addItem(banner);
            if(player.isOp()){
                //Rajouter l'item pour la config
            }
        }
    }
}
