package morphingFormesArrondies.controle;

import java.util.Observable;
import java.util.Observer;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import morphingFormesArrondies.abstraction.*;

/**
 * Cette classe représente le contrôleur de l'image de destination dans l'affichage du morphing
 * de formes arrondies.
 * Elle implémente l'interface Observer pour surveiller les changements de l'application.
 * 
 * Lorsqu'un changement est détecté, le contrôleur met à jour l'image de destination affichée dans
 * l'interface utilisateur.
 */
@SuppressWarnings("deprecation")
public class ControleImgEndCourbe implements Observer {
	
	private morphingSpline app;
	private ImageView ivEnd;
	
	/**
     * Constructeur de la classe ControleImgEndCourbe.
     * @param app L'application morphingSpline à observer.
     * @param ivEnd L'ImageView représentant l'image de destination dans l'interface utilisateur.
     */
	public ControleImgEndCourbe(morphingSpline app, ImageView ivEnd) {
		this.app = app;
		this.ivEnd = ivEnd;
	}
	
	/**
     * Méthode appelée lorsqu'un objet observé notifie un changement.
     * Elle met à jour l'image de destination affichée dans l'interface utilisateur.
     * @param o L'objet observé.
     * @param arg L'argument (donnée) transmis par l'objet observé lors de la notification.
     */
	@Override
	public void update(Observable o, Object arg) {
		// Création de ImageView pour affichage
		ImageS imgDest = app.getImgDest();
		if (imgDest != null)
		{
			Image image = SwingFXUtils.toFXImage(imgDest.getImage(), null);
			ivEnd.setImage(image);
			ivEnd.setFitWidth(550);
			ivEnd.setFitHeight(550);
		}
	}
}
