package service.botPlayingStrategy;

public class BotPlayingStrategyFactory {

    //TODO: Create an enum for different strategies, use switch case

    public BotPlayingStrategy getBotPlayingStrategy(){
        return new RandomBotPlayingStrategy();
    }
}
