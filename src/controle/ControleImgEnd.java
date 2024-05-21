package controle;

import java.util.Observable;
import java.util.Observer;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import morphing.MorphingApp;

public class ControleImgEnd implements Observer{
protected Button bouton;
	
	protected ImageView ivStart = new ImageView();
	protected MorphingApp app;
	
	public ControleImgEnd(MorphingApp app, ImageView ivStart) {
		this.ivStart = ivStart;
		this.app = app;

	}
	
	@Override
	public void update(Observable o, Object arg) {
		// Création de ImageView pour affichage
				Image image = SwingFXUtils.toFXImage(app.getImgSrc().getImage(), null);
		        ivStart.setImage(image);		
	}
}
