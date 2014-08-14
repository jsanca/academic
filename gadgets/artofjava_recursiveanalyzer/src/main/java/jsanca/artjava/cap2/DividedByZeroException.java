package jsanca.artjava.cap2;

/**
 * Exception when the user tries to split by zero
 *
 * User: jsanca
 * Date: 4/16/13
 * Time: 1:30 AM
 * @author jsanca
 */
public class DividedByZeroException extends RuntimeException {

    public DividedByZeroException() {
    }

    public DividedByZeroException(String message) {
        super(message);
    }

    public DividedByZeroException(String message, Throwable cause) {
        super(message, cause);
    }

    public DividedByZeroException(Throwable cause) {
        super(cause);
    }
} // E:O:F:DividedByZeroException.
