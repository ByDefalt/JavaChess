package model.movement;

import model.ChessGame;
import model.Chessboard;
import model.Move;
import model.Square;
import model.piece.Piece;

import java.util.ArrayList;
import java.util.List;

public class QueenMovement implements Movement {

    @Override
    public List<Move> getPossibleMoves(Piece piece, ChessGame chessGame) {
        List<Move> moves = new ArrayList<>();
        Square from = chessGame.getChessboard().getSquare(piece);
        if (from == null) return moves;

        char file = from.getName().charAt(0);
        int rank = Integer.parseInt(from.getName().substring(1));

        // 8 directions : tour + fou
        int[][] directions = {
                { 1, 0}, {-1, 0}, { 0, 1}, { 0,-1}, // horizontales / verticales
                { 1, 1}, { 1,-1}, {-1, 1}, {-1,-1} // diagonales
        };

        for (int[] dir : directions) {
            int dx = dir[0];
            int dy = dir[1];

            char targetFile = file;
            int targetRank = rank;

            while (true) {
                targetFile += dx;
                targetRank += dy;
                String targetName = "" + targetFile + targetRank;

                Square to = chessGame.getChessboard().getSquare(targetName);
                if (to == null) break; // hors échiquier

                Piece captured = to.getPiece();
                if (captured == null) {
                    moves.add(new Move(piece, from, to, null));
                } else {
                    if (!captured.getColor().equals(piece.getColor())) {
                        moves.add(new Move(piece, from, to, captured));
                    }
                    break; // bloqué après capture
                }
            }
        }

        return moves;
    }
}
