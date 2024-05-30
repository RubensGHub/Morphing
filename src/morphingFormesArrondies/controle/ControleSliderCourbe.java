package morphingFormesArrondies.controle;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.control.Slider;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;

import morphingFormesArrondies.abstraction.*;

/**
 * Contrôleur pour ajuster le nombre d'images intermédiaires dans le morphing.
 * Implémente Observer pour surveiller les changements et ChangeListener<Number> pour le slider.
 * Met à jour le nombre d'images intermédiaires et l'affichage de l'étiquette.
 */
@SuppressWarnings("deprecation")
public class ControleSliderCourbe implements Observer, ChangeListener<Number>{
    
    private morphingSpline app;
    private Slider slider;
    private Label valSlider;

    /**
     * Constructeur de la classe ControleSliderCourbe.
     * @param app L'application morphingSpline à observer.
     * @param slider Le slider pour le réglage du nombre d'images intermédiaires.
     * @param valSlider L'étiquette pour afficher la valeur actuelle du slider.
     */
    public ControleSliderCourbe(morphingSpline app, Slider slider, Label valSlider) {
        this.app = app;
        this.slider = slider;
        this.valSlider = valSlider;
    }

    /**
     * Méthode appelée lorsqu'un changement de valeur est détecté sur le slider.
     * Elle met à jour le nombre d'images intermédiaires dans l'application.
     * @param observable L'objet observable (le slider).
     * @param oldValue La valeur précédente du slider.
     * @param newValue La nouvelle valeur du slider.
     */
	@Override
	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        app.setNbFrames((int) slider.getValue());
	}

    /**
     * Méthode appelée lorsqu'un objet observé notifie un changement.
     * Elle met à jour l'affichage de l'étiquette pour afficher le nombre d'images intermédiaires.
     * @param o L'objet observé.
     * @param arg L'argument (donnée) transmis par l'objet observé lors de la notification.
     */
	@Override
	public void update(Observable o, Object arg) {
        valSlider.setText("Nombre d'images intermédiaires : " + app.getNbFrames());
	}
}
