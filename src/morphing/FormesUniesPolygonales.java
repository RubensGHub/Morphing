package morphing;

import java.util.List;
import java.util.Observable;

@SuppressWarnings("deprecation")

public class FormesUniesPolygonales extends Observable{

    private ImageT imgSrc;
    private ImageT imgDest;
    private ImageT[] frames;
    private int nbFrames = 0;
    private int color = -16777216; // Black

    public ImageT getImgSrc() {
        return this.imgSrc;
    }

    public void setImgSrc(ImageT imgSrc) {
        this.imgSrc = imgSrc;
        // PAC
        this.setChanged();
        this.notifyObservers();
    }

    public ImageT getImgDest() {
        return this.imgDest;
    }

    public void setImgDest(ImageT imgDest) {
        this.imgDest = imgDest;
        // PAC
        this.setChanged();
        this.notifyObservers();
    }

    public ImageT[] getFrames() {
        return this.frames;
    }

    public void setFrames(ImageT[] frames) {
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

    public int getColor() {
        return this.color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    /**
     * Constructeur par défaut
     */
    public FormesUniesPolygonales() {
        
    }

     /**
     * Calcule les frames intermédiaires
     * @param imgSrc
     * @param imgDest
     * @param k ordre de l'interpolation
     * @return ImageT
     * @autor Ryan Bouchou
     * @date 2024-05-27
     * @version 1.0
     */
    public ImageT newFrame(ImageT imgSrc, ImageT imgDest, int k) {
        ImageT img = new ImageT(imgSrc.getMaxX(), imgSrc.getMaxY(), imgSrc.getFormat());
        double t = (double)k/(double)(this.nbFrames);

        for (int i = 0; i < imgSrc.getPoints().size(); i++) {
            Point p = imgSrc.getPoints().get(i);
            Point q = imgDest.getPoints().get(i);
            int x = (int)(p.getX()*t + q.getX()*(1-t));
            int y = (int)(p.getY()*t + q.getY()*(1-t));
            img.addPoint(new Point(x, y));
        }
        
        return img;
    }

    /**
     * Méthode qui génère les frames intermédiaires
     * @param frame
     * @autor Ryan Bouchou
     * @date 2024-05-27
     * @version 1.0
     */
    public void remplissage(ImageT frame){
        List<Point> points = frame.getPoints();
        
        for(int x=0; x<frame.getMaxX(); x++){
            for(int y=0; y<frame.getMaxY(); y++){
                Point p = new Point(x, y);
                if(Collision(points, p)){
                    frame.getImage().setRGB(x, y, this.color);
                }
            }
        }
    }
   

    /**
     * Méthode qui vérifie si un point est dans un polygone
     * @param tab
     * @param P
     * @return true si le point est dans le polygone, false sinon
     */
    public boolean Collision(List<Point> tab, Point p) {
        int nbp = tab.size();
        int i;
        for (i = 0; i < nbp; i++){
            // Deux points consécutifs
            Point a = tab.get(i);
            Point b = tab.get((i+1) % nbp);
            
            Couple<Integer,Integer> ab = new Couple<>(b.getX() - a.getX(), b.getY() - a.getY()); // Vecteur AB
            Couple<Integer,Integer> ap = new Couple<>(p.getX() - a.getX(), p.getY() - a.getY()); // Vecteur AP

            // Déterminant des vecteurs AB et AP
            double det = ab.getX()*ap.getY() - ab.getY()*ap.getX();

            if (det < 0) {
                return false;  
            }
        }
        return true;
    }


     /**
     * Morphing naif d'une forme unie simple
     * @author Ryan Bouchou
     * @version 1.0
     * @date 2024-05-27
     */
    public void calculate() {
        this.frames = new ImageT[this.nbFrames];

        for (int i = 0; i < this.nbFrames; i++) {
            this.frames[i] = newFrame(this.imgSrc, this.imgDest, i);
            remplissage(this.frames[i]);
            this.frames[i].save("./" + i + ".png");
        }
    }
}
