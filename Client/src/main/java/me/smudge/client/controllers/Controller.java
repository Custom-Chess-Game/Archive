package me.smudge.client.controllers;

import me.smudge.client.items.chessboard.ChessColour;

/**
 * Represents one of the players in the game
 */
public abstract class Controller {

    private ChessColour colour;

    public Controller(ChessColour colour) {
        this.colour = colour;
    }

    public abstract boolean canClick();

    public ChessColour getColour() {
        return this.colour;
    }
}
