package umaru.tomonova.tomonova.utils.classes;

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
        if(brazoHakudaUpgrade.containsKey(playerName)){
            brazoHakudaUpgrade.remove(playerName);
        }
        if(classe.equals("brazo")){
            brazoHakudaUpgrade.put(playerName, 1);
        }
    }
    public boolean isPlayerClasse(String playerName, String classe) {
        if (playersClasses.keySet().contains(playerName)) {
            if (playersClasses.get(playerName).equals(classe)) {
                return true;
            }
        }
        return false;
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
}
