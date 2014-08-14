package jsanca.artjava.cap2;

/**
 * Exception throw when there is not any expression present
 *
 * User: jsanca
 * Date: 4/16/13
 * Time: 1:30 AM
 * @author jsanca
 */
public class NonExpressionDefinedException extends RuntimeException {

    public NonExpressionDefinedException() {
    }

    public NonExpressionDefinedException(String message) {
        super(message);
    }

    public NonExpressionDefinedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonExpressionDefinedException(Throwable cause) {
        super(cause);
    }

} // E:O:F:NonExpressionDefinedException.

