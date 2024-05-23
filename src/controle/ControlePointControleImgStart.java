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

    public ControlePointControleImgStart(MorphingApp app, GraphicsContext leftGC) {
        this.app = app;
        this.leftGC = leftGC;
    }

    @Override
	public void update(Observable o, Object arg) {
        // on récupère le nouveau tableau de lignes d'imgSrc
        List<Point> points = new ArrayList<>();

        // On met tous les points d'imgSrc dans une arrayList de Point pour pouvoir les dessiner tous en même temps
        if (app.getImgSrc() != null && app.getImgSrc().getLines() != null){
            for (Line line : app.getImgSrc().getLines()){
                points.add(line.getStart());
                if (line.getEnd() != null) {
                    points.add(line.getEnd());
                }
            }
            draw(leftGC, points);
        }
	}
    
    @Override
    public void handle(MouseEvent event) {

        // On récupère les coordonnées du clic de l'utilisateur pour créer un nouveau Point
        if (app.getImgSrc() != null) {
            double x = event.getX();
            double y = event.getY();
            int intX = (int)x;
            int intY = (int)y;
            Point newPoint = new Point(intX, intY);
        
            // Si le tableau est vide ou que toutes les lignes sont complètes, alors on créé une nouvelle ligne incomplète, sinon on complète la ligne incomplète.
            int i = incompleteLine(app.getImgSrc().getLines());
            if (i == -1) {
                Line newLine = new Line(newPoint, null);
                app.getImgSrc().addLine(newLine);
            } else {
                app.getImgSrc().getLines().get(i).setEnd(newPoint);
            }
        }
    }

    private int incompleteLine(ArrayList<Line> lines) {
        int i=0;
        if (app.getImgSrc().getLines() != null){
            for (Line line : app.getImgSrc().getLines()) {
                if (line.getEnd() == null) {
                    return i;
                } 
                i++;
            }
        }
        return -1;
    }

    // permet d'afficher tous les points du tableau de ligne d'une image sur un canvas (graphicsContext)
    private void draw(GraphicsContext gc, List<Point> points) {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        gc.setStroke(Color.RED);
        gc.setFill(Color.TRANSPARENT);
        for (Point pt : points) {
            gc.strokeOval(pt.getPoint().getX() - 3, pt.getPoint().getY() - 3, 6, 6);
        }
        gc.setStroke(Color.BLUE); 
        for (int i = 0; i < points.size() - 1; i++) {
            if (i % 2 == 1) {
                gc.strokeLine(
                    points.get(i - 1).getPoint().getX(), points.get(i - 1).getPoint().getY(),
                    points.get(i).getPoint().getX(), points.get(i).getPoint().getY()
                );
            }
        }
    }

}
