package morphingFormesArrondies.controle;

import java.util.Observable;
import java.util.Observer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import morphingFormesArrondies.abstraction.morphingSpline;

@SuppressWarnings("deprecation")
public class ControleBoutonCourbe implements Observer, EventHandler<ActionEvent> {
    private morphingSpline app;


    public ControleBoutonCourbe(morphingSpline app){
        this.app = app;
    }

    @Override
    public void handle(ActionEvent event) {
        app.calculate();
    }

    @Override
    public void update(Observable o, Object arg) {
        
    }
    
}
