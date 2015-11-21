package org.sanj2sanj.battleships.action;

import org.sanj2sanj.battleships.model.Board;
import org.sanj2sanj.battleships.model.BoardLocation;
import org.sanj2sanj.battleships.model.Ship;

import java.util.Optional;
import java.util.logging.Logger;

public class ShootAction implements Action {

    private static Logger LOG = Logger.getLogger(ShootAction.class.getPackage().getName());

    private BoardLocation location;

    public static ShootAction create(String commandString) {
        ShootAction shootCommand = new ShootAction();
        shootCommand.location = BoardLocation.from(commandString);
        return shootCommand;
    }

    @Override
    public void execute(Board board) {
        Optional<Ship> targetShip = board.findShip(location);
        if (targetShip.isPresent()) {
            targetShip.get().setStatus(Ship.STATUS.SUNK);
        } else {
            LOG.info("Missed target- No ship found at " + location);
        }
    }

}