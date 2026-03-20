package jsanca.download.api.model;

/**
 * Encapsulates the app configuration
 * @param maxConcurrentDownloads
 * @author jsanca & elo
 */
public record DownloadConfig(
        int maxConcurrentDownloads
) {
    public static DownloadConfig defaultConfig() {
        return new DownloadConfig(4);
    }
}
