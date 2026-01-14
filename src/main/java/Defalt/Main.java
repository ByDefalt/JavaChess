package Defalt;

import model.ClassicChessGame;
import model.Square;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ClassicChessGame classicChessGame = new ClassicChessGame();



        for (Map.Entry<String, Square> entry : classicChessGame.getChessboard().getSquares().entrySet()) {
            String key = entry.getKey();
            Square square = entry.getValue();
            System.out.println(key + " -> " + square.getPiece());
            try{
                System.out.println(classicChessGame.getChessboard().getSquare(key).getPiece().getPossibleMoves(classicChessGame.getChessboard()));
            } catch (NullPointerException e){
            }
        }


        System.out.println(classicChessGame);

    }
}

