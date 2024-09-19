package umaru.tomonova.tomonova.listeners.bleachUHC;

import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.bukkit.events.MythicMobDeathEvent;
import io.lumine.mythic.core.mobs.ActiveMob;
import org.bukkit.entity.Entity;
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
            if(mob.getKiller() != null){
                Player player = mob.getKiller();
                TomoNova.getPlugin().bleachUHC.addPoints(player.getName(),10);
            }
        }
        if(event.getEntity() instanceof Player){
            Player player = ((Player) event.getEntity()).getPlayer();

            if(MythicBukkit.inst().getMobManager().isMythicMob(player.getKiller())){
                String name = player.getKiller().getDisplayName();
                switch(name){
                    case BleachUHCConstants.SOI_FON_NAME:
                        SoundsUtils.playSoundIfInRange(player.getKiller().getLocation(),SoundsConstants.SOIFON_KILL,64);
                        break;
                    case BleachUHCConstants.YAMAMOTO_NAME:
                        SoundsUtils.playSoundIfInRange(player.getKiller().getLocation(),SoundsConstants.YAMAMOTO_KILL,64);
                        break;
                    case BleachUHCConstants.GIN_NAME:
                        SoundsUtils.playSoundIfInRange(player.getKiller().getLocation(),SoundsConstants.GIN_KILL,64);
                        break;
                    case BleachUHCConstants.UNOHANA_NAME:
                        SoundsUtils.playSoundIfInRange(player.getKiller().getLocation(),SoundsConstants.UNOHANA_KILL,64);
                        break;
                    case BleachUHCConstants.AIZEN_V2_NAME:
                        SoundsUtils.playSoundIfInRange(player.getKiller().getLocation(),SoundsConstants.AIZEN_KILL,64);
                        break;
                    case BleachUHCConstants.BYAKUYA_NAME:
                        SoundsUtils.playSoundIfInRange(player.getKiller().getLocation(),SoundsConstants.BYAKUYA_KILL,64);
                        break;
                    case BleachUHCConstants.KOMAMURA_NAME:
                        SoundsUtils.playSoundIfInRange(player.getKiller().getLocation(),SoundsConstants.KOMAMURA_KILL,64);
                        break;
                    case BleachUHCConstants.KYORAKU_NAME:
                        SoundsUtils.playSoundIfInRange(player.getKiller().getLocation(),SoundsConstants.KYORAKU_KILL,64);
                        break;
                    case BleachUHCConstants.TOSEN_NAME:
                        SoundsUtils.playSoundIfInRange(player.getKiller().getLocation(),SoundsConstants.TOSEN_KILL,64);
                        break;
                    case BleachUHCConstants.TOSHIRO_NAME:
                        SoundsUtils.playSoundIfInRange(player.getKiller().getLocation(),SoundsConstants.TOSHIRO_KILL,64);
                        break;
                    case BleachUHCConstants.KENPACHI_NAME:
                        SoundsUtils.playSoundIfInRange(player.getKiller().getLocation(),SoundsConstants.KENPACHI_KILL,64);
                        break;
                    case BleachUHCConstants.MAYURI_NAME:
                        SoundsUtils.playSoundIfInRange(player.getKiller().getLocation(),SoundsConstants.MAYURI_KILL,64);
                        break;
                    case BleachUHCConstants.UKITAKE_NAME:
                        SoundsUtils.playSoundIfInRange(player.getKiller().getLocation(),SoundsConstants.UKITAKE_COUGH,64);
                        break;
                }
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
                break;
            case BleachUHCConstants.YAMAMOTO_NAME:
                GiveItem.spawnRyujinJakka(event.getEntity().getLocation());
                GiveItem.spawnArtDuHakuda(event.getEntity().getLocation());
                break;
            case BleachUHCConstants.GIN_NAME:
                GiveItem.spawnShinso(event.getEntity().getLocation());
                GiveItem.spawnAveuxDeGin(event.getEntity().getLocation());
                GiveItem.spawnHogyokuInactifFragment(event.getEntity().getLocation());
                break;
            case BleachUHCConstants.UNOHANA_NAME:
                GiveItem.spawnMinazuki(event.getEntity().getLocation());
                GiveItem.spawnBaveMinazuki(event.getEntity().getLocation());
                break;
            case BleachUHCConstants.AIZEN_V1_NAME:
                GiveItem.spawnInsigne(event.getEntity().getLocation());
                break;
            case BleachUHCConstants.AIZEN_V2_NAME:
                GiveItem.spawnKyokaSuigetsu(event.getEntity().getLocation());
                GiveItem.spawnHogyokuCoeur(event.getEntity().getLocation());
                break;
            case BleachUHCConstants.BYAKUYA_NAME:
                GiveItem.spawnSenbonzakura(event.getEntity().getLocation());
                GiveItem.spawnKenseikan(event.getEntity().getLocation());
                break;
            case BleachUHCConstants.KOMAMURA_NAME:
                GiveItem.spawnTengen(event.getEntity().getLocation());
                GiveItem.spawnCasqueKomamura(event.getEntity().getLocation());
                break;
            case BleachUHCConstants.KYORAKU_NAME:
                GiveItem.spawnKatenKyokotsu(event.getEntity().getLocation());
                GiveItem.spawnSakeKyoraku(event.getEntity().getLocation());
                break;
            case BleachUHCConstants.TOSEN_NAME:
                GiveItem.spawnSuzumuchi(event.getEntity().getLocation());
                GiveItem.spawnLunettesTosen(event.getEntity().getLocation());
                GiveItem.spawnHogyokuInactifFragment(event.getEntity().getLocation());
                break;
            case BleachUHCConstants.TOSHIRO_NAME:
                GiveItem.spawnHyorinmaru(event.getEntity().getLocation());
                GiveItem.spawnLysDesNeiges(event.getEntity().getLocation());
                break;
            case BleachUHCConstants.KENPACHI_NAME:
                GiveItem.spawnKenpachiSword(event.getEntity().getLocation());
                GiveItem.spawnCacheOeilKenpachi(event.getEntity().getLocation());
                break;
            case BleachUHCConstants.MAYURI_NAME:
                GiveItem.spawnAshisogiJizo(event.getEntity().getLocation());
                GiveItem.spawnGantDeSanrei(event.getEntity().getLocation());
                break;
            case BleachUHCConstants.UKITAKE_NAME:
                GiveItem.spawnSogyoNoKotowari(event.getEntity().getLocation());
                GiveItem.spawnMedicament(event.getEntity().getLocation());
                break;
        }
    }
}
