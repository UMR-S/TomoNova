package umaru.tomonova.tomonova.gamemode;

import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.MythicBukkit;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.potion.PotionEffect;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.core.task.bleachUHCTask.SpawnBossesTask;
import umaru.tomonova.tomonova.gamemode.bleachUHC.GiveItem;
import umaru.tomonova.tomonova.utils.constants.BleachUHCConstants;
import umaru.tomonova.tomonova.utils.players.ArmorPlayer;

import java.util.*;

public class BleachUHC {
    public HashMap<String,Boolean> lunettesBoolean = new HashMap<String,Boolean>();
    public HashMap<String,MythicMob> bossesList = new HashMap<String,MythicMob>();
    public HashMap<String,Location> bossLocation = new HashMap<String,Location>();
    public HashMap<String, String> playersBossTarget = new HashMap<String, String>();
    public List<PotionEffect> sogyoNoKotowari = new ArrayList<PotionEffect>();
    public HashMap<String,Integer> pointJoueurs = new HashMap<String,Integer>();
    public HashMap<String, Boolean> playerUsedCacheOeil = new HashMap<String,Boolean>();
    public HashMap<String,Boolean> playerTracked = new HashMap<String,Boolean>();

    private List<Material> materialsToRemove = Arrays.asList(
            // Axes
            Material.WOODEN_AXE,
            Material.STONE_AXE,
            Material.IRON_AXE,
            Material.GOLDEN_AXE,
            Material.DIAMOND_AXE,
            Material.NETHERITE_AXE,

            // Diamond armor and tools
            Material.DIAMOND_HELMET,
            Material.DIAMOND_CHESTPLATE,
            Material.DIAMOND_LEGGINGS,
            Material.DIAMOND_BOOTS,
            Material.DIAMOND_SWORD,
            Material.DIAMOND_PICKAXE,
            Material.DIAMOND_AXE,
            Material.DIAMOND_SHOVEL,
            Material.DIAMOND_HOE,

            // Anvils
            Material.ANVIL,
            Material.CHIPPED_ANVIL,
            Material.DAMAGED_ANVIL,

            // Enchantment table
            Material.ENCHANTING_TABLE
    );


    public void bleachUhcSettings() {
        TomoNova.getPlugin().gameManager.setUhc(false);
        TomoNova.getPlugin().gameManager.setSwitch(false);
        TomoNova.getPlugin().gameManager.setTaupe(false);
        TomoNova.getPlugin().gameManager.setScarletMansion(false);
        TomoNova.getPlugin().gameManager.setTomoLostVillage(false);
        TomoNova.getPlugin().gameManager.setBleachUhc(true);
        TomoNova.getPlugin().gameManager.setPlayersPerTeam(3);
        if(TomoNova.test){
            initializeBleachUHC();
        }
    }

    public void initializeBleachUHC (){
        initializePointsJoueurs();
        initializePlayersBossTarget();
        initializeCacheOeilBoolean();
        initializeTargetBoolean();
    }

    public void initializeStuffJoueurs(){
        for(Player player : Bukkit.getOnlinePlayers()){
            if(TomoNova.getPlugin().classesUtils.isPlayerShinigami(player.getName())){
                GiveItem.giveZanpakuto(player.getName());
                GiveItem.dashShinigami(player.getName());
                ArmorPlayer.equipIronArmor(player);
            }
            if(TomoNova.getPlugin().classesUtils.isPlayerQuincy(player.getName())){
                GiveItem.giveBow(player.getName());
                GiveItem.dashQuincy(player.getName());
                ArmorPlayer.equipGoldArmor(player);
            }
            if(TomoNova.getPlugin().classesUtils.isPlayerSSR(player.getName())){
                GiveItem.give1Cieux(player.getName());
                GiveItem.give2Cieux(player.getName());
                GiveItem.give3Cieux(player.getName());
                GiveItem.give4Cieux(player.getName());
                ArmorPlayer.equipChainmailArmor(player);
            }
            if(TomoNova.getPlugin().classesUtils.isPlayerBrazo(player.getName())){
                GiveItem.giveShield(player.getName());
                ArmorPlayer.equipChainmailArmor(player);
            }
        }
    }

    public void removeCraft(){
        Iterator<Recipe> it = Bukkit.recipeIterator();
        while (it.hasNext()) {
            Recipe recipe = it.next();
            ItemStack result = recipe.getResult();

            if (materialsToRemove.contains(result.getType())) {
                it.remove();
            }
        }
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

    public void initializeCacheOeilBoolean(){
        for(Player player : Bukkit.getOnlinePlayers()){
            playerUsedCacheOeil.put(player.getName(),false);
        }
    }

    public void setCacheOeilBoolen(String playerName){
        if(playerUsedCacheOeil.containsKey(playerName)){
            playerUsedCacheOeil.put(playerName,true);
        }
    }

    public boolean getCacheOeilBoolean(String playerName){
        return playerUsedCacheOeil.getOrDefault(playerName, false);
    }

    public String getCacheOeilName(){
        for(String playerName : playerUsedCacheOeil.keySet()){
            if(playerUsedCacheOeil.get(playerName)){
                return playerName;
            }
        }
        return "None";
    }

    public void initializeTargetBoolean(){
        for(Player player : Bukkit.getOnlinePlayers()){
            playerTracked.put(player.getName(),false);
        }
    }

    public void setTrackedBoolen(String playerName){
        if(playerTracked.containsKey(playerName)){
            playerTracked.put(playerName,true);
        }
    }

    public boolean getTrackedBoolean(String playerName){
        return playerTracked.getOrDefault(playerName, false);
    }

    public String getTrackedName(){
        for(String playerName : playerTracked.keySet()){
            if(playerTracked.get(playerName)){
                return playerName;
            }
        }
        return "None";
    }

    public void initializePlayersBossTarget(){
        Bukkit.getOnlinePlayers().forEach(p -> playersBossTarget.put(p.getName(),"None"));
    }

    public void spawnBosses(){
        initializeBleachUhcBossLoc();
        for(String boss : bossLocation.keySet()){
            bossesList.put(boss, getBoss(boss));
        }
        new SpawnBossesTask(TomoNova.getPlugin(),bossLocation).runTaskTimer(TomoNova.getPlugin(),0,20);
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

    public MythicMob getBoss(String bossName) {
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
