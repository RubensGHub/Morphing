package controle;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.control.Slider;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;

@Deprecated
public class ControleSlider implements Observer, ChangeListener <Number>{
    
    private Slider slider;
    private Label valeurSlider;

    public ControleSlider(Slider slider, Label valeurSlider) {
        this.slider = slider;
        this.valeurSlider = valeurSlider;
    }

	@Override
	public void update(Observable o, Object arg) {
        valeurSlider.setText("Nombre d'images intermédiaires : " + String.valueOf((int) slider.getValue()));
	}

	@Override
	public void changed(ObservableValue<? extends Number> observable, Number value1, Number value2) {
          valeurSlider.setText("Nombre d'images intermédiaires : " + value2.intValue());
       	
	}
}
