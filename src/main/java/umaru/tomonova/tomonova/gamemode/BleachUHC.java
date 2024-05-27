package umaru.tomonova.tomonova.gamemode;

import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.MythicBukkit;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.utils.constants.BleachUHCConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BleachUHC {
    public HashMap<String,Boolean> lunettesBoolean = new HashMap<String,Boolean>();
    public HashMap<String,MythicMob> bossesList = new HashMap<String,MythicMob>();
    public HashMap<String,Location> bossLocation = new HashMap<String,Location>();
    public HashMap<String, String> playersBossTarget = new HashMap<String, String>();
    public List<PotionEffect> sogyoNoKotowari = new ArrayList<PotionEffect>();
    public HashMap<String,Integer> pointJoueurs = new HashMap<String,Integer>();


    public void bleachUhcSettings() {
        TomoNova.getPlugin().gameManager.setUhc(false);
        TomoNova.getPlugin().gameManager.setSwitch(false);
        TomoNova.getPlugin().gameManager.setTaupe(false);
        TomoNova.getPlugin().gameManager.setScarletMansion(false);
        TomoNova.getPlugin().gameManager.setTomoLostVillage(false);
        TomoNova.getPlugin().gameManager.setBleachUhc(true);
        TomoNova.getPlugin().gameManager.setPlayersPerTeam(3);
        initializePointsJoueurs();
    }

    public void initializePointsJoueurs(){
        for(Player player : Bukkit.getOnlinePlayers()){
            pointJoueurs.put(player.getName(),0);
        }
    }

    public void addPoints(String playerName,int nbPoints){
        pointJoueurs.merge(playerName, nbPoints, Integer::sum);
    }

    public int getPlayerPoints(String playerName){
        return pointJoueurs.getOrDefault(playerName, -1);
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
        bossesList.put(BleachUHCConstants.SAMOURAI_NAME, createBoss(BleachUHCConstants.SAMOURAI_NAME));
    }

    public void initializeBleachUhcBossLoc() {
        addBossLocation(BleachUHCConstants.UKITAKE_NAME, 166, 30, 488);
        addBossLocation(BleachUHCConstants.MAYURI_NAME, 251, 42, 286);
        addBossLocation(BleachUHCConstants.KENPACHI_NAME, 559, 31, -308);
        addBossLocation(BleachUHCConstants.TOSHIRO_NAME, 164, 31, -497);
        addBossLocation(BleachUHCConstants.TOSEN_NAME, -158, 31, 53);
        addBossLocation(BleachUHCConstants.KYORAKU_NAME, -222, 36, 367);
        addBossLocation(BleachUHCConstants.KOMAMURA_NAME, -68, 31, -351);
        addBossLocation(BleachUHCConstants.BYAKUYA_NAME, -64, 31, -562);
        addBossLocation(BleachUHCConstants.AIZEN_V2_NAME, -119, 12, -93);
        addBossLocation(BleachUHCConstants.AIZEN_V1_NAME, -254, 31, 203);
        addBossLocation(BleachUHCConstants.UNOHANA_NAME, -73, 31, 283);
        addBossLocation(BleachUHCConstants.GIN_NAME, 392, 31, 145);
        addBossLocation(BleachUHCConstants.SOI_FON_NAME, 248, 57, 20);
        addBossLocation(BleachUHCConstants.YAMAMOTO_NAME, 109, 74, -34);
    }

    private void addBossLocation(String bossName, int x, int y, int z) {
        Location bossLoc = new Location(TomoNova.getPlugin().worldUtils.getWorld(), x, y, z);
        bossLocation.put(bossName, bossLoc);
    }

    public MythicMob createBoss(String bossName) {
        return MythicBukkit.inst().getMobManager().getMythicMob(bossName).orElse(null);
    }

    public void addPotionKotowari(PotionEffect potionEffect){
        sogyoNoKotowari.add(potionEffect);
    }
    public void affectPotionKotowari(String playerName){
        Player player = Bukkit.getPlayer(playerName);
        for(PotionEffect potionKotowari :sogyoNoKotowari){
            player.addPotionEffect(potionKotowari);
        }
        sogyoNoKotowari = new ArrayList<PotionEffect>();
    }
    public String returnPlayerTargetName(String playerName) {
        return playersBossTarget.getOrDefault(playerName, "None");
    }
    public Location returnBossLoc(String bossName) {
        return bossLocation.getOrDefault(bossName, TomoNova.getPlugin().worldUtils.getWorld().getSpawnLocation()).clone();
    }

    public MythicMob getBossMM(String bossName) {
        return bossesList.get(bossName);
    }

    public Boolean getLunettesBooleanPlayer(String playerName) {
        return lunettesBoolean.getOrDefault(playerName, false);
    }

    public void setLunettesBooleanTruePlayer(String playerName) {
        lunettesBoolean.put(playerName, true);
    }
}
