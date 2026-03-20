package jsanca.download.internal.util;

import java.util.UUID;

/**
 * Utility class responsible for generating unique identifiers for download tasks.
 *
 * <p>The generated identifier is intended to be stable enough for in-memory tracking,
 * logging, debugging, and correlating events related to a single download operation.
 *
 * <p>This class is stateless and thread-safe because it delegates identifier creation
 * to {@link UUID#randomUUID()}.
 * @author jsanca & elo
 */
public final class DownloadIdGenerator {

    /**
     * Creates a new utility class instance.
     *
     * <p>This constructor is intentionally private because this class only exposes
     * static behavior.
     */
    private DownloadIdGenerator() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    /**
     * Generates a new unique identifier for a download.
     *
     * @return a randomly generated unique identifier as a string
     */
    public static String generateId() {
        return UUID.randomUUID().toString();
    }
}
