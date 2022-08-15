package me.smudge.client.game.pieces.standered;

import me.smudge.client.game.ChessBoard;
import me.smudge.client.game.ChessBoardTile;
import me.smudge.client.game.ChessColour;
import me.smudge.client.game.pieces.Piece;
import me.smudge.client.positions.TilePosition;

import java.util.ArrayList;

public class Rook extends Piece {

    /**
     * Create a new instance of {@link Piece}
     * Create a new instance of {@link Rook}
     * @param colour Colour of the chess piece
     */
    public Rook(ChessColour colour) {
        super(colour);
    }

    @Override
    public String getPathWhite() {
        return "src/main/resources/pieces/white_rook.png";
    }

    @Override
    public String getPathBlack() {
        return "src/main/resources/pieces/black_rook.png";
    }

    @Override
    public int getValue() {
        return 5;
    }

    @Override
    public ArrayList<ChessBoardTile> getValidPositions(ChessBoard board, ChessBoardTile tile) {
        ArrayList<ChessBoardTile> tiles = new ArrayList<>();
        TilePosition position = tile.getTilePosition();

        for (int index = 1; index < 9; index++) {
            tiles.add(board.getTile(position.addVector(index, 0, this.getColour())));
            tiles.add(board.getTile(position.addVector(-index, 0, this.getColour())));
            tiles.add(board.getTile(position.addVector(0, index, this.getColour())));
            tiles.add(board.getTile(position.addVector(0, -index, this.getColour())));
        }

        return tiles;
    }

    @Override
    public ArrayList<ChessBoardTile> getTakePositions(ChessBoard board, ChessBoardTile tile) {
        return this.getValidPositions(board, tile);
    }
}
