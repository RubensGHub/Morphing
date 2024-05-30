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
 * Contrôleur pour l'affichage de l'image de destination.
 * Il implémente l'interface Observer pour suivre les changements de l'application.
 */
public class ControleImgEndSimple implements Observer {
	
	private FormesUniesPolygonales app;
	private ImageView ivEnd;
	
	/**
	 * Constructeur du contrôleur.
	 * 
	 * @param app   L'application à contrôler.
	 * @param ivEnd L'objet ImageView où afficher l'image de destination.
	 */
	public ControleImgEndSimple(FormesUniesPolygonales app, ImageView ivEnd) {
		this.app = app;
		this.ivEnd = ivEnd;
	}
	
	/**
	 * Méthode de mise à jour appelée lorsqu'un changement est observé dans l'application.
	 * Elle met à jour l'ImageView pour afficher l'image de destination.
	 * 
	 * @param o   L'observable (l'application).
	 * @param arg L'argument (non utilisé ici).
	 */
	@Override
	public void update(Observable o, Object arg) {
		// Création de ImageView pour affichage
		ImageT imgDest = app.getImgDest();
		if (imgDest != null)
		{
			Image image = SwingFXUtils.toFXImage(imgDest.getImage(), null);
			ivEnd.setImage(image);
			ivEnd.setFitWidth(550);
			ivEnd.setFitHeight(550);
		}
	}
}
