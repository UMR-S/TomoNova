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
        Player player = event.getPlayer();

        if (GameStates.isState(GameStates.LOBBY)) {
            handleLobbyJoin(player);
        } else {
            handleNonLobbyJoin(player);
        }
    }

    private void handleLobbyJoin(Player player) {
        TomoNova.getPlugin().gameManager.addPlayer(player.getName());

        if (!TomoNova.test) {
            preparePlayerForLobby(player);
        }

        player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());

        giveLobbyItems(player);

        if (player.isOp()) {
            giveConfigItem(player);
        }

        if (TomoNova.getPlugin().gameManager.isBleachUhc()) {
            giveClassChoiceItems();
        }
    }

    private void preparePlayerForLobby(Player player) {
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

    private void giveLobbyItems(Player player) {
        List<String> loreBanner = new ArrayList<>();
        loreBanner.add("(sauf toi Shanto)");
        ItemStack banner = CustomItems.createCustomItem(Material.BLACK_BANNER, ChatColor.AQUA, "Choissisez votre équipe", loreBanner);
        player.getInventory().setItem(0, banner);
    }

    private void giveConfigItem(Player player) {
        List<String> loreConfig = new ArrayList<>();
        loreConfig.add("Tkt c'est débutant friendly Kud faut juste cliquer");
        ItemStack config = CustomItems.createCustomItem(Material.PAPER, ChatColor.DARK_PURPLE, "Config", loreConfig);
        player.getInventory().setItem(8, config);
    }

    private void giveClassChoiceItems() {
        List<String> loreClasses = new ArrayList<>();
        loreClasses.add("Sasageyo");
        ItemStack choixClasses = CustomItems.createCustomItem(Material.NETHERITE_SCRAP, ChatColor.AQUA, "Choissisez votre classe", loreClasses);
        Bukkit.getOnlinePlayers().forEach(p -> p.getInventory().addItem(choixClasses));
    }

    private void handleNonLobbyJoin(Player player) {
        String playerName = player.getName();
        List<String> players = TomoNova.getPlugin().gameManager.getPlayers();

        if (!players.contains(playerName)) {
            player.setGameMode(GameMode.SPECTATOR);
            player.teleport(new Location(TomoNova.getPlugin().worldUtils.getWorld(), 0.0, 200.0, 0.0));
        }
    }
}
