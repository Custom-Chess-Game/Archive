package me.smudge.client.algorithms;

import me.smudge.client.game.ChessBoard;
import me.smudge.client.game.ChessColour;
import me.smudge.client.game.ChessMove;
import me.smudge.client.game.Tile;
import me.smudge.client.items.chessboard.pieces.unicorn;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class Algorithm {

    public int i = 0;

    public abstract int score(ChessBoard board, ChessColour colour);

    public ChessMove getMove(ChessBoard board, ChessColour scoring) {
        HashMap<Integer, ChessMove> moves = new HashMap<>();

        for (ChessMove move : board.getPossibleMoveForColour(scoring)) {

            ChessBoard temp = new ChessBoard(board);
            temp.makeMove(move);

            int score = this.calculate(3, temp, scoring, scoring, -1000, 1000);

            moves.put(score, move);
        }

        System.out.println("--- Algorithm ---");
        System.out.println("Scored Move : " + Collections.max(moves.entrySet(), Map.Entry.comparingByKey()).getKey());
        System.out.println("Trees Created : " + i);

        return Collections.max(moves.entrySet(), Map.Entry.comparingByKey()).getValue();
    }

    private int calculate(int depth, ChessBoard board, ChessColour turn, ChessColour scoring, int alfa, int beta) {
        if (depth == 0) {
            i++;
            return this.score(board, scoring);
        }

        int max = -1000;

        for (ChessMove move : board.getPossibleMoveForColour(turn)) {

            ChessBoard temp = new ChessBoard(board);
            temp.makeMove(move);

            int value = this.calculate(depth - 1, temp, ChessColour.opposite(turn), scoring, alfa, beta);

            if (value > max) max = value;
            if (max > alfa) alfa = max;
            if (alfa >= beta) return beta;
        }

        return max;
    }

    private boolean test(ChessBoard board) {
        for (Tile tile : board.getAllPieces()) {
            if (tile.getPiece() instanceof unicorn && tile.getPiece().getColour() == ChessColour.BLACK) {
                return true;
            }
        }
        return false;
    }

}
