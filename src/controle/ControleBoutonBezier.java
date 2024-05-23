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
import morphing.*;

@SuppressWarnings("deprecation")
public class ControleBoutonBezier implements Observer, EventHandler<ActionEvent> {
    private MorphingApp app;


    public ControleBoutonBezier(MorphingApp app){
        this.app = app;
    }

    @Override
    public void handle(ActionEvent event) {
        /*try {
            CourbesBezier();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public void update(Observable o, Object arg) {
        
    }
    
}
