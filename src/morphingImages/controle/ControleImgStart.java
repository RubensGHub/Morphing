package morphingImages.controle;

import java.util.Observable;
import java.util.Observer;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import morphingImages.abstraction.MorphingApp;
import commun.ImageT;

@SuppressWarnings("deprecation")
public class ControleImgStart implements Observer {

    private MorphingApp app;
    private ImageView ivStart;

    /**
     * Constructeur du contrôleur pour l'image source.
     * 
     * @param app     L'application de morphing associée.
     * @param ivStart L'ImageView pour afficher l'image source.
     */
    public ControleImgStart(MorphingApp app, ImageView ivStart) {
        this.app = app;
        this.ivStart = ivStart;
    }

    /**
     * Met à jour l'ImageView de l'image source en fonction des changements observés.
     * 
     * @param o   L'observable qui a changé.
     * @param arg L'argument passé par l'observable lors de la notification.
     */
    @Override
    public void update(Observable o, Object arg) {
        // Création de ImageView pour affichage
        ImageT imgSrc = app.getImgSrc();
        if (imgSrc != null) {
            Image image = SwingFXUtils.toFXImage(imgSrc.getImage(), null);
            ivStart.setImage(image);
            ivStart.setFitWidth(550);
            ivStart.setFitHeight(550);
        }
    }
}
