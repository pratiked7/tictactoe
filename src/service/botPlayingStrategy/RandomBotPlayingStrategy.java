package service.botPlayingStrategy;

import exception.GameOverException;
import models.*;

import java.util.List;

public class RandomBotPlayingStrategy implements BotPlayingStrategy{

    //not an intelligent bot
    //find the next empty cell and makes move

    @Override
    public Move makeMove(Board board, Player player) throws GameOverException{

        List<List<Cell>> matrix = board.getBoard();

        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.size(); j++) {

                //look for an empty cell and make a move when find one
                if (matrix.get(i).get(j).getCellState().equals(CellState.EMPTY)){
                    board.getBoard().get(i).get(j).setPlayer(player);
                    board.getBoard().get(i).get(j).setCellState(CellState.FILLED);
                    return new Move(i, j, player);
                }
            }
        }
        throw new GameOverException("No cell remaining to play the next move, GAME OVER");
    }
}
