package umaru.tomonova.tomonova.listeners.bleachUHC;

import io.lumine.mythic.bukkit.events.MythicMobDeathEvent;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.gamemode.bleachUHC.GiveItem;
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
                System.out.println("A ajouter");
                break;
            case BleachUHCConstants.AIZEN_V2_NAME:
                //System.out.println("Arme");
                GiveItem.spawnHogyokuCoeur(event.getEntity().getLocation());
                break;
            case BleachUHCConstants.BYAKUYA_NAME:
                GiveItem.spawnSenbonzakura(event.getEntity().getLocation());
                break;
                //Rajouter dash
            case BleachUHCConstants.KOMAMURA_NAME:
                GiveItem.spawnTengen(event.getEntity().getLocation());
                break;
                //Casque
            case BleachUHCConstants.KYORAKU_NAME:
                GiveItem.spawnKatenKyokotsu(event.getEntity().getLocation());
                break;
                //Sake
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
                break;
                //CacheOeil
            case BleachUHCConstants.MAYURI_NAME:
                GiveItem.spawnAshisogiJizo(event.getEntity().getLocation());
                GiveItem.spawnGantDeSanrei(event.getEntity().getLocation());
                break;
            case BleachUHCConstants.UKITAKE_NAME:
                GiveItem.spawnMedicament(event.getEntity().getLocation());
                break;
        }
    }
}
