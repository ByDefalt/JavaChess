package model;

import model.color.Black;
import model.color.White;
import model.movement.KingMovement;
import model.movement.PawnMovement;
import model.movement.RookMovement;
import model.piece.*;

public class ClassicChessGame extends ChessGame {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_WHITE = "\u001B[97m"; // blanc lumineux
    public static final String ANSI_BLACK = "\u001B[30m"; // rouge clair


    public void createPiece(Square square, Piece piece, int rank) {
        if (rank == 1) {
            // Place white
            piece.setColor(new White());
            square.setPiece(piece);
        } else if (rank == 8) {
            // Place black
            piece.setColor(new Black());
            square.setPiece(piece);
        }
        Pawn pawn = new Pawn();
        pawn.setMovement(new PawnMovement());
        if (rank == 2) {
            // Place white pawn
            pawn.setColor(new White());
            square.setPiece(pawn);
        } else if (rank == 7) {
            // Place black pawn
            pawn.setColor(new Black());
            square.setPiece(pawn);
        }
    }
    public static char getPieceChar(Piece piece) {
        String name = piece.getClass().getSimpleName();
        if (name.equals("King")) return 'K';
        if (name.equals("Queen")) return 'Q';
        if (name.equals("Rook")) return 'R';
        if (name.equals("Bishop")) return 'B';
        if (name.equals("Knight")) return 'N'; // note le N pour le cavalier
        if (name.equals("Pawn")) return 'P';
        return '?';
    }

    @Override
    public void init() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                Square square = new Square();
                char file = (char) ('a' + j);
                int rank = 8 - i;
                square.setName("" + file + rank);
                switch (file) {
                    case 'a', 'h' -> {
                        Rook rook = new Rook();
                        rook.setMovement(new RookMovement());
                        createPiece(square, rook, rank);
                    }
                    case 'b', 'g' -> {
                        Knight knight = new Knight();
                        createPiece(square, knight, rank);
                    }
                    case 'c', 'f' -> {
                        Bishop bishop = new Bishop();
                        createPiece(square, bishop, rank);
                    }
                    case 'd' -> {
                        Queen queen = new Queen();
                        createPiece(square, queen, rank);
                    }
                    case 'e' -> {
                        King king = new King();
                        king.setMovement(new KingMovement());
                        createPiece(square, king, rank);
                    }
                }
                super.getChessboard().getSquares().put(square.getName(),square);
            }
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int rank = 8; rank >= 1; rank--) {
            for (char file = 'a'; file <= 'h'; file++) {
                String squareName = "" + file + rank;
                Square square = getChessboard().getSquare(squareName);
                if (square.getPiece() != null) {
                    char pieceChar = getPieceChar(square.getPiece());
                    if (square.getPiece().getColor() instanceof White) {
                        sb.append(ANSI_WHITE).append(pieceChar).append(ANSI_RESET);
                    } else {
                        sb.append(ANSI_BLACK).append(pieceChar).append(ANSI_RESET);
                    }
                } else {
                    sb.append("."); // case vide
                }
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
