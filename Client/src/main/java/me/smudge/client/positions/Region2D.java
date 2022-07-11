package me.smudge.client.positions;

/**
 * Represents a 2D region
 */
public class Region2D {

    /**
     * Region positions
     */
    public Position pos1;
    public Position pos2;

    /**
     * Create a new instance of a region
     * @param pos1 The first position
     * @param pos2 The second position
     */
    public Region2D(Position pos1, Position pos2) {
        this.pos1 = pos1;
        this.pos2 = pos2;
    }

    /**
     * @return The maximum point
     */
    public Position getMax() {
        return new Position(
                Math.max(this.pos1.getX(), this.pos2.getX()),
                Math.max(this.pos1.getY(), this.pos2.getY())
        );
    }

    /**
     * @return The minimum point
     */
    public Position getMin() {
        return new Position(
                Math.min(this.pos1.getX(), this.pos2.getX()),
                Math.min(this.pos1.getY(), this.pos2.getY())
        );
    }

    /**
     * Check if a location is within the region
     * @param position Position to check
     * @return True if within region
     */
    public boolean contains(Position position) {
        return  position.getX() <= this.getMax().getX() &&
                position.getX() >= this.getMin().getX() &&
                position.getY() <= this.getMax().getY() &&
                position.getY() >= this.getMin().getY();
    }
}
