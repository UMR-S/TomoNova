package umaru.tomonova.tomonova.listeners.bleachUHC;

import io.lumine.mythic.bukkit.events.MythicMobDeathEvent;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.gamemode.bleachUHC.GiveItem;
import umaru.tomonova.tomonova.utils.bleachUHC.sounds.SoundsConstants;
import umaru.tomonova.tomonova.utils.bleachUHC.sounds.SoundsUtils;
import umaru.tomonova.tomonova.utils.constants.BleachUHCConstants;


public class MobDeathEvent implements Listener {

    @EventHandler
    public void MobDeathEvent(EntityDeathEvent event){
        if(event.getEntity() instanceof Mob){
            Mob mob = (Mob) event.getEntity();
            System.out.println("Test");
            if(mob.getKiller() != null){
                Player player = mob.getKiller();
                TomoNova.getPlugin().bleachUHC.addPoints(player.getName(),10);
            }
        }
    }
    @EventHandler
    public void BossDeathEvent(MythicMobDeathEvent event){
        String name = event.getMob().getDisplayName();
        switch(name){
            case BleachUHCConstants.SOI_FON_NAME:
                GiveItem.spawnSuzumebachi(event.getEntity().getLocation());
                GiveItem.spawnPhotoYoruichi(event.getEntity().getLocation());
                SoundsUtils.playSoundForAll(SoundsConstants.SOIFON_DEATH);
                break;
            case BleachUHCConstants.YAMAMOTO_NAME:
                GiveItem.spawnRyujinJakka(event.getEntity().getLocation());
                GiveItem.spawnArtDuHakuda(event.getEntity().getLocation());
                SoundsUtils.playSoundForAll(SoundsConstants.YAMAMOTO_DEATH);
                break;
            case BleachUHCConstants.GIN_NAME:
                GiveItem.spawnShinso(event.getEntity().getLocation());
                GiveItem.spawnAveuxDeGin(event.getEntity().getLocation());
                GiveItem.spawnHogyokuInactifFragment(event.getEntity().getLocation());
                SoundsUtils.playSoundForAll(SoundsConstants.GIN_DEATH);
                break;
            case BleachUHCConstants.UNOHANA_NAME:
                GiveItem.spawnMinazuki(event.getEntity().getLocation());
                GiveItem.spawnBaveMinazuki(event.getEntity().getLocation());
                SoundsUtils.playSoundForAll(SoundsConstants.UNOHANA_DEATH);
                break;
            case BleachUHCConstants.AIZEN_V1_NAME:
                GiveItem.spawnInsigne(event.getEntity().getLocation());
                break;
            case BleachUHCConstants.AIZEN_V2_NAME:
                GiveItem.spawnKyokaSuigetsu(event.getEntity().getLocation());
                GiveItem.spawnHogyokuCoeur(event.getEntity().getLocation());
                SoundsUtils.playSoundForAll(SoundsConstants.AIZEN_DEATH);
                break;
            case BleachUHCConstants.BYAKUYA_NAME:
                GiveItem.spawnSenbonzakura(event.getEntity().getLocation());
                GiveItem.spawnKenseikan(event.getEntity().getLocation());
                SoundsUtils.playSoundForAll(SoundsConstants.BYAKUYA_DEATH);
                break;
            case BleachUHCConstants.KOMAMURA_NAME:
                GiveItem.spawnTengen(event.getEntity().getLocation());
                GiveItem.spawnCasqueKomamura(event.getEntity().getLocation());
                SoundsUtils.playSoundForAll(SoundsConstants.KOMAMURA_DEATH);
                break;
            case BleachUHCConstants.KYORAKU_NAME:
                GiveItem.spawnKatenKyokotsu(event.getEntity().getLocation());
                GiveItem.spawnSakeKyoraku(event.getEntity().getLocation());
                SoundsUtils.playSoundForAll(SoundsConstants.KYORAKU_DEATH);
                break;
            case BleachUHCConstants.TOSEN_NAME:
                GiveItem.spawnSuzumuchi(event.getEntity().getLocation());
                GiveItem.spawnLunettesTosen(event.getEntity().getLocation());
                GiveItem.spawnHogyokuInactifFragment(event.getEntity().getLocation());
                SoundsUtils.playSoundForAll(SoundsConstants.TOSEN_DEATH);
                break;
            case BleachUHCConstants.TOSHIRO_NAME:
                GiveItem.spawnHyorinmaru(event.getEntity().getLocation());
                GiveItem.spawnLysDesNeiges(event.getEntity().getLocation());
                SoundsUtils.playSoundForAll(SoundsConstants.TOSHIRO_DEATH);
                break;
            case BleachUHCConstants.KENPACHI_NAME:
                GiveItem.spawnKenpachiSword(event.getEntity().getLocation());
                GiveItem.spawnCacheOeilKenpachi(event.getEntity().getLocation());
                SoundsUtils.playSoundForAll(SoundsConstants.KENPACHI_DEATH);
                break;
            case BleachUHCConstants.MAYURI_NAME:
                GiveItem.spawnAshisogiJizo(event.getEntity().getLocation());
                GiveItem.spawnGantDeSanrei(event.getEntity().getLocation());
                SoundsUtils.playSoundForAll(SoundsConstants.MAYURI_DEATH);
                break;
            case BleachUHCConstants.UKITAKE_NAME:
                GiveItem.spawnSogyoNoKotowari(event.getEntity().getLocation());
                GiveItem.spawnMedicament(event.getEntity().getLocation());
                SoundsUtils.playSoundForAll(SoundsConstants.UKITAKE_DEATH);
                break;
        }
    }
}
