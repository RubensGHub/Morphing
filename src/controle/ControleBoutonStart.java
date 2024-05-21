package controle;
import java.util.Observable;
import java.util.Observer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import morphing.MorphingApp;

@Deprecated
public class ControleBoutonStart implements Observer, EventHandler<ActionEvent> {
    
    private MorphingApp app;
    private Button b;

    public ControleBoutonStart(MorphingApp app, Button b) {
        this.app = app;
        this.b = b;
    }

    @Override
    public void handle(ActionEvent arg0) {
        
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub
    }
}
