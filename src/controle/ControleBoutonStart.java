package controle;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import morphing.ImageT;
import morphing.MorphingApp;

@SuppressWarnings("deprecation")
public class ControleBoutonStart implements Observer, EventHandler<ActionEvent> {
    
    private MorphingApp app;
    private int wImgMax = 550;
	private int hImgMax = 550;

    public ControleBoutonStart(MorphingApp app) {
        this.app = app;
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            selectImage(app);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        
    }
    

    private static String getExtension(File f) {
        int i = f.getName().lastIndexOf(".");
        if (i > 0) {
            return f.getName().substring(i + 1);
        }
        return null;
    }
	
	private void selectImage(MorphingApp app) throws IOException {
        // Définition des extensions acceptées
        HashSet<String> ext = new HashSet<String>();
        ext.add("jpg");
        ext.add("jpeg");
        ext.add("png");
        ext.add("gif");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        File selectedFile = fileChooser.showOpenDialog(null);

        // Vérification de la sélection et de l'extension
        if (selectedFile != null) {
            if (ext.contains(getExtension(selectedFile))) {
                // Création de BufferedImage pour récupérer taille et construire ImageT
                BufferedImage bImg = ImageIO.read(selectedFile);
                int w = bImg.getWidth();
                int h = bImg.getHeight();

                // Ajustement de la taille de l'image
                if (w > h) {
                    h = h * wImgMax / w;
                    w = wImgMax;
                } else {
                    w = w * hImgMax / h;
                    h = hImgMax;
                }
                
                // Ajout de l'image à notre App
                ImageT imgT = new ImageT(bImg, w, h, getExtension(selectedFile));
                app.setImgSrc(imgT);
            }
            
            else {
                System.err.println("Seules les images sont acceptées.");
            }
        }
    }
}
