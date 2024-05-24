package controle;

import java.util.Observable;
import java.util.Observer;

import javax.swing.plaf.synth.SynthStyle;

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
        List<Point> points = new ArrayList<>();

        // On met tous les points d'imgSrc dans une arrayList de Point pour pouvoir les dessiner tous en même temps
        if (app.getImgSrc() != null && app.getImgSrc().getLines() != null) {

            System.out.println("bla");

            for (Line line : app.getImgSrc().getLines()){
                points.add(line.getStart());
                points.add(line.getEnd());
            }
            
            if (app.getImgSrc().getTempPoint() != null) {
                points.add(app.getImgSrc().getTempPoint());
            }
        }
        
        draw(leftGC, points);
    }
    
    @Override
    public void handle(MouseEvent event) {
        ImageT imgSrc = app.getImgSrc();
        if (imgSrc != null) {
            double x = event.getX();
            double y = event.getY();
            int intX = (int)x;
            int intY = (int)y;
            Point newPoint = new Point(intX, intY);

            if (app.getImgSrc().getTempPoint() == null) {
                app.getImgSrc().setTempPoint(newPoint);
                System.out.println("Le premier point a bien été sauvegardé");
            } else {
                Line newLine = new Line(app.getImgSrc().getTempPoint(), newPoint);

                if (imgSrc.getLines() == null) {
                    imgSrc.setLines(new ArrayList<>());
                }

                imgSrc.addLine(newLine);
                imgSrc.printLines(); // Affiche le contenu de la liste des lignes
                app.getImgSrc().setTempPoint(null);
            }
        } else {
            System.err.println("imgSrc est null");
        }
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
