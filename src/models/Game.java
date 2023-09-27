package models;

import exception.DuplicateSymbolException;
import exception.InvalidBoardSizeException;
import exception.InvalidBotCountException;
import service.winningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Game {

    private Board currentBoard;

    private List<Player> players;

    private Player currentPlayer;

    private GameStatus gameStatus;

    private Player winner;

    private List<Move> moves;

    private List<Board> boardStates;

    private WinningStrategy winningStrategy;


    private Game(Board currentBoard, List<Player> players, WinningStrategy winningStrategy) {
        this.currentBoard = currentBoard;
        this.players = players;
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.moves = new ArrayList<>();
        this.boardStates = new ArrayList<>();
        this.winningStrategy = winningStrategy;
    }

    public static Builder builder(){
        return new Builder();
    }


    public static class Builder{

        private int dimension;
        private List<Player> players;
        private WinningStrategy winningStrategy;

        public Builder dimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder players(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder winningStrategy(WinningStrategy winningStrategy) {
            this.winningStrategy = winningStrategy;
            return this;
        }

        public void validateBotCount(){
            int botCount = 0;
            for(Player player : players){
                if (player.getPlayerType().equals(PlayerType.BOT)){
                    botCount++;
                }
            }

            if (botCount > 1){
                throw new InvalidBotCountException("Bot count cannot be more than 1, current bot count: " + botCount);
            }
        }

        public void validateBoardSize(){
            if(dimension < 3 || dimension > 10){
                throw new InvalidBoardSizeException("Dimension should be between 3 and 10");
            }
        }

        public void validateDuplicateSymbols(){
            HashSet<Character> symbolSet = new HashSet<>();
            for (Player player : players){
                symbolSet.add(player.getSymbol());
            }

            if(symbolSet.size() != players.size()){
                throw new DuplicateSymbolException("Each player should have unique symbol");
            }
        }

        public void validate(){
            validateBoardSize();
            validateBotCount();
            validateDuplicateSymbols();
        }

        public Game build(){
            validate();
            return new Game(new Board(dimension), players, winningStrategy);
        }
    }
}
