package controle;
import java.util.Observable;
import java.util.Observer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.stage.FileChooser;

@Deprecated
public class ControleImgStart implements Observer, ChangeListener<Number> {
    
    private ImageView ivStart;
    private Button addFile;
    private FileChooser fileChooser = new FileChooser();

    public ControleImgStart(Button addFile, ImageView ivStart) {
        this.ivStart = ivStart;
        this.addFile = addFile;
    }

	@Override
	public void update(Observable o, Object arg) {
        
	}

	@Override
	public void changed(ObservableValue<? extends Number> observable, Number value1, Number value2) {
       	
	}
}
