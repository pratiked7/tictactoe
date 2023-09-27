package service.winningStrategy;

import models.Board;
import models.Move;
import models.Player;

public interface WinningStrategy {
    Player checkWinner(Board board, Move lastMove);
}
