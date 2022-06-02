package umaru.tomonova.tomonova.listeners.entities;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.core.game.GameStates;

public class EntityDamageByEntity implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {


        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            //PVP
            if(TomoNova.getPlugin().gameManager.getPlayersPerTeam() > 1){
                if(TomoNova.getPlugin().teamUtils.getTeamNameFromPlayer(event.getEntity().getName()).equals(TomoNova.getPlugin().teamUtils.getTeamNameFromPlayer(event.getDamager().getName()))){
                    event.setCancelled(true);
                }
            }
            //Pour le TLV
            if (GameStates.isState(GameStates.GAME) && TomoNova.getPlugin().gameManager.isTomoLostVillage()) {
                Player player = (Player) event.getEntity();
                Player damager = (Player) event.getDamager();
                if (!TomoNova.getPlugin().tomoLostVillage.isKilledInTeam(player.getName())) {
                    //Si le joueur meurt
                    if (player.getHealth() - event.getFinalDamage() < 1) {
                        int numberPlayerInTeamKiller = TomoNova.getPlugin().tomoLostVillage.playerTeamNumberPlayer(event.getDamager().getName());
                        if (0 < numberPlayerInTeamKiller && numberPlayerInTeamKiller < 5) {
                            event.setCancelled(true);
                            Bukkit.getOnlinePlayers().forEach(p -> p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1.0f, 1.0f));
                            TomoNova.getPlugin().tomoLostVillage.addPlayerToTeam(damager.getName(), player.getName());
                            Bukkit.broadcastMessage(player.getName() + " rejoint " + damager.getName());
                        }
                        //Si les joueurs n'ont jamais été dans une team
                        if (numberPlayerInTeamKiller == 0) {
                            event.setCancelled(true);
                            Bukkit.getOnlinePlayers().forEach(p -> p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1.0f, 1.0f));
                            player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                            damager.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                            TomoNova.getPlugin().tomoLostVillage.createNewTeam(damager.getName(), player.getName());
                            Bukkit.broadcastMessage(player.getName() + " rejoint " + damager.getName());
                        }
                    }
                }
                //PVP TLV
                if(TomoNova.getPlugin().tomoLostVillage.playersInSameTeam(event.getEntity().getName(),event.getDamager().getName())){
                    event.setCancelled(true);
                }
            }
        }
    }
}
