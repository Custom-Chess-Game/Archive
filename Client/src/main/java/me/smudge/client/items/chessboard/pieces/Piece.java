package me.smudge.client.items.chessboard.pieces;

import me.smudge.client.items.chessboard.Board;
import me.smudge.client.items.chessboard.ChessColour;
import me.smudge.client.items.chessboard.Tile;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Represents a chess board piece
 */
public abstract class Piece {

    /**
     * The colour of the chess piece
     */
    private ChessColour colour;

    /**
     * Create a new instance of {@link Piece}
     * @param colour Colour of the chess piece
     */
    public Piece(ChessColour colour) {
        this.colour = colour;
    }

    /**
     * Get image paths
     */
    public abstract String getPathWhite();
    public abstract String getPathBlack();

    /**
     * Get if the piece can jump over other pieces
     */
    public abstract boolean canJump();

    /**
     * Get piece colour
     */
    public ChessColour getColour() {
        return colour;
    }

    /**
     * Used to render the piece to the tile panel
     * @param panel The panel of the tile
     */
    public void render(JButton panel) {
        String path = getPathBlack();
        if (this.colour == ChessColour.WHITE) path = getPathWhite();

        Icon icon = new ImageIcon(path);
        panel.setIcon(icon);
        panel.setVisible(true);
    }

    /**
     * Used to get valid positions on the board
     * @return Valid tiles
     */
    public abstract ArrayList<Tile> getValidPositions(Board board, Tile tile);

    /**
     * Used to get the positions that the piece can take other pieces from
     * @return Valid tiles
     */
    public abstract ArrayList<Tile> getTakePositions(Board board, Tile tile);
}