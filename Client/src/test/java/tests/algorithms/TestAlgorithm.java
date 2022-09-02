package tests.algorithms;

import me.smudge.client.algorithms.Algorithm;
import me.smudge.client.game.ChessBoard;
import me.smudge.client.game.ChessColour;
import me.smudge.client.game.ChessMove;
import mocks.game.MockChessBoard;
import org.junit.jupiter.api.Test;
import utilitys.ResultChecker;
import utilitys.ResultNotNull;

/**
 * Used to test the algorithm class
 */
public class TestAlgorithm {

    @Test
    public void testGetMove() throws Exception {
        ChessBoard chessboard = MockChessBoard.get();

        Algorithm algorithm = new Algorithm() {
            @Override
            public int score(ChessBoard board, ChessColour colour) {
                return 0;
            }
        };

        ChessMove chessMove = algorithm.getMove(chessboard, ChessColour.WHITE);

        new ResultChecker().expect(chessMove.getPiece(), new ResultNotNull());
    }
}
