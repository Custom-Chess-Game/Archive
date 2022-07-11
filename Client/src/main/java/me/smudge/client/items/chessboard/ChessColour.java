package me.smudge.client.items.chessboard;

import java.awt.*;

/**
 * Represents the colour of the tile or piece
 */
public enum ChessColour {
    WHITE, BLACK;

    /**
     * Get the opposite colour
     */
    public static ChessColour opposite(ChessColour tileColour) {
        if (tileColour == ChessColour.BLACK) return ChessColour.WHITE;
        return ChessColour.BLACK;
    }

    public Color asColour() {
        if (this.equals(ChessColour.BLACK)) return Color.GRAY;
        if (this.equals(ChessColour.WHITE)) return Color.WHITE;
        return null;
    }
}
