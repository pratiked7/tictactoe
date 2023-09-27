package exception;

public class InvalidPlayersCountException extends RuntimeException{

    public InvalidPlayersCountException(String message) {
        super(message);
    }
}
