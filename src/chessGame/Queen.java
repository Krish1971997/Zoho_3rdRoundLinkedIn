package chessGame;

class Queen extends Piece {
    public Queen(PieceColor color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Board board) {
        return new Rook(color, position).isValidMove(newPosition, board) ||
               new Bishop(color, position).isValidMove(newPosition, board);
    }
}
