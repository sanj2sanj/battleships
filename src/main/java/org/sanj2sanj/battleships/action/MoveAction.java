package org.sanj2sanj.battleships.action;

import org.sanj2sanj.battleships.exception.IllegalMoveException;
import org.sanj2sanj.battleships.exception.ShipOutOfBoundsException;
import org.sanj2sanj.battleships.model.Board;
import org.sanj2sanj.battleships.model.BoardLocation;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoveAction implements Action {

    private static Logger LOG = Logger.getLogger(ShootAction.class.getPackage().getName());

    private static String REG_EX = "(\\(.*\\)).*?((?:[a-z][a-z0-9_]*))";
    private static Pattern p = Pattern.compile(REG_EX, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

    private BoardLocation shipLocation;

    private String moves;

    @Override
    public void execute(Board board) throws IllegalMoveException, ShipOutOfBoundsException {
        board.moveShip(shipLocation, moves);
    }

    public static MoveAction create(String commandString) {
        Matcher m = p.matcher(commandString);
        MoveAction moveCommand = new MoveAction();
        if (m.find()) {
            moveCommand.shipLocation = BoardLocation.from(m.group(1));
            moveCommand.moves = m.group(2);
        } else {
            LOG.warning("Could parse " + commandString);
        }
        return moveCommand;
    }


}