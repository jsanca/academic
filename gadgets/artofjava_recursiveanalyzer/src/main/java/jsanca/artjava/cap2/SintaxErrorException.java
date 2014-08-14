package jsanca.artjava.cap2;

/**
 * Exception for sintax error
 *
 * User: jsanca
 * Date: 4/16/13
 * Time: 1:30 AM
 * @author jsanca
 */
public class SintaxErrorException extends RuntimeException {

    public SintaxErrorException() {
    }

    public SintaxErrorException(String message) {
        super(message);
    }

    public SintaxErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public SintaxErrorException(Throwable cause) {
        super(cause);
    }
} // E:O:F:SintaxErrorException.

