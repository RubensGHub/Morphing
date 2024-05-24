package morphing;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

@SuppressWarnings("deprecation")
public class ImageT extends ImageG {
    ArrayList<Line> lines;
    private Point tempPoint;

    public ImageT(BufferedImage bImg, int w, int h, String ext) {
        super(bImg, w, h, ext);
    }

    public ImageT(String path) {
        super(path);
        lines = new ArrayList<>();
        this.tempPoint = null;
    }

    public ImageT(int width, int height, String format) {
        super(width, height, format);
        lines = new ArrayList<>();
        this.tempPoint = null;
    }

    public void addLine(Line l){
        lines.add(l);
        // PAC
        this.setChanged();
        this.notifyObservers();
    }

    public Line getLine(int i){
        return lines.get(i);
    }

    public void removeLine(int i){
        lines.remove(i);
        // PAC
        this.setChanged();
        this.notifyObservers();
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
            for (Line line : lines) {
                System.out.println(line);
            }
        }
    }



}
