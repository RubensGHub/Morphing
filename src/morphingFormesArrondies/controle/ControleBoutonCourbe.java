package morphingFormesArrondies.controle;

import java.util.Observable;
import java.util.Observer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import morphingFormesArrondies.abstraction.morphingSpline;

/**
 * Cette classe représente le contrôleur des boutons pour le morphing de formes arrondies.
 * Elle implémente l'interface Observer pour surveiller les changements et l'interface EventHandler
 * pour gérer les événements des boutons.
 * 
 * Lorsqu'un événement de bouton est déclenché, le contrôleur appelle la méthode 'calculate()' de
 * l'application 'morphingSpline' pour démarrer le calcul du morphing.
 */
@SuppressWarnings("deprecation")
public class ControleBoutonCourbe implements Observer, EventHandler<ActionEvent> {
    private morphingSpline app;

    /**
     * Constructeur de la classe ControleBoutonCourbe.
     * @param app L'application morphingSpline à contrôler.
     */
    public ControleBoutonCourbe(morphingSpline app){
        this.app = app;
    }

    /**
     * Méthode de gestion des événements des boutons.
     * Elle est appelée lorsqu'un événement de bouton est déclenché.
     * @param event L'événement déclenché.
     */
    @Override
    public void handle(ActionEvent event) {
        // Appel de la méthode calculate() de l'application pour démarrer le calcul du morphing.
        app.calculate();
    }

    /**
     * Méthode appelée lorsqu'un objet observé notifie un changement.
     * Actuellement, cette méthode est vide car elle n'est pas utilisée dans cette classe.
     * @param o L'objet observé.
     * @param arg L'argument (donnée) transmis par l'objet observé lors de la notification.
     */
    @Override
    public void update(Observable o, Object arg) {
        // Cette méthode est laissée vide car elle n'est pas utilisée dans cette classe.
    }
}
