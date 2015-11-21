package org.sanj2sanj;

import org.junit.Test;
import org.sanj2sanj.battleships.Game;
import org.sanj2sanj.battleships.exception.IllegalMoveException;
import org.sanj2sanj.battleships.exception.ShipOutOfBoundsException;

import static org.junit.Assert.assertEquals;

public class GameTest {

    @Test
    public void test_example_in_readme() throws NumberFormatException, ShipOutOfBoundsException, IllegalMoveException {
        String input[] = ("10\n" + "(0, 0, N) (9, 2, E)\n" + "(0, 0) MRMLMM\n" + "(9, 2)\n" + "").split("\n");
        String expectedOutput = "(3, 1, N) \n" + "(9, 2, E) SUNK\n";

        Game game = Game.create(Integer.parseInt(input[0]), input[1]);

        assertEquals(2, game.getNumberOfShips());
        assertEquals(0, game.getNumberOfShipsSunk());

        for (int i = 2; i < input.length; i++) {
            game.makeMove(input[i]);
        }
        assertEquals(2, game.getNumberOfShips());
        assertEquals(1, game.getNumberOfShipsSunk());
        assertEquals(expectedOutput, game.toString());
    }

    @Test
    public void test_missed_shot() throws NumberFormatException, ShipOutOfBoundsException, IllegalMoveException {
        String input[] = ("10\n" + "(0, 0, N) (9, 2, E)\n" + "(0, 0) MRMLMM\n" + "(9, 3)\n" + "").split("\n");
        String expectedOutput = "(3, 1, N) \n" + "(9, 2, E) \n";

        Game game = Game.create(Integer.parseInt(input[0]), input[1]);

        assertEquals(2, game.getNumberOfShips());
        assertEquals(0, game.getNumberOfShipsSunk());

        for (int i = 2; i < input.length; i++) {
            game.makeMove(input[i]);
        }
        assertEquals(2, game.getNumberOfShips());
        assertEquals(0, game.getNumberOfShipsSunk());
        assertEquals(expectedOutput, game.toString());
    }

    @Test(expected = ShipOutOfBoundsException.class)
    public void test_illegal_initial_state() throws NumberFormatException, ShipOutOfBoundsException, IllegalMoveException {
        String input[] = ("1\n" + "(0, 0, N) (9, 2, E)\n" + "(0, 0) MRMLMM\n" + "(9, 2)\n" + "").split("\n");
        Game.create(Integer.parseInt(input[0]), input[1]);
    }

    @Test(expected = IllegalMoveException.class)
    public void two_ships_cannot_occupy_the_same_cell_at_the_end_of_a_move_operation() throws NumberFormatException,
            ShipOutOfBoundsException, IllegalMoveException {
        String input[] = ("10\n" + "(0, 0, N) (1, 0, S)\n" + "(1, 0) M\n" + "").split("\n");
        playGame(input);
    }

    @Test(expected = ShipOutOfBoundsException.class)
    public void ship_cannot_move_beyond_the_bounds_of_the_board_bottom_left_pointing_west()
            throws NumberFormatException, ShipOutOfBoundsException, IllegalMoveException {
        bound_test("W", 0, 0);
    }

    @Test(expected = ShipOutOfBoundsException.class)
    public void ship_cannot_move_beyond_the_bounds_of_the_board_bottom_left_pointing_south()
            throws NumberFormatException, ShipOutOfBoundsException, IllegalMoveException {
        bound_test("S", 0, 0);
    }

    @Test(expected = ShipOutOfBoundsException.class)
    public void ship_cannot_move_beyond_the_bounds_of_the_board_bottom_right_pointing_south()
            throws NumberFormatException, ShipOutOfBoundsException, IllegalMoveException {
        bound_test("S", 0, 9);
    }

    @Test(expected = ShipOutOfBoundsException.class)
    public void ship_cannot_move_beyond_the_bounds_of_the_board_bottom_right_pointing_east()
            throws NumberFormatException, ShipOutOfBoundsException, IllegalMoveException {
        bound_test("E", 0, 9);
    }

    @Test(expected = ShipOutOfBoundsException.class)
    public void ship_cannot_move_beyond_the_bounds_of_the_board_top_right_pointing_east() throws NumberFormatException,
            ShipOutOfBoundsException, IllegalMoveException {
        bound_test("E", 9, 9);
    }

    @Test(expected = ShipOutOfBoundsException.class)
    public void ship_cannot_move_beyond_the_bounds_of_the_board_top_right_pointing_north()
            throws NumberFormatException, ShipOutOfBoundsException, IllegalMoveException {
        bound_test("N", 9, 9);
    }

    @Test(expected = ShipOutOfBoundsException.class)
    public void ship_cannot_move_beyond_the_bounds_of_the_board_top_left_pointing_north() throws NumberFormatException,
            ShipOutOfBoundsException, IllegalMoveException {
        bound_test("N", 9, 0);
    }

    @Test(expected = ShipOutOfBoundsException.class)
    public void ship_cannot_move_beyond_the_bounds_of_the_board_top_left_pointing_west() throws NumberFormatException,
            ShipOutOfBoundsException, IllegalMoveException {
        bound_test("W", 9, 0);
    }

    private void bound_test(String orientation, int x, int y) throws ShipOutOfBoundsException, IllegalMoveException {
        String input[] = ("10\n" + "(" + x + ", " + y + ", " + orientation + ")\n" + "(" + x + ", " + y + ") M\n" + "")
                .split("\n");
        playGame(input);
    }

    private void playGame(String[] input) throws ShipOutOfBoundsException, IllegalMoveException {
        Game game = Game.create(Integer.parseInt(input[0]), input[1]);
        for (int i = 2; i < input.length; i++) {
            game.makeMove(input[i]);
        }
    }

}
