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
public class ControlePointControleImgStart implements Observer, EventHandler<MouseEvent> {

    private MorphingApp app;
    private GraphicsContext leftGC;
    private Point selectedPoint = null;

    public ControlePointControleImgStart(MorphingApp app, GraphicsContext leftGC) {
        this.app = app;
        this.leftGC = leftGC;
    }

    @Override
    public void update(Observable o, Object arg) {
        List<Point> points = new ArrayList<>();

        // On met tous les points d'imgSrc dans une arrayList de Point pour pouvoir les dessiner tous en même temps
        if (app.getImgSrc() != null) {
            if (app.getImgSrc().getLines() != null) {
                for (Line line : app.getImgSrc().getLines()){
                    points.add(line.getStart());
                    points.add(line.getEnd());
                }
            }
            if (app.getImgSrc().getTempPoint() != null) {
                points.add(app.getImgSrc().getTempPoint());
            }
            draw(leftGC, points);
        }
    }

    @Override
    public void handle(MouseEvent event) {

        if (app.getImgSrc() != null && app.getImgDest() != null) {
            double x = event.getX();
            double y = event.getY();
            int intX = (int)x;
            int intY = (int)y;
        
            // on vérifie si l'utilisateur sélectionne un point existant
            for (Line line : app.getImgSrc().getLines()) {
                if (isNear(line.getStart(), intX, intY)) {
                    selectedPoint = line.getStart();
                    return;
                } else if (isNear(line.getEnd(), intX, intY)) {
                    selectedPoint = line.getEnd();
                    return;
                }
            }
            if (app.getImgSrc().getLines().size() <= app.getImgDest().getLines().size()) {
                
                // sinon on crée un nouveau point
                Point newPoint = new Point(intX, intY);
                if (app.getImgSrc().getTempPoint() == null) {
                    app.getImgSrc().setTempPoint(newPoint);
                } else {
                    Line newLine = new Line(app.getImgSrc().getTempPoint(), newPoint);
                    if (app.getImgSrc().getLines() == null) {
                        app.getImgSrc().setLines(new ArrayList<>());
                    }
                    app.getImgSrc().addLine(newLine);
                    app.getImgSrc().printLines();
                    app.getImgSrc().setTempPoint(null);
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
