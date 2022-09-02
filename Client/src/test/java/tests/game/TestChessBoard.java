package tests.game;

import me.smudge.client.game.ChessBoard;
import me.smudge.client.game.ChessBoardTile;
import me.smudge.client.game.ChessColour;
import me.smudge.client.positions.TilePosition;
import mocks.game.MockChessBoard;
import org.junit.jupiter.api.Test;
import utilitys.ResultChecker;
import utilitys.ResultNotNull;

/**
 * Used to test the chess board
 */
public class TestChessBoard {

    @Test
    public void testGetTile() throws Exception {
        ChessBoard chessBoard = MockChessBoard.get();

        TilePosition tilePosition = new TilePosition(0, 0);
        chessBoard.addTile(new ChessBoardTile(ChessColour.WHITE, tilePosition));

        new ResultChecker().expect(chessBoard.getTile(tilePosition), new ResultNotNull());
    }
}
