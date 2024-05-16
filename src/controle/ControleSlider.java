package controle;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.control.Slider;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;


public class ControleSlider implements Observer, ChangeListener <Number>{
    
    private Stage primaryStage;
    private Slider slider;
    private Label valeurSlider;

    public ControleSlider(Stage primaryStage, Slider slider, Label valeurSlider) {
        this.primaryStage = primaryStage;
        this.slider = slider;
        this.valeurSlider = valeurSlider;
    }

	@Override
	public void update(Observable o, Object arg) {
        valeurSlider.setText("Images sélectionnées : " + String.valueOf((int) slider.getValue()));
	}


	@Override
	public void changed(ObservableValue<? extends Number> observable, Number value1, Number value2) {
          valeurSlider.setText("Images sélectionnées : " + value2.intValue());
       	
	}
}
