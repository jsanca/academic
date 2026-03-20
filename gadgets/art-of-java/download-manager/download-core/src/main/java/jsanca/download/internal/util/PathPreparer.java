package jsanca.download.internal.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

/**
 * Utility class responsible for preparing filesystem paths required by a download.
 *
 * <p>This helper ensures that the parent directory for a target file exists before
 * the download starts. It does not create the target file itself.
 *
 * <p>This class is stateless and thread-safe.
 * @author jsanca & elo
 */
public final class PathPreparer {

    private static final Logger log = LoggerFactory.getLogger(PathPreparer.class);

    /**
     * Creates a new utility class instance.
     *
     * <p>This constructor is intentionally private because this class only exposes
     * static behavior.
     */
    private PathPreparer() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    /**
     * Ensures that the parent directory of the provided target path exists.
     *
     * <p>If the target path has no parent directory, this method does nothing.
     * If the parent directory does not exist, it is created along with any missing
     * intermediate directories.
     *
     * @param targetPath the target file path for the download
     * @throws NullPointerException if {@code targetPath} is {@code null}
     * @throws IOException if the parent directory cannot be created
     */
    public static void prepareParentDirectory(final Path targetPath) throws IOException {

        Objects.requireNonNull(targetPath, "targetPath must not be null");

        final Path parent = targetPath.getParent();
        if (parent == null) {
            log.debug("Target path {} has no parent directory. Nothing to prepare.", targetPath);
            return;
        }

        if (Files.exists(parent)) {
            log.debug("Parent directory already exists: {}", parent);
            return;
        }

        log.info("Creating parent directory structure for target path: {}", parent);
        Files.createDirectories(parent);
    }
}
