package umaru.tomonova.tomonova.core.game;

public enum GameStates {
    GEN,
    PREGEN,
    LOBBY,
    LOBBY_END,
    PREGAME,
    GAME,
    END;

    private static GameStates currentState;

    public static GameStates getCurrentState() { //Donne le GameState
        return GameStates.currentState;
    }

    public static void setCurrentState(final GameStates state) { //Fixe le GameState
        GameStates.currentState = state;
    }

    public static boolean isState(final GameStates state) {  //Teste le GameState
        return GameStates.currentState == state;
    }
}
