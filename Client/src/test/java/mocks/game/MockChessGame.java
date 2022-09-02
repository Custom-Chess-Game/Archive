package mocks.game;

import me.smudge.client.algorithms.AlgorithmBasic;
import me.smudge.client.controllers.Bot;
import me.smudge.client.controllers.Player;
import me.smudge.client.game.ChessColour;
import me.smudge.client.game.ChessGame;
import me.smudge.client.game.layout.BoardLayoutDefault;
import me.smudge.client.positions.BoardSize;
import me.smudge.client.positions.ModularPosition;

/**
 * Used to get an example game board
 */
public class MockChessGame {

    /**
     * Used to get a mock chess game
     * @return Chess game instance
     */
    public static ChessGame get() {
        return new ChessGame(
                new ModularPosition(0, 0),
                new Player(ChessColour.WHITE),
                new Bot(ChessColour.BLACK, new AlgorithmBasic()),
                new BoardSize(8, 8),
                new BoardLayoutDefault()
        );
    }
}
