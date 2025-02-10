package chessGame;

class Bishop extends Piece {
    public Bishop(PieceColor color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Board board) {
        return Math.abs(position.row - newPosition.row) == Math.abs(position.col - newPosition.col);
    }
}
