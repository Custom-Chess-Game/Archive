package me.smudge.client.items.chessboard.pieces;

import me.smudge.client.items.chessboard.Board;
import me.smudge.client.items.chessboard.ChessColour;
import me.smudge.client.items.chessboard.Tile;
import me.smudge.client.positions.TilePosition;

import java.util.ArrayList;

public class Bishop extends Piece {

    /**
     * Create a new instance of {@link Piece}
     * Create a new instance of {@link Bishop}
     * @param colour Colour of the chess piece
     */
    public Bishop(ChessColour colour) {
        super(colour);
    }

    @Override
    public String getPathWhite() {
        return "src/main/resources/pieces/white_bishop.png";
    }

    @Override
    public String getPathBlack() {
        return "src/main/resources/pieces/black_bishop.png";
    }

    @Override
    public boolean canJump() {
        return false;
    }

    @Override
    public ArrayList<Tile> getValidPositions(Board board, Tile tile) {
        ArrayList<Tile> tiles = new ArrayList<>();
        TilePosition position = tile.getTilePosition();

        for (int index = 1; index < 9; index++) {
            tiles.add(board.getTile(position.addVector(index, index, this.getColour())));
            tiles.add(board.getTile(position.addVector(index, -index, this.getColour())));
            tiles.add(board.getTile(position.addVector(-index, index, this.getColour())));
            tiles.add(board.getTile(position.addVector(-index, -index, this.getColour())));
        }

        return tiles;
    }

    @Override
    public ArrayList<Tile> getTakePositions(Board board, Tile tile) {
        return this.getValidPositions(board, tile);
    }
}
