package me.smudge.client.game;

import me.smudge.client.engine.Console;
import me.smudge.client.game.pieces.Piece;
import me.smudge.client.positions.TilePosition;

/**
 * Represents a chess move
 */
public class ChessMove {

    /**
     * Where the piece has moved to and from
     */
    private final ChessBoardTile from;
    private final ChessBoardTile to;

    /**
     * The piece that was moved
     */
    private final Piece piece;

    /**
     * Used to create a new chess move
     * @param from The tile the piece was before the move
     * @param to The tile the piece has moved to
     */
    public ChessMove(ChessBoardTile from, ChessBoardTile to, Piece piece) {
        this.from = from;
        this.to = to;
        this.piece = piece;
    }

    /**
     * @return Position where the piece moved from
     */
    public TilePosition getFrom() {
        return this.from.getTilePosition();
    }

    /**
     * @return Position where the piece moved to
     */
    public TilePosition getTo() {
        return this.to.getTilePosition();
    }

    /**
     * @return The piece moved
     */
    public Piece getPiece() {
        return this.piece;
    }

    /**
     * Get the move as a string
     * @return (X axis, Y axis as base 26)
     */
    public String asString() {
        return "(" + getTo().getX() + ", " + convertToLetters(getTo().getY()) + ")";
    }

    /**
     * Essential converting a number to base 26
     * @param number Number to convert
     * @return Converted number
     */
    private String convertToLetters(int number) {
        String[] letters = "abcdefghijklmnopqrstuvwxyz".split("");
        StringBuilder converted = new StringBuilder();

        while (number > 0) {
            converted.insert(0, letters[(number % letters.length) - 1]);
            number = number / letters.length;
        }

        return converted.toString();
    }

    /**
     * Used to debug and show the info contained in the chess move
     */
    public void asDebug() {
        Console.print("--- Debug ---");
        Console.print("From : " + this.from.getTilePosition().asString());
        Console.print("To : " + this.to.getTilePosition().asString());
    }
}
