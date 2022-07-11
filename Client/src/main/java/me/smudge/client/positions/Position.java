package me.smudge.client.positions;

/**
 * Represents a simple position
 */
public class Position {

    /**
     * The bare positions
     */
    protected int x;
    protected int y;

    /**
     * Used to create a new empty position
     */
    public Position() {}

    /**
     * Used to create a new position
     * @param x The exact x
     * @param y The exact y
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return The x value
     */
    public int getX() {
        return this.x;
    }

    /**
     * @return The y value
     */
    public int getY() {
        return this.y;
    }

    /**
     * Used to compare positions
     * @param position Position to compare
     * @return True if the x and y are the same
     */
    public boolean contains(Position position) {
        if (this.x != position.x) return false;
        if (this.y != position.y) return false;
        return true;
    }

    /**
     * Used to add values to the position
     * @param x Amount to add to x
     * @param y Amount to add to y
     * @return Return this instance
     */
    public Position add(int x, int y) {
        return new Position(
                this.x + x,
                this.y + y
        );
    }

    /**
     * Get position as string
     * @return Formatted string
     */
    public String asString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
