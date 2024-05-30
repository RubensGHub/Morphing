package controle;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.control.Slider;
import morphCourbe.*;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;


@SuppressWarnings("deprecation")
public class ControleSliderCourbe implements Observer, ChangeListener <Number>{
    
    private morphingSpline app;
    private Slider slider;
    private Label valSlider;

    public ControleSliderCourbe(morphingSpline app, Slider slider, Label valSlider) {
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
