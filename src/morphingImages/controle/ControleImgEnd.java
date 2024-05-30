package morphingImages.controle;

import java.util.Observable;
import java.util.Observer;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import morphingImages.abstraction.MorphingApp;
import commun.ImageT;

@SuppressWarnings("deprecation")
public class ControleImgEnd implements Observer {

    private MorphingApp app;
    private ImageView ivEnd;

    /**
     * Constructeur du contrôleur pour l'image de destination.
     * 
     * @param app   L'application de morphing associée.
     * @param ivEnd L'ImageView pour afficher l'image de destination.
     */
    public ControleImgEnd(MorphingApp app, ImageView ivEnd) {
        this.app = app;
        this.ivEnd = ivEnd;
    }

    /**
     * Met à jour l'ImageView de l'image de destination en fonction des changements observés.
     * 
     * @param o   L'observable qui a changé.
     * @param arg L'argument passé par l'observable lors de la notification.
     */
    @Override
    public void update(Observable o, Object arg) {
        // Création de ImageView pour affichage
        ImageT imgDest = app.getImgDest();
        if (imgDest != null) {
            Image image = SwingFXUtils.toFXImage(imgDest.getImage(), null);
            ivEnd.setImage(image);
            ivEnd.setFitWidth(550);
            ivEnd.setFitHeight(550);
        }
    }
}
