# Battleships with Java8

A program that reads a file that represents

1. The initial state of a battleships board
2. Moves made during a game of battleship

### Input File Example 
<pre>
  10                      // Size of the board is 10x10
  (0, 0, N) (9, 2, E)     // 3 ships in different locations
  (0, 0) MRMLMM           // move/rotate the ship located at (0, 0)
  (9, 2)                  // shoot at (9, 2) and sink the ship if there is one
</pre>

Consider a square board of size N. Each cell can either be empty or be occupied by a ship.

The position of a ship is defined by three parameters: an x-coordinate, a y-coordinate and an orientation (north, east, south, west).

Once the initial state of the board is set up, any number of operations can occur. There are two types of operations, Move and Rotate

Once the program has complete the final state of the game is written to output.txt.

### Output File Example 
<pre>
Output:
  (3, 1, N)
  (9, 2, E) SUNK
</pre>


### Move a ship
Specified by an initial coordinate and a series of movements which can be move forward(M) (in the direction that ship is facing), 
rotate left(L), rotate right(R).

A ship can navigate through an occupied cell. However, two ships cannot occupy the same cell at the end of a move operation.

### Shoot down a ship
Specified by an x- and y-coordinate. If the cell is occupied, that ship is sunk and the cell can be occupied by another ship. 

If there is no ship, nothing happens.

### Edge cases
The program will skip moves that it cannot parse with a regular expression and will log a warning. 

If an invalid state is passed to setup the game, no move will be processed. 

For other exception cases after this initial setup (e.g. when two ships occupy the same cell) the program will stop processing moves.
  
