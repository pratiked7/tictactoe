package service.winningStrategy;

public class WinningStrategyFactory {

    public static WinningStrategy getWinningStrategy(WinningStrategies winningStrategies, int dimension){
        //TODO: add a switch case for different strategy chosen and return the object
        return new OrderOneWinningStrategy(dimension);
    }
}
