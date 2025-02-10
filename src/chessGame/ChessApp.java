package chessGame;

public class ChessApp {
    public static void main(String[] args) {
        ChessGame game = new ChessGame("Alice", "Bob");
        game.play(new Position(6, 4), new Position(4, 4)); // Move White Pawn
        game.play(new Position(1, 4), new Position(3, 4)); // Move Black Pawn
    }
}
