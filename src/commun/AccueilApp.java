package commun;

import morphingImages.presentation.*;
import morphingFormesSimples.presentation.*;
import morphingFormesArrondies.presentation.*;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Classe de la fenêtre d'accueil de l'application de morphing. On peut y choisir le mode de morphing souhaité.
 */
@SuppressWarnings("deprecation")
public class AccueilApp extends Application {
    
    /**
     * Méthode principale qui démarre l'application.
     * @param args Les arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Méthode appelée au démarrage de l'application.
     * @param primaryStage La scène principale de l'application.
     * @throws Exception En cas d'erreur lors du démarrage de l'application.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Button boutonBeier = new Button("Morphing d'images");
        Button boutonSimple = new Button("Morphing Simple");
        Button boutonCourbe = new Button("Morphing Simple Arrondie");
        boutonBeier.getStyleClass().add("boutonBeier");
        boutonSimple.getStyleClass().add("boutonSimple");
        boutonCourbe.getStyleClass().add("boutonCourbe");
        boutonSimple.setOnAction(event -> afficherAppSimple(primaryStage));
        boutonCourbe.setOnAction(event -> afficherAppCourbe(primaryStage));
        boutonBeier.setOnAction(event -> afficherAppBeier(primaryStage));


        Label phrase = new Label("Pour commencer le morphing, appuyez sur le morphing que vous souhaitez utiliser");
        phrase.getStyleClass().add("phrase");

        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(15), Insets.EMPTY)));
        vbox.setPadding(new Insets(50)); 
        vbox.getStyleClass().add("vboxAccueil");
        vbox.getChildren().addAll(phrase, boutonSimple, boutonCourbe, boutonBeier);

        StackPane root = new StackPane();
        root.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        root.getChildren().add(vbox);

        Scene scene = new Scene(root, 900, 500);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("commun/style.css").toExternalForm());
        
        primaryStage.setTitle("Application de Morphing");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    /**
     * Passe à la fenêtre du morphing simple.
     * @param primaryStage La scène principale.
     */
    private void afficherAppSimple(Stage primaryStage) {
        Stage secondStage = new Stage();
        AppSimple secondScreen = new AppSimple();
        try {
            secondScreen.start(secondStage);
            primaryStage.close(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Passe à la fenêtre du morphing de formes unies arrondies.
     * @param primaryStage La scène principale.
     */
    private void afficherAppCourbe(Stage primaryStage) {
        Stage secondStage = new Stage();
        AppCourbe secondScreen = new AppCourbe();
        try {
            secondScreen.start(secondStage);
            primaryStage.close(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Passe à la fenêtre du morphing d'images (utilisant l'algorithme de Beier)
     * @param primaryStage La scène principale.
     */
    private void afficherAppBeier(Stage primaryStage) {
        Stage secondStage = new Stage();
        AppBeier secondScreen = new AppBeier();
        try {
            secondScreen.start(secondStage);
            primaryStage.close(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
