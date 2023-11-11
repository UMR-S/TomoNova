package umaru.tomonova.tomonova.listeners.players;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
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
            if(!TomoNova.test){
                player.setGameMode(GameMode.ADVENTURE);
                player.getInventory().clear();
                player.getInventory().setArmorContents(new ItemStack[]{new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR)});
                player.setExp(0.0f);
                player.setLevel(0);
                player.setHealth(20.0);
                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20.0);
                player.setFoodLevel(20);
                player.getActivePotionEffects().forEach(p -> player.removePotionEffect(p.getType()));
                player.teleport(new Location(TomoNova.getPlugin().worldUtils.getWorld(), 0.0, 202.0, 0.0, 0, 0));
            }
            player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
            player.setCollidable(false);
            List<String> loreBanner = new ArrayList<>();
            loreBanner.add("(sauf toi Shanto)");
            ItemStack banner;
            banner = CustomItems.createCustomItem(Material.BLACK_BANNER, ChatColor.AQUA, "Choissisez votre équipe", loreBanner);
            player.getInventory().setItem(0, banner);

            //Rajoute l'item pour la config
            if (player.isOp()) {

                ItemStack config;
                List<String> loreConfig = new ArrayList<>();
                loreConfig.add("Tkt c'est débutant friendly Kud faut juste cliquer");
                config = CustomItems.createCustomItem(Material.PAPER, ChatColor.DARK_PURPLE, "Config", loreConfig);
                player.getInventory().setItem(8, config);

            }
            // Rajout du choix de classe pour le bleach uhc
            if(TomoNova.getPlugin().gameManager.isBleachUhc()){
                List<String> loreClasses = new ArrayList<>();
                loreClasses.add("Sasageyo");
                ItemStack choixClasses;
                choixClasses = CustomItems.createCustomItem(Material.NETHERITE_SCRAP, ChatColor.AQUA, "Choissisez votre classe", loreClasses);
                Bukkit.getOnlinePlayers().forEach(p -> p.getInventory().addItem(choixClasses));
            }
        }
        //Si on est plus dans la phase de lobby et que le joueur n'était pas là
        else {
            String playerName = event.getPlayer().getName();
            List<String> players = TomoNova.getPlugin().gameManager.getPlayers();
            if (!players.contains(playerName)) {
                event.getPlayer().setGameMode(GameMode.SPECTATOR);
                event.getPlayer().teleport(new Location(TomoNova.getPlugin().worldUtils.getWorld(), 0.0, 200.0, 0.0));
            }
        }
    }
}
