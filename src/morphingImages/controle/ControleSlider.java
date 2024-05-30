package morphingImages.controle;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.control.Slider;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;

import morphingImages.abstraction.MorphingApp;

@SuppressWarnings("deprecation")
public class ControleSlider implements Observer, ChangeListener<Number> {
    
    private MorphingApp app;
    private Slider slider;
    private Label valSlider;

    /**
     * Constructeur pour ControleSlider.
     *
     * @param app       l'instance de MorphingApp
     * @param slider    le Slider pour sélectionner le nombre d'images intermédiaires
     * @param valSlider le Label pour afficher la valeur actuelle du Slider
     */
    public ControleSlider(MorphingApp app, Slider slider, Label valSlider) {
        this.app = app;
        this.slider = slider;
        this.valSlider = valSlider;
    }

    /**
     * Appelé lorsqu'il y a un changement de valeur dans le Slider.
     *
     * @param observable l'objet observé
     * @param oldValue   l'ancienne valeur du Slider
     * @param newValue   la nouvelle valeur du Slider
     */
    @Override
    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        app.setNbFrames((int) slider.getValue());
    }

    /**
     * Appelé lorsque MorphingApp est modifié. Met à jour le texte affiché pour le nombre d'images intermédiaires.
     *
     * @param o   l'objet observable
     * @param arg un argument passé à la méthode notifyObservers
     */
    @Override
    public void update(Observable o, Object arg) {
        valSlider.setText("Nombre d'images intermédiaires : " + app.getNbFrames());
    }
}
