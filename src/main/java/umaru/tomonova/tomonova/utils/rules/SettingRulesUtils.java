package umaru.tomonova.tomonova.utils.rules;

import org.bukkit.Difficulty;
import org.bukkit.GameRule;
import org.bukkit.World;
import umaru.tomonova.tomonova.core.TomoNova;

public class SettingRulesUtils {
    public static void setGamerules() {
        World overworld = TomoNova.getPlugin().worldUtils.getWorld();
        World nether = TomoNova.getPlugin().worldUtils.getNether();
        World end = TomoNova.getPlugin().worldUtils.getEnd();

        overworld.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        overworld.setGameRule(GameRule.NATURAL_REGENERATION, false);
        nether.setGameRule(GameRule.NATURAL_REGENERATION, false);
        end.setGameRule(GameRule.NATURAL_REGENERATION, false);
        overworld.setDifficulty(Difficulty.HARD);
        overworld.setSpawnLocation(0, 202, 0, 0);
        overworld.setPVP(false);

    }
}
