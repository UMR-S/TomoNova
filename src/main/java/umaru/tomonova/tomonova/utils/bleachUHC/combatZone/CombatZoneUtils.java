package umaru.tomonova.tomonova.utils.bleachUHC.combatZone;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CombatZoneUtils {
    private List<List> allZones;
    private HashMap<Integer, List<Integer>> finalZone;
    private List<List<Integer>> contoursLoc;
    private List<Integer> actualPoint;
    private List<Integer> firstPoint;
    private int ymin;
    private int ymax;
    private boolean isFirstPoint;
    private boolean closedShape;

    public CombatZoneUtils() {
        this.contoursLoc = new ArrayList<List<Integer>>();
        this.ymin = 90;
        this.ymax = 120;
        this.actualPoint = new ArrayList<Integer>();
        this.firstPoint = new ArrayList<Integer>();
        this.isFirstPoint = false;
        this.finalZone = new HashMap<Integer, List<Integer>>();
        this.closedShape = false;
        this.allZones = new ArrayList<>();
    }

    public void addLine(int x, int z) {

        List<List<Integer>> locList = new ArrayList<>();
        locList = DrawingLines.drawLineBetweenPoints(getActualPoint().get(0), getActualPoint().get(1), x, z, getYmin());
        for (List<Integer> point : locList) {
            addcontoursLoc(point);
        }
        setActualPoint(x, z);
    }

    public void sortingContours() {
        boolean sorted = false;
        this.contoursLoc.remove(0);
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < this.contoursLoc.size() - 1; i++) {
                if (this.contoursLoc.get(i).get(0) > this.contoursLoc.get(i + 1).get(0)) {
                    List<Integer> mem = this.contoursLoc.get(i);
                    this.contoursLoc.set(i, this.contoursLoc.get(i + 1));
                    this.contoursLoc.set(i + 1, mem);
                    sorted = false;
                }
            }
        }
        contoursMinimalList();
    }

    public void contoursMinimalList() {
        int debut;
        int end = this.contoursLoc.size() - 1;
        int i = 0;
        while (i < end) {
            debut = i;
            while (i < end && this.contoursLoc.get(i).get(0) == this.contoursLoc.get(i + 1).get(0)) {
                i++;
            }
            int minZ = this.contoursLoc.get(debut).get(1);
            int maxZ = this.contoursLoc.get(debut).get(1);
            for (int k = debut + 1; k <= i; k++) {
                if (this.contoursLoc.get(k).get(1) > maxZ) {
                    maxZ = this.contoursLoc.get(k).get(1);
                }
                if (this.contoursLoc.get(k).get(1) < minZ) {
                    minZ = this.contoursLoc.get(k).get(1);
                }
            }
            addFinalZone(this.contoursLoc.get(i).get(0), Arrays.asList(minZ, maxZ));
            i++;
        }
    }

    public void resetZone() {
        this.finalZone = new HashMap<Integer, List<Integer>>();
        this.contoursLoc = new ArrayList<>();
        setIsFirstPoint(false);
    }

    public boolean isClosedShape() {
        if (this.actualPoint.get(0).intValue() == this.firstPoint.get(0).intValue()) {
            if (this.actualPoint.get(1).intValue() == this.firstPoint.get(1).intValue()) {
                setClosedShape(true);
                return true;
            }
        }
        return false;
    }

    public boolean isFirstPoint() {
        return this.isFirstPoint;
    }

    public void setIsFirstPoint(Boolean value) {
        this.isFirstPoint = value;
    }

    public void setFirstPoint(int x, int z) {
        this.firstPoint = new ArrayList<>(Arrays.asList(x, z));
        setIsFirstPoint(true);
    }

    public List<Integer> getFirstPoint() {
        return this.firstPoint;
    }

    public List<Integer> getActualPoint() {
        return actualPoint;
    }

    public void setActualPoint(int x, int z) {
        this.actualPoint = new ArrayList<>(Arrays.asList(x, z));
    }

    public void addcontoursLoc(List<Integer> point) {
        contoursLoc.add(point);
    }

    public void resetContoursLoc() {
        contoursLoc.clear();
    }

    public List<List<Integer>> getContoursLoc() {
        return contoursLoc;
    }

    public int getYmin() {
        return ymin;
    }

    public void setYmin(int ymin) {
        this.ymin = ymin;
    }

    public int getYmax() {
        return ymax;
    }

    public void setYmax(int ymax) {
        this.ymax = ymax;
    }

    public void addFinalZone(int x, List<Integer> z) {
        this.finalZone.put(x, z);
    }

    public HashMap<Integer, List<Integer>> getFinalZone() {
        return this.finalZone;
    }

    public void setClosedShape(boolean closedShape) {
        this.closedShape = closedShape;
    }

    public boolean getClosedShape() {
        return this.closedShape;
    }

    public List getAllZones() {
        return allZones;
    }

    public void setAllZones(List<List> allZones) {
        this.allZones = allZones;
    }

    public boolean isPlayerInZone(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        int xPlayer = player.getLocation().getBlockX();
        int yPlayer = player.getLocation().getBlockY();
        int zPlayer = player.getLocation().getBlockZ();

        for (List combatZone : this.allZones) {

            HashMap<Integer, List<Integer>> tempHashMap = (HashMap<Integer, List<Integer>>) combatZone.get(2);
            int ymin = (int) combatZone.get(0);
            int ymax = (int) combatZone.get(1);
            for (Integer x : tempHashMap.keySet()) {

                if (x.intValue() == xPlayer) {
                    if (zPlayer >= tempHashMap.get(x).get(0) && zPlayer <= tempHashMap.get(x).get(1)) {
                        if (yPlayer >= ymin && yPlayer <= ymax) {
                            return true;
                        }
                    }
                }

            }
        }
        return false;
    }

    public boolean isBlockInZone(int xBlock, int yBlock, int zBlock) {

        for (List combatZone : this.allZones) {

            HashMap<Integer, List<Integer>> tempHashMap = (HashMap<Integer, List<Integer>>) combatZone.get(2);
            int ymin = (int) combatZone.get(0);
            int ymax = (int) combatZone.get(1);
            for (Integer x : tempHashMap.keySet()) {

                if (x.intValue() == xBlock) {
                    if (zBlock >= tempHashMap.get(x).get(0) && zBlock <= tempHashMap.get(x).get(1)) {
                        if (yBlock >= ymin && yBlock <= ymax) {
                            return true;
                        }
                    }
                }

            }
        }
        return false;
    }
}
