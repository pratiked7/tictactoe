package service.botPlayingStrategy;

import exception.GameOverException;
import models.Board;
import models.Cell;
import models.CellState;
import models.Move;

import java.util.List;

public class RandomBotPlayingStrategy implements BotPlayingStrategy{

    //not an intelligent bot
    //find the next empty cell and makes move

    @Override
    public Move makeMove(Board board) {

        List<List<Cell>> matrix = board.getBoard();

        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.size(); j++) {

                //look for an empty cell and make a move when find one
                if (matrix.get(i).get(j).getCellState().equals(CellState.EMPTY)){
                    return new Move(i, j);
                }
            }
        }
        throw new GameOverException("No cell remaining to play the next move, GAME OVER");
    }
}
