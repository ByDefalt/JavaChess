package model.movement;

import model.Chessboard;
import model.Move;
import model.Square;
import model.piece.Piece;
import model.color.Color;

import java.util.ArrayList;
import java.util.List;

public class PawnMovement implements Movement {

    @Override
    public List<Move> getPossibleMoves(Piece piece, Chessboard chessboard) {
        List<Move> moves = new ArrayList<>();
        Square from = chessboard.getSquare(piece);

        if (from == null) return moves; // sécurité

        char file = from.getName().charAt(0); // colonne a-h
        int rank = Integer.parseInt(from.getName().substring(1)); // ligne 1-8

        int forward = (piece.getColor() instanceof model.color.White) ? 1 : -1;

        // Avance simple
        int nextRank = rank + forward;
        String targetName = "" + file + nextRank;
        Square to = chessboard.getSquare(targetName);
        if (to != null && to.getPiece() == null) {
            moves.add(new Move(piece, from, to, null));

            // Double avance depuis la ligne de départ
            boolean isStartRank = (piece.getColor() instanceof model.color.White && rank == 2)
                    || (piece.getColor() instanceof model.color.Black && rank == 7);
            if (isStartRank) {
                int doubleRank = rank + 2 * forward;
                String doubleTargetName = "" + file + doubleRank;
                Square doubleTo = chessboard.getSquare(doubleTargetName);
                if (doubleTo != null && doubleTo.getPiece() == null) {
                    moves.add(new Move(piece, from, doubleTo, null));
                }
            }
        }

        // Captures diagonales
        for (int side : new int[]{-1, 1}) {
            char captureFile = (char) (file + side);
            String captureName = "" + captureFile + nextRank;
            Square captureSquare = chessboard.getSquare(captureName);
            if (captureSquare != null) {
                Piece captured = captureSquare.getPiece();
                if (captured != null && !captured.getColor().equals(piece.getColor())) {
                    moves.add(new Move(piece, from, captureSquare, captured));
                }
            }
        }

        return moves;
    }
}
