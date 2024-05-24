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

    public ControlePointControleImgEnd(MorphingApp app, GraphicsContext rightGC) {
        this.app = app;
        this.rightGC = rightGC;
    }

    @Override
	public void update(Observable o, Object arg) {
        List<Point> points = new ArrayList<>();

        // On met tous les points d'ImgDest dans une arrayList de Point pour pouvoir les dessiner tous en même temps
        if (app.getImgDest() != null && app.getImgDest().getLines() != null){
            for (Line line : app.getImgDest().getLines()){
                points.add(line.getStart());
                points.add(line.getEnd());
            }
            if (app.getImgDest().getTempPoint() != null) {
                points.add(app.getImgDest().getTempPoint());
            }
        }
        draw(rightGC, points);
    }
	
    @Override
    public void handle(MouseEvent event) {
        ImageT ImgDest = app.getImgDest();
        if (ImgDest != null) {
            double x = event.getX();
            double y = event.getY();
            int intX = (int)x;
            int intY = (int)y;
            Point newPoint = new Point(intX, intY);

            if (app.getImgDest().getTempPoint() == null) {
                app.getImgDest().setTempPoint(newPoint);
                System.out.println("Le premier point a bien été sauvegardé");
            } else {
                Line newLine = new Line(app.getImgDest().getTempPoint(), newPoint);

                if (ImgDest.getLines() == null) {
                    ImgDest.setLines(new ArrayList<>());
                }

                ImgDest.addLine(newLine);
                ImgDest.printLines();
                app.getImgDest().setTempPoint(null);
            }
        } else {
            System.err.println("ImgDest est null");
        }
    }

    // permet d'afficher tous les points (ainsi que les segments) du tableau de ligne d'une image sur un canvas (graphicsContext)
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
