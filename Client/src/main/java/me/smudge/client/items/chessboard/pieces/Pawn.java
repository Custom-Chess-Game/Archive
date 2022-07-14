package me.smudge.client.items.chessboard.pieces;

import me.smudge.client.game.ChessBoard;
import me.smudge.client.game.ChessColour;
import me.smudge.client.game.Tile;
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
    public int getValue() {
        return 1;
    }

    @Override
    public ArrayList<Tile> getValidPositions(ChessBoard board, Tile tile) {
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

    @Override
    public ArrayList<Tile> getTakePositions(ChessBoard board, Tile tile) {
        ArrayList<Tile> tiles = new ArrayList<>();
        TilePosition position = tile.getTilePosition();

        tiles.add(board.getTile(position.addVector(1, 1, this.getColour())));
        tiles.add(board.getTile(position.addVector(-1, 1, this.getColour())));

        return tiles;
    }
}
