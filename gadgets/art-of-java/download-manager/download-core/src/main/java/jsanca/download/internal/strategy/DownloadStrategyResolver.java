package jsanca.download.internal.strategy;

import jsanca.download.api.model.DownloadInfo;

import java.util.Objects;

/**
 * Resolves the appropriate {@link DownloadStrategy} for a given download.
 *
 * <p>This abstraction allows the system to select a strategy based on the
 * characteristics of the download (e.g., protocol, URI scheme, or other
 * metadata) without coupling the manager to specific implementations.
 * @author jsanca + elo
 */
public interface DownloadStrategyResolver {

    /**
     * Resolves a {@link DownloadStrategy} for the given {@link DownloadInfo}.
     *
     * @param info the download information
     * @return the resolved strategy
     * @throws NullPointerException if {@code info} is {@code null}
     * @throws IllegalArgumentException if no suitable strategy can be found
     */
    DownloadStrategy resolve(DownloadInfo info);

    /**
     * Validates that the provided {@link DownloadInfo} is not {@code null}.
     *
     * @param info the download information
     */
    static void validate(DownloadInfo info) {
        Objects.requireNonNull(info, "info must not be null");
    }
}
