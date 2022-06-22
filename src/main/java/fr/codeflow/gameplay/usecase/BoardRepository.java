package fr.codeflow.gameplay.usecase;

import fr.codeflow.gameplay.entity.Board;

public interface BoardRepository {

    Board getBoard();
    void saveBoard(Board board);
}
