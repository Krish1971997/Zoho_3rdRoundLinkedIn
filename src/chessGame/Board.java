package chessGame;

class Board {
    private Piece[][] grid;

    public Board() {
        grid = new Piece[8][8];
        resetBoard();
    }

    public void resetBoard() {
        grid = new Piece[8][8];

        // Placing Pawns
        for (int i = 0; i < 8; i++) {
            grid[1][i] = new Pawn(PieceColor.BLACK, new Position(1, i));
            grid[6][i] = new Pawn(PieceColor.WHITE, new Position(6, i));
        }

        // Placing other pieces
        grid[0][0] = grid[0][7] = new Rook(PieceColor.BLACK, new Position(0, 0));
        grid[7][0] = grid[7][7] = new Rook(PieceColor.WHITE, new Position(7, 0));

        grid[0][1] = grid[0][6] = new Knight(PieceColor.BLACK, new Position(0, 1));
        grid[7][1] = grid[7][6] = new Knight(PieceColor.WHITE, new Position(7, 1));

        grid[0][2] = grid[0][5] = new Bishop(PieceColor.BLACK, new Position(0, 2));
        grid[7][2] = grid[7][5] = new Bishop(PieceColor.WHITE, new Position(7, 2));

        grid[0][3] = new Queen(PieceColor.BLACK, new Position(0, 3));
        grid[7][3] = new Queen(PieceColor.WHITE, new Position(7, 3));

        grid[0][4] = new King(PieceColor.BLACK, new Position(0, 4));
        grid[7][4] = new King(PieceColor.WHITE, new Position(7, 4));
    }

    public boolean movePiece(Position from, Position to) {
        if (!from.isWithinBounds() || !to.isWithinBounds()) return false;
        Piece piece = grid[from.row][from.col];
        if (piece == null || !piece.isValidMove(to, this)) return false;

        grid[to.row][to.col] = piece;
        grid[from.row][from.col] = null;
        piece.move(to);
        return true;
    }

    public boolean isOccupied(Position position) {
        return grid[position.row][position.col] != null;
    }
}
