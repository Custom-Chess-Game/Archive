package me.smudge.client.game;

import java.util.ArrayList;

/**
 * Represents the move log
 * Stores the moves that have happend
 */
public class ChessMoveLog {

    /**
     * The move log
     */
    private ArrayList<ChessMove> log = new ArrayList<>();

    /**
     * Used to create a new instance
     */
    public ChessMoveLog() {}

    /**
     * Used to clone an already existing log
     * @param log Log to clone
     */
    public ChessMoveLog(ChessMoveLog log) {
        this.log = new ArrayList<>(log.log);
    }

    /**
     * Used to add a move to the log
     * @param move Move to add to the log
     */
    public void add(ChessMove move) {
        this.log.add(move);
    }

    /**
     * Used to get the list of moves that have happened
     * @return Log as a string
     */
    public String asString() {
        StringBuilder builder = new StringBuilder();

        builder.append("[");

        for (ChessMove move : this.log) {
            builder.append(move.asString());
            builder.append(", ");
        }

        builder.append("]");

        return builder.toString();
    }
}
