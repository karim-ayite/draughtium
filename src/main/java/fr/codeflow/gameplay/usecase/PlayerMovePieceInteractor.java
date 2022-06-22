package fr.codeflow.gameplay.usecase;

import fr.codeflow.gameplay.usecase.validator.ValidationResult;

public interface PlayerMovePieceInteractor {

    ValidationResult movePiece(PlayerMovePieceRequest playerMovePieceRequest) ;

}
