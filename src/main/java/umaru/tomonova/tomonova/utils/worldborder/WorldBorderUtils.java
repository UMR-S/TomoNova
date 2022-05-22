package umaru.tomonova.tomonova.utils.worldborder;

import org.bukkit.WorldBorder;
import umaru.tomonova.tomonova.core.TomoNova;

public class WorldBorderUtils {
    private int startSize;
    private int speed;
    private int finalSize;

    public WorldBorderUtils() {
        this.startSize = 2000;
        this.speed = 4;
        this.finalSize = 50;
        WorldBorder wb = TomoNova.getPlugin().worldUtils.getWorld().getWorldBorder();
        wb.reset();
        wb = TomoNova.getPlugin().worldUtils.getNether().getWorldBorder();
        wb.reset();
        wb = TomoNova.getPlugin().worldUtils.getEnd().getWorldBorder();
        wb.reset();
    }

    public void change(final int size) {
        WorldBorder wb = TomoNova.getPlugin().worldUtils.getWorld().getWorldBorder();
        wb.setCenter(0.0, 0.0);
        wb.setSize((double) size);
        wb.setDamageAmount(2.0);
        wb.setDamageBuffer(5.0);
        wb.setWarningDistance(20);
        wb = TomoNova.getPlugin().worldUtils.getNether().getWorldBorder();
        wb.setCenter(0.0, 0.0);
        wb.setSize((double) size);
        wb.setDamageAmount(2.0);
        wb.setDamageBuffer(5.0);
        wb.setWarningDistance(20);
        wb = TomoNova.getPlugin().worldUtils.getEnd().getWorldBorder();
        wb.setCenter(0.0, 0.0);
        wb.setSize((double) size);
        wb.setDamageAmount(2.0);
        wb.setDamageBuffer(5.0);
        wb.setWarningDistance(20);
    }

    public void change(final int size, final long time) {
        WorldBorder wb = TomoNova.getPlugin().worldUtils.getWorld().getWorldBorder();
        wb.setCenter(0.0, 0.0);
        wb.setSize((double) size, time);
        wb.setDamageAmount(2.0);
        wb.setDamageBuffer(5.0);
        wb.setWarningDistance(20);
        wb = TomoNova.getPlugin().worldUtils.getNether().getWorldBorder();
        wb.setCenter(0.0, 0.0);
        wb.setSize((double) size, time);
        wb.setDamageAmount(2.0);
        wb.setDamageBuffer(5.0);
        wb.setWarningDistance(20);
        wb = TomoNova.getPlugin().worldUtils.getEnd().getWorldBorder();
        wb.setCenter(0.0, 0.0);
        wb.setSize((double) size, time);
        wb.setDamageAmount(2.0);
        wb.setDamageBuffer(5.0);
        wb.setWarningDistance(20);
    }

    public int getFinalSize() {
        return this.finalSize;
    }

    public void setFinalSize(final int finalSize) {
        this.finalSize = finalSize;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(final int speed) {
        this.speed = speed;
    }

    public int getStartSize() {
        return this.startSize;
    }

    public void setStartSize(final int startSize) {
        this.startSize = startSize;
    }
}
