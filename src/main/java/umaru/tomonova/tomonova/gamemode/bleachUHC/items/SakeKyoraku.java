package umaru.tomonova.tomonova.gamemode.bleachUHC.items;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class SakeKyoraku {
    public static void sakeTeleport(String firstPlayerName, String secondPlayerName){
        Player firstPlayer = Bukkit.getPlayer(firstPlayerName);
        Player secondPlayer = Bukkit.getPlayer(secondPlayerName);
        Location firstPlayerLoc = firstPlayer.getLocation().clone();
        Location secondPlayerLoc = secondPlayer.getLocation().clone();
        //Vecteur allant du premier joueur au second joueur
        Vector playersVector = secondPlayerLoc.toVector().subtract(firstPlayerLoc.toVector()).normalize().multiply(100);
        secondPlayerLoc.add(playersVector);
        firstPlayerLoc.add(playersVector.clone().multiply(-1));
        //Rechercher les blocs d'air les plus proches (en hauteur uniquement)
        while(!firstPlayerLoc.getBlock().isEmpty()
                || !firstPlayerLoc.add(0,1,0).getBlock().isEmpty()){
            firstPlayerLoc.add(0,1,0);
        }
        while(!secondPlayerLoc.getBlock().isEmpty()
                || !secondPlayerLoc.add(0,1,0).getBlock().isEmpty()){
            secondPlayerLoc.add(0,1,0);
        }
        firstPlayer.teleport(firstPlayerLoc);
        secondPlayer.teleport(secondPlayerLoc);

    }
}
