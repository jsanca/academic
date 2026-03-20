package jsanca.download.api.exception;

public class InvalidDownloadRequestException extends RuntimeException {

    public InvalidDownloadRequestException() {
    }

    public InvalidDownloadRequestException(String message) {
        super(message);
    }

    public InvalidDownloadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDownloadRequestException(Throwable cause) {
        super(cause);
    }

    public InvalidDownloadRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
