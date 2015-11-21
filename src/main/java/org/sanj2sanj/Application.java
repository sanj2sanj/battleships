package org.sanj2sanj;

import org.sanj2sanj.battleships.Game;
import org.sanj2sanj.battleships.exception.IllegalMoveException;
import org.sanj2sanj.battleships.exception.ShipOutOfBoundsException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Application {

    private static final String input = "/input.txt";
    private static final String output = "output.txt";

    public void loadInput() throws ShipOutOfBoundsException, IllegalMoveException, IOException {
        InputStream is = getClass().getResourceAsStream(input);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        int lineNumber = 0;
        int boardSize = 0;
        String ships = null;

        Game game = null;
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                System.out.println(line);
                if (lineNumber == 1) {
                    boardSize = Integer.parseInt(line);
                } else if (lineNumber == 2) {
                    ships = line;
                } else if (lineNumber == 3) {
                    game = Game.create(boardSize, ships);
                    game.makeMove(line);
                } else {
                    game.makeMove(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        game.toFile(output);

    }

    public static void main(String... args) throws ShipOutOfBoundsException, IllegalMoveException, IOException {
        Application app = new Application();
        app.loadInput();
    }
}
