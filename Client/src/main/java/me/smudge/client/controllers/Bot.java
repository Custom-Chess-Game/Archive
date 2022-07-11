package me.smudge.client.controllers;

import me.smudge.client.items.chessboard.Board;
import me.smudge.client.items.chessboard.ChessColour;

/**
 * Represents a computer player
 */
public class Bot extends Controller {
    public Bot(ChessColour colour) {
        super(colour);
    }

    @Override
    public boolean canClick() {
        return false;
    }

    @Override
    public void onMyTurn(Board board) {

    }
}
