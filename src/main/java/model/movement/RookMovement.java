package model.movement;

import model.Chessboard;
import model.Move;
import model.Square;
import model.piece.Piece;

import java.util.ArrayList;
import java.util.List;

public class RookMovement implements Movement {

    @Override
    public List<Move> getPossibleMoves(Piece piece, Chessboard chessboard) {
        List<Move> moves = new ArrayList<>();
        Square from = chessboard.getSquare(piece);
        if (from == null) return moves;

        char file = from.getName().charAt(0);
        int rank = Integer.parseInt(from.getName().substring(1));

        // 4 directions : droite, gauche, haut, bas
        int[][] directions = {
                {1,0}, {-1,0}, {0,1}, {0,-1}
        };

        for (int[] dir : directions) {
            int dx = dir[0], dy = dir[1];
            char targetFile = (char) file;
            int targetRank = rank;
            while (true) {
                targetFile += dx;
                targetRank += dy;
                String targetName = "" + targetFile + targetRank;
                Square to = chessboard.getSquare(targetName);
                if (to == null) break;

                Piece captured = to.getPiece();
                if (captured == null) {
                    moves.add(new Move(piece, from, to, null));
                } else {
                    if (!captured.getColor().equals(piece.getColor())) {
                        moves.add(new Move(piece, from, to, captured));
                    }
                    break; // bloque après capture
                }
            }
        }

        return moves;
    }
}
