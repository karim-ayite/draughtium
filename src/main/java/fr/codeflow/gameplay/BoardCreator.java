package fr.codeflow.gameplay;

public class BoardCreator {

    private static final int NB_ROWS = 10;
    private static final int NB_COLUMNS = 10;
    public static final int LIGHT_SQUARE_MIN_NUMBER = 31;
    public static final int LIGHT_SQUARE_MAX_NUMBER = 50;
    private static final int DARK_SQUARE_MIN_NUMBER = 1;
    private static final int DARK_SQUARE_MAX_NUMBER = 20;


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

        for (int rowIndex = 0; rowIndex < NB_ROWS; rowIndex++) {
            for (int columnIndex = 0; columnIndex < NB_COLUMNS; columnIndex++) {
                var currentSquare = squares[rowIndex][columnIndex];

                if (currentSquare.isActive() && isInColorInitialSquare(currentSquare, LIGHT_SQUARE_MIN_NUMBER, LIGHT_SQUARE_MAX_NUMBER)) {
                    Piece lightPiece = createPiece(PieceColor.LIGHT);
                    currentSquare.setPiece(lightPiece);
                } else if (currentSquare.isActive() && isInColorInitialSquare(currentSquare, DARK_SQUARE_MIN_NUMBER, DARK_SQUARE_MAX_NUMBER)) {
                    Piece lightPiece = createPiece(PieceColor.DARK);
                    currentSquare.setPiece(lightPiece);
                }
            }
        }
    }

    private Piece createPiece(PieceColor pieceColor) {
        Piece piece = new Piece();
        piece.setColor(pieceColor);
        piece.setKing(false);
        return piece;
    }

    private boolean isInColorInitialSquare(Square currentSquare, int squareMinNumber, int squareMaxNumber) {
        return currentSquare.getId() >= squareMinNumber && currentSquare.getId() <= squareMaxNumber;
    }
}
