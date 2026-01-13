package Defalt;

import model.Chessboard;
import model.Square;

public class Main {
    static void main(String args[]) {
        Chessboard chessboard = new Chessboard();
        for (Square square : chessboard.getSquares()) {
            System.out.println(square.getName());
        }
    }
}
