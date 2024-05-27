package morphing;
import java.util.ArrayList;
import java.util.List;

import java.util.Observable;
@SuppressWarnings("deprecation")

public class FormesUniesPolygonales extends Observable{

    private ImageT imgSrc;
    private ImageT imgDest;
    private ImageT[] frames;
    private int nbFrames = 0;

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

    /**
     * Constructeur par défaut
     */
    public FormesUniesPolygonales() {
        
    }

    /**
     * Constructeur avec paramètres
     * @param imgSrc
     * @param imgDest
     * @param k ordre de l'interpolation
     * @return ImageT
     * @autor Ryan Bouchou$
     * @date 2024-05-27
     * @version 1.0
     */
    ImageT newFrame(ImageT imgSrc, ImageT imgDest, int k){
        ImageT img = new ImageT(imgSrc.getMaxX(), imgSrc.getMaxY(), imgSrc.getFormat());
        
        double t = (double)k/(double)(this.nbFrames);

        for(int i=0; i<imgSrc.getPoints().size(); i++){
            Point p = imgSrc.getPoints().get(i);
            Point q = imgDest.getPoints().get(i);
            int x = (int)(p.getX()*t + q.getX()*(1-t));
            int y = (int)(p.getY()*t + q.getY()*(1-t));
            img.addPoint(new Point(x, y));
        }
        
        return img;
    }



    

    /**
     * Méthode qui vérifie si un point est dans un polygone
     * @param tab
     * @param P
     * @return true si le point est dans le polygone, false sinon
     */
    boolean Collision(Point tab[],Point P){
    int nbp = tab.length;
    int i;
    for(i=0;i<nbp;i++){
        Point A = tab[i];
        Point B;
        if (i==nbp-1){ 
            B = tab[0];     // si c'est le dernier point, on relie au premier
        }
        else{ 
            B = tab[i+1];   // sinon on relie au suivant.
        }

        Couple<Integer,Integer> D = new Couple<>(B.getX() - A.getX(),B.getY() - A.getY());
        Couple<Integer,Integer> T = new Couple<>(P.getX() - A.getX(),P.getY() - A.getY());

        double d = D.getX()*T.getY() - D.getY()*T.getX();

        if (d<0){
            return false;  // un point à droite et on arrête tout.
        }
    }
    return true;  // si on sort du for, c'est qu'aucun point n'est à gauche, donc c'est bon.
    }
}
