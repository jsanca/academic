package jsanca.download;


import jsanca.download.api.manager.DownloadManager;
import jsanca.download.api.manager.DownloadManagers;
import jsanca.download.api.model.DownloadRequest;

import java.net.URI;
import java.nio.file.Path;

/**
 * Simple entry point to test the DownloadManager.
 */
public class Main {

    public static void main(String[] args) {

        final String urlToDownload = "??";
        final String localPathToDownload = "???";
        final DownloadManager manager = DownloadManagers.createDefault();

        manager.addListener(System.out::println);

        final DownloadRequest request = new DownloadRequest(
                URI.create(urlToDownload),
                Path.of(localPathToDownload)
        );

        manager.submit(request);

        // Prevent main thread from exiting immediately (simple approach)
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
