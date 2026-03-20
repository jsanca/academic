package jsanca.download.internal.util;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class responsible for resolving a file name for a download target.
 *
 * <p>This resolver derives a file name from a URL path when possible. If the URL does not
 * contain a usable file name, a default fallback name is returned instead.
 *
 * <p>This class is stateless and thread-safe.
 * @author jsanca & elo
 */
public final class FileNameResolver {

    private static final Logger log = LoggerFactory.getLogger(FileNameResolver.class);

    /**
     * Default file name used when the source URL does not expose a file name.
     */
    public static final String DEFAULT_FILE_NAME = "download.bin";

    /**
     * Creates a new utility class instance.
     *
     * <p>This constructor is intentionally private because this class only exposes
     * static behavior.
     */
    private FileNameResolver() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    /**
     * Resolves a file name from the provided URL string.
     *
     * <p>If the URL path ends with a valid file name, that value is returned. Otherwise,
     * {@link #DEFAULT_FILE_NAME} is returned.
     *
     * @param url the source URL string
     * @return the resolved file name, or a default fallback name when none can be derived
     * @throws NullPointerException if the URL is {@code null}
     * @throws IllegalArgumentException if the URL cannot be parsed as a valid URI
     */
    public static String resolveFileName(final String url) {

        Objects.requireNonNull(url, "url must not be null");

        try {
            final URI uri = URI.create(url);
            final String path = uri.getPath();

            if (path == null || path.isBlank() || path.endsWith("/")) {
                log.debug("No valid path found in URL: {}. Using default file name.", url);
                return DEFAULT_FILE_NAME;
            }

            final Path filePath = Paths.get(path);
            final Path fileName = filePath.getFileName();

            if (fileName == null) {
                log.debug("No file name could be extracted from URL: {}. Using default.", url);
                return DEFAULT_FILE_NAME;
            }

            final String resolved = fileName.toString().trim();

            if (resolved.isEmpty()) {
                log.debug("Resolved file name is empty for URL: {}. Using default.", url);
                return DEFAULT_FILE_NAME;
            }

            return resolved;
        } catch (IllegalArgumentException ex) {
            log.warn("Invalid download URL: {}", url, ex);
            throw new IllegalArgumentException("Invalid download URL: " + url, ex);
        }
    }
}
