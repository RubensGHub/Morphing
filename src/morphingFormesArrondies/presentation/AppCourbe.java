package morphingFormesArrondies.presentation;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import commun.*;
import morphingFormesArrondies.abstraction.*;
import morphingFormesArrondies.controle.*;

/**
 * Cette classe représente l'application de morphing de formes unies arrondies utilisant des B-splines.
 */
@Deprecated
public class AppCourbe extends Application {

    private int wImgMax = 550; // largeur maximale des images.
    private int hImgMax = 550; // Hauteur maximale des images.
    private ImageView ivStart = new ImageView(); // ImageView pour l'image de départ.
    private ImageView ivEnd = new ImageView(); // ImageView pour l'image d'arrivée.

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

     /**
     * Point d'entrée de l'application.
     * @param primaryStage La fenêtre principale de l'application.
     */
    @Override
    public void start(Stage primaryStage) {

        morphingSpline app = new morphingSpline();

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
        Button buttonReturn = new Button("Retour à l'accueil");
        buttonReturn.getStyleClass().add("bouton");

        // ZONE IMAGE LEFT
        StackPane zoneImgLeft = new StackPane();
        zoneImgLeft.getChildren().add(cadreStart);
        zoneImgLeft.getChildren().add(ivStart);
        Canvas canvasLeft = new Canvas(wImgMax, hImgMax);
        zoneImgLeft.getChildren().add(canvasLeft);
        GraphicsContext leftGC = canvasLeft.getGraphicsContext2D();

        // ZONE IMAGE RIGHT
        StackPane zoneImgRight = new StackPane();
        zoneImgRight.getChildren().add(cadreEnd);
        zoneImgRight.getChildren().add(ivEnd);
        Canvas canvasRight = new Canvas(wImgMax, hImgMax);
        zoneImgRight.getChildren().add(canvasRight);
        GraphicsContext rightGC = canvasRight.getGraphicsContext2D();

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
        VBox vBoxRetour = new VBox(10);
        vBoxRetour.getChildren().add(buttonReturn); 
        vBoxTop.getChildren().add(vBoxRetour); 
        vBoxTop.setId("header");
        Text title = new Text("Morphing Courbe");
        title.setId("h1");
        vBoxTop.getChildren().add(title);
        Text subTitle = new Text("By CY Tech");
        subTitle.setId("h2");
        vBoxTop.getChildren().add(subTitle);

        // VBOX BOTTOM
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
        root.getStyleClass().add("body-AppCourbe");

        // SCENE
        Scene scene = new Scene(root, 1500, 1500);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("commun/style.css").toExternalForm());
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Application de Morphing");
        primaryStage.show();






        // CONTROLEURS

        // LEFT
        ControleBoutonStartCourbe cbs = new ControleBoutonStartCourbe(app);
        buttonAddImgStart.setOnAction(cbs);

        ControleImgStartCourbe cis = new ControleImgStartCourbe(app, ivStart);
        app.addObserver(cis);

        ControlePointCourbeImgStart cpcis = new ControlePointCourbeImgStart(app, leftGC);
        canvasLeft.setOnMouseClicked(cpcis);
        app.addObserver(cpcis);
        

        // CENTER
        ControleSliderCourbe cs = new ControleSliderCourbe(app, slider, valSlider);
        slider.valueProperty().addListener(cs);
        app.addObserver(cs);

        ControleBoutonCourbe cbb = new ControleBoutonCourbe(app);
        buttonGen.setOnAction(cbb);
        

        // RIGHT
        ControleBoutonEndCourbe cbe = new ControleBoutonEndCourbe(app);
        buttonAddImgEnd.setOnAction(cbe);
        
        ControleImgEndCourbe cie = new ControleImgEndCourbe(app, ivEnd);
        app.addObserver(cie);

        ControlePointCourbeImgEnd cpcie = new ControlePointCourbeImgEnd(app, rightGC);
        canvasRight.setOnMouseClicked(cpcie);
        app.addObserver(cpcie);

        // CONTROLEUR RETOUR
        buttonReturn.setOnAction(e -> {
            AccueilApp accueilApp = new AccueilApp();
            try {
                accueilApp.start(new Stage());
                primaryStage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }


    
    /**
     * Crée un nouveau Slider avec des paramètres par défaut.
     * @return Un nouveau Slider initialisé avec des paramètres par défaut.
     */
    private Slider newSlider()
    {
        Slider slider = new Slider();
        slider.setMin(1); 
        slider.setMax(50); 
        slider.setValue(1);
        return slider;
    }

    /**
     * Crée une nouvelle étiquette pour le Slider avec un texte donné.
     * @param slider Le Slider auquel l'étiquette est liée.
     * @param txt    Le texte à afficher dans l'étiquette.
     * @return Une nouvelle étiquette liée au Slider avec le texte spécifié.
     */
    private Label newLabelSlider(Slider slider, String txt)
    {
        Label valSlider = new Label(txt + " : " + String.valueOf((int) slider.getValue()));
        return valSlider;
    }

    /**
     * Crée un nouveau bouton avec un texte donné.
     * @param texte Le texte à afficher sur le bouton.
     * @return Un nouveau bouton avec le texte spécifié.
     */
    private Button newButton(String texte) {
        Button bouton = new Button(texte);
        return bouton;
    }

    /**
     * Crée un nouveau Rectangle avec une largeur, une hauteur et une couleur spécifiées.
     * @param w     La largeur du Rectangle.
     * @param h     La hauteur du Rectangle.
     * @param color La couleur du Rectangle.
     * @return Un nouveau Rectangle avec la largeur, la hauteur et la couleur spécifiées.
     */
    private Rectangle newRectangle(int w, int h, Color color) {
        int sizeBorder = 5;
        Rectangle rectangle = new Rectangle(w+sizeBorder, h+sizeBorder);
        rectangle.setStroke(color);
        rectangle.setFill(Color.WHITE);
        rectangle.setStrokeWidth(sizeBorder);
        return rectangle;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
