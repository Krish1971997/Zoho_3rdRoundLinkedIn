package chessGame;

enum PieceColor {
    WHITE, BLACK
}

// Abstract class for all chess pieces
abstract class Piece {
    protected PieceColor color;
    protected Position position;

    public Piece(PieceColor color, Position position) {
        this.color = color;
        this.position = position;
    }

    public abstract boolean isValidMove(Position newPosition, Board board);

    public void move(Position newPosition) {
        this.position = newPosition;
    }

    public PieceColor getColor() {
        return color;
    }
}
