package me.smudge.client.items.chessboard;

import me.smudge.client.controllers.Controller;
import me.smudge.client.engine.Application;
import me.smudge.client.positions.BoardSize;
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
    private final Controller player1;
    private final Controller player2;

    /**
     * Who's turn it is
     */
    private ChessColour turn;

    /**
     * Stores for the previous tile
     * Used to disable and reset tiles that are no longer hovered or clicked
     */
    private Tile clickStore;
    private Tile hoverStore;
    private Tile clickHoverStore;

    /**
     * Used to create a new instance of the chessboard
     * @param modularPosition The position of the board on the screen
     * @param player1 The first player to play white
     * @param player2 The second player to play black
     * @param size The size of the board
     */
    public ChessBoard(ModularPosition modularPosition, Controller player1, Controller player2, BoardSize size) {
        super(modularPosition);

        this.player1 = player1;
        this.player2 = player2;
        this.turn = ChessColour.WHITE;

        this.board = new Board(size.getX(), size.getY());
        this.board.create();
    }

    @Override
    public Board getBoard() {
        return this.board;
    }

    @Override
    public void onTileHover(Tile tile) {

        // If a piece is selected
        if (this.clickStore != null && this.clickStore.getPiece() != null) {

            // If it's a new piece remove old color
            if (this.clickStore != this.clickHoverStore) {
                if (this.clickHoverStore != null) this.resetTiles(this.hoverStore);
                this.clickHoverStore = this.clickStore;
            }

            // color new pieces tiles
            this.colourTiles(this.clickStore);
            return;
        }

        // If the hovered tile has changed
        if (this.hoverStore != null && tile != this.hoverStore)
            this.resetTiles(this.hoverStore);

        // Set colour for available places to move
        if (tile != null && tile.getPiece() != null)
            this.colourTiles(tile);

        this.hoverStore = tile;
    }

    /**
     * Used to reset the tiles to its original colour
     * @param tile The pieces possible moves to reset
     */
    public void resetTiles(Tile tile) {
        ArrayList<Tile> tiles = this.board.getPossibleMoves(tile);
        if (tiles == null) return;
        for (Tile temp : tiles) {
            if (temp == null) continue;
            temp.setTileColour(temp.tileColour.asColour());
        }
    }

    /**
     * Used to colour the tiles based on if the piece can jump
     * @param tile The pieces possible moves to colour
     */
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

    /**
     * Called when a player has clicked a tile
     * @param tile Tile that has been clicked
     */
    private void playerHasClicked(Tile tile) {
        if (this.board.getPossibleMoves(this.clickStore).contains(tile)) {
            tile.setPiece(this.clickStore.getPiece());
            this.clickStore.setEmpty();
            this.switchTurn();
        }

        if (!(this.board.isPieceAt(tile.getTilePosition()) && tile.getPiece().getColour() != this.turn)) {
            this.clickStore = tile;
        }
    }

    /**
     * Used to get the player who's turn it is
     */
    private Controller getWhoseTurn() {
        if (this.turn == this.player1.getColour()) return this.player1;
        return this.player2;
    }

    /**
     * Used to switch to the next players turn
     */
    private void switchTurn() {
        // Flip the board
        this.board.flip();

        // Render the new board
        Application.render();

        // Reset what the player has clicked
        this.clickStore = null;

        // Change whose turn it is
        this.turn = ChessColour.opposite(this.turn);
        this.getWhoseTurn().onMyTurn(this.board);
    }
}
