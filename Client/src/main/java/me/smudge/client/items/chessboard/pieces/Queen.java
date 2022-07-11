package me.smudge.client.items.chessboard.pieces;

import me.smudge.client.items.chessboard.Board;
import me.smudge.client.items.chessboard.ChessColour;
import me.smudge.client.items.chessboard.Tile;
import me.smudge.client.positions.TilePosition;

import java.util.ArrayList;

public class Queen extends Piece {

    /**
     * Create a new instance of {@link Piece}
     * Create a new instance of {@link Queen}
     * @param colour Colour of the chess piece
     */
    public Queen(ChessColour colour) {
        super(colour);
    }

    @Override
    public String getPathWhite() {
        return "src/main/resources/pieces/white_queen.png";
    }

    @Override
    public String getPathBlack() {
        return "src/main/resources/pieces/black_queen.png";
    }

    @Override
    public boolean canJump() {
        return false;
    }

    @Override
    public ArrayList<Tile> getValidPositions(Board board, Tile tile) {
        ArrayList<Tile> tiles = new ArrayList<>();
        TilePosition position = tile.getTilePosition();

        Tile bishop = new Tile(tile.tileColour, new TilePosition(position.getX(), position.getY()));
        bishop.setPiece(new Bishop(this.getColour()));
        tiles.addAll(bishop.getPiece().getValidPositions(board, bishop));

        Tile rook = new Tile(tile.tileColour, new TilePosition(position.getX(), position.getY()));
        rook.setPiece(new Rook(this.getColour()));
        tiles.addAll(rook.getPiece().getValidPositions(board, bishop));

        return tiles;
    }

    @Override
    public ArrayList<Tile> getTakePositions(Board board, Tile tile) {
        return this.getValidPositions(board, tile);
    }
}
