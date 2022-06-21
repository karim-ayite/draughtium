package fr.codeflow.gameplay;

public class Board {

    private Square[][] squares;

    public Board(Square[][] squares) {
        this.squares = squares;
    }

    public Square[][] getSquares() {
        return squares;
    }
}
