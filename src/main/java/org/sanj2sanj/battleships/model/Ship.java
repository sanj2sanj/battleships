package org.sanj2sanj.battleships.model;

import com.google.common.collect.Sets;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ship {

    private static int idGenerator = 1;

    private static String REG_EX = ".*?([-+]?\\d+).*?([-+]?\\d+).*?((?:[NESW][NESW]*))";

    private static Pattern shipLocationPattern = Pattern.compile(REG_EX, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

    public static String TO_STRING_TEMPLATE = "(%s, %s, %s) %s";

    public enum STATUS {
        AFLOAT, SUNK
    }

    private int id;

    private STATUS status = STATUS.AFLOAT;

    private ORIENTATION orientation;

    private BoardLocation location;

    public BoardLocation getLocation() {
        return location;
    }

    public ORIENTATION getOrientation() {
        return orientation;
    }

    /**
     * Create a list of ships with the specified shipLocations
     *
     * @param shipLocations - initial ship locations in the format (0, 0, N) (2, 0, N)
     * @throws IllegalArgumentException if negative x/y co-ordinates are specified or an invalid
     *                                  orientation is specified
     **/
    public static Set<Ship> createShips(String shipLocationsString) {
        Set<Ship> ships = Sets.newHashSet();
        Matcher m = shipLocationPattern.matcher(shipLocationsString);
        while (m.find()) {
            Ship ship = new Ship();
            ship.id = idGenerator++;
            ship.location = BoardLocation.from(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)));
            ship.orientation = Enum.valueOf(ORIENTATION.class, m.group(3));
            if (ship.location.getLocationX() < 0) {
                throw new IllegalArgumentException("Invalid X co-ordinate for ship" + ship.location.getLocationX());
            }
            if (ship.location.getLocationY() < 0) {
                throw new IllegalArgumentException("Invalid Y co-ordinate for ship" + ship.location.getLocationY());
            }
            ships.add(ship);
        }
        return ships;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ship other = (Ship) obj;
        if (id != other.id)
            return false;
        return true;
    }

    public int getId() {
        return id;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public boolean isSunk() {
        return this.status == STATUS.SUNK;
    }

    public void move() {
        location.move(orientation);
    }

    public void rotateLeft() {
        orientation = orientation.rotateLeft();
    }

    public void rotateRight() {
        orientation = orientation.rotateRight();
    }

    @Override
    public String toString() {
        String statusString = (status == STATUS.SUNK) ? STATUS.SUNK.toString() : "";
        return String.format(TO_STRING_TEMPLATE, location.getLocationX(), location.getLocationY(), orientation,
                statusString);
    }


}
