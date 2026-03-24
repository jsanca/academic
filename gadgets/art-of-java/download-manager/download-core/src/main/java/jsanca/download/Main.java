package jsanca.download;


import jsanca.download.api.event.DownloadEvent;
import jsanca.download.api.manager.DownloadManager;
import jsanca.download.api.manager.DownloadManagers;
import jsanca.download.api.model.DownloadRequest;
import jsanca.download.api.model.DownloadSubmissionResult;

import java.net.URI;
import java.nio.file.Path;

/**
 * Simple entry point to test the DownloadManager.
 * @author jsanca & elo
 */
public class Main {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: java Main <url> <targetPath>");
            return;
        }

        final String urlToDownload = args[0];
        final String localPathToDownload = args[1];
        final DownloadManager manager = DownloadManagers.createDefault();

        manager.addListener(System.out::println);

        final DownloadRequest request = new DownloadRequest(
                URI.create(urlToDownload),
                Path.of(localPathToDownload)
        );

        final DownloadSubmissionResult result = manager.download(request);
        System.out.println("Result: " + result);

        // Prevent main thread from exiting immediately (simple approach)
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
