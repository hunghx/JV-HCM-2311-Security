package ra.academy.exception;

public class UserNameOrPasswordInvalidException extends RuntimeException{
    public UserNameOrPasswordInvalidException(String message) {
        super(message);
    }
}
