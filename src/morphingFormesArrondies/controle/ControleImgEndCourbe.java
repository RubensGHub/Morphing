package morphingFormesArrondies.controle;

import java.util.Observable;
import java.util.Observer;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import morphingFormesArrondies.abstraction.*;


@SuppressWarnings("deprecation")
public class ControleImgEndCourbe implements Observer {
	
	private morphingSpline app;
	private ImageView ivEnd;
	
	public ControleImgEndCourbe(morphingSpline app, ImageView ivEnd) {
		this.app = app;
		this.ivEnd = ivEnd;
	}
	
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
