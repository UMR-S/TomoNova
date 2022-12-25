package umaru.tomonova.tomonova.gamemode.bleachUHC.classes.cooldowns;


import umaru.tomonova.tomonova.utils.cooldowns.MathUtils;
import umaru.tomonova.tomonova.utils.cooldowns.TimeUtils;

public class Convert {
    public static double convert(long time, TimeUtils.TimeUnit unit, int decPoint) {
        if(unit == TimeUtils.TimeUnit.BEST) {
            if(time < 60000L) unit = TimeUtils.TimeUnit.SECONDS;
            else if(time < 3600000L) unit = TimeUtils.TimeUnit.MINUTES;
            else if(time < 86400000L) unit = TimeUtils.TimeUnit.HOURS;
            else unit = TimeUtils.TimeUnit.DAYS;
        }
        if(unit == TimeUtils.TimeUnit.SECONDS) return MathUtils.trim(time / 1000.0D, decPoint);
        if(unit == TimeUtils.TimeUnit.MINUTES) return MathUtils.trim(time / 60000.0D, decPoint);
        if(unit == TimeUtils.TimeUnit.HOURS) return MathUtils.trim(time / 3600000.0D, decPoint);
        if(unit == TimeUtils.TimeUnit.DAYS) return MathUtils.trim(time / 86400000.0D, decPoint);
        return MathUtils.trim(time, decPoint);
    }
}
