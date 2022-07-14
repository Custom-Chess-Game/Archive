package me.smudge.client.items.chessboard;

import me.smudge.client.game.ChessBoard;
import me.smudge.client.game.Tile;
import me.smudge.client.items.Item;
import me.smudge.client.positions.ModularPosition;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the interface for the chessboard
 */
public abstract class ChessItem extends Item {

    /**
     * The chessboard panel
     */
    private JButton panel = new JButton();

    /**
     * Used to create a new chessboard interface
     * @param modularPosition The position of the chessboard
     */
    public ChessItem(ModularPosition modularPosition) {
        super(modularPosition);
    }

    /**
     * Creates the chessboard based on its position
     */
    private void create() {
        this.panel = new JButton();

        this.panel.setBounds(
                this.getItemRegion().getMin().getX(),
                this.getItemRegion().getMin().getY(),
                this.modularPosition.getWidth(), this.modularPosition.getHeight()
        );

        this.panel.setBackground(Color.white);
        this.getBoard().render(this.panel);

        // Adding a listener to all buttons
        for (Component component : this.panel.getComponents()) {
            if (!(component instanceof JButton button)) continue;
            button.addActionListener(event -> this.onClick(this.getBoard().getTileAt(this.getMouseLocation())));
        }
    }

    /**
     * Used to get the board
     */
    public abstract ChessBoard getBoard();

    /**
     * When the mouse is hovering over a tile
     */
    public abstract void onTileHover(Tile tile);

    /**
     * When the mouse clicks a tile
     */
    public abstract void onClick(Tile tile);

    /**
     * Item hover events
     */
    @Override public void onHover() {}
    @Override public void onNotHover() {}

    @Override
    public void updateItem() {
        if (this.getBoard() == null) return;
        this.onTileHover(this.getBoard().getTileAt(this.getMouseLocation()));
    }

    @Override
    public void render(JPanel frame) {
        this.create();
        frame.add(this.panel);
    }

    @Override
    public Component getComponent() {
        return this.panel;
    }
}
