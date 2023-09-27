package models;

import service.botPlayingStrategy.BotDifficultyLevel;

public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;

    public Bot() {
        super(0, "Jarvis", 'B', PlayerType.BOT);
    }
}
