package presentation;

import morphing.*;
import controle.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

@Deprecated
public class MainApp extends Application {

    private int wImgMax = 550;
    private int hImgMax = 550;
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

        // CADRES IMAGE
        Rectangle cadreStart = newRectangle(wImgMax, hImgMax, Color.BLACK);
        Rectangle cadreEnd = newRectangle(wImgMax, hImgMax, Color.BLACK);

        // BOUTONS
        Button buttonAddImgStart = newButton("Ajouter");
        buttonAddImgStart.getStyleClass().add("bouton");
        Button buttonAddImgEnd = newButton("Ajouter");
        buttonAddImgEnd.getStyleClass().add("bouton");
        Button buttonGen = newButton("Morphing");
        buttonGen.getStyleClass().add("bouton");

        // ZONE IMAGE LEFT
        StackPane zoneImgLeft = new StackPane();
        zoneImgLeft.getChildren().add(cadreStart);
        zoneImgLeft.getChildren().add(ivStart);
        Canvas canvasLeft = new Canvas(wImgMax, hImgMax);
        zoneImgLeft.getChildren().add(canvasLeft);

        // ZONE IMAGE RIGHT
        StackPane zoneImgRight = new StackPane();
        zoneImgRight.getChildren().add(cadreEnd);
        zoneImgRight.getChildren().add(ivEnd);
        Canvas canvasRight = new Canvas(wImgMax, hImgMax);
        zoneImgRight.getChildren().add(canvasRight);

        // VBOX LEFT
        VBox vBoxLeft = new VBox(10);
        vBoxLeft.getStyleClass().add("vb");
        vBoxLeft.getChildren().add(zoneImgLeft);
        vBoxLeft.getChildren().add(buttonAddImgStart);

        // VBOX CENTER
        VBox vBoxCenter = new VBox(10);
        vBoxCenter.getStyleClass().add("vb");
        vBoxCenter.getChildren().add(slider);
        vBoxCenter.getChildren().add(valSlider);
        vBoxCenter.getChildren().add(buttonGen);

        // VBOX RIGHT
        VBox vBoxRight = new VBox(10);
        vBoxRight.getStyleClass().add("vb");
        vBoxRight.getChildren().add(zoneImgRight);
        vBoxRight.getChildren().add(buttonAddImgEnd);

        // VBOX TOP
        VBox vBoxTop = new VBox(10);
        vBoxTop.setId("header");
        Text title = new Text("Morphing");
        title.setId("h1");
        vBoxTop.getChildren().add(title);
        Text subTitle = new Text("By CY Tech");
        subTitle.setId("h2");
        vBoxTop.getChildren().add(subTitle);

        // VBOX TOP
        VBox vBoxBottom = new VBox(10);
        vBoxBottom.setId("footer");
        Text credits = new Text("Authors : Romain, Ryan, Paul, Rubens, Alexandre");
        vBoxBottom.getChildren().add(credits);
        Text date = new Text("2024");
        vBoxBottom.getChildren().add(date);

        // ROOT
        BorderPane root = new BorderPane();
        root.setId("body");
        root.setLeft(vBoxLeft);
        root.setCenter(vBoxCenter);
        root.setRight(vBoxRight);
        root.setTop(vBoxTop);
        root.setBottom(vBoxBottom);

        // SCENE
        Scene scene = new Scene(root, 1500, 1500);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Application de Morphing");
        primaryStage.show();






        // CONTROLEURS
        ControleSlider cs = new ControleSlider(app, slider, valSlider);
        slider.valueProperty().addListener(cs);
        app.addObserver(cs);
        

        buttonAddImgStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    selectImage(app, ivStart);
                } catch (IOException e) {
                    
                }
            }
        });

        buttonAddImgEnd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    selectImage(app, ivEnd);
                } catch (IOException e) {
                    
                }
            }
        });










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
        return valSlider;
    }

    private Button newButton(String texte) {
        Button bouton = new Button(texte);
        return bouton;
    }

    private Rectangle newRectangle(int w, int h, Color color) {
        int sizeBorder = 5;
        Rectangle rectangle = new Rectangle(w+sizeBorder, h+sizeBorder);
        rectangle.setStroke(color);
        rectangle.setFill(Color.WHITE);
        rectangle.setStrokeWidth(sizeBorder);
        return rectangle;
    }

    private static String getExtension(File f)
    {
        int i = f.getName().lastIndexOf(".");
        if (i > 0) {
            return f.getName().substring(i+1);
        }
        
        return null;
    }

    private void selectImage(MorphingApp app, ImageView imageView) throws IOException {
        // Définition des extension acceptées
        HashSet<String> ext = new HashSet<String>();
        ext.add("jpg");
        ext.add("jpeg");
        ext.add("png");
        ext.add("gif");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        File selectedFile = fileChooser.showOpenDialog(null);

        // Il a bien choisi un fichier
        if (selectedFile != null) {
            // Extension valide
            if (ext.contains(getExtension(selectedFile))) {

                // Création de ImageView pour affichage
                Image image = new Image(selectedFile.toURI().toString());
                imageView.setImage(image);

                // Création de BufferedImage pour récupérer taille et construire ImageT
                BufferedImage bImg = ImageIO.read(selectedFile);
                int w = bImg.getWidth();
                int h = bImg.getHeight();

                /*
                 * Proportionnalité (produit en croix)
                 * w -> wImgMax
                 * h -> ?
                 */
                if (w > h)
                {
                    h = h * wImgMax / w;
                    w = wImgMax;
                }

                /*
                 * Proportionnalité (produit en croix)
                 * w -> ?
                 * h -> hImgMax
                 */
                else
                {
                    w = w * hImgMax / h;
                    h = hImgMax;
                }

                // Affectation pour l'interface
                imageView.setFitWidth(w);
                imageView.setFitHeight(h);

                // Ajout de l'image à notre App
                ImageT imgT = new ImageT(bImg, w, h, getExtension(selectedFile));
                app.setImgSrc(imgT);
            }

            else {
                System.err.println("Seule les images sont acceptées.");
            }
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
