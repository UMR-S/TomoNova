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
    //Shinigami & Quincy
    public int minazukiLeftUses = 3;
    public boolean minazukiActive = false;
    public void Dash(double power, String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        Vector playerFacing = player.getLocation().getDirection();
        playerFacing.normalize();
        playerFacing.multiply(power);
        player.setVelocity(playerFacing);
    }
    //Quincy
    public void Carquois(String playerName) {
        Player player = Bukkit.getPlayer(playerName);

        ItemStack quincyArrow = GiveItem.getQuincyArrow();
        quincyArrow.setAmount(0);

        int arrowAmount = 0;
        PlayerInventory inv = player.getInventory();
        for (ItemStack is : inv.all(Material.ARROW).values()) {
            if (is != null && is.getType() == Material.ARROW) {
                arrowAmount = arrowAmount + is.getAmount();
            }
        }
        if (arrowAmount <= 11) {
            quincyArrow.setAmount(17);
            player.setCooldown(Material.CARROT_ON_A_STICK, 600);
        } else if (12 <= arrowAmount && arrowAmount <= 28) {
            quincyArrow.setAmount(28 - arrowAmount);
            player.setCooldown(Material.CARROT_ON_A_STICK, 600);
        }
        player.getInventory().addItem(quincyArrow);
    }

    //SSR

    public LivingEntity getEntityInSight(Player player, int range) {

        List<Entity> targetList = player.getNearbyEntities(range, range, range);

        BlockIterator bi = new BlockIterator(player, range);

        LivingEntity target = null;


        while (bi.hasNext()) {


            Block b = bi.next();

            int bx = b.getX();

            int by = b.getY();

            int bz = b.getZ();


            if (b.getType().isSolid()) {

                break;

            } else {

                for (Entity e : targetList) {

                    Location l = e.getLocation();

                    double ex = l.getX();

                    double ey = l.getY();

                    double ez = l.getZ();

                    if ((bx - .75 <= ex && ex <= bx + 1.75) && (bz - .75 <= ez && ez <= bz + 1.75) && (by - 1 <= ey && ey <= by + 2.5)) {

                        if (e instanceof LivingEntity) {
                            target = (LivingEntity) e;
                        }

                    }

                }

            }

        }


        //if (target != null)
            //player.sendMessage("You are looking at " + target.getName() + " (" + target.getUniqueId() + ") BI");
        return target;
    }

    public void cielUnique(String playerName) {
        LivingEntity entity = getEntityInSight(Bukkit.getPlayer(playerName), 50);
        if (entity != null) {
            entity.damage(5);
            entity.setVelocity(entity.getLocation().add(0.0, 1.0, 0.0).clone().toVector().subtract(Bukkit.getPlayer(playerName).getLocation().clone().toVector()).normalize().multiply(5));
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
            for (PotionEffect effect : entity.getActivePotionEffects()) {
                if (effect.getType() != PotionEffectType.HERO_OF_THE_VILLAGE) {
                    entity.removePotionEffect(effect.getType());
                }
            }
            entity.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20, 0));
        }
    }
    //Bave de minazuki
    public void baveDeMinazuki(String playerName, int absorption){
        LivingEntity entity = getEntityInSight(Bukkit.getPlayer(playerName), 50);
        if (entity != null) {
            entity.setAbsorptionAmount(absorption);
        }
    }
    //Shinigami
    public void sogyoNoKotowari(String playerName){
        LivingEntity entity = getEntityInSight(Bukkit.getPlayer(playerName), 50);
        if(entity != null){
            if(entity instanceof Player){
                TomoNova.getPlugin().bleachUHC.affectPotionKotowari(entity.getName());
            }
        }
    }

    public int getMinazukiLeftUses() {
        return minazukiLeftUses;
    }

    public void setMinazukiLeftUses(int minazukiLeftUses) {
        this.minazukiLeftUses = minazukiLeftUses;
    }

    public boolean isMinazukiActive() {
        return minazukiActive;
    }

    public void setMinazukiActive(boolean minazukiActive) {
        this.minazukiActive = minazukiActive;
    }
}
