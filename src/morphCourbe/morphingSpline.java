package morphCourbe;
import com.squareup.gifencoder.GifEncoder;
import com.squareup.gifencoder.ImageOptions;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Observable;
import morphing.*;
@Deprecated
public class morphingSpline extends Observable {
    private ImageS imgSrc;
    private ImageS imgDest;
    private ImageS[] frames;
    private int nbFrames;
    protected double[][] matrice;

    

     public ImageS getImgSrc() {
        return this.imgSrc;
    }

    public void setImgSrc(ImageS imgSrc) {
        this.imgSrc = imgSrc;
        // PAC
        this.setChanged();
        this.notifyObservers();
    }

    public ImageS getImgDest() {
        return this.imgDest;
    }

    public void setImgDest(ImageS imgDest) {
        this.imgDest = imgDest;
        // PAC
        this.setChanged();
        this.notifyObservers();
    }

    public ImageS[] getFrames() {
        return this.frames;
    }

    public void setFrames(ImageS[] frames) {
        this.frames = frames;
    }

    public int getNbFrames() {
        return this.nbFrames;
    }

    public void setNbFrames(int nbFrames) {
        this.nbFrames = nbFrames;
        // PAC
        this.setChanged();
        this.notifyObservers();
    }



    /**
     * Constructeur par défaut
     */
    public morphingSpline() {

    }

    /**
     * Calcule les frames intermédiaires
     * @param imgSrc
     * @param imgDest
     * @param k ordre de l'interpolation
     * @return ImageS
     * @autor Ryan Bouchou
     * @date 2024-05-27
     * @version 1.0
     */
    public ImageS newFrame(ImageS imgSrc, ImageS imgDest, int k) {
        ImageS img = new ImageS(imgSrc.getMaxX(), imgSrc.getMaxY(), imgSrc.getFormat());
        img.setSpline(new BSpline());
        img.getSpline().setDeg(imgSrc.getSpline().getDeg());
        img.getSpline().computeUniformVector();
        double t = (double)k/(double)(this.nbFrames);
        for(int i=0 ; i < imgSrc.getSpline().getcontrolPolygon().size()-1 ; i++) {
            Point p = new Point(0,0);
            p.setX((int)(imgSrc.getSpline().getcontrolPoint(i).getX()*(1-t) + imgDest.getSpline().getcontrolPoint(i).getX()*t));
            p.setY((int)(imgSrc.getSpline().getcontrolPoint(i).getY()*(1-t) + imgDest.getSpline().getcontrolPoint(i).getY()*t));
            img.getSpline().addControlPoint(p);
        }
        img.getSpline().close();      
        return img;
    }

    /**
     * Dessine la courbe de B-Spline
     * @param g2d
     * @param spline
     * @autor Ryan Bouchou
     * @date 2024-05-27
     * @version 1.0
     */
    private static void drawBSpline(Graphics2D g2d, BSpline spline) {
        double step = 0.01; // Increment for the parameter t
        double tMin = spline.getNode(spline.getDeg());
        double tMax = spline.getNode(spline.getNodeVector().size() - 1 - spline.getDeg());

        g2d.setColor(Color.BLUE);
        Point previousPoint = spline.calculerPoint(tMin);

        for (double t = tMin + step; t <= tMax; t += step) {
            Point point = spline.calculerPoint(t);
            g2d.draw(new Line2D.Double(previousPoint.getX(),previousPoint.getY(),point.getX(),point.getY()));
            previousPoint = point;
        }
    }


    /**
     * Calcule les frames intermédiaires
     * @autor Ryan Bouchou
     * @date 2024-05-27
     * @version 1.0
     */
    public void calculate(){
        this.frames = new ImageS[this.nbFrames];
        
        for(int i=0 ; i < this.nbFrames ; i++) {
            this.frames[i] = this.newFrame(this.imgSrc, this.imgDest, i);
            Graphics2D g2d = this.frames[i].getImage().createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            drawBSpline(g2d, this.frames[i].getSpline());
            g2d.dispose();
            saveFrames("./test");

        }
    }

    /**
     * Sauvegarde des images intermédiaires
     * @param path : chemin de sauvegarde
     * @return void
     * @autor : Romain Corral
     */
    public void saveFrames(String path){
        for (int i = 0 ; i < this.getNbFrames() ; i++){
            System.out.println("fct save");
            System.out.println(frames[i]);
            frames[i].save(path + "/frame" + i + ".png");
            System.out.println(frames[i]);
        }
    }

    
    
}
