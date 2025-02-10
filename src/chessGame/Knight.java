package chessGame;

class Knight extends Piece {
    public Knight(PieceColor color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Board board) {
        int dx = Math.abs(newPosition.row - position.row);
        int dy = Math.abs(newPosition.col - position.col);
        return (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
    }
}
