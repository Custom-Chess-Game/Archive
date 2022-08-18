package me.smudge.client.game.layout;

import me.smudge.client.game.ChessBoard;
import me.smudge.client.game.ChessColour;
import me.smudge.client.game.pieces.standered.*;
import me.smudge.client.positions.TilePosition;

/**
 * Represents the default chess board layout
 */
public class BoardLayoutDefault implements BoardLayout {

    @Override
    public void update(ChessBoard board) {

        // Black pieces
        board.getTile(new TilePosition(1, 8)).setPiece(new Rook(ChessColour.BLACK));
        board.getTile(new TilePosition(2, 8)).setPiece(new Knight(ChessColour.BLACK));
        board.getTile(new TilePosition(3, 8)).setPiece(new Bishop(ChessColour.BLACK));
        board.getTile(new TilePosition(4, 8)).setPiece(new King(ChessColour.BLACK));
        board.getTile(new TilePosition(5, 8)).setPiece(new Queen(ChessColour.BLACK));
        board.getTile(new TilePosition(6, 8)).setPiece(new Bishop(ChessColour.BLACK));
        board.getTile(new TilePosition(7, 8)).setPiece(new Knight(ChessColour.BLACK));
        board.getTile(new TilePosition(8, 8)).setPiece(new Rook(ChessColour.BLACK));

        board.getTile(new TilePosition(1, 7)).setPiece(new Pawn(ChessColour.BLACK));
        board.getTile(new TilePosition(2, 7)).setPiece(new Pawn(ChessColour.BLACK));
        board.getTile(new TilePosition(3, 7)).setPiece(new Pawn(ChessColour.BLACK));
        board.getTile(new TilePosition(4, 7)).setPiece(new Pawn(ChessColour.BLACK));
        board.getTile(new TilePosition(5, 7)).setPiece(new Pawn(ChessColour.BLACK));
        board.getTile(new TilePosition(6, 7)).setPiece(new Pawn(ChessColour.BLACK));
        board.getTile(new TilePosition(7, 7)).setPiece(new Pawn(ChessColour.BLACK));
        board.getTile(new TilePosition(8, 7)).setPiece(new Pawn(ChessColour.BLACK));

        // White piece
        board.getTile(new TilePosition(1, 1)).setPiece(new Rook(ChessColour.WHITE));
        board.getTile(new TilePosition(2, 1)).setPiece(new Knight(ChessColour.WHITE));
        board.getTile(new TilePosition(3, 1)).setPiece(new Bishop(ChessColour.WHITE));
        board.getTile(new TilePosition(4, 1)).setPiece(new King(ChessColour.WHITE));
        board.getTile(new TilePosition(5, 1)).setPiece(new Queen(ChessColour.WHITE));
        board.getTile(new TilePosition(6, 1)).setPiece(new Bishop(ChessColour.WHITE));
        board.getTile(new TilePosition(7, 1)).setPiece(new Knight(ChessColour.WHITE));
        board.getTile(new TilePosition(8, 1)).setPiece(new Rook(ChessColour.WHITE));

        board.getTile(new TilePosition(1, 2)).setPiece(new Pawn(ChessColour.WHITE));
        board.getTile(new TilePosition(2, 2)).setPiece(new Pawn(ChessColour.WHITE));
        board.getTile(new TilePosition(3, 2)).setPiece(new Pawn(ChessColour.WHITE));
        board.getTile(new TilePosition(4, 2)).setPiece(new Pawn(ChessColour.WHITE));
        board.getTile(new TilePosition(5, 2)).setPiece(new Pawn(ChessColour.WHITE));
        board.getTile(new TilePosition(6, 2)).setPiece(new Pawn(ChessColour.WHITE));
        board.getTile(new TilePosition(7, 2)).setPiece(new Pawn(ChessColour.WHITE));
        board.getTile(new TilePosition(8, 2)).setPiece(new Pawn(ChessColour.WHITE));
    }
}
