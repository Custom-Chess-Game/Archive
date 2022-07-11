package me.smudge.client.items;

import me.smudge.client.engine.Application;
import me.smudge.client.positions.ModularPosition;
import me.smudge.client.positions.Position;
import me.smudge.client.positions.Region2D;

import javax.swing.*;
import java.awt.*;

/**
 * Represents a single item on the frame
 */
public abstract class Item {

    /**
     * Item position
     */
    protected Position pos1;
    protected Position pos2;

    protected ModularPosition modularPosition = null;

    /**
     * Used to create an item instance
     * @param pos1 First position of the item
     * @param pos2 Second position of the item
     */
    public Item(Position pos1, Position pos2) {
        this.pos1 = pos1;
        this.pos2 = pos2;
    }

    /**
     * Used to create an item instance
     * @param modularPosition The modular position
     */
    public Item(ModularPosition modularPosition) {
        this.pos1 = modularPosition.getPos1();
        this.pos2 = modularPosition.getPos2();
        this.modularPosition = modularPosition;
    }

    /**
     * Called when updating the item
     */
    public void update() {
        this.updateItem();
        if (this.getItemRegion().contains(getMouseLocation())) this.onHover();
        else this.onNotHover();
    }

    /**
     * Update events
     */
    public abstract void updateItem();
    public abstract void onHover();
    public abstract void onNotHover();

    /**
     * Called when rendering the item
     * @param frame The window to render
     */
    public abstract void render(JPanel frame);

    /**
     * Used to get the item region
     * @return The items Region2D
     */
    public Region2D getItemRegion() {
        if (this.modularPosition != null) {
            this.pos1 = modularPosition.getPos1();
            this.pos2 = modularPosition.getPos2();
        }
        return new Region2D(this.pos1, this.pos2);
    }

    /**
     * Get the location of the mouse as a Position
     * @return Mouse location
     */
    public Position getMouseLocation() {
        return new Position(
                MouseInfo.getPointerInfo().getLocation().x - Application.getLocation().getX(),
                MouseInfo.getPointerInfo().getLocation().y - Application.getLocation().getY()
        );
    }

    /**
     * Used to get the modular position
     */
    public ModularPosition getModularPosition() {
        return modularPosition;
    }

    /**
     * @return The item {@link Component}
     */
    public abstract Component getComponent();
}
