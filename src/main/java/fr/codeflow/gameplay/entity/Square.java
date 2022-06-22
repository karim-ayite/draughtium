package fr.codeflow.gameplay.entity;


public class Square {

    /**
     * 2.6 The dark squares are as a convention, without actual writing numbers on the board, numbered from 1 to 50. This numbering follows the rows, from left to right, starting at the first square of the upper row and ending at the last square of the lowest row, with the following results:
     *
     * 2.6.1. The numbers on the bases or promotion rows are numbered 1 through 5, and 46 through 50;
     *
     *  2.6.2. The 5 squares of the sides, or the first and the last columns, are numbered at the left
     *         6-16-26-36-46 and at the right 5-15-25-35-45;
     */
    private int id;
    private boolean active;
    private Piece piece;

    private Position position;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
