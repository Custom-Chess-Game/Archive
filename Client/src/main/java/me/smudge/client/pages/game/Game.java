package me.smudge.client.pages.game;

import me.smudge.client.game.ChessGame;
import me.smudge.client.items.ItemCollection;
import me.smudge.client.pages.Page;

/**
 * Represents the menu where the game is played
 */
public class Game extends Page {

    /**
     * The menu item collection
     * Used to format the buttons
     */
    private final ItemCollection itemCollection;

    /**
     * New instance of the offline menu
     * @param chessGame Instance of the game
     */
    public Game(ChessGame chessGame) {
        this.itemCollection = new ItemCollection().setColumns(3);

        this.itemCollection.addItem(chessGame);

        this.itemCollection.toPage(this);
    }

    @Override
    public void onRender() {
        this.itemCollection.calculate();
    }
}