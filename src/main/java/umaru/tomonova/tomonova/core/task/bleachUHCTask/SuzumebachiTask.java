package umaru.tomonova.tomonova.core.task.bleachUHCTask;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import umaru.tomonova.tomonova.core.TomoNova;

public class SuzumebachiTask extends BukkitRunnable {
    TomoNova tomoNova;
    Player player;
    ItemStack[] armor;
    public SuzumebachiTask(TomoNova tomoNova,String playerName) {
        this.tomoNova = tomoNova;
        this.player = Bukkit.getPlayer(playerName);
        this.armor = player.getInventory().getArmorContents();
        player.getInventory().setArmorContents(new ItemStack[4]);
    }

    @Override
    public void run() {
        if (!(player.getInventory().getItemInMainHand() == null || player.getInventory().getItemInMainHand().getType().equals(Material.AIR))) {
            if(player.getInventory().getItemInMainHand().hasItemMeta()) {
                if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                    if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1021105) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 6, 0, false, false, false));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 6, 0, false, false, false));
                    }
                }
            }
        }
        else {
            player.removePotionEffect(PotionEffectType.INVISIBILITY);
            player.removePotionEffect(PotionEffectType.SPEED);
            player.getInventory().setBoots(armor[0]);
            player.getInventory().setLeggings(armor[1]);
            player.getInventory().setChestplate(armor[2]);
            player.getInventory().setHelmet(armor[3]);
            this.cancel();
        }
    }
}
