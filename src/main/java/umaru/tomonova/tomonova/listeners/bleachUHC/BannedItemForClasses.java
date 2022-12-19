package umaru.tomonova.tomonova.listeners.bleachUHC;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.core.task.bleachUHCTask.HogyokuInactifTask;

public class BannedItemForClasses implements Listener {
    //Item Interdit par classes
    @EventHandler
    public void PlayerPickEvent(EntityPickupItemEvent event){
        if(event.getEntity() instanceof Player){
            Player player = ((Player) event.getEntity()).getPlayer();
            //Shinigami
            if(TomoNova.getPlugin().classesUtils.isPlayerClasse(player.getName(), "shinigami")){
                if(event.getItem().getItemStack().getType().equals(Material.BOW)
                        || event.getItem().getItemStack().getType().equals(Material.CROSSBOW)
                        || event.getItem().getItemStack().getType().equals(Material.SHIELD)){
                    event.setCancelled(true);
                }
            }
            //Quincy
            if(TomoNova.getPlugin().classesUtils.isPlayerClasse(player.getName(), "quincy")){
                if(event.getItem().getItemStack().getType().equals(Material.WOODEN_SWORD)
                        || event.getItem().getItemStack().getType().equals(Material.STONE_SWORD)
                        || event.getItem().getItemStack().getType().equals(Material.IRON_SWORD)
                        || event.getItem().getItemStack().getType().equals(Material.GOLDEN_SWORD)
                        || event.getItem().getItemStack().getType().equals(Material.DIAMOND_SWORD)
                        || event.getItem().getItemStack().getType().equals(Material.NETHERITE_SWORD)
                        || event.getItem().getItemStack().getType().equals(Material.CROSSBOW)
                        || event.getItem().getItemStack().getType().equals(Material.SHIELD)){
                    event.setCancelled(true);
                }
            }
            //Shun shun rika
            if(TomoNova.getPlugin().classesUtils.isPlayerClasse(player.getName(), "ssr")){
                if(event.getItem().getItemStack().getType().equals(Material.WOODEN_SWORD)
                        || event.getItem().getItemStack().getType().equals(Material.STONE_SWORD)
                        || event.getItem().getItemStack().getType().equals(Material.IRON_SWORD)
                        || event.getItem().getItemStack().getType().equals(Material.GOLDEN_SWORD)
                        || event.getItem().getItemStack().getType().equals(Material.DIAMOND_SWORD)
                        || event.getItem().getItemStack().getType().equals(Material.NETHERITE_SWORD)
                        || event.getItem().getItemStack().getType().equals(Material.BOW)
                        || event.getItem().getItemStack().getType().equals(Material.CROSSBOW)
                        || event.getItem().getItemStack().getType().equals(Material.SHIELD)){
                    event.setCancelled(true);
                }
            }
            //Brazo
            if(TomoNova.getPlugin().classesUtils.isPlayerClasse(player.getName(), "brazo")){
                if(event.getItem().getItemStack().getType().equals(Material.WOODEN_SWORD)
                        || event.getItem().getItemStack().getType().equals(Material.STONE_SWORD)
                        || event.getItem().getItemStack().getType().equals(Material.IRON_SWORD)
                        || event.getItem().getItemStack().getType().equals(Material.GOLDEN_SWORD)
                        || event.getItem().getItemStack().getType().equals(Material.DIAMOND_SWORD)
                        || event.getItem().getItemStack().getType().equals(Material.NETHERITE_SWORD)
                        || event.getItem().getItemStack().getType().equals(Material.BOW)
                        || event.getItem().getItemStack().getType().equals(Material.CROSSBOW)){
                    event.setCancelled(true);
                }
            }
        }
    }

    //Impossibilité de jeter le Hogyoku
    @EventHandler
    public void PlayerDropEvent(PlayerDropItemEvent event) {

        if(event.getItemDrop().getItemStack().getItemMeta().hasCustomModelData()){
            if(event.getItemDrop().getItemStack().getItemMeta().getCustomModelData() == 5149612
                    || event.getItemDrop().getItemStack().getItemMeta().getCustomModelData() == 5149613){
                event.setCancelled(true);
            }
        }
    }
}
