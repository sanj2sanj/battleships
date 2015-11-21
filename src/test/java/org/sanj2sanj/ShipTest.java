package org.sanj2sanj;

import org.junit.Test;
import org.sanj2sanj.battleships.model.ORIENTATION;
import org.sanj2sanj.battleships.model.Ship;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ShipTest extends Ship {

    @Test
    public void test_create_ship_at_valid_location() {
        String shipLocation = "(0, 0, N)";
        Ship ship = Ship.createShips(shipLocation).iterator().next();
        assertEquals(0, ship.getLocation().getLocationX());
        assertEquals(0, ship.getLocation().getLocationY());
        assertEquals(ORIENTATION.N, ship.getOrientation());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_create_ship_at_invalid_location() {
        Ship.createShips("(-1, 2, N)");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_create_ship_at_invalid_y_location() {
        Ship.createShips("(1, -2, N)");
    }

    @Test
    public void test_create_ship_at_invalid_orientation() {
        assertEquals(0, Ship.createShips("(1, 2, P)").size());
    }

    @Test
    public void test_ship_not_created() {
        assertTrue(Ship.createShips("!$!$!%£").isEmpty());
    }

    @Test
    public void test_ship_orientation() {
        Ship ship = Ship.createShips("(1, 2, N)").iterator().next();
        ship.rotateLeft();
        assertEquals(ORIENTATION.W, ship.getOrientation());
        ship.rotateRight();
        assertEquals(ORIENTATION.N, ship.getOrientation());
        ship.rotateRight();
        assertEquals(ORIENTATION.E, ship.getOrientation());
        ship.rotateRight();
        assertEquals(ORIENTATION.S, ship.getOrientation());
        ship.rotateRight();
        assertEquals(ORIENTATION.W, ship.getOrientation());
        ship.rotateLeft();
        assertEquals(ORIENTATION.S, ship.getOrientation());
    }

    @Test
    public void test_ship_movement() {
        Ship ship = Ship.createShips("(1, 2, N)").iterator().next();
        assertEquals(ORIENTATION.N, ship.getOrientation());

        ship.move();
        ship.move();

        assertEquals(1 + 2, ship.getLocation().getLocationX());

        ship.rotateRight();

        ship.move();
        ship.move();

        assertEquals(2 + 2, ship.getLocation().getLocationY());

    }


}
