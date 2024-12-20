package snakeLaderGame;

public class Player {
    private String name;
    private int position;

    public Player(String name) {
        this.name = name;
        this.position = 1; // Start at position 1
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
