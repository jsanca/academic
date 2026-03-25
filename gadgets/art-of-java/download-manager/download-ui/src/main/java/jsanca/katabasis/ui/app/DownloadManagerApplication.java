package jsanca.katabasis.ui.app;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Entry point for the Katabasis UI application.
 * @author jsanca
 */
public final class DownloadManagerApplication extends Application {

    /**
     * Launches the JavaFX application.
     *
     * @param args application arguments
     */
    public static void main(final String[] args) {
        launch(args);
    }

    /**
     * Initializes and displays the primary application window.
     *
     * @param primaryStage primary JavaFX stage
     */
    @Override
    public void start(final Stage primaryStage) {
        final Label titleLabel = new Label("Katabasis Download Manager");
        final Label subtitleLabel = new Label("UI bootstrap ready. Next step: connect the core download manager.");
        final Button testButton = new Button("UI is alive");
        testButton.setOnAction(event -> subtitleLabel.setText("JavaFX started successfully. Ready for the next atom."));

        final VBox root = new VBox(12, titleLabel, subtitleLabel, testButton);
        root.setPadding(new Insets(16));

        final Scene scene = new Scene(root, 640, 240);

        primaryStage.setTitle("Katabasis");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
