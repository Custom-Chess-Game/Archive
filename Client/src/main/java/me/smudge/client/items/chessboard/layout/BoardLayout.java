package me.smudge.client.items.chessboard.layout;

import me.smudge.client.items.chessboard.Board;

/**
 * Represents a chess board layout
 */
public abstract class BoardLayout {

    /**
     * Used to update the board to the layout
     * @param board Instance of the chessboard
     */
    public abstract void update(Board board);

}
