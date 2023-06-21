package Calendar.Exceptions;

public class EmptyFieldException extends IllegalArgumentException {
    public EmptyFieldException(String msg) {
        super(msg);
    }
}
