package umaru.tomonova.tomonova.gamemode.bleachUHC.classes.cooldowns;

import java.util.HashMap;

public class AbilityCooldown {
    public String ability = "";
    public String playerName = "";
    public long seconds;
    public long systime;
    public HashMap<String, AbilityCooldown> cooldownMap = new HashMap<String, AbilityCooldown>();
    public AbilityCooldown(String ability, long seconds, long systime) {
        this.ability = ability;
        this.seconds = seconds;
        this.systime = systime;
    }

    public AbilityCooldown(String playerName) {
        this.playerName = playerName;
    }
}
