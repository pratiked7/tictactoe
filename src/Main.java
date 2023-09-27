import models.Bot;
import models.Game;
import models.Player;
import models.PlayerType;
import service.winningStrategy.OrderOneWinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Start a new Game!");

        int size = 3;

        Player player1 = new Player(1, "Pratik", 'O', PlayerType.HUMAN);
        Player player2 = new Player(2, "Neha", 'X', PlayerType.HUMAN);

        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        Game game = Game.builder()
                .dimension(size)
                .players(players)
                .winningStrategy(new OrderOneWinningStrategy(size))
                .build();


    }
}