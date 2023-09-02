package excepciones;

public class WrongOptionException extends Exception{
    public WrongOptionException(String message) {
        super(message);
    }
}