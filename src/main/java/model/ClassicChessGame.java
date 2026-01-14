package model;

import model.color.Black;
import model.color.Color;
import model.color.White;
import model.movement.*;
import model.piece.*;

import java.util.ArrayList;
import java.util.List;

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
                        knight.setMovement(new KnightMovement());
                        createPiece(square, knight, rank);
                    }
                    case 'c', 'f' -> {
                        Bishop bishop = new Bishop();
                        bishop.setMovement(new BishopMovement());
                        createPiece(square, bishop, rank);
                    }
                    case 'd' -> {
                        Queen queen = new Queen();
                        queen.setMovement(new QueenMovement());
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
    public void play() {
        boolean gameFinished = false;
        int turn = 0; // 0 = blanc, 1 = noir
        int maxTurns = 300;
        int currentTurn = 1;

        System.out.println("=== GAME STARTED ===");
        System.out.println(this); // afficher plateau initial

        while (!gameFinished && currentTurn <= maxTurns) {
            System.out.println("=== Turn " + currentTurn + " ===");
            System.out.println(turn == 0 ? "White to move" : "Black to move");

            // Récupérer toutes les pièces du joueur actuel
            Color currentColor = turn == 0 ? new White() : new Black();
            List<Move> possibleMoves = new ArrayList<>();

            for (Square square : getChessboard().getSquares().values()) {
                Piece piece = square.getPiece();
                if (piece != null && piece.getColor().equals(currentColor)) {
                    possibleMoves.addAll(piece.getPossibleMoves(this));
                }
            }

            if (possibleMoves.isEmpty()) {
                System.out.println((turn == 0 ? "White" : "Black") + " has no moves. Game over!");
                gameFinished = true;
                break;
            }

            // Pour l'instant : choisir un coup aléatoire
            Move move = possibleMoves.get((int) (Math.random() * possibleMoves.size()));

            // Appliquer le coup
            move.getTo().setPiece(move.getPiece());
            move.getFrom().setPiece(null);

            // Afficher le coup joué
            System.out.println(move);

            // Afficher plateau
            System.out.println(this);

            // Vérifier si un roi est capturé → fin de partie
            boolean whiteKingAlive = false;
            boolean blackKingAlive = false;
            for (Square square : getChessboard().getSquares().values()) {
                Piece piece = square.getPiece();
                if (piece != null) {
                    if (piece instanceof King && piece.getColor() instanceof White) whiteKingAlive = true;
                    if (piece instanceof King && piece.getColor() instanceof Black) blackKingAlive = true;
                }
            }
            if (!whiteKingAlive || !blackKingAlive) {
                System.out.println("Game finished! " + (!whiteKingAlive ? "Black wins!" : "White wins!"));
                gameFinished = true;
                break;
            }

            // Passer au joueur suivant
            turn = 1 - turn;
            currentTurn++;
        }

        if (!gameFinished) {
            System.out.println("Max turns reached. Draw!");
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
