package me.smudge.client.items.chessboard;

import me.smudge.client.controllers.Controller;
import me.smudge.client.engine.Application;
import me.smudge.client.positions.ModularPosition;

import java.awt.*;
import java.util.ArrayList;

/**
 * Represents the game instance
 */
public class ChessBoard extends ChessBoardInterface {

    /**
     * The board instance
     */
    private final Board board;

    /**
     * The players
     */
    private Controller player1;
    private Controller player2;

    /**
     * Who's turn it is
     */
    private ChessColour turn;

    /**
     * When a player clicks a tile it is stored here
     */
    private Tile store;
    private Tile hoverStore;

    /**
     * The tile the player is hovering over
     */
    private Tile tileHovering;

    /**
     * Used to create a new instance of the chessboard
     * @param modularPosition The position of the board on the screen
     */
    public ChessBoard(ModularPosition modularPosition, Controller player1, Controller player2) {
        super(modularPosition);

        this.player1 = player1;
        this.player2 = player2;

        this.turn = ChessColour.WHITE;

        this.board = new Board(8, 8);
        this.board.create();
    }

    @Override
    public Board getBoard() {
        return this.board;
    }

    @Override
    public void onTileHover(Tile tile) {

        // If a piece is selected
        if (this.store != null && this.store.getPiece() != null) {

            // If it's a new piece remove old color
            if (this.store != this.hoverStore) {
                if (this.hoverStore != null) this.resetTiles(this.tileHovering);
                this.hoverStore = this.store;
            }

            // color new pieces tiles
            this.colourTiles(this.store);
            return;
        }

        // If the hovered tile has changed
        if (this.tileHovering != null && tile != this.tileHovering) {
            this.resetTiles(this.tileHovering);
        }

        // Set colour for available places to move
        if (tile != null && tile.getPiece() != null) {
            this.colourTiles(tile);
        }

        this.tileHovering = tile;
    }

    public void resetTiles(Tile tile) {
        ArrayList<Tile> tiles = this.board.getPossibleMoves(tile);
        if (tiles == null) return;
        for (Tile temp : tiles) {
            if (temp == null) continue;
            temp.setTileColour(temp.tileColour.asColour());
        }
    }

    private void colourTiles(Tile tile) {
        ArrayList<Tile> tiles = this.board.getPossibleMoves(tile);
        if (tiles != null) {
            for (Tile temp : tiles) {
                if (temp == null) continue;
                if (tile.getPiece().canJump()) temp.setTileColour(Color.yellow);
                else temp.setTileColour(Color.green);
            }
        }
    }

    @Override
    public void onClick(Tile tile) {
        Controller controller = this.getWhoseTurn();
        if (controller.canClick()) this.playerHasClicked(tile);
    }

    private void playerHasClicked(Tile tile) {
        if (this.board.getPossibleMoves(this.store).contains(tile)) {
            tile.setPiece(this.store.getPiece());
            this.store.setEmpty();
            this.switchTurn();
        }

        if (!(this.board.isPieceAt(tile.getTilePosition()) && tile.getPiece().getColour() != this.turn)) {
            this.store = tile;
        }
    }

    private Controller getWhoseTurn() {
        if (this.turn == this.player1.getColour()) return this.player1;
        return this.player2;
    }

    private void switchTurn() {
        Application.render();
        this.board.flip();
        this.store = null;
        this.turn = ChessColour.opposite(this.turn);
    }
}
