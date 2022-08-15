package me.smudge.client.algorithms;

import me.smudge.client.engine.Console;
import me.smudge.client.engine.ConsoleColour;
import me.smudge.client.game.ChessBoard;
import me.smudge.client.game.ChessColour;
import me.smudge.client.game.ChessMove;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents an algorithm
 * A controller can use the algorithm to get the next move
 */
public abstract class Algorithm {

    /**
     * Counts how many instances were crated
     */
    private int instances = 0;

    /**
     * Used to calculate if the player is winning
     * by returning a value given the instance of the chess board
     * @param board Instance of the chess board
     * @param colour In context of this players colour
     * @return A value determining if they are winning
     * A positive value means they are winning
     * A negative value means the other player is winning
     */
    public abstract int score(ChessBoard board, ChessColour colour);

    /**
     * Used to get the best move given the instance of the board
     * @param board Instance of the board
     * @param scoring The player of which the move should benefit
     * @return The best move to make on the board
     */
    public ChessMove getMove(ChessBoard board, ChessColour scoring) {
        HashMap<Integer, ChessMove> moves = new HashMap<>();

        // Loop though the possible moves
        for (ChessMove move : board.getPossibleMoveForColour(scoring)) {

            // Make the move
            ChessBoard temp = new ChessBoard(board);
            temp.makeSilentMove(move);

            // Score the move
            int score = this.calculate(3, temp, scoring, scoring, -1000, 1000);

            // Add the move to a map
            moves.put(score, move);

            // Return if the score is already maxed
            if (score == 1000) break;
        }

        Map.Entry<Integer, ChessMove> entry = Collections.max(moves.entrySet(), Map.Entry.comparingByKey());

        Console.print(ConsoleColour.PINK + "--- Algorithm ---");
        Console.print("Scored Move : " + ConsoleColour.YELLOW + entry.getKey());
        Console.print("Instances Created : " + ConsoleColour.YELLOW + instances);

        // Return the move that scores the highest
        return entry.getValue();
    }

    /**
     * The main algorithm to calculate the tree
     * This algorithm is NegaMax
     * @param depth Maximum depth of the tree
     * @param board The instance of the board to score
     * @param turn Who's turn it currently is
     * @param scoring The player of which the move should benefit
     * @param alfa The minimum value
     * @param beta The maximum value
     * @return The score of the board
     */
    protected int calculate(int depth, ChessBoard board, ChessColour turn, ChessColour scoring, int alfa, int beta) {
        // When the maximum depth has been reached
        if (depth == 0) {
            instances++;
            return this.score(board, scoring);
        }

        int max = -1000;

        for (ChessMove move : board.getPossibleMoveForColour(turn)) {

            ChessBoard temp = new ChessBoard(board);
            temp.makeSilentMove(move);

            int value = this.calculate(depth - 1, temp, ChessColour.opposite(turn), scoring, alfa, beta);

            if (value > max) max = value;
            if (max > alfa) alfa = max;
            if (alfa >= beta) return beta;
        }

        return max;
    }
}
