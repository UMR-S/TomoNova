package umaru.tomonova.tomonova.gamemode.bleachUHC.classes;

import umaru.tomonova.tomonova.gamemode.bleachUHC.GiveItem;

public class Quincy {

    public static void giveStatingStuff(String playerName){
        GiveItem.giveBow(playerName);
        GiveItem.giveCarquois(playerName);
        GiveItem.giveQuincyArrow(playerName,17);
    }

}
