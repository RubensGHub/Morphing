package morphingFormesArrondies.controle;

import java.util.Observable;
import java.util.Observer;
import java.util.ArrayList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import morphingFormesArrondies.abstraction.morphingSpline;
import morphingFormesSimples.abstraction.FormesUniesPolygonales;
import commun.*;

@SuppressWarnings("deprecation")
public class ControlePointCourbeImgStart implements Observer, EventHandler<MouseEvent> {
    
    private morphingSpline app;
    private GraphicsContext leftGC;
    private Point selectedPoint = null;

    /**
     * Construit un nouveau ControlePointControleImgStart.
     *
     * @param app l'instance de MorphingApp
     * @param leftGC le GraphicsContext pour le canevas de l'image de source
     */
    public ControlePointCourbeImgStart(morphingSpline app, GraphicsContext leftGC) {
        this.app = app;
        this.leftGC = leftGC;
    }


    @Override
    public void update(Observable o, Object arg) {

    }


    @Override
    public void handle(MouseEvent event) {

    }

}
