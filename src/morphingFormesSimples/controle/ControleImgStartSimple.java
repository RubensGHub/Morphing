package morphingFormesSimples.controle;

import java.util.Observable;
import java.util.Observer;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import morphingFormesSimples.abstraction.FormesUniesPolygonales;
import commun.ImageT;

@SuppressWarnings("deprecation")
/**
 * Contrôleur pour l'affichage de l'image de départ.
 * Il implémente l'interface Observer pour suivre les changements de l'application.
 */
public class ControleImgStartSimple implements Observer {
	
	private FormesUniesPolygonales app;
	private ImageView ivStart;
	
	/**
	 * Constructeur du contrôleur.
	 * 
	 * @param app     L'application à contrôler.
	 * @param ivStart L'objet ImageView où afficher l'image de départ.
	 */
	public ControleImgStartSimple(FormesUniesPolygonales app, ImageView ivStart) {
		this.app = app;
		this.ivStart = ivStart;
	}

	/**
	 * Méthode de mise à jour appelée lorsqu'un changement est observé dans l'application.
	 * Elle met à jour l'ImageView pour afficher l'image de départ.
	 * 
	 * @param o   L'observable (l'application).
	 * @param arg L'argument (non utilisé ici).
	 */
	@Override
	public void update(Observable o, Object arg) {
		// Création de ImageView pour affichage
		ImageT imgSrc = app.getImgSrc();
		if (imgSrc != null)
		{
			Image image = SwingFXUtils.toFXImage(imgSrc.getImage(), null);
			ivStart.setImage(image);
			ivStart.setFitWidth(550);
			ivStart.setFitHeight(550);
		}
    }
}
