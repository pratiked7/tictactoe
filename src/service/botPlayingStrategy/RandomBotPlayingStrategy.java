package service.botPlayingStrategy;

import models.Board;
import models.Cell;
import models.CellState;
import models.Move;

import java.util.List;

public class RandomBotPlayingStrategy implements BotPlayingStrategy{


    @Override
    public Move makeMove(Board board) {

        List<List<Cell>> matrix = board.getBoard();

        for (int i = 0; i < matrix.size(); i++) {

            for (int j = 0; j < matrix.size(); j++) {

                if (matrix.get(i).get(j).getCellState().equals(CellState.EMPTY)){

                }
            }

        }

        return null;
    }
}
