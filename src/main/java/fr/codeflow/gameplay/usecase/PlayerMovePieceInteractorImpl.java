package fr.codeflow.gameplay.usecase;

import fr.codeflow.gameplay.entity.Board;
import fr.codeflow.gameplay.entity.Piece;
import fr.codeflow.gameplay.usecase.validator.MoveOnFreeSquareValidator;
import fr.codeflow.gameplay.usecase.validator.MoveForwardsForManValidator;
import fr.codeflow.gameplay.usecase.validator.ValidationResult;

public class PlayerMovePieceInteractorImpl implements PlayerMovePieceInteractor {

    private final BoardRepository boardRepository;

    PlayerMovePieceInteractorImpl(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    @Override
    public ValidationResult movePiece(PlayerMovePieceRequest playerMovePieceRequest) {
        var board = this.boardRepository.getBoard();
        var validationResult = validateMove(playerMovePieceRequest, board);

        if (validationResult.notValid()){
            return validationResult;
        } else {
            makeMove(playerMovePieceRequest,board);
        }

        return validationResult;
    }

    private void makeMove(PlayerMovePieceRequest playerMovePieceRequest, Board board) {
        var targetSquare = board.getSquareById(playerMovePieceRequest.targetSquareId());
        var originSquare = board.getSquareById(playerMovePieceRequest.originSquareId());

        Piece pieceToMove = originSquare.getPiece();

        if (!pieceToMove.isKing()){
            targetSquare.setPiece(pieceToMove);
            originSquare.setPiece(null);
        }
    }

    private ValidationResult validateMove(PlayerMovePieceRequest playerMovePieceRequest, Board board) {

        MoveValidationParams moveParams = new MoveValidationParams(
                playerMovePieceRequest.originSquareId(),
                playerMovePieceRequest.targetSquareId(), playerMovePieceRequest.capture(), board
        );

        return new MoveOnFreeSquareValidator()
                .linkWith(new MoveForwardsForManValidator())
                .validate(moveParams);
    }


}
