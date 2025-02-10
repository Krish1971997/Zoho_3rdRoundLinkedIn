package chessGame;

class Rook extends Piece {
    public Rook(PieceColor color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Board board) {
        return position.row == newPosition.row || position.col == newPosition.col;
    }
}
