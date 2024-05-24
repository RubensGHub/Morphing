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

@Deprecated
public class ControlePointControleImgEnd implements Observer, EventHandler<MouseEvent> {

    private MorphingApp app;
    private GraphicsContext rightGC;
    private Point selectedPoint = null;

    public ControlePointControleImgEnd(MorphingApp app, GraphicsContext rightGC) {
        this.app = app;
        this.rightGC = rightGC;
    }

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

    private boolean isNear(Point point, int x, int y) {
        return Math.abs(point.getPoint().getX() - x) < 5 && Math.abs(point.getPoint().getY() - y) < 5;
    }

    public void onMouseDragged(MouseEvent event) {
        if (selectedPoint != null) {
            selectedPoint.setPoint((int)event.getX(), (int)event.getY());
            app.setNbFrames(app.getNbFrames());
        }
    }

    public void onMouseReleased(MouseEvent event) {
        selectedPoint = null;
    }

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
