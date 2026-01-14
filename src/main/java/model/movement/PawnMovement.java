package model.movement;

import model.ChessGame;
import model.Chessboard;
import model.Move;
import model.Square;
import model.piece.Piece;
import model.color.White;
import model.color.Black;

import java.util.ArrayList;
import java.util.List;

public class PawnMovement implements Movement {

    @Override
    public List<Move> getPossibleMoves(Piece piece, ChessGame chessGame) {
        List<Move> moves = new ArrayList<>();
        Square from = chessGame.getChessboard().getSquare(piece);
        if (from == null) return moves;

        char file = from.getName().charAt(0);
        int rank = Integer.parseInt(from.getName().substring(1));
        int forward = (piece.getColor() instanceof White) ? 1 : -1;

        /* =======================
           Avance simple
         ======================= */
        int nextRank = rank + forward;
        Square to = chessGame.getChessboard().getSquare("" + file + nextRank);
        if (to != null && to.getPiece() == null) {
            moves.add(new Move(piece, from, to, null));

            // Double avance
            boolean isStartRank =
                    (piece.getColor() instanceof White && rank == 2) ||
                            (piece.getColor() instanceof Black && rank == 7);

            if (isStartRank) {
                Square doubleTo = chessGame.getChessboard().getSquare("" + file + (rank + 2 * forward));
                if (doubleTo != null && doubleTo.getPiece() == null) {
                    moves.add(new Move(piece, from, doubleTo, null));
                }
            }
        }

        /* =======================
           Captures normales
         ======================= */
        for (int side : new int[]{-1, 1}) {
            char captureFile = (char) (file + side);
            Square captureSquare =
                    chessGame.getChessboard().getSquare("" + captureFile + nextRank);

            if (captureSquare != null) {
                Piece captured = captureSquare.getPiece();
                if (captured != null && !captured.getColor().equals(piece.getColor())) {
                    moves.add(new Move(piece, from, captureSquare, captured));
                }
            }
        }

        /* =======================
           Prise en passant
         ======================= */
        Move lastMove = chessGame.getLastMove();
        if (lastMove != null && lastMove.getPiece() != null) {

            Piece lastPiece = lastMove.getPiece();

            // Le dernier coup doit être un pion
            if (lastPiece.getMovement() instanceof PawnMovement) {

                Square lastFrom = lastMove.getFrom();
                Square lastTo = lastMove.getTo();

                int lastFromRank = Integer.parseInt(lastFrom.getName().substring(1));
                int lastToRank = Integer.parseInt(lastTo.getName().substring(1));

                // Double pas
                if (Math.abs(lastFromRank - lastToRank) == 2) {

                    char lastFile = lastTo.getName().charAt(0);
                    int lastRank = lastToRank;

                    // Le pion adverse est juste à côté
                    if (lastRank == rank && Math.abs(lastFile - file) == 1) {

                        // Case de capture en passant
                        String enPassantTarget =
                                "" + lastFile + (rank + forward);
                        Square enPassantSquare =
                                chessGame.getChessboard().getSquare(enPassantTarget);

                        if (enPassantSquare != null && enPassantSquare.getPiece() == null) {
                            moves.add(new Move(piece, from, enPassantSquare, lastPiece));
                        }
                    }
                }
            }
        }

        return moves;
    }
}
