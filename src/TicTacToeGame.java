import controller.GameController;
import models.*;
import service.botPlayingStrategy.BotDifficultyLevel;
import service.winningStrategy.OrderOneWinningStrategy;
import service.winningStrategy.WinningStrategies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {
    public static void main(String[] args) {
        System.out.println("Start a new Game!");

        Scanner scanner = new Scanner(System.in);
        GameController gameController = new GameController();

        System.out.println("Enter dimension");
        int dimension = scanner.nextInt();

        System.out.println("Will there be any bot in the game? Y/N");
        String isBotPresent = scanner.next();

        List<Player> players = new ArrayList<>();
        int ite = dimension - 1;

        if(isBotPresent.equalsIgnoreCase("Y")){
            ite = ite - 1;
        }

        for (int i = 1; i <= ite; i++) {

            System.out.println("What is the name of player, number: " + i);
            String playerName = scanner.next();

            System.out.println("What is the symbol of player, number: " + i);
            String symbol = scanner.next();

            players.add(new Player(i, playerName, symbol.charAt(0), PlayerType.HUMAN));
        }

        if(isBotPresent.equalsIgnoreCase("Y")){

            System.out.println("What is the name of BOT");
            String botName = scanner.next();

            System.out.println("What is the symbol of BOT");
            String botSymbol = scanner.next();

            //TODO: take input for BOT difficulty level
            BotDifficultyLevel botDifficultyLevel =  BotDifficultyLevel.EASY;

            Bot bot = new Bot(dimension, botName, botSymbol.charAt(0), PlayerType.BOT, botDifficultyLevel);
            players.add(bot);
        }

        //randomises the list of players
        Collections.shuffle(players);

        Game game = gameController.createGame(dimension, players, WinningStrategies.ORDER_ONE_WINNING_STRATEGY);
        int playerIndex = -1;

        while (gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)){
            System.out.println("Current board status");
            gameController.displayBoard(game);
            playerIndex++;
            playerIndex = playerIndex % players.size();

            Move movePlayed = gameController.executeMove(game, players.get(playerIndex));

            //TODO
/*            System.out.println("Do you want to undo your move? Y/N");
            String isUndo = scanner.next();
            if (isUndo.equalsIgnoreCase("Y")){
                gameController.undoMove(game, movePlayed);
            }*/

            Player winner = gameController.checkWinner(game, movePlayed);


            if(winner != null){
                gameController.displayBoard(game);
                System.out.println("Winner is " + winner.getName());
                break;
            }
        }

        System.out.println("Final board status");
        gameController.displayBoard(game);
    }
}