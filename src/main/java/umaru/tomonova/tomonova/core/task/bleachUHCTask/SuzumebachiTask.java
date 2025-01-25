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

    public SuzumebachiTask(TomoNova tomoNova, String playerName) {
        this.tomoNova = tomoNova;
        this.player = Bukkit.getPlayer(playerName);
        this.armor = player.getInventory().getArmorContents();
        player.getInventory().setArmorContents(new ItemStack[4]);
    }

    @Override
    public void run() {
        if (!(player.getInventory().getItemInMainHand() == null || player.getInventory().getItemInMainHand().getType().equals(Material.AIR))) {
            if (player.getInventory().getItemInMainHand().hasItemMeta()) {
                if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                    if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1021105) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 6, 0, false, false, false));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 6, 0, false, false, false));
                    }
                }
            }
        } else {
            player.removePotionEffect(PotionEffectType.INVISIBILITY);
            player.removePotionEffect(PotionEffectType.SPEED);
            for (ItemStack item : armor) {
                if(isBoots(item.getType())){
                    player.getInventory().setBoots(item);
                } else if (isLeggings(item.getType())) {
                    player.getInventory().setLeggings(item);
                } else if (isChestplate(item.getType())) {
                    player.getInventory().setChestplate(item);
                } else if (isHelmet(item.getType())) {
                    player.getInventory().setHelmet(item);
                }
            }
            this.cancel();
        }
    }

    public boolean isBoots(Material item) {
        return item.equals(Material.LEATHER_BOOTS)
                || item.equals(Material.CHAINMAIL_BOOTS)
                || item.equals(Material.IRON_BOOTS)
                || item.equals(Material.GOLDEN_BOOTS)
                || item.equals(Material.DIAMOND_BOOTS)
                || item.equals(Material.NETHERITE_BOOTS);
    }
    public boolean isLeggings(Material item) {
        return item.equals(Material.LEATHER_LEGGINGS)
                || item.equals(Material.CHAINMAIL_LEGGINGS)
                || item.equals(Material.IRON_LEGGINGS)
                || item.equals(Material.GOLDEN_LEGGINGS)
                || item.equals(Material.DIAMOND_LEGGINGS)
                || item.equals(Material.NETHERITE_LEGGINGS);
    }
    public boolean isChestplate(Material item) {
        return item.equals(Material.LEATHER_CHESTPLATE)
                || item.equals(Material.CHAINMAIL_CHESTPLATE)
                || item.equals(Material.IRON_CHESTPLATE)
                || item.equals(Material.GOLDEN_CHESTPLATE)
                || item.equals(Material.DIAMOND_CHESTPLATE)
                || item.equals(Material.NETHERITE_CHESTPLATE);
    }
    public boolean isHelmet(Material item) {
        return item.equals(Material.LEATHER_HELMET)
                || item.equals(Material.CHAINMAIL_HELMET)
                || item.equals(Material.IRON_HELMET)
                || item.equals(Material.GOLDEN_HELMET)
                || item.equals(Material.DIAMOND_HELMET)
                || item.equals(Material.NETHERITE_HELMET);
    }
}
