package jsanca.download.internal.strategy;

import jsanca.download.api.model.DownloadInfo;
import jsanca.download.internal.http.HttpDownloadStrategy;
import jsanca.download.internal.model.Scheme;

import java.util.Locale;
import java.util.Objects;

/**
 * Default implementation of {@link DownloadStrategyResolver}.
 *
 * <p>This resolver currently supports HTTP and HTTPS downloads by delegating to
 * {@link HttpDownloadStrategy}. Additional protocols can be added later without
 * changing the manager orchestration logic.
 * @author jsanca + elo
 */
public final class DefaultDownloadStrategyResolver implements DownloadStrategyResolver {

    private final DownloadStrategy httpDownloadStrategy;

    /**
     * Creates a resolver with the default HTTP strategy.
     */
    public DefaultDownloadStrategyResolver() {
        this(new HttpDownloadStrategy());
    }

    /**
     * Creates a resolver with an explicit HTTP strategy.
     *
     * @param httpDownloadStrategy the strategy used for HTTP and HTTPS downloads
     */
    public DefaultDownloadStrategyResolver(final DownloadStrategy httpDownloadStrategy) {
        this.httpDownloadStrategy = Objects.requireNonNull(httpDownloadStrategy,
                "httpDownloadStrategy must not be null");
    }

    /**
     * Resolves a strategy based on the URI scheme of the download source.
     *
     * @param info the download information
     * @return the resolved download strategy
     * @throws NullPointerException if {@code info} is {@code null}
     * @throws IllegalArgumentException if the URI scheme is missing or unsupported
     */
    @Override
    public DownloadStrategy resolve(final DownloadInfo info) {
        DownloadStrategyResolver.validate(info);

        final String scheme = info.sourceUri().getScheme();
        if (scheme == null || scheme.isBlank()) {
            throw new IllegalArgumentException(
                    "Download source URI must define a scheme: " + info.sourceUri());
        }

        switch (Scheme.from(scheme)) {
            case HTTP, HTTPS -> {
                return httpDownloadStrategy;
            }
            default -> throw new IllegalArgumentException(
                    "No download strategy available for scheme: " + scheme);
        }
    }
}
