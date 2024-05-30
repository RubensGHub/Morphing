package morphingFormesArrondies.controle;

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

import morphingFormesArrondies.abstraction.*;

/**
 * Cette classe représente le contrôleur du bouton pour la sélection de l'image de destination
 * dans le processus de morphing de formes arrondies.
 * Elle implémente l'interface Observer pour surveiller les changements et l'interface EventHandler
 * pour gérer les événements du bouton.
 * 
 * Lorsqu'un événement de bouton est déclenché, le contrôleur ouvre une boîte de dialogue pour
 * permettre à l'utilisateur de choisir une image. L'image sélectionnée est alors chargée et ajoutée
 * à l'application morphingSpline comme image de destination.
 */
@SuppressWarnings("deprecation")
public class ControleBoutonEndCourbe implements Observer, EventHandler<ActionEvent> {

	private morphingSpline app;
	
	/**
     * Constructeur de la classe ControleBoutonEndCourbe.
     * @param app L'application morphingSpline à contrôler.
     */
	public ControleBoutonEndCourbe(morphingSpline app) {
        this.app = app;
    }
	
	/**
     * Méthode de gestion des événements du bouton.
     * Elle est appelée lorsqu'un événement de bouton est déclenché.
     * @param event L'événement déclenché.
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
     * Méthode appelée lorsqu'un objet observé notifie un changement.
     * Actuellement, cette méthode est vide car elle n'est pas utilisée dans cette classe.
     * @param o L'objet observé.
     * @param arg L'argument (donnée) transmis par l'objet observé lors de la notification.
     */
	@Override
    public void update(Observable o, Object arg) {

    }
    
    /**
     * Méthode pour récupérer l'extension d'un fichier.
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
     * Méthode pour sélectionner une image et l'ajouter à l'application.
     * @param app L'application morphingSpline.
     * @throws IOException En cas d'erreur d'entrée/sortie lors de la lecture de l'image.
     */
	private void selectImage(morphingSpline app) throws IOException {
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
                // Création de BufferedImage pour récupérer taille et construire ImageS
                BufferedImage bImg = ImageIO.read(selectedFile);
                int w = bImg.getWidth();
                int h = bImg.getHeight();
                
                // Ajout de l'image à notre App
                ImageS imgS = new ImageS(bImg, w, h, getExtension(selectedFile));
                app.setImgDest(imgS);
            }
            else {
                System.err.println("Seules les images sont acceptées.");
            }
        }
    }
}
