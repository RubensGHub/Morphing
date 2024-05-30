package morphingImages.controle;

import morphingImages.abstraction.MorphingApp;
import commun.ImageT;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;

@SuppressWarnings("deprecation")
public class ControleBoutonEnd implements Observer, EventHandler<ActionEvent> {

    private MorphingApp app;

    /**
     * Constructeur du contrôleur.
     * 
     * @param app L'application de morphing associée.
     */
    public ControleBoutonEnd(MorphingApp app) {
        this.app = app;
    }

    /**
     * Gère l'événement lorsqu'un utilisateur clique sur le bouton pour sélectionner l'image de destination.
     * 
     * @param event L'événement d'action associé au clic sur le bouton.
     */
    @Override
    public void handle(ActionEvent event) {
        try {
            selectImage(app);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Met à jour le contrôleur en réponse aux changements de l'observable.
     * 
     * @param o   L'observable qui a changé.
     * @param arg L'argument passé par l'observable lors de la notification.
     */
    @Override
    public void update(Observable o, Object arg) {
        // Méthode laissée vide car cette classe ne réagit pas aux mises à jour de l'observable.
    }

    private static String getExtension(File f) {
        int i = f.getName().lastIndexOf(".");
        if (i > 0) {
            return f.getName().substring(i + 1);
        }
        return null;
    }
    
    /**
     * Laisse l'utilisateur choisir une image dans ses fichiers.
     * 
     * @param app L'application de morphing associée.
     * @throws IOException En cas d'erreur lors de la lecture de l'image.
     */
    private void selectImage(MorphingApp app) throws IOException {
        // Définition des extensions acceptées
        HashSet<String> ext = new HashSet<String>();
        ext.add("jpg");
        ext.add("jpeg");
        ext.add("png");
        ext.add("gif");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        File selectedFile = fileChooser.showOpenDialog(null);

        // Vérification de la sélection et de l'extension
        if (selectedFile != null) {
            if (ext.contains(getExtension(selectedFile))) {
                // Création de BufferedImage pour récupérer taille et construire ImageT
                BufferedImage bImg = ImageIO.read(selectedFile);
                int w = bImg.getWidth();
                int h = bImg.getHeight();

                // Ajout de l'image à notre App
                ImageT imgT = new ImageT(bImg, w, h, getExtension(selectedFile));
                app.setImgDest(imgT);
            } else {
                System.err.println("Seules les images sont acceptées.");
            }
        }
    }
}
