package me.smudge.client.controllers;

import me.smudge.client.game.ChessBoard;
import me.smudge.client.game.ChessColour;

/**
 * Represents a real player
 */
public class Player extends Controller {
    public Player(ChessColour colour) {
        super(colour);
    }

    @Override
    public boolean canClick() {
        return true;
    }

    @Override public boolean onMyTurn(ChessBoard board) {
        return false;
    }
}
