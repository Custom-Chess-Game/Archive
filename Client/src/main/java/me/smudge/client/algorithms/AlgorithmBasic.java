package me.smudge.client.algorithms;

import me.smudge.client.game.ChessBoard;
import me.smudge.client.game.ChessColour;
import me.smudge.client.game.Tile;

public class AlgorithmBasic extends Algorithm {

    @Override
    public int score(ChessBoard board, ChessColour colour) {
        int white = 0;
        int black = 0;

        for (Tile tile : board.getAllTiles()) {
            if (tile == null || tile.getPiece() == null) continue;
            if (tile.getPiece().getColour() == ChessColour.WHITE) white += tile.getPiece().getValue();
            if (tile.getPiece().getColour() == ChessColour.BLACK) black += tile.getPiece().getValue();
        }

        if (colour == ChessColour.WHITE) return white - black;
        if (colour == ChessColour.BLACK) return black - white;
        return 0;
    }
}
