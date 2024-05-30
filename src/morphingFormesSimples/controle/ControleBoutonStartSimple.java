package morphingFormesSimples.controle;

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

import morphingFormesSimples.abstraction.FormesUniesPolygonales;
import commun.ImageT;

@SuppressWarnings("deprecation")
/**
 * Contrôleur pour le bouton de sélection de l'image de départ.
 * Il implémente l'interface Observer pour suivre les changements de l'application.
 */
public class ControleBoutonStartSimple implements Observer, EventHandler<ActionEvent> {
    
    private FormesUniesPolygonales app;

    /**
     * Constructeur du contrôleur.
     * 
     * @param app L'application à contrôler.
     */
    public ControleBoutonStartSimple(FormesUniesPolygonales app) {
        this.app = app;
    }

    /**
     * Gère l'événement du clic sur le bouton de sélection de l'image de départ.
     * 
     * @param event L'événement associé au clic sur le bouton.
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
     * Méthode de mise à jour appelée lorsqu'un changement est observé dans l'application.
     * 
     * @param o   L'observable (l'application).
     * @param arg L'argument (non utilisé ici).
     */
    @Override
    public void update(Observable o, Object arg) {
        
    }
    

    /**
     * Récupère l'extension du fichier.
     * 
     * @param f Le fichier dont on veut récupérer l'extension.
     * @return L'extension du fichier.
     */
    private static String getExtension(File f) {
        int i = f.getName().lastIndexOf(".");
        if (i > 0) {
            return f.getName().substring(i + 1);
        }
        return null;
    }
	
    /**
     * Sélectionne une image et l'ajoute à l'application en tant qu'image de départ.
     * 
     * @param app L'application à laquelle ajouter l'image.
     * @throws IOException Si une erreur d'entrée/sortie se produit lors de la lecture de l'image.
     */
	private void selectImage(FormesUniesPolygonales app) throws IOException {
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

                // Ajout de l'image à l'application
                ImageT imgT = new ImageT(bImg, w, h, getExtension(selectedFile));
                app.setImgSrc(imgT);
            }
            
            else {
                System.err.println("Seules les images sont acceptées.");
            }
        }
    }
}
