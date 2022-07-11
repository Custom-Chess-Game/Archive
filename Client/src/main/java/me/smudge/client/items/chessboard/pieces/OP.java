package me.smudge.client.items.chessboard.pieces;

import me.smudge.client.items.chessboard.Board;
import me.smudge.client.items.chessboard.ChessColour;
import me.smudge.client.items.chessboard.Tile;

import java.util.ArrayList;

public class OP extends Piece {
    /**
     * Create a new instance of {@link Piece}
     *
     * @param colour Colour of the chess piece
     */
    public OP(ChessColour colour) {
        super(colour);
    }

    @Override
    public String getPathWhite() {
        return "src/main/resources/pieces/white_king.png";
    }

    @Override
    public String getPathBlack() {
        return "src/main/resources/pieces/white_king.png";
    }

    @Override
    public boolean canJump() {
        return true;
    }

    @Override
    public ArrayList<Tile> getValidPositions(Board board, Tile tile) {
        return board.getAllTiles();
    }
}
