package model.movement;

import model.ChessGame;
import model.Chessboard;
import model.Move;
import model.Square;
import model.piece.Piece;

import java.util.ArrayList;
import java.util.List;

public class KnightMovement implements Movement {

    @Override
    public List<Move> getPossibleMoves(Piece piece, ChessGame chessGame) {
        List<Move> moves = new ArrayList<>();
        Square from = chessGame.getChessboard().getSquare(piece);
        if (from == null) return moves;

        char file = from.getName().charAt(0);
        int rank = Integer.parseInt(from.getName().substring(1));

        // Déplacements en L du cavalier
        int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
        int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};

        for (int i = 0; i < dx.length; i++) {
            char targetFile = (char) (file + dx[i]);
            int targetRank = rank + dy[i];
            String targetName = "" + targetFile + targetRank;

            Square to = chessGame.getChessboard().getSquare(targetName);
            if (to == null) continue;

            Piece captured = to.getPiece();
            if (captured == null || !captured.getColor().equals(piece.getColor())) {
                moves.add(new Move(piece, from, to, captured));
            }
        }

        return moves;
    }
}
