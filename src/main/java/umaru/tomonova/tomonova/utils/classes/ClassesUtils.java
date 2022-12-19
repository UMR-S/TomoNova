package umaru.tomonova.tomonova.utils.classes;

import java.util.HashMap;

public class ClassesUtils {
    private HashMap<String, String> playersClasses;

    public ClassesUtils() {
        this.playersClasses = new HashMap<String, String>();
    }

    public void addPlayerToClassesMap(String playerName, String classe) {
        this.playersClasses.put(playerName, classe);
    }

    public boolean isPlayerClasse(String playerName, String classe) {
        if (this.playersClasses.keySet().contains(playerName)) {
            if (playersClasses.get(playerName).equals(classe)) {
                return true;
            }
        }
        return false;
    }
}
