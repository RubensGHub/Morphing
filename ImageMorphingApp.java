import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ImageMorphingApp extends Application {

    private ImageView imageDebut;
    private ImageView imageFin;
    private ImageView imageMorphing;

    private Image image_base;
    private Image nouvelle_image;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Image Morphing App");

        imageDebut = new ImageView();
        imageDebut.setFitWidth(200);
        imageDebut.setFitHeight(200); 
        imageFin = new ImageView();
        imageFin.setFitWidth(200); 
        imageFin.setFitHeight(200);
        imageMorphing = new ImageView();
        imageMorphing.setFitWidth(200); 
        imageMorphing.setFitHeight(200); 

        Button bouton1 = new Button("Choisir image de départ");
        bouton1.setOnAction(e -> selectImage(imageDebut, true));

        Button bouton2 = new Button("Choisir autre image");
        bouton2.setOnAction(e -> selectImage(imageFin, false));

        Button boutonCentre = new Button("Centrer image de départ");
        boutonCentre.setOnAction(e -> centerImage());

        Button boutonMorphing = new Button("Morphing");
        // On pourra ajouter ici la méthode pour le morphing par exemple

        Button boutonAfficher = new Button("Afficher points");
        // On pourra ajouter ici la méthode pour afficher les points par exemple

        Button boutonRetirer = new Button("Retirer points");
        // On pourra ajouter ici la méthode pour retirer les points par exemple

        HBox imageSelectionBox = new HBox(10, bouton1, bouton2);
        imageSelectionBox.setAlignment(Pos.CENTER);

        HBox buttonsBox = new HBox(10, boutonCentre, boutonMorphing, boutonAfficher, boutonRetirer);

        VBox topBox = new VBox(10, imageSelectionBox, new Region(), new Region()); // Espaces vides entre boutons et images avec region
        topBox.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setTop(topBox);
        root.setBottom(buttonsBox);
        root.setLeft(imageDebut);
        root.setRight(imageFin);

        VBox centerBox = new VBox(10, imageMorphing);
        centerBox.setAlignment(Pos.CENTER);
        root.setCenter(centerBox);

        Scene scene = new Scene(root, 1000, 600); 
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void selectImage(ImageView imageView, boolean isFirstImage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            imageView.setImage(image);
            imageView.setFitWidth(200); 
            imageView.setFitHeight(200); 
            if (isFirstImage) {
                image_base = image;
            } else {
                nouvelle_image = image;
            }
        }
    }

    private void centerImage() {
        if (image_base != null) {
            ImageView centeredImageView = new ImageView(image_base);
            centeredImageView.setFitWidth(200); 
            centeredImageView.setFitHeight(200); 
            centeredImageView.setPreserveRatio(true);
            centeredImageView.setSmooth(true);
            centeredImageView.setCache(true);
            imageMorphing.setImage(null);
            ((VBox) imageMorphing.getParent()).getChildren().set(0, centeredImageView);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
