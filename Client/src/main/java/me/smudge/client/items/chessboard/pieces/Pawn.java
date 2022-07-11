package me.smudge.client.items.chessboard.pieces;

import me.smudge.client.items.chessboard.Board;
import me.smudge.client.items.chessboard.ChessColour;
import me.smudge.client.items.chessboard.Tile;
import me.smudge.client.positions.TilePosition;

import java.util.ArrayList;

public class Pawn extends Piece {

    /**
     * Create a new instance of {@link Piece}
     * Create a new instance of {@link Pawn}
     * @param colour Colour of the chess piece
     */
    public Pawn(ChessColour colour) {
        super(colour);
    }

    @Override
    public String getPathWhite() {
        return "src/main/resources/pieces/white_pawn.png";
    }

    @Override
    public String getPathBlack() {
        return "src/main/resources/pieces/black_pawn.png";
    }

    @Override
    public boolean canJump() {
        return false;
    }

    @Override
    public ArrayList<Tile> getValidPositions(Board board, Tile tile) {
        ArrayList<Tile> tiles = new ArrayList<>();
        TilePosition position = tile.getTilePosition();

        tiles.add(board.getTile(position.addVector(0, 1, this.getColour())));

        // Check if the piece has not moved
        if (
           this.getColour() == ChessColour.WHITE && position.getY() == 2 ||
           this.getColour() == ChessColour.BLACK && position.getY() == 7
        ) {
            tiles.add(board.getTile(position.addVector(0, 2, this.getColour())));
        }

        return tiles;
    }
}
