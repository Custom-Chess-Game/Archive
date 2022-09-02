package tests.game;

import me.smudge.client.controllers.Controller;
import me.smudge.client.game.ChessGame;
import me.smudge.client.positions.ModularPosition;
import mocks.game.MockChessGame;
import org.junit.jupiter.api.Test;
import utilitys.ResultChecker;
import utilitys.ResultInstanceOf;
import utilitys.ResultNotNull;

/**
 * Used to test the chess game class
 */
public class TestChessGame {

    @Test
    public void testGetBoard() throws Exception {
        ChessGame chessGame = MockChessGame.get();

        new ResultChecker().expect(chessGame.getBoard(), new ResultNotNull());
    }

    @Test
    public void testGetWhoseTurn() throws Exception {
        ChessGame chessGame = MockChessGame.get();

        new ResultChecker().expect(chessGame.getWhoseTurn(), new ResultInstanceOf(Controller.class));
    }
}
