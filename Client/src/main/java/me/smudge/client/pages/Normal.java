package me.smudge.client.pages;

import me.smudge.client.algorithms.AlgorithmBasic;
import me.smudge.client.controllers.Bot;
import me.smudge.client.controllers.Player;
import me.smudge.client.game.ChessColour;
import me.smudge.client.game.ChessGame;
import me.smudge.client.game.layout.BoardLayoutDefault;
import me.smudge.client.items.ItemCollection;
import me.smudge.client.positions.BoardSize;
import me.smudge.client.positions.ModularPosition;
import me.smudge.client.positions.Position;

public class Normal extends Page {

    /**
     * The menu item collection
     * Used to format the buttons
     */
    private final ItemCollection itemCollection;

    /**
     * New instance of the offline menu
     */
    public Normal() {
        this.itemCollection = new ItemCollection().setColumns(3);

        ChessGame chessBoard = new ChessGame(
                new ModularPosition(500, 500).setStatic(true).setPos1(
                        new Position(10, 10)
                ).setToFirstPosition(),

                new Player(ChessColour.WHITE),
                new Bot(ChessColour.BLACK, new AlgorithmBasic()),

                new BoardSize(8, 8),
                new BoardLayoutDefault()
        );

        this.itemCollection.addItem(chessBoard);

        this.itemCollection.toPage(this);
    }

    @Override
    public void onRender() {
        this.itemCollection.calculate();
    }
}