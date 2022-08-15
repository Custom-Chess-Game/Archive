package me.smudge.client.game;

import java.awt.*;

/**
 * Represents the colour of the tile or piece
 */
public enum ChessColour {
    WHITE, BLACK;

    /**
     * Get the opposite colour
     */
    public static ChessColour opposite(ChessColour colour) {
        if (colour == ChessColour.BLACK) return ChessColour.WHITE;
        return ChessColour.BLACK;
    }

    /**
     * @return chess colour as a colour
     */
    public Color asColour() {
        if (this.equals(ChessColour.BLACK)) return Color.GRAY;
        if (this.equals(ChessColour.WHITE)) return Color.WHITE;
        return null;
    }
}
