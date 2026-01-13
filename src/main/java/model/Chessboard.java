package model;

import java.util.ArrayList;
import java.util.List;

public class Chessboard {
    private List<Square> squares = new ArrayList<>();

    public Chessboard() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                Square square = new Square();
                char file = (char) ('a' + j);
                int rank = 8 - i;
                square.setName("" + file + rank);
                squares.add(square);
            }
        }
    }

    public List<Square> getSquares() {
        return squares;
    }

    public void setSquares(List<Square> squares) {
        this.squares = squares;
    }
}
