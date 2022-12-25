package umaru.tomonova.tomonova.utils.cooldowns;

public class TimeUtils {

    public enum TimeUnit {
        BEST,
        DAYS,
        HOURS,
        MINUTES,
        SECONDS,
    }

    public static double convert(long time, TimeUnit unit, int decPoint) {
        if(unit == TimeUnit.BEST) {
            if(time < 60000L) unit = TimeUnit.SECONDS;
            else if(time < 3600000L) unit = TimeUnit.MINUTES;
            else if(time < 86400000L) unit = TimeUnit.HOURS;
            else unit = TimeUnit.DAYS;
        }
        if(unit == TimeUnit.SECONDS) return MathUtils.trim(time / 1000.0D, decPoint);
        if(unit == TimeUnit.MINUTES) return MathUtils.trim(time / 60000.0D, decPoint);
        if(unit == TimeUnit.HOURS) return MathUtils.trim(time / 3600000.0D, decPoint);
        if(unit == TimeUnit.DAYS) return MathUtils.trim(time / 86400000.0D, decPoint);
        return MathUtils.trim(time, decPoint);
    }



}
