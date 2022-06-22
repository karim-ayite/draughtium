package fr.codeflow.gameplay.entity;


import static fr.codeflow.gameplay.entity.Board.*;

class BoardCreator {

    public Square[][] createSquare() {
        var squares = new Square[NB_ROWS][NB_COLUMNS];
        int n = 1;
        int activateSquareNumber = 1;
        for (int rowIndex = 0; rowIndex < NB_ROWS; rowIndex++) {
            for (int columnIndex = 0; columnIndex < NB_COLUMNS; columnIndex++) {
                squares[rowIndex][columnIndex] = new Square();

                var currentSquare = squares[rowIndex][columnIndex];
                currentSquare.setActive(n % 2 == 0);

                if (currentSquare.isActive()) {
                    currentSquare.setId(activateSquareNumber);
                    activateSquareNumber++;
                }
                n++;
            }
            n++;
        }

        placePieces(squares);


        return squares;
    }

    private void placePieces(Square[][] squares) {
        int pieceIndex = 0;
        for (int rowIndex = 0; rowIndex < NB_ROWS; rowIndex++) {
            for (int columnIndex = 0; columnIndex < NB_COLUMNS; columnIndex++) {
                var currentSquare = squares[rowIndex][columnIndex];
                Piece placedPiece = null;
                if (currentSquare.isActive() && isInColorInitialSquare(currentSquare, LIGHT_SQUARE_MIN_NUMBER, LIGHT_SQUARE_MAX_NUMBER)) {
                     placedPiece = createPiece(PieceColor.LIGHT);
                    currentSquare.setPiece(placedPiece);
                } else if (currentSquare.isActive() && isInColorInitialSquare(currentSquare, DARK_SQUARE_MIN_NUMBER, DARK_SQUARE_MAX_NUMBER)) {
                     placedPiece = createPiece(PieceColor.DARK);
                    currentSquare.setPiece(placedPiece);
                }

                if (placedPiece != null) {
                    placedPiece.setId(pieceIndex);
                    currentSquare.setPiece(placedPiece);
                    pieceIndex++;
                }
            }
        }
    }

    private Piece createPiece(PieceColor pieceColor) {
        Piece piece = new Piece();
        piece.setColor(pieceColor);
        return piece;
    }

    private boolean isInColorInitialSquare(Square currentSquare, int squareMinNumber, int squareMaxNumber) {
        return currentSquare.getId() >= squareMinNumber && currentSquare.getId() <= squareMaxNumber;
    }
}
