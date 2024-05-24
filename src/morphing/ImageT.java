package morphing;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ImageT extends ImageG {
    private ArrayList<Line> lines = new ArrayList<>();
    private Point tempPoint;
    private String imageType;
    private int compteur = 1;

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
            System.out.println("\nTableau de ligne d'" + imageType + " n°" + compteur + " :");
            for (Line line : lines) {
                System.out.print(line);
                
            }
            System.out.println("");
            compteur++;
        }
    }
    
    public void setImageType(String imageType) {
        this.imageType = imageType;
    }



}
