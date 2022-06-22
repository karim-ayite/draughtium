package fr.codeflow.gameplay.usecase;

public record PlayerMovePieceRequest(int originSquareId, int targetSquareId, boolean capture) {
}
