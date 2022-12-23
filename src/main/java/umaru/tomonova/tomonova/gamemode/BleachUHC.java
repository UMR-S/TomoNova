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
    public HashMap<String,Boolean> lunettesBoolean = new HashMap<String,Boolean>();
    public HashMap<String,MythicMob> bossesList = new HashMap<String,MythicMob>();
    public HashMap<String,Location> bossLocation = new HashMap<String,Location>();
    public HashMap<String, String> playersBossTarget = new HashMap<String, String>();
    public List<PotionEffect> sogyoNoKotowari = new ArrayList<PotionEffect>();

    public void bleachUhcSettings() {
        TomoNova.getPlugin().gameManager.setUhc(false);
        TomoNova.getPlugin().gameManager.setSwitch(false);
        TomoNova.getPlugin().gameManager.setTaupe(false);
        TomoNova.getPlugin().gameManager.setScarletMansion(false);
        TomoNova.getPlugin().gameManager.setTomoLostVillage(false);
        TomoNova.getPlugin().gameManager.setBleachUhc(true);
        TomoNova.getPlugin().gameManager.setPlayersPerTeam(3);
    }
    public void initializeLunettesBoolean(){
        for(Player player : Bukkit.getOnlinePlayers()){
            lunettesBoolean.put(player.getName(),false);
        }
    }
    public void initializePlayersBossTarget(){
        Bukkit.getOnlinePlayers().forEach(p -> playersBossTarget.put(p.getName(),"None"));
    }
    public void initializeBleachUhcMobs() {
        bossesList.put("PlayerSamourai",createBoss("PlayerSamourai"));
    }
    public void initializeBleachUhcBossLoc(){
        Location bossLoc = new Location(TomoNova.getPlugin().worldUtils.getWorld(),166,30,488);
        bossLocation.put("ukitake",bossLoc.clone());
        bossLoc = new Location(TomoNova.getPlugin().worldUtils.getWorld(),251,42,286);
        bossLocation.put("mayuri",bossLoc.clone());
        bossLoc = new Location(TomoNova.getPlugin().worldUtils.getWorld(),559,31,-308);
        bossLocation.put("kenpachi",bossLoc.clone());
        bossLoc = new Location(TomoNova.getPlugin().worldUtils.getWorld(),164,31,-497);
        bossLocation.put("toshiro",bossLoc.clone());
        bossLoc = new Location(TomoNova.getPlugin().worldUtils.getWorld(),-158,31,53);
        bossLocation.put("tosen",bossLoc.clone());
        bossLoc = new Location(TomoNova.getPlugin().worldUtils.getWorld(),-222,36,367);
        bossLocation.put("kyoraku",bossLoc.clone());
        bossLoc = new Location(TomoNova.getPlugin().worldUtils.getWorld(),-68,31,-351);
        bossLocation.put("komamura",bossLoc.clone());
        bossLoc = new Location(TomoNova.getPlugin().worldUtils.getWorld(),-64,31,-562);
        bossLocation.put("byakuya",bossLoc.clone());
        bossLoc = new Location(TomoNova.getPlugin().worldUtils.getWorld(),-119,12,-93);
        bossLocation.put("aizenv2",bossLoc.clone());
        bossLoc = new Location(TomoNova.getPlugin().worldUtils.getWorld(),-254,31,203);
        bossLocation.put("aizenv1",bossLoc.clone());
        bossLoc = new Location(TomoNova.getPlugin().worldUtils.getWorld(),-73,31,283);
        bossLocation.put("unohana",bossLoc.clone());
        bossLoc = new Location(TomoNova.getPlugin().worldUtils.getWorld(),392,31,145);
        bossLocation.put("gin",bossLoc.clone());
        bossLoc = new Location(TomoNova.getPlugin().worldUtils.getWorld(),248,57,20);
        bossLocation.put("soifon",bossLoc.clone());
        bossLoc = new Location(TomoNova.getPlugin().worldUtils.getWorld(),109,74,-34);
        bossLocation.put("yamamoto",bossLoc.clone());
    }
    public MythicMob createBoss(String bossName){
        MythicMob boss = MythicBukkit.inst().getMobManager().getMythicMob(bossName).get();
        return boss;
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
    public String returnPlayerTargetName(String playerName){
        if(playersBossTarget.containsKey(playerName)){
            return playersBossTarget.get(playerName);
        }
        return "None";
    }
    public Location returnBossLoc(String bossName){
        if(bossLocation.containsKey(bossName)){
            return bossLocation.get(bossName).clone();
        }
        return TomoNova.getPlugin().worldUtils.getWorld().getSpawnLocation().clone();
    }

    public MythicMob getBossMM(String bossName) {
        if(bossesList.containsKey(bossName)) {
            return bossesList.get(bossName);
        }
        return null;
    }

    public Boolean getLunettesBooleanPlayer(String playerName) {
        if(lunettesBoolean.containsKey(playerName)){
            return lunettesBoolean.get(playerName);
        }
        return false;
    }

    public void setLunettesBooleanTruePlayer(String playerName) {
        this.lunettesBoolean.put(playerName,true);
    }
}
