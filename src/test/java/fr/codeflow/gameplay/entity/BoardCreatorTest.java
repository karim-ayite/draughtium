package fr.codeflow.gameplay.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BoardCreator")
class BoardCreatorTest {

    private final BoardCreator boardCreator = new BoardCreator();

    @Test
    @DisplayName("Should have created a board with 100 squares")
    void shouldHave100Squares() {
        var squares = boardCreator.createSquare();

        int nbSquares = 0;
        for (int rowIndex = 0; rowIndex < Arrays.stream(squares).count(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < squares[rowIndex].length; columnIndex++) {
                nbSquares++;
            }
        }

        assertEquals(100, nbSquares);
    }

    //2.2. The game is played on the dark squares of the board. Thus, 50 squares are active
    @Test
    @DisplayName("Should have created a board with 50 actives squares")
    void shouldHave50ActiveSquares() {
        var squares = boardCreator.createSquare();

        int nbActiveSquare = 0;
        for (int rowIndex = 0; rowIndex < Arrays.stream(squares).count(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < squares[rowIndex].length; columnIndex++) {
                var currentSquare = squares[rowIndex][columnIndex];
                if (currentSquare.isActive()) {
                    nbActiveSquare++;
                }
            }
        }

        assertEquals(50, nbActiveSquare);
    }

    //2.4. The board must be placed between the two players in such a way that the long diagonal starts at the left-hand side of each player. This way, the first square at the left hand, for each player is a dark square.
    @Test
    @DisplayName("Should have created a board with 1 square active on 2")
    void shouldHaveCreatedABoardWith1SquareActiveOn2() {
        var squares = boardCreator.createSquare();

        for (int rowIndex = 0; rowIndex < Arrays.stream(squares).count(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < squares[rowIndex].length; columnIndex++) {
                if (squares[rowIndex][columnIndex].isActive() && columnIndex+1 < squares[rowIndex].length) {
                    assertFalse(squares[rowIndex][columnIndex + 1].isActive());
                }
            }
        }
    }

    //The dark squares are as a convention, without actual writing numbers on the board, numbered from 1 to 50.
    @Test
    @DisplayName("Should have created a board with active numbered from 1 to 50")
    void shouldHaveCreatedABoardWithActiveNumberedFrom1To50() {
        var squares = boardCreator.createSquare();

        int squareNumber = 0;
        for (int rowIndex = 0; rowIndex < Arrays.stream(squares).count(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < squares[rowIndex].length; columnIndex++) {
                var square = squares[rowIndex][columnIndex];
                if (square.isActive()){
                    squareNumber++;
                    assertEquals(squareNumber, square.getId());
                }

            }
        }
    }

    // 2.6.1. The numbers on the bases or promotion rows are numbered 1 through 5, and 46 through 50;
    @Test
    @DisplayName("Should have created a board with promotion rows numbered 1 through 5")
    void shouldHaveCreatedABoardWithPromotionRowsNumbered1Through5() {
        var squares  = boardCreator.createSquare();

        int promotionRowIndex = 0;

        var square = squares[promotionRowIndex][0];
        if (square.isActive()){ 
            assertEquals(1, square.getId());
        }

        square = squares[promotionRowIndex][squares[promotionRowIndex].length-3];
        if (square.isActive()){ 
            assertEquals(4, square.getId());
        }

        square = squares[promotionRowIndex][squares[promotionRowIndex].length-1];
        if (square.isActive()){
            assertEquals(5, square.getId());
        }
    }
    
    @Test
    @DisplayName("Should have created a board with promotion rows numbered 46 through 50")
    void shouldHaveCreatedABoardWithPromotionRowsNumbered46Through50() {
        var squares  = boardCreator.createSquare();
        
        int baseRowIndex = 9;

        var square = squares[baseRowIndex][0];
        if (square.isActive()){ 
            assertEquals(46, square.getId());
        }

        square = squares[baseRowIndex][squares[baseRowIndex].length-3];
        if (square.isActive()){ 
            assertEquals(49, square.getId());
        }

        square = squares[baseRowIndex][squares[baseRowIndex].length-1];
        if (square.isActive()){ 
            assertEquals(50, square.getId());
        }
    }

    // 2.6.2. The 5 squares of the sides, or the first and the last columns, are numbered at the left
    //6-16-26-36-46 and at the right 5-15-25-35-45;
    @Test
    @DisplayName("Should have created a board with first column numbered 6-16-26-36-46")
    void shouldHaveCreatedABoardWithFirstColumnNumbered_6_16_26_36_46() {
        var squares  = boardCreator.createSquare();

        int firstColumnRow = 0;

        var square = squares[0][firstColumnRow];
        if (square.isActive()){ 
            assertEquals(6, square.getId());
        }

        square = squares[3][firstColumnRow];
        if (square.isActive()){ 
            assertEquals(16, square.getId());
        }

        square = squares[5][firstColumnRow];
        if (square.isActive()){ 
            assertEquals(26, square.getId());
        }

        square = squares[7][firstColumnRow];
        if (square.isActive()){ 
            assertEquals(36, square.getId());
        }

        square = squares[9][firstColumnRow];
        if (square.isActive()){ 
            assertEquals(46, square.getId());
        }
    }

    @Test
    @DisplayName("Should have created a board with last column numbered 5-15-25-35-45")
    void shouldHaveCreatedABoardWithLastColumnNumbered5_15_25_35_45() {
        var squares  = boardCreator.createSquare();

        int lastColumnRow = 9;

        var square = squares[0][lastColumnRow];
        if (square.isActive()){ 
            assertEquals(5, square.getId());
        }

        square = squares[3][lastColumnRow];
        if (square.isActive()){ 
            assertEquals(15, square.getId());
        }

        square = squares[5][lastColumnRow];
        if (square.isActive()){ 
            assertEquals(25, square.getId());
        }

        square = squares[7][lastColumnRow];
        if (square.isActive()){ 
            assertEquals(35, square.getId());
        }

        square = squares[9][lastColumnRow];
        if (square.isActive()){ 
            assertEquals(45, square.getId());
        }
    }

    // 2.6.3. The squares at the end of the long diagonal are numbered 5 and 46 and are called the corners of the board.
    @Test
    @DisplayName("Should have created a board with the squares at the end of the long diagonal numbered 5 and 46")
    void shouldHaveCreatedABoardWithTheSquaresAtTheEndOfTheLongDiagonalNumbered5And46() {
        var squares  = boardCreator.createSquare();

        var square = squares[0][9];
        if (square.isActive()){ // A Dark square
            assertEquals(5, square.getId());
        }

        square = squares[9][0];
        if (square.isActive()){ // A Dark square
            assertEquals(46, square.getId());
        }
    }

    // 2.7. International draughts is played with 20 white or light coloured men, and 20 black or dark coloured men.
    @Test
    @DisplayName("Should have created a board with 20 white men")
    void shouldHaveCreatedABoardWith20WhitesMen() {
        var squares  = boardCreator.createSquare();

        int nbLightMen = 0;
        for (int rowIndex = 0; rowIndex < Arrays.stream(squares).count(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < squares[rowIndex].length; columnIndex++) {
                var square = squares[rowIndex][columnIndex];
                if (square.isActive() && square.getPiece() != null && square.getPiece().getColor() == PieceColor.LIGHT){ // A Dark square
                    assertTrue(square.getId() >= 31 && square.getId() <= 50);
                    nbLightMen++;
                }
            }
        }

        assertEquals(20, nbLightMen);
    }

    @Test
    @DisplayName("Should have created a board with 20 black men")
    void shouldHaveCreatedABoardWith20BlackMen() {
        var squares  = boardCreator.createSquare();

        int nbDarkMen = 0;
        for (int rowIndex = 0; rowIndex < Arrays.stream(squares).count(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < squares[rowIndex].length; columnIndex++) {
                var square = squares[rowIndex][columnIndex];
                if (square.isActive() && square.getPiece() != null && square.getPiece().getColor() == PieceColor.DARK){ // A Dark square
                    assertTrue(square.getId() >= 1 && square.getId() <= 20);
                    nbDarkMen++;
                }
            }
        }
        assertEquals(20, nbDarkMen);
    }

    // 2.8. At the start of the game, the 20 black men are put on the squares with the numbers 1 to 20 and the 20 white
    // men on those numbered 31 to 50. The squares with the numbers 21 to 30 are empty, or free.
    @Test
    @DisplayName("Should have created a board with squares numbered 21 to 30 empty")
    void shouldHaveCreatedABoardWithSquaresNumbered21To30Empty() {
        var squares  = boardCreator.createSquare();

        for (int rowIndex = 0; rowIndex < Arrays.stream(squares).count(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < squares[rowIndex].length; columnIndex++) {
                var square = squares[rowIndex][columnIndex];
                if (square.getId() >= 21 && square.getId() <= 30){ // A Dark square
                    assertNull(square.getPiece());
                }
            }
        }
    }

    @Test
    @DisplayName("Should have created a board with black men on board 1 to 20")
    void shouldHaveCreatedABoardWithBlackMenOnBoard1To20() {
        var squares  = boardCreator.createSquare();

        for (int rowIndex = 0; rowIndex < Arrays.stream(squares).count(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < squares[rowIndex].length; columnIndex++) {
                var square = squares[rowIndex][columnIndex];
                if (square.getId() >= 1 && square.getId() <= 20){ // A Dark square
                    assertSame(PieceColor.DARK, square.getPiece().getColor());
                }
            }
        }
    }
}