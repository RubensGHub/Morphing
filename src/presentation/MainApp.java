package presentation;

import morphing.*;

import java.io.File;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
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
//import javafx.scene.input.MouseEvent;
//import javafx.scene.shape.Circle;

public class MainApp extends Application {

    private ImageView ivStart = new ImageView();
    private ImageView ivEnd = new ImageView();

    public ImageView getIvStart() {
        return this.ivStart;
    }

    public void setIvStart(ImageG img) {
        if (img != null)
        {
            Image image = SwingFXUtils.toFXImage(img.getImage(), null);
            ivStart.setImage(image);
        }
    }

    public ImageView getIvEnd() {
        return this.ivEnd;
    }

    public void setIvEnd(ImageG img) {
        if (img != null)
        {
            Image image = SwingFXUtils.toFXImage(img.getImage(), null);
            ivEnd.setImage(image);
        }
    }




    @Override
    public void start(Stage primaryStage) {

        MorphingApp app = new MorphingApp();

        // CREATION IMAGEVIEW (initialement : null)
        this.setIvStart(app.getImgSrc());
        this.setIvEnd(app.getImgDest());

        // SLIDER
        Slider slider = newSlider();
        Label valSlider = newLabelSlider(slider, "Nombre d'images intermédiaires");

        // CADRE IMAGES
        Rectangle cadreStart = newRectangle(550, 550, Color.BLACK);
        Rectangle cadreEnd = newRectangle(550, 550, Color.BLACK);

        // BOUTONS
        Button buttonAddImgStart = newButton("Ajouter");
        buttonAddImgStart.getStyleClass().add("bouton");
        Button buttonAddImgEnd = newButton("Ajouter");
        buttonAddImgEnd.getStyleClass().add("bouton");
        Button buttonGen = newButton("Morphing");
        buttonGen.getStyleClass().add("bouton");

        // VBOX LEFT
        VBox vBoxLeft = new VBox(10);
        vBoxLeft.getStyleClass().add("vb");
        vBoxLeft.getChildren().add(ivStart);
        vBoxLeft.getChildren().add(cadreStart);
        vBoxLeft.getChildren().add(buttonAddImgStart);
        vBoxLeft.setAlignment(Pos.CENTER_LEFT);

        // VBOX CENTER
        VBox vBoxCenter = new VBox(10);
        vBoxCenter.getStyleClass().add("vb");
        vBoxCenter.getChildren().add(slider);
        vBoxCenter.getChildren().add(valSlider);
        vBoxCenter.getChildren().add(buttonGen);
        vBoxCenter.setAlignment(Pos.CENTER);

        // VBOX RIGHT
        VBox vBoxRight = new VBox(10);
        vBoxRight.getStyleClass().add("vb");
        vBoxRight.getChildren().add(ivEnd);
        vBoxRight.getChildren().add(cadreEnd);
        vBoxRight.getChildren().add(buttonAddImgEnd);
        vBoxRight.setAlignment(Pos.CENTER_RIGHT);

        // ROOT
        BorderPane root = new BorderPane();
        root.setLeft(vBoxLeft);
        root.setCenter(vBoxCenter);
        root.setRight(vBoxRight);

        // SCENE
        Scene scene = new Scene(root, 1500, 1500);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Application de Morphing");
        primaryStage.show();
    }

    private Slider newSlider()
    {
        Slider slider = new Slider();
        slider.setMin(1); 
        slider.setMax(50); 
        slider.setValue(1);
        return slider;
    }

    private Label newLabelSlider(Slider slider, String txt)
    {
        Label valSlider = new Label(txt + " : " + String.valueOf((int) slider.getValue()));
        /*
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            valeurSlider.setText("Images sélectionnées : " + newValue.intValue());
        });*/
        return valSlider;
    }

    private Button newButton(String texte) {
        Button bouton = new Button(texte);/*
        bouton.setMinWidth(170);
        bouton.setPrefWidth(170);
        bouton.setMaxWidth(200);*/
        return bouton;
    }

    private Rectangle newRectangle(int w, int h, Color color) {
        Rectangle rectangle = new Rectangle(w, h);
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
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
