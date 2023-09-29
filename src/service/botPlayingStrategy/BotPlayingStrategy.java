package service.botPlayingStrategy;

import exception.GameOverException;
import models.Board;
import models.Move;
import models.Player;

public interface BotPlayingStrategy {
    Move makeMove(Board board, Player player) throws GameOverException;
}
