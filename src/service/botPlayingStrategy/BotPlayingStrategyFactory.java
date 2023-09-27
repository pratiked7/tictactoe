package service.botPlayingStrategy;

public class BotPlayingStrategyFactory {

    //TODO: Create an enum for different strategies

    public BotPlayingStrategy getBotPlayingStrategy(){
        return new RandomBotPlayingStrategy();
    }
}
