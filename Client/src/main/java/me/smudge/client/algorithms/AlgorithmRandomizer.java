package me.smudge.client.algorithms;

import me.smudge.client.game.ChessBoard;
import me.smudge.client.game.ChessBoardTile;
import me.smudge.client.game.ChessColour;

import java.util.Random;

/**
 * Represents a random algorithm
 */
public class AlgorithmRandomizer extends Algorithm {

    /**
     * This scoring method simply compares the values
     * of the pieces for each player and works out the difference.
     * @param board Instance of the chess board
     * @param colour In context of this players colour
     */
    @Override
    public int score(ChessBoard board, ChessColour colour) {
        int white = 0;
        int black = 0;

        for (ChessBoardTile tile : board.getAllTiles()) {
            if (tile == null || tile.getPiece() == null) continue;
            if (tile.getPiece().getColour() == ChessColour.WHITE) white += tile.getPiece().getValue();
            if (tile.getPiece().getColour() == ChessColour.BLACK) black += tile.getPiece().getValue();
        }

        Random random = new Random();
        int value = random.nextInt(10);

        if (value == 9) return 1000;

        if (colour == ChessColour.WHITE) return white - black;
        if (colour == ChessColour.BLACK) return black - white;
        return 0;
    }
}