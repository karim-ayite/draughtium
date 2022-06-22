package fr.codeflow.gameplay.entity;

import java.util.HashMap;

public class Board {

    public static final int NB_ROWS = 10;
    public static final int NB_COLUMNS = 10;
    public static final int LIGHT_SQUARE_MIN_NUMBER = 31;
    public static final int LIGHT_SQUARE_MAX_NUMBER = 50;
    public static final int DARK_SQUARE_MIN_NUMBER = 1;
    public static final int DARK_SQUARE_MAX_NUMBER = 20;

    private final Square[][] squares;

    private HashMap<Integer, Square> squareHashMap;

    public Board() {
        BoardCreator boardCreator = new BoardCreator();
        this.squares = boardCreator.createSquare();
        this.initializeSquareMap();
    }

    private void initializeSquareMap() {
        this.squareHashMap = new HashMap<>();
        for (int rowIndex = 0; rowIndex < NB_ROWS; rowIndex++) {
            for (int columnIndex = 0; columnIndex < NB_COLUMNS; columnIndex++) {
                var currentSquare = this.squares[rowIndex][columnIndex];
                if (currentSquare.isActive()) {
                    this.squareHashMap.put(currentSquare.getId(), currentSquare);
                }
            }
        }

    }

    public Square[][] getSquares() {
        return squares;
    }

    public Square getSquareById(int squareId) {
        return this.squareHashMap.get(squareId);
    }
}
