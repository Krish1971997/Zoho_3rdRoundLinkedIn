package chessGame;

class King extends Piece {
    public King(PieceColor color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Board board) {
        int dx = Math.abs(newPosition.row - position.row);
        int dy = Math.abs(newPosition.col - position.col);
        return dx <= 1 && dy <= 1;
    }
}
