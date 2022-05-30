package umaru.tomonova.tomonova.core.task;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;
import umaru.tomonova.tomonova.core.TomoNova;
import umaru.tomonova.tomonova.core.game.GameStates;
import umaru.tomonova.tomonova.utils.lobby.LobbyUtils;

public class TaskFinalCountdown extends BukkitRunnable {
    private static int preStartTime = 10; //En sec
    private ChatColor color = ChatColor.GREEN;
    TomoNova tomoNova;

    public TaskFinalCountdown(TomoNova tomoNova) {
        this.tomoNova = tomoNova;
    }

    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(p -> p.sendTitle(color + Integer.toString(preStartTime), "", 1, 18, 1));
        Bukkit.getOnlinePlayers().forEach(p -> p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f, 1.0f));
        if (preStartTime <= 6) {
            color = ChatColor.YELLOW;
        }
        if (preStartTime <= 3) {
            color = ChatColor.RED;
        }
        if (preStartTime == 0) {
            TomoNova.getPlugin().gameManager.deletePreGameLobby();
            LobbyUtils.deleteLobby();
            GameStates.setCurrentState(GameStates.GAME);
            Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
            scoreboard.getObjectivesByCriteria(Criterias.HEALTH).forEach(o -> o.unregister());
            Objective health = Bukkit.getScoreboardManager().getMainScoreboard().registerNewObjective("health", Criterias.HEALTH, "");
            health.setRenderType(RenderType.HEARTS);
            health.setDisplaySlot(DisplaySlot.PLAYER_LIST);
            this.cancel();
        }
        preStartTime--;
    }

    public static void setPreStartTime(int preStartTime) {
        TaskFinalCountdown.preStartTime = preStartTime;
    }
}
