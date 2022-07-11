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

        // If the hovered tile has changed
        if (this.tileHovering != null && tile != this.tileHovering) {
            ArrayList<Tile> tiles = this.board.getPossibleMoves(this.tileHovering);
            if (tiles != null) {
                for (Tile temp : tiles) {
                    if (temp == null) continue;
                    temp.setTileColour(temp.tileColour.asColour());
                }
            }
        }

        // Set colour for available places to move
        if (tile != null && tile.getPiece() != null) {
            ArrayList<Tile> tiles = this.board.getPossibleMoves(tile);
            if (tiles != null) {
                for (Tile temp : tiles) {
                    if (temp == null) continue;
                    if (tile.getPiece().canJump()) temp.setTileColour(Color.yellow);
                    else temp.setTileColour(Color.green);
                }
            }
        }

        this.tileHovering = tile;
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
            Application.render();
            this.board.flip();
            this.store = null;
        }
        else this.store = tile;
    }

    private Controller getWhoseTurn() {
        if (this.turn == this.player1.getColour()) return this.player1;
        return this.player2;
    }
}
