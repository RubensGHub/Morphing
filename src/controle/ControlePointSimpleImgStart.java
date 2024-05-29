package controle;

import java.util.Observable;
import java.util.Observer;
import java.util.ArrayList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import morphing.*;

/**
 * Classe de contrôleur pour gérer les points de contrôle sur l'image source dans MorphingApp.
 * Cette classe observe les changements dans MorphingApp et met à jour les points de contrôle en conséquence.
 * Elle gère également les événements de la souris pour sélectionner et créer des points de contrôle sur l'image.
 **/
@SuppressWarnings("deprecation")
public class ControlePointSimpleImgStart implements Observer, EventHandler<MouseEvent> {

    private FormesUniesPolygonales app;
    private GraphicsContext leftGC;
    private Point selectedPoint = null;

    /**
     * Construit un nouveau ControlePointControleImgStart.
     *
     * @param app l'instance de MorphingApp
     * @param leftGC le GraphicsContext pour le canevas de l'image de source
     */
    public ControlePointSimpleImgStart(FormesUniesPolygonales app, GraphicsContext leftGC) {
        this.app = app;
        this.leftGC = leftGC;
    }

    /**
     * Appelé lorsque l'objet observé est modifié. Met à jour les points de contrôle sur le canevas de l'image source.
     *
     * @param o l'objet observable
     * @param arg un argument passé à la méthode notifyObservers
     */
    @Override
    public void update(Observable o, Object arg) {

        if (app.getImgSrc() != null) {
            draw(leftGC, app.getImgSrc().getPoints());
        }
    }

    /**
     * Gère les événements de clic de souris sur le canevas. Sélectionne les points existants ou crée de nouveaux points.
     *
     * @param event l'événement MouseEvent déclenché par l'utilisateur
     */
    @Override
    public void handle(MouseEvent event) {

        if (app.getImgSrc() != null && app.getImgDest() != null) {
            double x = event.getX();
            double y = event.getY();
            int intX = (int)x;
            int intY = (int)y;
        
            // on vérifie si l'utilisateur sélectionne un point existant
            for (Point point : app.getImgSrc().getPoints()) {
                if (isNear(point, intX, intY)) {
                    selectedPoint = point;
                }
            }
            if (app.getImgSrc().getPoints().size() <= app.getImgDest().getPoints().size()) {

                // sinon on crée un nouveau point
                Point newPoint = new Point(intX, intY);
                if (app.getImgSrc().getPoints() == null) {
                    app.getImgSrc().setPoints(new ArrayList<>());
                }
                app.getImgSrc().addPoint(newPoint);
                app.setNbFrames(app.getNbFrames()); // On notifie l'observateur
            } else {
                System.err.println("\n Veuillez créer la paire du point précédent sur l'autre image.");
            }
        } else {
            System.err.println("\n Il manque l'une des deux images.");
        }
    }

    /**
     * Vérifie si un point est proche des coordonnées données.
     *
     * @param point le point à vérifier (là où a cliqué l'utilisateur)
     * @param x la coordonnée x
     * @param y la coordonnée y
     * @return true si le point est proche des coordonnées données, false sinon
     */
    private boolean isNear(Point point, int x, int y) {
        return Math.abs(point.getPoint().getX() - x) < 5 && Math.abs(point.getPoint().getY() - y) < 5;
    }

    /**
     * Gère les événements de glissement de la souris sur le canevas. Déplace le point sélectionné vers le nouvel emplacement.
     *
     * @param event l'événement MouseEvent déclenché par l'utilisateur
     */
    public void onMouseDragged(MouseEvent event) {
        if (selectedPoint != null) {
            selectedPoint.setPoint((int)event.getX(), (int)event.getY());
            app.setNbFrames(app.getNbFrames());
        }
    }

    /**
     * Gère les événements de relâchement de la souris sur le canevas. Désélectionne le point sélectionné.
     *
     * @param event l'événement MouseEvent déclenché par l'utilisateur
     */
    public void onMouseReleased(MouseEvent event) {
        selectedPoint = null;
    }

    /**
     * Dessine les points de contrôle et les lignes sur le canevas.
     *
     * @param gc le GraphicsContext sur lequel dessiner
     * @param points la liste des points à dessiner
     */
    private void draw(GraphicsContext gc, List<Point> points) {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        gc.setStroke(Color.RED);
        gc.setFill(Color.TRANSPARENT);
        for (Point pt : points) {
            gc.strokeOval(pt.getPoint().getX() - 3, pt.getPoint().getY() - 3, 6, 6);
        }
        gc.setStroke(Color.BLUE); 
    }
}
