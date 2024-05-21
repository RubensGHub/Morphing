package controle;
import morphing.*;

import java.util.Observer;

import javax.imageio.ImageIO;

import java.util.Observable;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import javafx.stage.FileChooser;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class ControleImgStart implements Observer {
	
	protected Button bouton;
	
	protected ImageView ivStart = new ImageView();
	protected MorphingApp app;
	
	public ControleImgStart(MorphingApp app, ImageView ivStart) {
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
