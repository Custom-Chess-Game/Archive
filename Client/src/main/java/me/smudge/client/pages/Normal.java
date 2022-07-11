package me.smudge.client.pages;

import me.smudge.client.controllers.Bot;
import me.smudge.client.controllers.Player;
import me.smudge.client.items.ItemCollection;
import me.smudge.client.items.chessboard.ChessBoard;
import me.smudge.client.items.chessboard.ChessColour;
import me.smudge.client.items.chessboard.layout.DefaultBoardLayout;
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

        ChessBoard chessBoard = new ChessBoard(
                new ModularPosition(500, 500).setStatic(true).setPos1(
                        new Position(10, 10)
                ).setToFirstPosition(),

                new Player(ChessColour.WHITE),
                new Player(ChessColour.BLACK)
        );
        chessBoard.getBoard().setLayout(new DefaultBoardLayout());

        this.itemCollection.addItem(chessBoard);

        this.itemCollection.toPage(this);
    }

    @Override
    public void onRender() {
        this.itemCollection.calculate();
    }
}