package morphingFormesSimples.controle;

import java.util.Observable;
import java.util.Observer;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import morphingFormesSimples.abstraction.FormesUniesPolygonales;
import commun.ImageT;


@SuppressWarnings("deprecation")
public class ControleImgEndSimple implements Observer {
	
	private FormesUniesPolygonales app;
	private ImageView ivEnd;
	
	public ControleImgEndSimple(FormesUniesPolygonales app, ImageView ivEnd) {
		this.app = app;
		this.ivEnd = ivEnd;
	}
	
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
