package me.smudge.client.game.pieces;

import me.smudge.client.Chess;
import me.smudge.client.game.ChessBoard;
import me.smudge.client.game.ChessBoardTile;
import me.smudge.client.game.ChessColour;

import javax.swing.*;
import java.awt.*;
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
     * The options tied to the piece
     */
    private PieceOptions options;

    /**
     * Create a new instance of {@link Piece}
     * @param colour Colour of the chess piece
     */
    public Piece(ChessColour colour) {
        this.colour = colour;
        this.options = new PieceOptions();
    }

    /**
     * Get image paths
     */
    public abstract String getPathWhite();
    public abstract String getPathBlack();

    /**
     * Used to get the value of the piece that an algorithm can use
     * to determine what is the best move to make
     * @return Value of the piece as an integer
     */
    public abstract int getValue();

    /**
     * Used to get the pieces options
     * @return Piece options
     */
    public PieceOptions getOptions() {
        return options;
    }

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

        // How to screen relates to its default size
        float multiplierX = (float) (Chess.getConfig().getScreenSize().get(0) / (double)1000);
        float multiplierY = (float) (Chess.getConfig().getScreenSize().get(1) / (double)600);
        float multiplier = Math.min(multiplierX, multiplierY);

        ImageIcon icon = new ImageIcon(path);
        ImageIcon scaled = new ImageIcon(icon.getImage().getScaledInstance((int) (60 * multiplier), (int) (60 * multiplier), Image.SCALE_SMOOTH));

        panel.setIcon(scaled);
        panel.setVisible(true);
    }

    /**
     * Used to get valid positions on the board
     * @return Valid tiles
     */
    public abstract ArrayList<ChessBoardTile> getValidPositions(ChessBoard board, ChessBoardTile tile);

    /**
     * Used to get the positions that the piece can take other pieces from
     * @return Valid tiles
     */
    public abstract ArrayList<ChessBoardTile> getTakePositions(ChessBoard board, ChessBoardTile tile);
}