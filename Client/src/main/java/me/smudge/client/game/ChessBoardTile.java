package me.smudge.client.game;

import me.smudge.client.game.pieces.Piece;
import me.smudge.client.positions.Region2D;
import me.smudge.client.positions.TilePosition;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Represents a chess tile and the piece on the tile
 */
public class ChessBoardTile {

    /**
     * The colour of the tile
     */
    public ChessColour tileColour;

    /**
     * The position of the tile in relation to other tiles
     */
    private final TilePosition tilePosition;

    /**
     * The exact region on the chess board panel
     */
    private Region2D region;

    /**
     * The piece placed on the tile
     */
    private Piece piece;

    /**
     * The tiles panel
     */
    private JButton button;

    /**
     * Used to create a new instance of the tile
     * @param tileColour The colour of the tile
     * @param position The position of the tile on the board
     */
    public ChessBoardTile(ChessColour tileColour, TilePosition position) {
        this.tileColour = tileColour;
        this.tilePosition = position;
    }

    /**
     * Used to clone a tile
     * @param tile Tile to clone
     */
    public ChessBoardTile(ChessBoardTile tile) {
        this.tileColour = tile.tileColour;
        this.tilePosition = tile.tilePosition;
        this.piece = tile.piece;
    }

    /**
     * Used to change the piece on the tile
     * @param piece Piece to change too
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * Used to get the piece
     */
    public Piece getPiece() {
        return this.piece;
    }

    /**
     * Remove the piece from the tile
     */
    public void setEmpty() {
        this.piece = null;
    }

    /**
     * Get the position of the {@link ChessBoardTile} on the {@link ChessBoard}
     */
    public TilePosition getTilePosition() {
        return this.tilePosition;
    }

    /**
     * Used to set the colour of the tile
     * @param color The colour to set the tile
     */
    public void setTileColour(Color color) {
        this.button.setBackground(color);
    }

    /**
     * Used to get the exact tile region
     */
    public Region2D getRegion() {
        return region;
    }

    /**
     * Render the tile and piece
     * @param button The tile button
     */
    public void render(JButton button) {
        this.button = button;

        if (tileColour == ChessColour.BLACK) button.setBackground(Color.gray);
        if (tileColour == ChessColour.WHITE) button.setBackground(Color.WHITE);

        button.setOpaque(true);
        button.setBorder(new LineBorder(Color.BLACK, 2));

        // Render the piece
        if (this.piece != null) this.piece.render(button);
    }

    public void setRegion(Region2D region) {
        this.region = region;
    }
}
