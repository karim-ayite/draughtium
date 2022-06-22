package fr.codeflow.gameplay.entity;


import static fr.codeflow.gameplay.entity.PieceType.KING;
import static fr.codeflow.gameplay.entity.PieceType.MAN;

public class Piece {

    private int id;
    private PieceColor color;
    private PieceType pieceType = MAN;

    public PieceColor getColor() {
        return color;
    }

    public void setColor(PieceColor color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public void promoteToKing() {
        this.pieceType = KING;
    }

    public boolean isKing(){
        return this.pieceType == KING;
    }

    public boolean isLight() {
        return this.color == PieceColor.LIGHT;
    }
}
