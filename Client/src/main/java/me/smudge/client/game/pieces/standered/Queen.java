package me.smudge.client.game.pieces.standered;

import me.smudge.client.game.ChessBoard;
import me.smudge.client.game.ChessBoardTile;
import me.smudge.client.game.ChessColour;
import me.smudge.client.game.pieces.Piece;
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
    public int getValue() {
        return 9;
    }

    @Override
    public ArrayList<ChessBoardTile> getValidPositions(ChessBoard board, ChessBoardTile tile) {
        ArrayList<ChessBoardTile> tiles = new ArrayList<>();
        TilePosition position = tile.getTilePosition();

        ChessBoardTile bishop = new ChessBoardTile(tile.tileColour, new TilePosition(position.getX(), position.getY()));
        bishop.setPiece(new Bishop(this.getColour()));
        tiles.addAll(bishop.getPiece().getValidPositions(board, bishop));

        ChessBoardTile rook = new ChessBoardTile(tile.tileColour, new TilePosition(position.getX(), position.getY()));
        rook.setPiece(new Rook(this.getColour()));
        tiles.addAll(rook.getPiece().getValidPositions(board, bishop));

        return tiles;
    }

    @Override
    public ArrayList<ChessBoardTile> getTakePositions(ChessBoard board, ChessBoardTile tile) {
        return this.getValidPositions(board, tile);
    }
}
