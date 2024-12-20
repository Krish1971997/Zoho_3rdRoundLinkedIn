package snakeLaderGame;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public static final int BOARD_SIZE = 100;
    private List<Jump> snakes;
    private List<Jump> ladders;

    public Board() {
        snakes = new ArrayList<>();
        ladders = new ArrayList<>();
        initializeSnakesAndLadders();
    }

    private void initializeSnakesAndLadders() {
        // Add some snakes
        snakes.add(new Jump(99, 78));
        snakes.add(new Jump(87, 36));
        snakes.add(new Jump(53, 13));
        snakes.add(new Jump(42, 18));
        snakes.add(new Jump(78, 45));
        snakes.add(new Jump(64, 28));
        snakes.add(new Jump(38, 19));

        // Add some ladders
        ladders.add(new Jump(3, 22));
        ladders.add(new Jump(8, 26));
        ladders.add(new Jump(20, 41));
        ladders.add(new Jump(13, 45));
        ladders.add(new Jump(25, 71));
        ladders.add(new Jump(33, 81));
        ladders.add(new Jump(23, 91));
        ladders.add(new Jump(47, 87));
        ladders.add(new Jump(29, 75));
    }

    public int getBoardSize() {
        return BOARD_SIZE;
    }

    public int getNewPositionAfterSnakeOrLadder(int position) {
        for (Jump snake : snakes) {
            if (snake.getStart() == position) {
                System.out.println("Bitten by a snake! Moving down to " + snake.getEnd());
                return snake.getEnd();
            }
        }
        for (Jump ladder : ladders) {
            if (ladder.getStart() == position) {
                System.out.println("Climbed a ladder! Moving up to " + ladder.getEnd());
                return ladder.getEnd();
            }
        }
        return position;
    }
}
