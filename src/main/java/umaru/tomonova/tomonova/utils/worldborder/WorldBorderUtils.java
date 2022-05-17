package umaru.tomonova.tomonova.utils.worldborder;

import org.bukkit.WorldBorder;
import umaru.tomonova.tomonova.utils.world.WorldUtils;

public class WorldBorderUtils {
    private static int startSize = 1000;
    private static int speed = 4;
    private static int finalSize = 100;

    public static void WorldBorderUtils() {
        WorldUtils.getWorld().getWorldBorder();
        WorldUtils.getNether().getWorldBorder().reset();
        WorldUtils.getEnd().getWorldBorder().reset();
    }
    public static void changeBorder(int size){
        WorldBorder wb = WorldUtils.getWorld().getWorldBorder();
        wb.setCenter(0.0, 0.0);
        wb.setSize((double)size);
        wb.setDamageAmount(2.0);
        wb.setDamageBuffer(5.0);
        wb.setWarningDistance(20);
        wb = WorldUtils.getNether().getWorldBorder();
        wb.setCenter(0.0, 0.0);
        wb.setSize((double)size);
        wb.setDamageAmount(2.0);
        wb.setDamageBuffer(5.0);
        wb.setWarningDistance(20);
        wb = WorldUtils.getEnd().getWorldBorder();
        wb.setCenter(0.0, 0.0);
        wb.setSize((double)size);
        wb.setDamageAmount(2.0);
        wb.setDamageBuffer(5.0);
        wb.setWarningDistance(20);
    }

    public static int getStartSize() {
        return startSize;
    }

    public static void setStartSize(int startSize) {
        WorldBorderUtils.startSize = startSize;
    }

    public static int getSpeed() {
        return speed;
    }

    public static void setSpeed(int speed) {
        WorldBorderUtils.speed = speed;
    }

    public static int getFinalSize() {
        return finalSize;
    }

    public static void setFinalSize(int finalSize) {
        WorldBorderUtils.finalSize = finalSize;
    }
}
