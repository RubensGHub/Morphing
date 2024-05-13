import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DeuxiemeApp extends Application {
	private ImageView imageDebut;
    private ImageView imageFin;
	
    @Override
    public void start(Stage primaryStage) throws Exception {
        Button imageDepart = new Button("Choisir Image 1");
        Button imageFin = new Button("Choisir Image 2");
        Button boutonMorphing = new Button("Morphing");
        Button ajouterPoint = new Button("Ajouter Point");
        Button retirerPoint = new Button("Retirer Point");

        HBox topBox = new HBox(10); // 10 pixels d'espacement entre les éléments
        topBox.setAlignment(Pos.CENTER);
        topBox.getChildren().addAll(imageDepart, imageFin);

        HBox bottomBox = new HBox(10);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.getChildren().addAll(boutonMorphing, ajouterPoint, retirerPoint);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topBox);
        borderPane.setBottom(bottomBox);

        Scene scene = new Scene(borderPane, 600, 400);

        primaryStage.setTitle("Application de Morphing");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
