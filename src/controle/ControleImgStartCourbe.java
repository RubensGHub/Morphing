package controle;

import java.util.Observable;
import java.util.Observer;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import morphCourbe.ImageS;
import morphCourbe.morphingSpline;


@SuppressWarnings("deprecation")
public class ControleImgStartCourbe implements Observer {
	
	private morphingSpline app;
	private ImageView ivStart;
	
	public ControleImgStartCourbe(morphingSpline app, ImageView ivStart) {
		this.app = app;
		this.ivStart = ivStart;
	}

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
