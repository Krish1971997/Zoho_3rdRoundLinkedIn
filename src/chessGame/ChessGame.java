package chessGame;

class ChessGame {
    private Board board;
    private Player whitePlayer, blackPlayer;
    private boolean isWhiteTurn;

    public ChessGame(String whiteName, String blackName) {
        this.board = new Board();
        this.whitePlayer = new Player(whiteName, PieceColor.WHITE);
        this.blackPlayer = new Player(blackName, PieceColor.BLACK);
        this.isWhiteTurn = true;
    }

    public void play(Position from, Position to) {
        if (board.movePiece(from, to)) {
            isWhiteTurn = !isWhiteTurn;
        } else {
            System.out.println("Invalid Move!");
        }
    }
}
