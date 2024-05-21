package controle;

import java.util.Observable;
import java.util.Observer;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import morphing.MorphingApp;


@SuppressWarnings("deprecation")
public class ControleImgEnd implements Observer {
	
protected MorphingApp app;
	protected ImageView ivStart = new ImageView();
	
	public ControleImgEnd(MorphingApp app, ImageView ivStart) {
		this.app = app;
		this.ivStart = ivStart;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// Création de ImageView pour affichage
		Image image = SwingFXUtils.toFXImage(app.getImgSrc().getImage(), null);
		ivStart.setImage(image);		
	}
}
