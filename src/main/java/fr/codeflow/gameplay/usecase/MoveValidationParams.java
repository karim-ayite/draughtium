package fr.codeflow.gameplay.usecase;

import fr.codeflow.gameplay.entity.Board;

public record MoveValidationParams(int originSquareId, int targetSquareId, boolean capture, Board board) {

}
