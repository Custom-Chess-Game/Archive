package me.smudge.client.items.chessboard.pieces;

import me.smudge.client.items.chessboard.Board;
import me.smudge.client.items.chessboard.ChessColour;
import me.smudge.client.items.chessboard.Tile;
import me.smudge.client.positions.TilePosition;

import java.util.ArrayList;

public class Knight extends Piece {

    /**
     * Create a new instance of {@link Piece}
     * Create a new instance of {@link Knight}
     * @param colour Colour of the chess piece
     */
    public Knight(ChessColour colour) {
        super(colour);
    }

    @Override
    public String getPathWhite() {
        return "src/main/resources/pieces/white_knight.png";
    }

    @Override
    public String getPathBlack() {
        return "src/main/resources/pieces/black_knight.png";
    }

    @Override
    public boolean canJump() {
        return true;
    }

    @Override
    public ArrayList<Tile> getValidPositions(Board board, Tile tile) {
        ArrayList<Tile> tiles = new ArrayList<>();
        TilePosition position = tile.getTilePosition();

        tiles.add(board.getTile(position.addVector(1, 2, this.getColour())));
        tiles.add(board.getTile(position.addVector(1, -2, this.getColour())));
        tiles.add(board.getTile(position.addVector(-1, 2, this.getColour())));
        tiles.add(board.getTile(position.addVector(-1, -2, this.getColour())));

        tiles.add(board.getTile(position.addVector(2, 1, this.getColour())));
        tiles.add(board.getTile(position.addVector(2, -1, this.getColour())));
        tiles.add(board.getTile(position.addVector(-2, 1, this.getColour())));
        tiles.add(board.getTile(position.addVector(-2, -1, this.getColour())));

        return tiles;
    }
}
