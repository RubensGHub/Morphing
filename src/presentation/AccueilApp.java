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

@Deprecated
public class AccueilApp extends Application {
	
    @Override
    public void start(Stage primaryStage) throws Exception {
        Button boutonStart = new Button("Start");
        boutonStart.getStyleClass().add("boutonStart");
        boutonStart.setOnAction(event -> afficherSecondEcran(primaryStage));

        Label phrase = new Label("Pour commencer le morphing, appuyez sur le bouton start");
        phrase.getStyleClass().add("phrase");

        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(15), Insets.EMPTY)));
        vbox.setPadding(new Insets(50)); 
        vbox.getStyleClass().add("vboxAccueil");
        vbox.getChildren().addAll(phrase, boutonStart);

        StackPane root = new StackPane();
        root.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        root.getChildren().add(vbox);

        Scene scene = new Scene(root, 700, 400);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        
        primaryStage.setTitle("Application de Morphing");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void afficherSecondEcran(Stage primaryStage) {
        Stage secondStage = new Stage();
        MainApp secondScreen = new MainApp();
        try {
            secondScreen.start(secondStage);
            primaryStage.close(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}