package umaru.tomonova.tomonova.listeners.bleachUHC;

import io.lumine.mythic.bukkit.events.MythicMobDeathEvent;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.gamemode.bleachUHC.GiveItem;


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
            case "Soi Fon":
                GiveItem.spawnSuzumebachi(event.getEntity().getLocation());
                GiveItem.spawnPhotoYoruichi(event.getEntity().getLocation());
                break;
            case "Yamamoto":
                GiveItem.spawnRyujinJakka(event.getEntity().getLocation());
                GiveItem.spawnArtDuHakuda(event.getEntity().getLocation());
                break;
            case "Gin":
                GiveItem.spawnShinso(event.getEntity().getLocation());
                GiveItem.spawnAveuxDeGin(event.getEntity().getLocation());
                GiveItem.spawnHogyokuInactifFragment(event.getEntity().getLocation());
                break;
            case "Unohana":
                GiveItem.spawnMinazuki(event.getEntity().getLocation());
                GiveItem.spawnBaveMinazuki(event.getEntity().getLocation());
                break;
            case "Illusion d'Aizen":
                System.out.println("A ajouter");
                break;
            case "Aizen":
                //System.out.println("Arme");
                GiveItem.spawnHogyokuCoeur(event.getEntity().getLocation());
                break;
            case "Byakuya":
                GiveItem.spawnSenbonzakura(event.getEntity().getLocation());
                break;
                //Rajouter dash
            case "Komamura":
                GiveItem.spawnTengen(event.getEntity().getLocation());
                break;
                //Casque
            case "Kyoraku":
                GiveItem.spawnKatenKyokotsu(event.getEntity().getLocation());
                break;
                //Sake
            case "Tosen":
                GiveItem.spawnSuzumuchi(event.getEntity().getLocation());
                GiveItem.spawnLunettesTosen(event.getEntity().getLocation());
                GiveItem.spawnHogyokuInactifFragment(event.getEntity().getLocation());
                break;
            case "Toshiro":
                GiveItem.spawnHyorinmaru(event.getEntity().getLocation());
                GiveItem.spawnLysDesNeiges(event.getEntity().getLocation());
                break;
            case "Kenpachi":
                GiveItem.spawnKenpachiSword(event.getEntity().getLocation());
                break;
                //CacheOeil
            case "Mayuri":
                GiveItem.spawnAshisogiJizo(event.getEntity().getLocation());
                GiveItem.spawnGantDeSanrei(event.getEntity().getLocation());
                break;
            case "Ukitake":
                GiveItem.spawnMedicament(event.getEntity().getLocation());
                break;
        }
    }
}
