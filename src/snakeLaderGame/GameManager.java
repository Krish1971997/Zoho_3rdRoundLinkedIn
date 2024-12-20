package snakeLaderGame;

import java.util.List;

public class GameManager {
    private static GameManager instance;
   // private List<SnakeAndLadderGame> games;

    private GameManager() {
     //   games = new ArrayList<>();
    }

    public static synchronized GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public void startNewGame(List<String> playerNames) {
        SnakeAndLadderGame game = new SnakeAndLadderGame(playerNames);
       // games.add(game);
        game.play();
    }
}
