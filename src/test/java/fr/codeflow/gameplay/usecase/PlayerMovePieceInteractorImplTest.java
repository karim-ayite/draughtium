package fr.codeflow.gameplay.usecase;

import fr.codeflow.dataaccess.InMemoryBoardRepository;
import fr.codeflow.gameplay.entity.Board;
import fr.codeflow.gameplay.entity.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PlayerMovePieceInteractorImpl")
class PlayerMovePieceInteractorImplTest {

    private final BoardRepository boardRepository = new InMemoryBoardRepository();

    //3.4. A man has to move, forwards over a diagonal, towards an empty square of the next row.

    @Test
    @DisplayName("Should have moved man from origin square to target square")
    void shouldHaveMovedManFromOriginSquareToTargetSquare() {

        boardRepository.saveBoard(new Board());

        int originSquareId = 31;
        int targetSquareId = 27;

        var board = this.boardRepository.getBoard();

        int manId = board.getSquareById(originSquareId).getPiece().getId();

        var playerMovePieceInteractor = new PlayerMovePieceInteractorImpl(boardRepository);

        playerMovePieceInteractor.movePiece(new PlayerMovePieceRequest(originSquareId, targetSquareId, false));

        board = this.boardRepository.getBoard();

        Square targetSquare = board.getSquareById(targetSquareId);

        var movedMan = targetSquare.getPiece();

        assertEquals(manId, movedMan.getId());

        assertNull(board.getSquareById(originSquareId).getPiece());
    }

    @Test
    @DisplayName("Should have validation failed when moving on a occupied square")
    void shouldHaveValidationFailedWhenMovingOnAOccupiedSquare() {
        boardRepository.saveBoard(new Board());

        int originSquareId = 31;
        int targetSquareId = 19;

        var playerMovePieceInteractor = new PlayerMovePieceInteractorImpl(boardRepository);

        var validationResult = playerMovePieceInteractor.movePiece(new PlayerMovePieceRequest(originSquareId, targetSquareId, false));

        assertTrue(validationResult.notValid());
        assertEquals("Target square [19] is not empty", validationResult.getErrorMsg());
    }

    @Test
    @DisplayName("Should have validation failed when man not moving forwards")
    void shouldHaveValidationFailedWhenManNotMovingForwards() {
        boardRepository.saveBoard(new Board());

        var playerMovePieceInteractor = new PlayerMovePieceInteractorImpl(boardRepository);

        playerMovePieceInteractor.movePiece(new PlayerMovePieceRequest(31, 27, false));

        playerMovePieceInteractor.movePiece(new PlayerMovePieceRequest(27, 22, false));

        int originSquareId = 22;
        int targetSquareId = 27;

        var validationResult = playerMovePieceInteractor.movePiece(new PlayerMovePieceRequest(originSquareId, targetSquareId, false));

        assertTrue(validationResult.notValid());
        assertEquals("Man can not move backward", validationResult.getErrorMsg());
    }
}