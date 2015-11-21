package org.sanj2sanj.battleships.action;

import org.sanj2sanj.battleships.exception.IllegalMoveException;
import org.sanj2sanj.battleships.exception.ShipOutOfBoundsException;
import org.sanj2sanj.battleships.model.Board;

public interface Action {
    /**
     * Executes the action the board
     *
     * @param board
     * @throws IllegalMoveException     - if the action causes two ships to occupy the same board location
     * @throws ShipOutOfBoundsException - if the action takes a ship out of the bounds of the board
     */
    void execute(Board board) throws IllegalMoveException, ShipOutOfBoundsException;
}
