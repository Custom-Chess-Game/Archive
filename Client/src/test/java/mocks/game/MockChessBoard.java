package mocks.game;

import me.smudge.client.game.ChessBoard;
import me.smudge.client.game.layout.BoardLayoutDefault;

/**
 * Used to get an example chess board
 */
public class MockChessBoard {

    /**
     * Used to get a mock chessboard
     * @return Chess board instance
     */
    public static ChessBoard get() {
        ChessBoard chessBoard = new ChessBoard(8, 8);

        chessBoard.setup();
        chessBoard.setLayout(new BoardLayoutDefault());

        return chessBoard;
    }
}
