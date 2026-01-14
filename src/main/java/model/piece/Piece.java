package model.piece;


import model.Chessboard;
import model.Move;
import model.color.Color;
import model.movement.Movement;

import java.util.List;

public abstract class Piece {
    private Color color;
    private Movement movement;

    public List<Move> getPossibleMoves(Chessboard chessboard){
        if (movement == null) return List.of();
        return movement.getPossibleMoves(this, chessboard);
    }


    public Movement getMovement() {
        return movement;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "color=" + color +
                ",name=" + this.getClass().getSimpleName() +
                '}';
    }
}
