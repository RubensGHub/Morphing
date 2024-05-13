import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AccueilApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Button startButton = new Button("Start");
        startButton.setOnAction(event -> afficherSecondEcran(primaryStage));

        StackPane root = new StackPane();
        root.getChildren().add(startButton);
        Scene scene = new Scene(root, 600, 400);

        primaryStage.setTitle("Application de Morphing");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void afficherSecondEcran(Stage primaryStage) {
        DeuxiemeApp secondScreen = new DeuxiemeApp();
        try {
            secondScreen.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
