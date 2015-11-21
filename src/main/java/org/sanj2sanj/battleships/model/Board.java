package org.sanj2sanj.battleships.model;

import com.google.common.collect.Sets;
import org.sanj2sanj.battleships.exception.IllegalMoveException;
import org.sanj2sanj.battleships.exception.ShipOutOfBoundsException;

import java.util.Optional;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Represents a board that contains the ships.
 *
 * @author sanj@email.com
 */
public class Board {

    private Set<Ship> ships;
    private int boardSize;

    public static Board create(int boardSize, Set<Ship> ships) throws ShipOutOfBoundsException {
        return new Board(boardSize, ships);
    }

    private Board(int boardSize, Set<Ship> ships) throws ShipOutOfBoundsException {
        checkArgument(boardSize > 0, "You cannot have a board with a negative size " + boardSize);
        this.boardSize = boardSize;
        for (Ship ship : ships) {
            if (!hasValidLocation(ship)) {
                throw new ShipOutOfBoundsException("Unable to place ship in location " + ship);
            }
        }
        this.ships = Sets.newHashSet(ships);
    }


    /**
     * Finds a ship for a given {@link BoardLocation}
     *
     * @param location
     * @return the ship wrapped in an {@link Optional} type
     */
    public Optional<Ship> findShip(BoardLocation location) {
        return ships.stream().filter(ship -> ship.getLocation().equals(location)).findFirst();
    }

    /**
     * For a the given ship, returns true if another {@link Ship.STATUS.AFLOAT}
     * ship occupies that location.
     *
     * @param location
     */
    private boolean isOccupied(Ship ship) {
        return ships.stream().findFirst()
                .filter(s -> (s.getId() != ship.getId() && s.getLocation().equals(ship.getLocation()) && !s.isSunk()))
                .isPresent();
    }

    public void moveShip(BoardLocation shipLocation, String moves) throws IllegalMoveException,
            ShipOutOfBoundsException {
        Optional<Ship> oShip = findShip(shipLocation);
        if (oShip.isPresent()) {
            Ship ship = oShip.get();
            char[] moveArray = moves.toCharArray();
            for (char move : moveArray) {
                switch (move) {
                    case 'M':
                        ship.move();
                        break;
                    case 'R':
                        ship.rotateRight();
                        break;
                    case 'L':
                        ship.rotateLeft();
                        break;
                }
                if (!hasValidLocation(ship)) {
                    throw new ShipOutOfBoundsException("Unable to place ship in location " + ship);
                }
            }
            if (isOccupied(ship)) {
                throw new IllegalMoveException("You cannot move a ship " + ship + " to a location that is already occupied");
            }
        }
    }

    private boolean hasValidLocation(Ship ship) {
        int locationX = ship.getLocation().getLocationX();
        int locationY = ship.getLocation().getLocationY();
        return (locationX >= 0) && (locationY >= 0) && (locationX < boardSize) && (locationY < boardSize);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Ship ship : ships) {
            sb.append(ship);
            sb.append("\n");
        }
        return sb.toString();
    }

    public int numberOfShips() {
        return ships.size();
    }

    public long numberOfShipsSunk() {
        return ships.stream().filter(p -> p.isSunk()).count();
    }

    public Set<Ship> getShips() {
        return ships;
    }


}
