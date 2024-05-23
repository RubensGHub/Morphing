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
public class ControleBoutonSimple implements Observer, EventHandler<ActionEvent> {
    private MorphingApp app;


    public ControleBoutonSimple(MorphingApp app){
        this.app = app;
    }

    @Override
    public void handle(ActionEvent event) {
        /*try {
            FormeSimple();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public void update(Observable o, Object arg) {
        
    }
    
}
