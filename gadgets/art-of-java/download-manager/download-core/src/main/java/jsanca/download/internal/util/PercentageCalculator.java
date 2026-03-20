
package jsanca.download.internal.util;

import java.util.Objects;

/**
 * Utility class responsible for calculating download progress percentages.
 *
 * <p>This helper centralizes percentage calculation logic so that progress reporting
 * remains consistent across the downloader components.
 *
 * <p>This class is stateless and thread-safe.
 * @author jsanca & elo
 */
public final class PercentageCalculator {

    /**
     * Creates a new utility class instance.
     *
     * <p>This constructor is intentionally private because this class only exposes
     * static behavior.
     */
    private PercentageCalculator() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    /**
     * Calculates the completion percentage for a download.
     *
     * <p>If {@code totalBytes} is zero or negative, this method returns {@code 0.0}
     * because a meaningful percentage cannot be computed.
     *
     * <p>The returned value is clamped to the range {@code 0.0} to {@code 100.0}.
     * This protects callers from unexpected values when downloaded bytes exceed the
     * reported total size.
     *
     * @param downloadedBytes the number of bytes downloaded so far
     * @param totalBytes the total expected number of bytes
     * @return the completion percentage in the range {@code 0.0} to {@code 100.0}
     */
    public static double calculatePercentage(final long downloadedBytes, final long totalBytes) {
        if (totalBytes <= 0 || downloadedBytes <= 0) {

            return 0.0d;
        }

        final double percentage = ((double) downloadedBytes / totalBytes) * 100.0d;
        return Math.min(100.0d, Math.max(0.0d, percentage));
    }
}
