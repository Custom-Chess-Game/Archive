package me.smudge.client.controllers;

import me.smudge.client.game.ChessBoard;
import me.smudge.client.game.ChessColour;

/**
 * Represents one of the players in the game
 */
public abstract class Controller {

    /**
     * The colour the controller plays as
     */
    private final ChessColour colour;

    /**
     * Used to create a new instance of a controller
     * @param colour The colour the controller plays
     */
    public Controller(ChessColour colour) {
        this.colour = colour;
    }

    /**
     * @return If this controller is able to click the screen
     */
    public abstract boolean canClick();

    /**
     * Called when it's the turn of the controllers
     * @param board The instance of the chess board
     * @return If the turn has ended
     */
    public abstract boolean onMyTurn(ChessBoard board);

    /**
     * Used to get the controller type
     * @return Controller type
     */
    public abstract ControllerType getType();

    /**
     * Used to get the controllers colour that they play as
     */
    public ChessColour getColour() {
        return this.colour;
    }
}
