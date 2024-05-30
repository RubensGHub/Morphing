package morphingFormesArrondies.controle;

import java.util.Observable;
import java.util.Observer;
import java.util.ArrayList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import morphingFormesArrondies.abstraction.*;
import commun.*;

/**
 * Classe de contrôleur pour gérer les points de contrôle sur l'image destination dans MorphingApp.
 * Cette classe observe les changements dans MorphingApp et met à jour les points de contrôle en conséquence.
 * Elle gère également les événements de la souris pour sélectionner, créer ou déplacer des points de contrôle sur l'image.
 **/
@Deprecated
public class ControlePointCourbeImgEnd implements Observer, EventHandler<MouseEvent> {

    private morphingSpline app;
    private GraphicsContext rightGC;

    /**
     * Construit un nouveau ControlePointControleImgEnd.
     *
     * @param app2 l'instance de MorphingApp
     * @param leftGC le GraphicsContext pour le canevas de l'image destination
     */
    public ControlePointCourbeImgEnd(morphingSpline app, GraphicsContext rightGC) {
        this.app = app;
        this.rightGC = rightGC;
    }

   
    @Override
    public void update(Observable o, Object arg) {

    }

   
    @Override
    public void handle(MouseEvent event) {

       
    }

}
