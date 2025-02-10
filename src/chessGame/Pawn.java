package chessGame;

class Pawn extends Piece {
    public Pawn(PieceColor color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Board board) {
        int direction = (color == PieceColor.WHITE) ? -1 : 1;
        return (newPosition.row == position.row + direction) && (newPosition.col == position.col);
    }
}
