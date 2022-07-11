package me.smudge.client.controllers;

import me.smudge.client.items.chessboard.Board;
import me.smudge.client.items.chessboard.ChessColour;

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

    @Override public void onMyTurn(Board board) {}
}
