package morphingImages.controle;

import java.util.Observable;
import java.util.Observer;
import java.util.ArrayList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import commun.*;
import morphingImages.abstraction.MorphingApp;

/**
 * Classe de contrôleur pour gérer les points de contrôle sur l'image destination dans MorphingApp.
 * Cette classe observe les changements dans MorphingApp et met à jour les points de contrôle en conséquence.
 * Elle gère également les événements de la souris pour sélectionner, créer ou déplacer des points de contrôle sur l'image.
 **/

@Deprecated
public class ControlePointBeierImgEnd implements Observer, EventHandler<MouseEvent> {

    private MorphingApp app;
    private GraphicsContext rightGC;
    private Point selectedPoint = null;

    /**
     * Construit un nouveau ControlePointControleImgEnd.
     *
     * @param app l'instance de MorphingApp
     * @param leftGC le GraphicsContext pour le canevas de l'image destination
     */
    public ControlePointBeierImgEnd(MorphingApp app, GraphicsContext rightGC) {
        this.app = app;
        this.rightGC = rightGC;
    }

    /**
     * Appelé lorsque MorphingApp est modifié. Met à jour les points de contrôle sur le canevas de l'image destination.
     *
     * @param o l'objet observable
     * @param arg un argument passé à la méthode notifyObservers
     */
    @Override
    public void update(Observable o, Object arg) {
        List<Point> points = new ArrayList<>();

        // On met tous les points d'imgSrc dans une arrayList de Point pour pouvoir les dessiner tous en même temps
        if (app.getImgDest() != null) {
            if (app.getImgDest().getLines() != null) {
                for (Line line : app.getImgDest().getLines()){
                    points.add(line.getStart());
                    points.add(line.getEnd());
                }
            }
            if (app.getImgDest().getTempPoint() != null) {
                points.add(app.getImgDest().getTempPoint());
            }
            draw(rightGC, points);
        }
    }

    /**
     * Gère les événements de clic de souris sur le canevas. Sélectionne les points existants ou crée de nouveaux points.
     * 
     * @param event l'événement MouseEvent déclenché par l'utilisateur
     */
    @Override
    public void handle(MouseEvent event) {

        if (app.getImgDest() != null && app.getImgSrc() != null) {
            double x = event.getX();
            double y = event.getY();
            int intX = (int)x;
            int intY = (int)y;
            
            // on vérifie si l'utilisateur sélectionne un point existant
            for (Line line : app.getImgDest().getLines()) {
                if (isNear(line.getStart(), intX, intY)) {
                    selectedPoint = line.getStart();
                    return;
                } else if (isNear(line.getEnd(), intX, intY)) {
                    selectedPoint = line.getEnd();
                    return;
                }
            }

            if (app.getImgDest().getLines().size() <= app.getImgSrc().getLines().size()) {
                
                // sinon on crée un nouveau point
                Point newPoint = new Point(intX, intY);

                if (app.getImgDest().getTempPoint() == null) {
                    app.getImgDest().setTempPoint(newPoint);
                } else {
                    Line newLine = new Line(app.getImgDest().getTempPoint(), newPoint);
            
                    if (app.getImgDest().getLines() == null) {
                        app.getImgDest().setLines(new ArrayList<>());
                    }
                    app.getImgDest().addLine(newLine);
                    app.getImgDest().printLines();
                    app.getImgDest().setTempPoint(null);
                }
                app.setNbFrames(app.getNbFrames());
            } else {
                System.err.println("\n Veuillez créer la paire de la ligne précédente sur l'autre image.");
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

        for (int i = 0; i < points.size() - 1; i++) {
            if (i % 2 == 0) {
                gc.strokeLine(
                    points.get(i + 1).getPoint().getX(), points.get(i + 1).getPoint().getY(),
                    points.get(i).getPoint().getX(), points.get(i).getPoint().getY()
                );
            }
        }
    }
}