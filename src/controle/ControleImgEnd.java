package controle;

import java.util.Observable;
import java.util.Observer;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import morphing.MorphingApp;


@SuppressWarnings("deprecation")
public class ControleImgEnd implements Observer {
	
	private MorphingApp app;
	private ImageView ivEnd;
	
	public ControleImgEnd(MorphingApp app, ImageView ivEnd) {
		this.app = app;
		this.ivEnd = ivEnd;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// Création de ImageView pour affichage
		Image image = SwingFXUtils.toFXImage(app.getImgDest().getImage(), null);
		ivEnd.setImage(image);
		ivEnd.setFitWidth(550);
		ivEnd.setFitHeight(550);
	}
}
