package snakeLaderGame;

import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		GameManager gameManager = GameManager.getInstance();
		gameManager.startNewGame(Arrays.asList("Alice", "Bob","Bob1"));
	}
}
