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
    private int color;

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
    public ImageT newFrame(ImageT imgSrc, ImageT imgDest, int k){
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
     * Méthode qui génère les frames intermédiaires
     * @param frame
     * @return void
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
    public boolean Collision(List<Point> tab,Point P){
        int nbp = tab.size();
        int i;
        for(i=0;i<nbp;i++){
            Point A = tab.get(i);
            Point B = i==(nbp-1) ? tab.get(0) : tab.get(i+1);
        
            Couple<Integer,Integer> D = new Couple<>(B.getX() - A.getX(),B.getY() - A.getY());
            Couple<Integer,Integer> T = new Couple<>(P.getX() - A.getX(),P.getY() - A.getY());

            double d = D.getX()*T.getY() - D.getY()*T.getX();

            if (d<0){
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
    public void calculate(){
        int n = imgSrc.getPoints().size();
        this.frames = new ImageT[this.nbFrames];
        if(n>2){
            Line temp = new Line(imgSrc.getPoint(0), imgSrc.getPoint(2));
            Couple<Double,Double> vTemp = temp.getVector();
            Point xC = new Point((int)(imgSrc.getPoint(0).getX() + 0.5* vTemp.getX()), (int)(imgSrc.getPoint(0).getY() + 0.5* vTemp.getY()));
            this.setColor(imgSrc.getImage().getRGB(xC.getX(), xC.getY()));
        }

        for(int i=0; i<this.nbFrames; i++){
            this.frames[i] = newFrame(this.imgSrc, this.imgDest, i);
            remplissage(this.frames[i]);
            this.frames[i].save("/home/cytech/Desktop/Morphing/bin/test/partie1/frame"+i+".png");
        }
    }
}
