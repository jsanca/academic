package jsanca.artjava.cap2;

/**
 * Exception unbalanced parentheses
 *
 * User: jsanca
 * Date: 4/16/13
 * Time: 1:30 AM
 * @author jsanca
 */
public class UnbalanceParenthesesException extends RuntimeException {

    public UnbalanceParenthesesException() {
    }

    public UnbalanceParenthesesException(String message) {
        super(message);
    }

    public UnbalanceParenthesesException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnbalanceParenthesesException(Throwable cause) {
        super(cause);
    }
}