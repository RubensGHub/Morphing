package controle;

import java.util.Observable;
import java.util.Observer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
