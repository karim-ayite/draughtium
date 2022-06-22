package fr.codeflow.gameplay.usecase.validator;

import fr.codeflow.gameplay.entity.Piece;
import fr.codeflow.gameplay.entity.Square;
import fr.codeflow.gameplay.usecase.MoveValidationParams;

public class MoveForwardsForManValidator extends ValidationStep<MoveValidationParams> {

    public static final String ERROR_MSG = "Man can not move backward";
    @Override
    public ValidationResult validate(MoveValidationParams validationParams) {
        Square targetSquare = validationParams.board().getSquareById(validationParams.targetSquareId());
        Square originSquare = validationParams.board().getSquareById(validationParams.originSquareId());

        if (isMovingBackward(targetSquare, originSquare)) {
            return ValidationResult.invalid(ERROR_MSG);
        }

        return ValidationResult.valid();
    }

    private boolean isMovingBackward(Square targetSquare, Square originSquare) {

        Piece pieceToMove = originSquare.getPiece();

        return !pieceToMove.isKing() && pieceToMove.isLight() && targetSquare.getId() >= originSquare.getId();
    }
}
