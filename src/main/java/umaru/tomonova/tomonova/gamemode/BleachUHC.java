package umaru.tomonova.tomonova.gamemode;

import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.MythicBukkit;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import umaru.tomonova.tomonova.core.TomoNova;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BleachUHC {
    public List<MythicMob> bossesList = new ArrayList<MythicMob>();
    public HashMap<String,Location> bossLocation = new HashMap<String,Location>();
    public HashMap<String, Location> playersBossTarget = new HashMap<String, Location>();
    public HashMap<String, String> playersBossTargetName = new HashMap<String, String>();
    public List<PotionEffect> sogyoNoKotowari = new ArrayList<PotionEffect>();

    public void bleachUhcSettings() {
        TomoNova.getPlugin().gameManager.setUhc(false);
        TomoNova.getPlugin().gameManager.setSwitch(false);
        TomoNova.getPlugin().gameManager.setTaupe(false);
        TomoNova.getPlugin().gameManager.setScarletMansion(false);
        TomoNova.getPlugin().gameManager.setTomoLostVillage(false);
        TomoNova.getPlugin().gameManager.setBleachUhc(true);
        TomoNova.getPlugin().gameManager.setPlayersPerTeam(4);
    }
    public void initializePlayersBossTarget(){
        Bukkit.getOnlinePlayers().forEach(p -> playersBossTarget.put(p.getName(),null));
    }
    public void initializeBleachUhcMobs() {

    }
    public void createBoss(String bossName){
        MythicMob boss = MythicBukkit.inst().getMobManager().getMythicMob("AizenV2").get();
    }

    public void addPotionKotowari(PotionEffect potionEffect){
        for(PotionEffect potionKotowari : sogyoNoKotowari){
            if(potionKotowari.getType().equals(potionEffect.getType())
                && potionKotowari.getAmplifier() == potionEffect.getAmplifier()){
                PotionEffect newPotionEffect = new PotionEffect(potionKotowari.getType(), potionKotowari.getAmplifier(),potionKotowari.getDuration() + potionEffect.getDuration());
                sogyoNoKotowari.remove(potionKotowari);
                sogyoNoKotowari.add(newPotionEffect);
                return;
            }
        }
        sogyoNoKotowari.add(potionEffect);
    }
    public void affectPotionKotowari(String playerName){
        Player player = Bukkit.getPlayer(playerName);
        for(PotionEffect potionKotowari :sogyoNoKotowari){
            player.addPotionEffect(potionKotowari);
        }
        sogyoNoKotowari = new ArrayList<PotionEffect>();
    }
}
