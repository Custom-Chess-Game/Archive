package me.smudge.client.game.layout;

import me.smudge.client.game.ChessBoard;

/**
 * Represents a chess board layout
 */
public interface BoardLayout {

    /**
     * Used to update the board to the layout
     * @param board Instance of the chessboard
     */
    void update(ChessBoard board);
}
