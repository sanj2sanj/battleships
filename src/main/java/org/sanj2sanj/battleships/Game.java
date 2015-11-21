package org.sanj2sanj.battleships;

import com.google.common.collect.Lists;
import org.sanj2sanj.battleships.action.MoveAction;
import org.sanj2sanj.battleships.action.ShootAction;
import org.sanj2sanj.battleships.exception.IllegalMoveException;
import org.sanj2sanj.battleships.exception.ShipOutOfBoundsException;
import org.sanj2sanj.battleships.model.Board;
import org.sanj2sanj.battleships.model.Ship;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

public class Game {

    private Board board;

    /**
     * Creates a game with the specified board-size and shipLocations
     *
     * @param boardSize     - the size of the board
     * @param shipLocations - initial ship locations in the format (0, 0, N)
     * @throws a {@link ShipOutOfBoundsException} if you attempt to place a
     *           ship outside of the bounds of the board
     **/
    public static Game create(int boardSize, String shipLocations) throws ShipOutOfBoundsException {
        return new Game(boardSize, shipLocations);
    }

    private Game(int boardSize, String shipLocations) throws ShipOutOfBoundsException {
        board = Board.create(boardSize, Ship.createShips(shipLocations));
    }

    @Override
    public String toString() {
        return board.toString();
    }

    public void toFile(String fileName) throws IOException {
        ArrayList<String> ships = Lists.newArrayList();
        board.getShips().forEach(ship -> ships.add(ship.toString()));
        Files.write(Paths.get(fileName), ships, StandardCharsets.UTF_8);
    }

    public int getNumberOfShips() {
        return board.numberOfShips();
    }

    public long getNumberOfShipsSunk() {
        return board.numberOfShipsSunk();
    }

    public void makeMove(String moveString) throws IllegalMoveException, ShipOutOfBoundsException {
        checkNotNull(moveString);
        if (moveString.length() == 6) {
            ShootAction.create(moveString).execute(board);
        } else if (moveString.length() > 6) {
            MoveAction.create(moveString).execute(board);
        }
    }

}
