package me.smudge.client.game.layout;

import me.smudge.client.game.ChessBoard;

/**
 * Represents a chess board layout
 */
public abstract class BoardLayout {

    /**
     * Used to update the board to the layout
     * @param board Instance of the chessboard
     */
    public abstract void update(ChessBoard board);

}
