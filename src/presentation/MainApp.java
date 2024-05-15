package presentation;

import java.io.File;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Slider;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class MainApp extends Application {
    
    private ImageView imageDebut;
    private ImageView imageFin;
    private ImageView imageMorphing;

    private Image image_base;
    private Image nouvelle_image;
    
    private Circle point;

    @Override
    public void start(Stage primaryStage) {
        imageDebut = new ImageView();
        imageDebut.setFitWidth(400);
        imageDebut.setFitHeight(400); 
        imageFin = new ImageView();
        imageFin.setFitWidth(400); 
        imageFin.setFitHeight(400);
        imageMorphing = new ImageView();
        imageMorphing.setFitWidth(400); 
        imageMorphing.setFitHeight(400);
        
        Slider slider = new Slider();
        slider.setMin(1); 
        slider.setMax(50); 
        slider.setValue(1);
        Label valeurSlider = new Label("Images sélectionnées : " + String.valueOf((int) slider.getValue()));
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            valeurSlider.setText("Images sélectionnées : " + newValue.intValue());
        });

        
        Rectangle rectangleGauche = creerRectangle(Color.BLACK);
        Rectangle rectangleDroite = creerRectangle(Color.BLACK);
        Rectangle rectangleMorphing = creerRectangle(Color.BLACK);

        Button bouton1 = creerBouton("Morphing");
        Button bouton2 = creerBouton("Ajouter Point");
        Button bouton3 = creerBouton("Retirer Point");

        VBox vBoxBoutons = new VBox(10);
        vBoxBoutons.getChildren().addAll(bouton1, bouton2, bouton3,slider,valeurSlider);
        vBoxBoutons.setAlignment(Pos.CENTER);

        HBox hBoxBoutonsHaut = new HBox(950);
        Button boutonHaut1 = creerBouton("Image de départ");
        boutonHaut1.setOnAction(e -> selectImage(imageDebut, true));
        Button boutonHaut2 = creerBouton("Image de fin");
        boutonHaut2.setOnAction(e -> selectImage(imageFin, false));
        hBoxBoutonsHaut.getChildren().addAll(boutonHaut1, boutonHaut2);
        hBoxBoutonsHaut.setAlignment(Pos.TOP_CENTER);
        hBoxBoutonsHaut.setPadding(new Insets(50));

        StackPane stackPaneGauche = new StackPane();
        stackPaneGauche.getChildren().addAll(rectangleGauche, imageDebut);
        stackPaneGauche.setAlignment(Pos.CENTER);

        StackPane stackPaneDroite = new StackPane();
        stackPaneDroite.getChildren().addAll(rectangleDroite, imageFin);
        stackPaneDroite.setAlignment(Pos.CENTER);

        StackPane stackPaneMorphing = new StackPane();
        stackPaneMorphing.getChildren().addAll(rectangleMorphing, imageMorphing);
        stackPaneMorphing.setAlignment(Pos.CENTER);

        HBox hBoxGauche = new HBox();
        hBoxGauche.getChildren().add(stackPaneGauche);
        hBoxGauche.setAlignment(Pos.CENTER);
        HBox.setHgrow(hBoxGauche, Priority.ALWAYS);

        HBox hBoxDroite = new HBox();
        hBoxDroite.getChildren().add(stackPaneDroite);
        hBoxDroite.setAlignment(Pos.CENTER);
        HBox.setHgrow(hBoxDroite, Priority.ALWAYS);

        HBox hBox = new HBox(100);
        hBox.getChildren().addAll(hBoxGauche, vBoxBoutons, hBoxDroite);
        hBox.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(hBox, stackPaneMorphing);
        vBox.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setTop(hBoxBoutonsHaut);
        root.setCenter(vBox);
        BorderPane.setMargin(hBox, new Insets(50, 0, 0, 0));

        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        root.setBackground(background);
        Scene scene = new Scene(root, 1500, 1500);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Application de Morphing");
        primaryStage.show();
    }

    private Button creerBouton(String texte) {
        Button bouton = new Button(texte);
        bouton.setStyle("-fx-background-color: #317AC1; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10 20 10 20; -fx-border-radius: 5; -fx-background-radius: 5;");
        bouton.setMinWidth(170);
        bouton.setPrefWidth(170);
        bouton.setMaxWidth(200);
        return bouton;
    }

    private Rectangle creerRectangle(Color color) {
        Rectangle rectangle = new Rectangle(400, 400);
        rectangle.setStroke(color);
        rectangle.setFill(null);
        rectangle.setStrokeWidth(5);
        return rectangle;
    }

    private void selectImage(ImageView imageView, boolean isFirstImage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            imageView.setImage(image);
            imageView.setFitWidth(400);
            imageView.setFitHeight(400);
            if (isFirstImage) {
                image_base = image;
            } else {
                nouvelle_image = image;
            }
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}