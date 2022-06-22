package fr.codeflow.gameplay.usecase.validator;

import fr.codeflow.gameplay.usecase.MoveValidationParams;

public class MoveOnFreeSquareValidator extends ValidationStep<MoveValidationParams> {

    public static final String ERROR_MSG = "Target square [%s] is not empty";

    @Override
    public ValidationResult validate(MoveValidationParams moveParams) {
        var targetSquare = moveParams.board().getSquareById(moveParams.targetSquareId());
        if (targetSquare.getPiece() != null) {
            return ValidationResult.invalid(String.format(ERROR_MSG, moveParams.targetSquareId()));
        }
        return checkNext(moveParams);
    }
}
