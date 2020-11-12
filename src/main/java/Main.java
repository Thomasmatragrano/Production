import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Main Driver Class.
 *
 * @author Thomas Matragrano
 */
public class Main extends Application {

    /**
     * Main Initialization.
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Creates Scene for sample.fxml implementation.
     * Implements style.css Style Sheet.
     *
     * @param primaryStage
     * @return void
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root, 710, 420);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setTitle("Production Line Tracker");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
