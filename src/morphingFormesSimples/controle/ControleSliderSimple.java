package morphingFormesSimples.controle;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.control.Slider;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;

import morphingFormesSimples.abstraction.FormesUniesPolygonales;

@SuppressWarnings("deprecation")
public class ControleSliderSimple implements Observer, ChangeListener<Number> {
    
    private FormesUniesPolygonales app;
    private Slider slider;
    private Label valSlider;

    /**
     * Constructeur de ControleSliderSimple.
     *
     * @param app       L'application de morphing.
     * @param slider    Le Slider associé au contrôleur.
     * @param valSlider Le Label affichant la valeur du Slider.
     */
    public ControleSliderSimple(FormesUniesPolygonales app, Slider slider, Label valSlider) {
        this.app = app;
        this.slider = slider;
        this.valSlider = valSlider;
    }

    /**
     * Méthode appelée lorsqu'une modification est détectée sur le Slider.
     *
     * @param observable L'objet Observable.
     * @param oldValue   Ancienne valeur du Slider.
     * @param newValue   Nouvelle valeur du Slider.
     */
    @Override
    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        app.setNbFrames((int) slider.getValue());
    }

    /**
     * Méthode appelée lorsqu'une mise à jour est reçue de l'Observable.
     *
     * @param o   L'Observable.
     * @param arg L'argument passé lors de la mise à jour.
     */
    @Override
    public void update(Observable o, Object arg) {
        valSlider.setText("Nombre d'images intermédiaires : " + app.getNbFrames());
    }
}
