package model;

import java.util.ArrayList;
import java.util.List;

public class ChessGame extends Game {

    private Chessboard chessboard;
    private List<Move> moves;

    public ChessGame() {
        this.chessboard = new Chessboard();
        this.moves = new ArrayList<>();
        this.init();
    }

    public Move getLastMove(){
        if (moves.isEmpty()) {
            return null;
        }else {
            return moves.getLast();
        }
    }

    public Chessboard getChessboard() {
        return chessboard;
    }

    public void setChessboard(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    @Override
    public void init() {

    }

    @Override
    public void play() {
    }

}
