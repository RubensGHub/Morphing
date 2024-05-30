package morphingFormesArrondies.controle;

import java.util.Observable;
import java.util.Observer;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import morphingFormesArrondies.abstraction.*;

/**
 * Cette classe représente le contrôleur de l'image source dans l'affichage du morphing
 * de formes arrondies.
 * Elle implémente l'interface Observer pour surveiller les changements de l'application.
 * 
 * Lorsqu'un changement est détecté, le contrôleur met à jour l'image source affichée dans
 * l'interface utilisateur.
 */
@SuppressWarnings("deprecation")
public class ControleImgStartCourbe implements Observer {
	
	private morphingSpline app;
	private ImageView ivStart;
	
	/**
     * Constructeur de la classe ControleImgStartCourbe.
     * @param app L'application morphingSpline à observer.
     * @param ivStart L'ImageView représentant l'image source dans l'interface utilisateur.
     */
	public ControleImgStartCourbe(morphingSpline app, ImageView ivStart) {
		this.app = app;
		this.ivStart = ivStart;
	}

	/**
     * Méthode appelée lorsqu'un objet observé notifie un changement.
     * Elle met à jour l'image source affichée dans l'interface utilisateur.
     * @param o L'objet observé.
     * @param arg L'argument (donnée) transmis par l'objet observé lors de la notification.
     */
	@Override
	public void update(Observable o, Object arg) {
		// Création de ImageView pour affichage
		ImageS imgSrc = app.getImgSrc();
		if (imgSrc != null)
		{
			Image image = SwingFXUtils.toFXImage(imgSrc.getImage(), null);
			ivStart.setImage(image);
			ivStart.setFitWidth(550);
			ivStart.setFitHeight(550);
		}
    }
}
