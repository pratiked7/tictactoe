package service.botPlayingStrategy;

import exception.GameOverException;
import models.Board;
import models.Move;

public interface BotPlayingStrategy {
    Move makeMove(Board board) throws GameOverException;
}
