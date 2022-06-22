package fr.codeflow.dataaccess;

import fr.codeflow.gameplay.entity.Board;
import fr.codeflow.gameplay.usecase.BoardRepository;

public class InMemoryBoardRepository implements BoardRepository {

    private  Board board;

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public void saveBoard(Board board) {
        this.board = board;
    }
}
