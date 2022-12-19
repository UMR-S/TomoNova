package umaru.tomonova.tomonova.utils.combatZone;

import org.bukkit.Bukkit;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DrawingLines {
    public static List<List<Integer>> drawLineBetweenPoints(int x0, int y0, int x1, int y1, int z){

        List<List<Integer>> locList = new ArrayList<>();

        int dx = x1-x0;
        int dy = y1-y0;
        int dirX1 = 0; int dirY1 = 0; int dirX2 = 0 ;int dirY2 = 0;

        if (dx<0){
            dirX1 = -1;
            dirX2 = -1;
        } else if(dx>0){
            dirX1 = 1;
            dirX2 = 1;
        }
        if(dy<0){
            dirY1 = -1;
        } else if (dy>0) {
            dirY1 = 1;
        }

        int longest = Math.abs(dx);
        int shortest = Math.abs(dy);

        if(!(longest>shortest)){
            longest = Math.abs(dy);
            shortest = Math.abs(dx);
            if(dy<0){
                dirY2 = -1;
            } else if (dy>0) {
                dirY2 = 1;
            }
            dirX2 = 0;
        }
        int numerator =  longest >>1;
        for (int i=0;i<=longest;i++) {
            Bukkit.getWorld("world").getBlockAt(x0,z,y0).setType(Material.RED_CONCRETE);
            locList.add(new ArrayList<>(Arrays.asList(x0,y0)));
            numerator += shortest ;
            if (!(numerator<longest)) {
                numerator -= longest ;
                x0 += dirX1 ;
                y0 += dirY1 ;
            } else {
                x0 += dirX2 ;
                y0 += dirY2 ;
            }
        }
        return locList;
    }
}
