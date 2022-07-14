package me.smudge.client.items.chessboard.pieces;

import me.smudge.client.game.ChessBoard;
import me.smudge.client.game.ChessColour;
import me.smudge.client.game.Tile;
import me.smudge.client.positions.TilePosition;

import java.util.ArrayList;

public class unicorn extends Piece {

    /**
     * Create a new instance of {@link Piece}
     * Create a new instance of {@link unicorn}
     *
     * @param colour Colour of the chess piece
     */
    public unicorn(ChessColour colour) {
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
    public boolean canJump() {
        return true;
    }

    @Override
    public int getValue() {
        return 4;
    }

    @Override
    public ArrayList<Tile> getValidPositions(ChessBoard board, Tile tile) {
        ArrayList<Tile> tiles = new ArrayList<>();

        for (Tile temp : board.getAllTiles()) {
            TilePosition vector = new TilePosition(
                    tile.getTilePosition().getX() - temp.getTilePosition().getX(),
                    tile.getTilePosition().getY() - temp.getTilePosition().getY()
            );

            if (Math.abs(vector.getX()) > 3) continue;
            if (Math.abs(vector.getY()) > 3) continue;
            tiles.add(temp);
        }

        return tiles;
    }

    @Override
    public ArrayList<Tile> getTakePositions(ChessBoard board, Tile tile) {
        return this.getValidPositions(board, tile);
    }
}