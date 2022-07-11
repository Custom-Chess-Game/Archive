package me.smudge.client.positions;

import me.smudge.client.Chess;

/**
 * Represents a modular position
 * Used to quickly and dynamically give an item a position
 */
public class ModularPosition {

    /**
     * Specific positions
     */
    private Position pos1;
    private Position pos2;

    /**
     * The width and height
     */
    private int width;
    private int height;

    // The true scale
    private final int fistWidth;
    private final int fistHeight;

    /**
     * Position options
     */
    private boolean isCentered = false;
    private boolean isStatic;

    /**
     * Create a new instance of a modular position
     * @param width The true width of the item
     * @param height The true height of the item
     */
    public ModularPosition(int width, int height) {
        this.pos1 = new Position(0, 0);
        this.pos2 = new Position(100, 100);

        this.width = width;
        this.height = height;

        this.fistWidth = width;
        this.fistHeight = height;
    }

    /**
     * Used to calculate where the position is if centered
     */
    public void calculate() {
        if (!this.isCentered) return;

        int centerX = (Chess.getConfig().getScreenSize().get(0) / 2) - (this.width / 2);
        int centerY = (Chess.getConfig().getScreenSize().get(1) / 2) - (this.height / 2);

        this.pos1 = new Position(centerX, centerY);
        this.setToFirstPosition();
    }

    /**
     * Used to set whether the item is centered
     * @param isCentered If the item is centered
     */
    public ModularPosition setCentered(boolean isCentered) {
        this.isCentered = isCentered;
        this.calculate();
        return this;
    }

    public ModularPosition setStatic(boolean isStatic) {
        this.isStatic = isStatic;
        return this;
    }

    /**
     * Calculate the second position based on the first and the scale
     */
    public ModularPosition setToFirstPosition() {
        this.pos2 = new Position(this.pos1.getX() + this.width, this.pos1.getY() + this.height);
        return this;
    }

    /**
     * @param pos1 Set the first position to this value
     */
    public ModularPosition setPos1(Position pos1) {
        this.pos1 = pos1;
        return this;
    }

    /**
     * Used to set the width
     */
    public ModularPosition setWidth(int width) {
        this.width = width;
        return this;
    }

    /**
     * Used to set the height
     */
    public ModularPosition setHeight(int height) {
        this.height = height;
        return this;
    }

    /**
     * Getters
     */
    public Position getPos1() {return this.pos1;}
    public Position getPos2() {return this.pos2;}

    public int getWidth() {return this.width;}
    public int getHeight() {return this.height;}

    public int getFirstWidth() {return this.fistWidth;}
    public int getFirstHeight() {return this.fistHeight;}

    public boolean isCentered() {return this.isCentered;}
    public boolean isStatic() {return this.isStatic;}
}
