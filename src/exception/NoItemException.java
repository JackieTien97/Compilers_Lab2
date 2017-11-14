package exception;

public class NoItemException extends Exception {

    public NoItemException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
