package umaru.tomonova.tomonova.listeners.players;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.core.game.GameStates;
import umaru.tomonova.tomonova.utils.customItems.CustomItems;

import java.util.ArrayList;
import java.util.List;


public class Join implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event) {
        if (GameStates.isState(GameStates.LOBBY)) {
            Player player = event.getPlayer();
            TomoNova.getPlugin().gameManager.addPlayer(player.getName());

            player.setGameMode(GameMode.ADVENTURE);
            player.getInventory().clear();
            player.getInventory().setArmorContents(new ItemStack[]{new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR)});
            player.setExp(0.0f);
            player.setLevel(0);
            player.setHealth(20.0);
            player.teleport(new Location(TomoNova.getPlugin().worldUtils.getWorld(), 0.0, 202.0, 0.0, 0, 0));

            List<String> loreBanner = new ArrayList<>();
            loreBanner.add("(sauf toi Shanto)");
            ItemStack banner;
            banner = CustomItems.createCustomItem(Material.BLACK_BANNER, ChatColor.AQUA, "Choissisez votre équipe", loreBanner);
            player.getInventory().setItem(0, banner);
            player.setFoodLevel(20);

            //Rajoute l'item pour la config
            if (player.isOp()) {

                ItemStack config;
                List<String> loreConfig = new ArrayList<>();
                loreConfig.add("Tkt c'est débutant friendly Kud faut juste cliquer");
                config = CustomItems.createCustomItem(Material.PAPER, ChatColor.DARK_PURPLE, "Config", loreConfig);
                player.getInventory().setItem(8, config);

            }
        }
        else{
            String playerName = event.getPlayer().getName();
            List<String> players = TomoNova.getPlugin().gameManager.getPlayers();
            if(!players.contains(playerName)){
                event.getPlayer().setGameMode(GameMode.SPECTATOR);
                event.getPlayer().teleport(new Location(TomoNova.getPlugin().worldUtils.getWorld(), 0.0, 200.0, 0.0));
            }
        }
    }
}
