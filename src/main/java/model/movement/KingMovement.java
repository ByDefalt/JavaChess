package model.movement;

import model.ChessGame;
import model.Chessboard;
import model.Move;
import model.Square;
import model.piece.Piece;

import java.util.ArrayList;
import java.util.List;

public class KingMovement implements Movement {

    @Override
    public List<Move> getPossibleMoves(Piece piece, ChessGame chessGame) {
        List<Move> moves = new ArrayList<>();
        Square from = chessGame.getChessboard().getSquare(piece);

        int[] dx = {-1, 0, 1};
        int[] dy = {-1, 0, 1};

        for (int x : dx) {
            for (int y : dy) {
                if (x == 0 && y == 0) continue; // pas rester sur place
                char file = (char)(from.getName().charAt(0) + x);
                int rank = Integer.parseInt(from.getName().substring(1)) + y;
                String targetName = "" + file + rank;
                Square to = chessGame.getChessboard().getSquare(targetName);

                if (to != null) { // case dans le plateau
                    Piece captured = to.getPiece();
                    if (captured == null || !captured.getColor().equals(piece.getColor())) {
                        moves.add(new Move(piece, from, to, captured));
                    }
                }
            }
        }

        return moves;
    }
}

