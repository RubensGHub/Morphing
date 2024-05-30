package morphingFormesSimples.controle;

import java.util.Observable;
import java.util.Observer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import morphingFormesSimples.abstraction.FormesUniesPolygonales;

@SuppressWarnings("deprecation")
/**
 * Contrôleur pour le bouton de génération de morphing.
 * Il implémente l'interface Observer pour suivre les changements de l'application.
 */
public class ControleBoutonSimple implements Observer, EventHandler<ActionEvent> {
    private FormesUniesPolygonales app;

    /**
     * Constructeur du contrôleur.
     * 
     * @param app L'application à contrôler.
     */
    public ControleBoutonSimple(FormesUniesPolygonales app){
        this.app = app;
    }

    /**
     * Gère l'événement du clic sur le bouton de génération de morphing.
     * 
     * @param event L'événement associé au clic sur le bouton.
     */
    @Override
    public void handle(ActionEvent event) {
            app.calculate();
    }

    /**
     * Méthode de mise à jour appelée lorsqu'un changement est observé dans l'application.
     * 
     * @param o   L'observable (l'application).
     * @param arg L'argument (non utilisé ici).
     */
    @Override
    public void update(Observable o, Object arg) {
        
    }
    
}
