package org.sanj2sanj.battleships.model;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkNotNull;

/*
 * Represents a location on the board.
 */
public class BoardLocation {

    private static Logger LOG = Logger.getLogger(BoardLocation.class.getPackage().getName());

    private static Pattern REG_EX = Pattern.compile(".*?(\\d+).*?(\\d+)", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

    private int locationX;

    private int locationY;

    public int getLocationX() {
        return locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + locationX;
        result = prime * result + locationY;
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
        BoardLocation other = (BoardLocation) obj;
        if (locationX != other.locationX)
            return false;
        if (locationY != other.locationY)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "BoardLocation [locationX=" + locationX + ", locationY=" + locationY + "]";
    }

    public void move(ORIENTATION orientation) {
        switch (orientation) {
            case N:
                locationX++;
                break;
            case S:
                locationX--;
                break;
            case W:
                locationY--;
                break;
            case E:
                locationY++;
                break;
        }
    }

    /**
     * Parses a board location from a string formated as (9, 2)
     *
     * @param commandString
     * @return
     */
    public static BoardLocation from(String commandString) {
        checkNotNull(commandString);
        Matcher m = REG_EX.matcher(commandString);
        if (m.find()) {
            return BoardLocation.from(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)));
        } else {
            LOG.warning("Could parse " + commandString);
        }
        return null;
    }

    public static BoardLocation from(int x, int y) {
        BoardLocation boardLocation = new BoardLocation();
        boardLocation.locationX = x;
        boardLocation.locationY = y;
        return boardLocation;
    }

}
