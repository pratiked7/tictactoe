package controller;

import models.*;
import service.winningStrategy.WinningStrategies;
import service.winningStrategy.WinningStrategyFactory;

import java.util.List;

public class GameController {

    public Game createGame(int size, List<Player> players, WinningStrategies winningStrategies){
        try{

            return Game.builder()
                    .dimension(size)
                    .players(players)
                    .winningStrategy(WinningStrategyFactory.getWinningStrategy(winningStrategies, size))
                    .build();

        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            System.out.println("Could not start the game. Something went wrong.");
        }
        return  null;
    }

    public void displayBoard(Game game){
        game.getCurrentBoard().printBoard();
    }

    public GameStatus getGameStatus(Game game){
        return game.getGameStatus();
    }

    public Player getGameWinner(Game game){
        return game.getWinner();
    }

    public Move executeMove(Game game, Player player){

        Move move = player.makeMove(game.getCurrentBoard());

        game.setNumberOfSymbols(game.getNumberOfSymbols() + 1);
        updateGameStatus(game);
        updateGameMoves(game, move);
        updateBoardStates(game);
        return move;
    }

    public Player checkWinner(Game game, Move lastPlayedMove){
        Player player = game.getWinningStrategy().checkWinner(game.getCurrentBoard(), lastPlayedMove);
        if(player != null){
            game.setWinner(player);
            game.setGameStatus(GameStatus.COMPLETED);
            return player;
        }
        return  null;
    }

    public Board undoMove(Game game, Move lastMove){
        //TODO: write undo logic
        return null;
    }


    private void updateGameMoves(Game game, Move move){
        game.getMoves().add(move);
    }

    private void updateBoardStates(Game game){
        game.getBoardStates().add(new Board(game.getCurrentBoard()));
    }

    private void updateGameStatus(Game game){
        int numberOfSymbols = game.getNumberOfSymbols();
        int dimension = game.getCurrentBoard().getSize();
        if(numberOfSymbols == dimension*dimension){
            game.setGameStatus(GameStatus.DRAW);
        }
    }
}
