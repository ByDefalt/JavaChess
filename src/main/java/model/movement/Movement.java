package model.movement;

import model.Chessboard;
import model.Move;
import model.Square;
import model.piece.Piece;

import java.util.List;

public interface Movement {

    public List<Move> getPossibleMoves(Piece piece, Chessboard chessboard);
}
