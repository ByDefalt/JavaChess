package model;

import model.piece.Piece;

public class Move {
    private final Piece piece;
    private final Square from;
    private final Square to;
    private final Piece capturedPiece;

    public Move(Piece piece, Square from, Square to, Piece capturedPiece) {
        this.piece = piece;
        this.from = from;
        this.to = to;
        this.capturedPiece = capturedPiece;
    }

    public Piece getPiece() { return piece; }
    public Square getFrom() { return from; }
    public Square getTo() { return to; }
    public Piece getCapturedPiece() { return capturedPiece; }

    @Override
    public String toString() {
        String capture = (capturedPiece != null) ? " x " + capturedPiece : "";
        return piece + ": " + from.getName() + " -> " + to.getName() + capture;
    }
}


