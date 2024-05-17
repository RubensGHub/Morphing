package morphing;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ImageT extends ImageG{
    ArrayList<Line> lines;

    public ImageT(BufferedImage bImg, int w, int h, String ext) {
        super(bImg, w, h, ext);
    }

    public ImageT(String path) {
        super(path);
        lines = new ArrayList<>();
    }

    public ImageT(int width, int height, String format) {
        super(width, height, format);
        lines = new ArrayList<>();
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



   



}
