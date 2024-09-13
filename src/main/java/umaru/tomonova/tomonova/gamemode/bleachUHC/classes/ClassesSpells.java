package umaru.tomonova.tomonova.gamemode.bleachUHC.classes;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.gamemode.bleachUHC.GiveItem;

import java.util.List;

public class ClassesSpells {
    private boolean minazukiActive = false;
    private int minazukiCharges = Integer.MAX_VALUE;

    // Shinigami & Quincy
    public void dash(double power, String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        if (player != null) {
            Vector playerFacing = player.getLocation().getDirection().normalize().multiply(power);
            player.setVelocity(playerFacing);
        }
    }

    // Quincy
    public void carquois(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        if (player == null) return;

        int arrowAmount = calculateArrowAmount(player.getInventory());
        if (arrowAmount <= 11) {
            GiveItem.giveQuincyArrow(playerName, 17);
            player.setCooldown(Material.CARROT_ON_A_STICK, 600);
        } else if (arrowAmount <= 28) {
            GiveItem.giveQuincyArrow(playerName, 28 - arrowAmount);
        }
    }

    private int calculateArrowAmount(PlayerInventory inventory) {
        return inventory.all(Material.ARROW).values().stream()
                .filter(itemStack -> itemStack != null && itemStack.getType() == Material.ARROW)
                .mapToInt(ItemStack::getAmount)
                .sum();
    }

    // SSR
    public LivingEntity getEntityInSight(Player player, int range) {
        if (player == null) return null;

        List<Entity> targetList = player.getNearbyEntities(range, range, range);
        BlockIterator blockIterator = new BlockIterator(player, range);

        while (blockIterator.hasNext()) {
            Block block = blockIterator.next();
            if (block.getType().isSolid()) break;

            LivingEntity target = getTargetEntityInBlock(block, targetList);
            if (target != null) return target;
        }
        return null;
    }

    private LivingEntity getTargetEntityInBlock(Block block, List<Entity> targetList) {
        int bx = block.getX();
        int by = block.getY();
        int bz = block.getZ();

        for (Entity entity : targetList) {
            Location location = entity.getLocation();
            double ex = location.getX();
            double ey = location.getY();
            double ez = location.getZ();

            if ((bx - 0.75 <= ex && ex <= bx + 1.75) && (bz - 0.75 <= ez && ez <= bz + 1.75) && (by - 1 <= ey && ey <= by + 2.5)) {
                if (entity instanceof LivingEntity) {
                    return (LivingEntity) entity;
                }
            }
        }
        return null;
    }

    public void cielUnique(String playerName) {
        LivingEntity entity = getEntityInSight(Bukkit.getPlayer(playerName), 50);
        if (entity != null) {
            entity.damage(5);
            Vector velocity = entity.getLocation().add(0.0, 1.0, 0.0).toVector()
                    .subtract(Bukkit.getPlayer(playerName).getLocation().toVector())
                    .normalize().multiply(5);
            entity.setVelocity(velocity);
        }
    }

    public void deuxCieux(String playerName) {
        LivingEntity entity = getEntityInSight(Bukkit.getPlayer(playerName), 50);
        if (entity != null) {
            entity.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60, 2));
            entity.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 144000, 0));
        }
    }

    public void troisCieux(String playerName) {
        LivingEntity entity = getEntityInSight(Bukkit.getPlayer(playerName), 50);
        if (entity != null) {
            entity.getActivePotionEffects().stream()
                    .filter(effect -> effect.getType() != PotionEffectType.HERO_OF_THE_VILLAGE)
                    .forEach(effect -> entity.removePotionEffect(effect.getType()));
            entity.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20, 0));
        }
    }

    // Bave de minazuki
    public void baveDeMinazuki(String playerName, int absorption) {
        LivingEntity entity = getEntityInSight(Bukkit.getPlayer(playerName), 50);
        if (entity != null) {
            entity.setAbsorptionAmount(absorption);
        }
    }

    // Shinigami
    public void sogyoNoKotowari(String playerName) {
        LivingEntity entity = getEntityInSight(Bukkit.getPlayer(playerName), 50);
        if (entity instanceof Player) {
            TomoNova.getPlugin().bleachUHC.affectPotionKotowari(entity.getName());
        }
    }

    public boolean isMinazukiActive() {
        return minazukiActive;
    }

    public void setMinazukiActive(boolean minazukiActive) {
        this.minazukiActive = minazukiActive;
    }

    public int getMinazukiCharges() {
        return minazukiCharges;
    }

    public void setMinazukiCharges(int minazukiCharges) {
        this.minazukiCharges = minazukiCharges;
    }
}
