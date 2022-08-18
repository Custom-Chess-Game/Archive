package me.smudge.client.pages;

import me.smudge.client.controllers.Player;
import me.smudge.client.engine.Application;
import me.smudge.client.game.ChessColour;
import me.smudge.client.items.ItemCollection;
import me.smudge.client.items.button.Button;
import me.smudge.client.items.button.ButtonExecute;
import me.smudge.client.items.button.ButtonText;
import me.smudge.client.pages.offline.Normal;
import me.smudge.client.positions.ModularPosition;

public class Offline extends Page {

    /**
     * The menu item collection
     * Used to format the buttons
     */
    private final ItemCollection itemCollection;

    /**
     * New instance of the offline menu
     */
    public Offline() {
        this.itemCollection = new ItemCollection().setColumns(1);

        this.itemCollection.addItem(new Button(
                new ModularPosition(400, 100).setCentered(true),
                new ButtonText("Normal"),
                new ButtonExecute(() -> Application.setPage(new Normal(
                        new Player(ChessColour.WHITE),
                        new Player(ChessColour.BLACK)
                )))
        ));

        this.itemCollection.addItem(new Button(
                new ModularPosition(400, 100).setCentered(true),
                new ButtonText("Custom")
        ));

        this.itemCollection.toPage(this);
    }

    @Override
    public void onRender() {
        this.itemCollection.calculate();
    }
}
