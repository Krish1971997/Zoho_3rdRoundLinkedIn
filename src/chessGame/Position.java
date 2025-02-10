package chessGame;

class Position {
    int row, col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean isWithinBounds() {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }
}
