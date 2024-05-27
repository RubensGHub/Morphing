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

@SuppressWarnings("deprecation")
public class AccueilApp extends Application {
	
    @Override
    public void start(Stage primaryStage) throws Exception {
        Button boutonBeier = new Button("Morphing d'images");
        Button boutonSimple = new Button("Morphing Simple");
        boutonBeier.getStyleClass().add("boutonBeier");
        boutonSimple.getStyleClass().add("boutonSimple");
        boutonBeier.setOnAction(event -> afficherSecondEcran(primaryStage));
        boutonSimple.setOnAction(event -> afficherSecondEcran2(primaryStage));


        Label phrase = new Label("Pour commencer le morphing, appuyez sur le bouton start");
        phrase.getStyleClass().add("phrase");

        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(15), Insets.EMPTY)));
        vbox.setPadding(new Insets(50)); 
        vbox.getStyleClass().add("vboxAccueil");
        vbox.getChildren().addAll(phrase, boutonSimple, boutonBeier);

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
        AppBeier secondScreen = new AppBeier();
        try {
            secondScreen.start(secondStage);
            primaryStage.close(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void afficherSecondEcran2(Stage primaryStage) {
        Stage secondStage = new Stage();
        AppSimple secondScreen = new AppSimple();
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
