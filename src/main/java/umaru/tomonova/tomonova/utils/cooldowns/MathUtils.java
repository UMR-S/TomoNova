package umaru.tomonova.tomonova.utils.cooldowns;

import java.text.DecimalFormat;

public class MathUtils {
    public static double trim(double untrimmeded, int decimal) {
        String format = "#.#";

        for(int i = 1; i < decimal; i++) {
            format = format + "#";
        }
        DecimalFormat twoDec = new DecimalFormat(format);
        return Double.valueOf(twoDec.format(untrimmeded)).doubleValue();
    }
}
