package model;

import model.piece.Piece;

import java.util.Map;
import java.util.TreeMap;

public class Chessboard {
    Map<String, Square> squares;


    public Chessboard() {
        squares = new TreeMap<>((s1, s2) -> {
            int rank1 = Integer.parseInt(s1.substring(1));
            int rank2 = Integer.parseInt(s2.substring(1));
            char file1 = s1.charAt(0);
            char file2 = s2.charAt(0);

            // Comparer d'abord les rangs (ligne), puis fichiers (colonne)
            if (rank1 != rank2) return rank1 - rank2;
            return file1 - file2;
        });
    }

    public Square getSquare(String name) {
        return squares.get(name);
    }

    public Square getSquare(Piece piece) {
        for (Square square : squares.values()) {
            if (square.getPiece() != null && square.getPiece().equals(piece)) {
                return square;
            }
        }
        return null;
    }

    public Map<String, Square> getSquares() {
        return squares;
    }

    public void setSquares(Map<String, Square> squares) {
        this.squares = squares;
    }
}
