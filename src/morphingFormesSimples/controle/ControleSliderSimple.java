package morphingFormesSimples.controle;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.control.Slider;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;

import morphingFormesSimples.abstraction.FormesUniesPolygonales;


@SuppressWarnings("deprecation")
public class ControleSliderSimple implements Observer, ChangeListener <Number>{
    
    private FormesUniesPolygonales app;
    private Slider slider;
    private Label valSlider;

    public ControleSliderSimple(FormesUniesPolygonales app, Slider slider, Label valSlider) {
        this.app = app;
        this.slider = slider;
        this.valSlider = valSlider;
    }

	@Override
	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        app.setNbFrames((int) slider.getValue());
	}

	@Override
	public void update(Observable o, Object arg) {
        valSlider.setText("Nombre d'images intermédiaires : " + app.getNbFrames());
	}
}
