package snakeLaderGame;

import java.util.ArrayList;
import java.util.List;

public class SnakeAndLadderGame {
    private Board board;
    private List<Player> players;
    private Dice dice;
    private int currentPlayerIndex;

    public SnakeAndLadderGame(List<String> playerNames) {
        board = new Board();
        players = new ArrayList<>();
        dice = new Dice();
        currentPlayerIndex = 0;

        // Initialize players
        for (String name : playerNames) {
            players.add(new Player(name));
        }
    }

    public void play() {
        while (!isGameOver()) {
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println(currentPlayer.getName() + "'s turn.");
            int roll = dice.roll();
            System.out.println("Rolled a " + roll);
            int newPosition = currentPlayer.getPosition() + roll;
            newPosition = board.getNewPositionAfterSnakeOrLadder(newPosition);
            if (newPosition < board.getBoardSize()) {
                currentPlayer.setPosition(newPosition);
                System.out.println(currentPlayer.getName() + " moved to position " + newPosition);
            }
            if (newPosition == board.getBoardSize()) {
                System.out.println(currentPlayer.getName() + " wins!");
                break;
            }
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
    }

    public boolean isGameOver() {
        for (Player player : players) {
            if (player.getPosition() == board.getBoardSize()) {
                return true;
            }
        }
        return false;
    }
}
