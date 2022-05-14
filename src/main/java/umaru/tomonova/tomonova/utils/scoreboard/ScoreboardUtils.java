package umaru.tomonova.tomonova.utils.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class ScoreboardUtils {
    static ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
    static Scoreboard scoreboard = scoreboardManager.getNewScoreboard();


    public static ScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }

    public static Scoreboard getScoreboard() {
        return scoreboard;
    }
}
