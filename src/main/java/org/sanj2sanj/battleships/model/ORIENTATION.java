package org.sanj2sanj.battleships.model;

/**
 * A ships orientation
 *
 * @author sanj@email.com
 */
public enum ORIENTATION {

    N(1), E(2), S(3), W(4);

    int orientationIndex;

    ORIENTATION(int index) {
        orientationIndex = index;
    }

    public ORIENTATION rotateLeft() {
        if (this == ORIENTATION.N) {
            return ORIENTATION.W;
        }
        return ORIENTATION.valueOf(this.orientationIndex - 1);
    }

    public ORIENTATION rotateRight() {
        if (this == ORIENTATION.W) {
            return ORIENTATION.N;
        }
        return ORIENTATION.valueOf(this.orientationIndex + 1);
    }

    private static ORIENTATION valueOf(int i) {
        if (i == 1) {
            return ORIENTATION.N;
        } else if (i == 2) {
            return ORIENTATION.E;
        } else if (i == 3) {
            return ORIENTATION.S;
        } else if (i == 4) {
            return ORIENTATION.W;
        }
        return null;
    }
}