package morphingImages.controle;

import java.util.Observable;
import java.util.Observer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import morphingImages.abstraction.MorphingApp;

/**
 * Cette classe représente le contrôleur pour le bouton de génération du morphing d'images utilisant l'algorithme de Beier-Neely.
 **/
@SuppressWarnings("deprecation")
public class ControleBoutonBeier implements Observer, EventHandler<ActionEvent> {
    private MorphingApp app;

    /**
     * Constructeur
     * @param app L'application de morphing pour le morphing d'images
     */
    public ControleBoutonBeier(MorphingApp app){
        this.app = app;
    }

    /**
     * Lance le morphing d'images et la génération de gif.
     * @param event L'événement d'action associé au clic sur le bouton.
     */
    @Override
    public void handle(ActionEvent event) {
        app.calculate();
    }

    @Override
    public void update(Observable o, Object arg) {
        
    }
}
