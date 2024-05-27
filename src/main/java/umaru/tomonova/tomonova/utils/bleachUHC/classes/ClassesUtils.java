package umaru.tomonova.tomonova.utils.bleachUHC.classes;

import org.bukkit.entity.Player;
import umaru.tomonova.tomonova.utils.constants.BleachUHCConstants;

import java.util.HashMap;

public class ClassesUtils {
    private HashMap<String, String> playersClasses;
    private HashMap<String, Integer> brazoHakudaUpgrade;

    public ClassesUtils() {
        playersClasses = new HashMap<String, String>();
        brazoHakudaUpgrade = new HashMap<String,Integer>();
    }

    public void addPlayerToClassesMap(String playerName, String classe) {
        playersClasses.put(playerName, classe);
        brazoHakudaUpgrade.remove(playerName);
        if(classe.equals(BleachUHCConstants.BRAZO)){
            brazoHakudaUpgrade.put(playerName, 1);
        }
    }
    public int getPlayerHakudaUpgrade(String playerName){
        if(brazoHakudaUpgrade.containsKey(playerName)){
            return brazoHakudaUpgrade.get(playerName);
        }
        return 1;
    }
    public void playerHakudaUpgrade(String playerName){
        if(brazoHakudaUpgrade.containsKey(playerName)){
            brazoHakudaUpgrade.put(playerName,2);
        }
    }

    public String getPlayerClasse(String playerName) {
        if (playersClasses.containsKey(playerName)) {
            return playersClasses.get(playerName);
        }
        return "None";
    }
    public boolean isPlayerShinigami(String playerName) {
        return BleachUHCConstants.SHINIGAMI.equals(getPlayerClasse(playerName));
    }

    public boolean isPlayerQuincy(String playerName) {
        return BleachUHCConstants.QUINCY.equals(getPlayerClasse(playerName));
    }

    public boolean isPlayerSSR(String playerName) {
        return BleachUHCConstants.SHUN_SHUN_RIKA.equals(getPlayerClasse(playerName));
    }

    public boolean isPlayerBrazo(String playerName) {
        return BleachUHCConstants.BRAZO.equals(getPlayerClasse(playerName));
    }
}
