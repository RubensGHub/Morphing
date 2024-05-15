package presentation;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AccueilApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Button boutonStart = new Button("Start");
        boutonStart.setStyle(
            "-fx-background-color: linear-gradient(#4CAF50, #2E7D32);" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 18px;" +
            "-fx-padding: 10px 20px;" +
            "-fx-background-radius: 10;"
        );
        boutonStart.setOnAction(event -> afficherSecondEcran(primaryStage));

        Label phrase = new Label("Pour commencer le morphing, appuyez sur le bouton start");
        phrase.setStyle(
            "-fx-font-size: 16px;" +
            "-fx-text-fill: #2E7D32;" +
            "-fx-font-weight: bold;"
        );

        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(15), Insets.EMPTY)));
        vbox.setPadding(new Insets(50)); 
        vbox.setStyle("-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.25), 10, 0, 0, 5);"); // Effet d'ombre 
        vbox.getChildren().addAll(phrase, boutonStart);

        StackPane root = new StackPane();
        root.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        root.getChildren().add(vbox);

        Scene scene = new Scene(root, 700, 400);
        primaryStage.setTitle("Application de Morphing");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void afficherSecondEcran(Stage primaryStage) {
        Stage secondStage = new Stage();
        MainApp secondScreen = new MainApp();
        try {
            secondScreen.start(secondStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}