package jsanca.download.internal.util;

import jsanca.download.api.event.DownloadErrorCode;

import java.io.IOException;

public class MapperExceptionUtil {

    public static DownloadErrorCode mapExceptionToErrorCode(final Exception e) {
        // todo: on the future create a MapperExceptionUtil to handle this
        if (e instanceof IOException) {
            return DownloadErrorCode.IO_ERROR;
        }

        if (e instanceof InterruptedException) {
            return DownloadErrorCode.INTERRUPTED;
        }

        return DownloadErrorCode.UNKNOWN;
    }
}
