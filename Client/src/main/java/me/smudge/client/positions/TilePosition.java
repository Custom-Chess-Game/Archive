package me.smudge.client.positions;

import me.smudge.client.game.ChessColour;

/**
 * Represents the position of the tile
 * This is a separate class to avoid confusion
 * as the x and y don't represent the same scale.
 */
public class TilePosition extends Position {

    /**
     * Create a new instance of a tile position as {@link Position}
     * @param x The x index
     * @param y The y index
     */
    public TilePosition(int x, int y) {
        super(x, y);
    }

    @Override
    public TilePosition add(int x, int y) {
        return new TilePosition(
                this.x + x,
                this.y + y
        );
    }

    /**
     * Used to add a vector to the position
     * @param x The amount to increase x
     * @param y The amount to increase y
     * @param colour The colour of the piece
     */
    public TilePosition addVector(int x, int y, ChessColour colour) {
        if (colour == ChessColour.WHITE) return this.add(x, y);
        if (colour == ChessColour.BLACK) return this.add(-x, -y);
        return null;
    }

    /**
     * Used to check if the position isn't (0, 0)
     */
    public boolean not0() {
        return this.x != 0 || this.y != 0;
    }

    /**
     * Used to decrease the position to (0, 0)
     * On each call of the method, both x and y will decrease
     */
    public TilePosition decrease() {
        if (this.x != 0) {
            if (this.x > 0) this.x -= 1;
            if (this.x < 0) this.x += 1;
        }
        if (this.y != 0) {
            if (this.y > 0) this.y -= 1;
            if (this.y < 0) this.y += 1;
        }
        return this;
    }
}
