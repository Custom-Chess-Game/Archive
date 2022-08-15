package me.smudge.client.pages;

import me.smudge.client.engine.Application;
import me.smudge.client.game.ChessColour;
import me.smudge.client.game.ChessMoveLog;
import me.smudge.client.items.ItemCollection;
import me.smudge.client.items.button.Button;
import me.smudge.client.items.button.ButtonExecute;
import me.smudge.client.items.button.ButtonText;
import me.smudge.client.items.text.Text;
import me.smudge.client.positions.ModularPosition;

/**
 * Represents the menu when a game ends
 */
public class GameEnd extends Page {

    /**
     * The menu item collection
     * Used to format the buttons
     */
    private final ItemCollection itemCollection;

    /**
     * New instance of the game end menu
     * @param colour The colour of the winning player
     *               This can be null if it's a tie
     * @param log The log of the moves in the game
     */
    public GameEnd(ChessColour colour, ChessMoveLog log) {
        this.itemCollection = new ItemCollection().setColumns(2);

        this.itemCollection.addItem(new Text(
                new ModularPosition(200, 50),
                colour.name() + " Won!"
        ));

        this.itemCollection.addItem(new Button(
                new ModularPosition(200, 50),
                new ButtonText("Exit to main menu"),
                new ButtonExecute(() -> Application.setPage(new MainMenu()))
        ));

        this.itemCollection.addItem(new Text(
                new ModularPosition(200, 50),
                log.asString()
        ));

        this.itemCollection.toPage(this);
    }

    @Override
    public void onRender() {
        this.itemCollection.calculate();
    }
}