package commun;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ImageT extends ImageG {
    private ArrayList<Line> lines = new ArrayList<>();
    private ArrayList<Point> points = new ArrayList<>();
    private Point tempPoint;
    private int compteur = 1;
    private final static Couple<Integer, Integer> DEFAULT_ORIENTATION = new Couple<Integer,Integer>(-1,0);
    ArrayList<Double> lengthVector;
    ArrayList<Double> angleVector;

    public ImageT(BufferedImage bImg, int w, int h, String ext) {
        super(bImg, w, h, ext);
    }

    public ImageT(String path) {
        super(path);
    }

    public ImageT(int width, int height, String format) {
        super(width, height, format);
    }

    public void addLine(Line l){
        lines.add(l);
    }

    public Line getLine(int i){
        return lines.get(i);
    }

    public void removeLine(int i){
        lines.remove(i);
    }


    public ArrayList<Line> getLines() {
        return lines;
    }

    public void setLines(ArrayList<Line> lines) {
        this.lines = lines;
    }

    public Point getTempPoint() {
        return this.tempPoint;
    }

    public void setTempPoint(Point tempPoint) {
        this.tempPoint = tempPoint;
    }

    public void printLines() {
        if (lines == null) {
            System.out.println("La liste de lignes est null.");
        } else if (lines.isEmpty()) {
            System.out.println("La liste de lignes est vide.");
        } else {
            System.out.println("\nTableau à " + compteur + " lignes :");
            for (Line line : lines) {
                System.out.print(line);
                
            }
            System.out.println("");
            compteur++;
        }
    }
    
    public void addPoint(Point p){
        points.add(p);
    }

    public Point getPoint(int i){
        return points.get(i);
    }

    public void removePoint(int i){
        points.remove(i);
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }




}
