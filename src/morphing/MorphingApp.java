package morphing;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


@SuppressWarnings("deprecation")
public class MorphingApp extends Observable {

    private ImageT imgSrc;
    private ImageT imgDest;
    private ImageT[] frames;
    private int nbFrames = 0;
    private int nbLines = 0;

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

    public int getNbLines() {
        return this.nbLines;
    }

    public void setNbLines(int nbLines) {
        this.nbLines = nbLines;
    }


    /**
     * Constructeur par défaut
     */
    public MorphingApp() {
        
    }

    /**
     * Créer une image intermédiaire à partir de l'image de départ et de l'image d'arrivée
     * @param t : ordre de l'image intermédiaire
     * @return ImageT
     */
    public ImageT newFrame(int t){
        ImageT frame = new ImageT(imgSrc.getWidth(), imgSrc.getHeight(), imgSrc.getFormat());
        double f = (double) t / this.getNbFrames();

        for (int i = 0 ; i < this.getNbLines() ; i++)
        {
            Line v1 = imgSrc.getLine(i);
            Line v2 = imgDest.getLine(i);

            // Calcul des nouveaux points pour définir les nouvelles lignes de contrainte
            Point p = v1.getStart().nextPoint(v2.getStart(), f);
            Point q = v1.getEnd().nextPoint(v2.getEnd(), f);

            frame.addLine(new Line(p, q));
        } 

        return frame;
    }

    public ImageT newFrameBezier(int t)
    {
        ImageT frame = new ImageT(imgSrc.getWidth(), imgSrc.getHeight(), imgSrc.getFormat());
        double f = (double) t / this.getNbFrames();
        List<CourbesBezier> bezierCurves = courbesBezierInit();

        for (int i = 0; i < bezierCurves.size(); i++) {
            CourbesBezier bezier = bezierCurves.get(i);

            // Calculer les nouveaux points via les courbes de Bézier
            Point p = bezier.calculerPoint(f); // Point de départ
            Point q = bezier.calculerPoint(f); // Point d'arrivée

            frame.addLine(new Line(p, q));
        } 

        return frame;
    }

    private List<CourbesBezier> courbesBezierInit() 
    {
        List<CourbesBezier> courbes = new ArrayList<>();
        for (int i = 0; i < this.getNbLines(); i++) {
            Line lineSrc = imgSrc.getLine(i);
            Line lineDest = imgDest.getLine(i);
    
            // Créer une nouvelle courbe de Bézier pour cette paire de lignes
            CourbesBezier bezier = new CourbesBezier();
    
            // Ajouter les points de contrôle pour la courbe de Bézier
            bezier.ajouterPointControle(lineSrc.getStart().getPoint().getX(), lineSrc.getStart().getPoint().getY());
            bezier.ajouterPointControle(lineSrc.getEnd().getPoint().getX(), lineSrc.getEnd().getPoint().getY());
            bezier.ajouterPointControle(lineDest.getStart().getPoint().getX(), lineDest.getStart().getPoint().getY());
            bezier.ajouterPointControle(lineDest.getEnd().getPoint().getX(), lineDest.getEnd().getPoint().getY());
    
            // Ajoutez la courbe de Bézier à la liste
            courbes.add(bezier);
        }
        return courbes;
    }
    


    
    /**
     * Calcul de la transformation de l'image de départ vers l'image d'arrivée
     * @param imgSrc : image de départ
     * @param imgDest : image d'arrivée
     * @return ImageT
     */
    public void wrap(ImageT imgSrc, ImageT imgDest){
        double a = 0.5f;
        double b = 0.5f;
        double length;
        for (int x = 0 ; x < imgDest.getWidth() ; x++)
        {
            for (int y = 0 ; y < imgDest.getHeight() ; y++)
            {
                Point dsum = new Point(0, 0);
                double weightsum = 0;
                double dist = imgDest.getWidth() * imgDest.getHeight();

                for (int k = 0 ; k < this.getNbLines() ; k++){
                    
                    // Calcul de (u,v) dans imgDest
                    length = imgDest.getLine(k).norme();

                    double u, v;
                    Line l = imgDest.getLine(k);
                    u = l.hauteurRelative(new Point(x, y));
                    v = l.dist(new Point(x, y));
                    // Calcul de (XhX)
                    Couple<Double,Double> tempV = l.getVector().copy();
                    tempV.setX(tempV.getX()*u);
                    tempV.setY(tempV.getY()*u);
                    Point temp = new Point((int)(l.getStart().getX()+tempV.getX()),(int) (l.getStart().getY()+tempV.getY()));
                    Couple<Integer, Integer> vectDist = new Couple<Integer,Integer>(x-temp.getX(), y-temp.getX());


                    // Déduction de (x',y') dans imgSrc d'après (u,v)
                    Line lp = imgSrc.getLine(k);
                    Point pp = lp.getStart();
                    Point qp = lp.getEnd();
                    Point xpH = new Point((int)(pp.getPoint().getX() + u * lp.getVector().getX()), (int)(pp.getPoint().getY() + u * lp.getVector().getY()));
                    Point xp = new Point((int)(xpH.getPoint().getX() + vectDist.getX()), (int)(xpH.getPoint().getY() + vectDist.getY()));
                    
                    // Calcul du déplacement X'-X
                    Point d = new Point(x - xp.getPoint().getX(), y - xp.getPoint().getY());

                    // Calcul de la plus courte distance entre (x,y) et la ligne de contrainte
                    if (0<=u && u<=1){
                        dist = Math.abs(v);
                    } else {
                        if(u<0){
                            dist = Math.sqrt(Math.pow(x - pp.getPoint().getX(), 2) + Math.pow(y - pp.getPoint().getY(), 2));
                        } else {
                            dist = Math.sqrt(Math.pow(x - qp.getPoint().getX(), 2) + Math.pow(y - qp.getPoint().getY(), 2));
                        }
                    }
                    
                    // Calcul du poids
                    double weight = Math.pow(length / (dist + a), b);

                    // Calcul de la somme des distances et des poids
                    dsum.setPoint((int) (dsum.getPoint().getX() + d.getPoint().getX() * weight),(int) (dsum.getPoint().getY() +  d.getPoint().getY() * weight)); 
                    weightsum += weight;
                }

                // Calcul de la nouvelle position du pixel
                int xnew = (int) (x + dsum.div(weightsum).getPoint().getX());
                int ynew = (int) (y + dsum.div(weightsum).getPoint().getY());

                // Vérification des bornes
                if (xnew >= 0 && xnew < imgSrc.getWidth() && ynew >= 0 && ynew < imgSrc.getHeight())
                {
                    int pix = imgSrc.getImage().getRGB(xnew, ynew);
                    imgDest.getImage().setRGB(x, y, pix);
                }
            }
        }
    }

    /**
     * Interpolation de couleur
     * @param k : ordre de l'image intermédiaire (entre 0 et 1)
     * @param wrapSrc : image de départ
     * @param wrapDest : image d'arrivée
     * @return ImageT
     */
    public ImageT interpolateColor(int k, ImageT wrapSrc, ImageT wrapDest){
        ImageT img = new ImageT(wrapSrc.getWidth(), wrapSrc.getHeight(), wrapSrc.getFormat());
        double t = (double) k / this.getNbFrames();
        for (int x = 0 ; x < wrapSrc.getWidth() ; x++)
        {
            for (int y = 0 ; y < wrapSrc.getHeight() ; y++)
            {
                int pixSrc = wrapSrc.getImage().getRGB(x, y);
                int pixDest = wrapDest.getImage().getRGB(x, y);
                int pix = (int) (pixSrc * (1 - t) + pixDest * t);
                img.getImage().setRGB(x, y, pix);
            }
        }
        return img;

    }



    /**
     * Calcul de la transformation de l'image de départ vers l'image d'arrivée
     * @param imgSrc : image de départ
     * @param imgDest : image d'arrivée
     * @return ImageT
     */
    public void calculate(){
        setNbLines(imgSrc.getLines().size());
        frames = new ImageT[getNbFrames() + 1];

        for (int f = 0 ; f <= this.getNbFrames() ; f++){
            
            ImageT wrapSrc = newFrame(f);
            ImageT wrapDest = newFrame(f);
            wrap(imgSrc, wrapSrc);
            wrap(imgDest, wrapDest);            
            frames[f] = interpolateColor(f, wrapSrc, wrapDest);
        }
        saveFrames("test");
    }

    /**
     * Sauvegarde des images intermédiaires
     * @param path : chemin de sauvegarde
     */
    public void saveFrames(String path){
        for (int i = 0 ; i < this.getNbFrames() ; i++){
            System.out.println("fct save");
            System.out.println(frames[i]);
            frames[i].save(path + "/frame" + i + ".png");
            System.out.println(frames[i]);
        }
    }

    /**
     * Génération du gif
     */
    /*
    public void generateGif(String pathSrc, String pathTarget){
        try {
            saveFrames(pathSrc);
            OutputStream outputStream = new FileOutputStream(pathTarget+".gif");
            ImageOptions options = new ImageOptions();
            GifEncoder encod = new GifEncoder(outputStream, imgSrc.getWidth(), imgSrc.getHeight(), 0);
            int[][] pixels = new int[imgSrc.getWidth()][imgSrc.getHeight()];
            for (int i = 0 ; i < this.getNbFrames() ; i++){
                for (int x = 0 ; x < imgSrc.getWidth() ; x++){
                    for (int y = 0 ; y < imgSrc.getHeight() ; y++){
                        pixels[x][y] = frames[i].getImage().getRGB(x, y);
                    }
                }
                encod.addImage(pixels, options);
            }
            encod.finishEncoding();

            outputStream.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void generateGif(String pathTarget){
        try {
            OutputStream outputStream = new FileOutputStream(pathTarget+".gif");
            ImageOptions options = new ImageOptions();
            GifEncoder encod = new GifEncoder(outputStream, imgSrc.getWidth(), imgSrc.getHeight(), 0);
            int[][] pixels = new int[imgSrc.getWidth()][imgSrc.getHeight()];
            for (int i = 0 ; i < this.getNbFrames() ; i++){
                for (int x = 0 ; x < imgSrc.getWidth() ; x++){
                    for (int y = 0 ; y < imgSrc.getHeight() ; y++){
                        pixels[x][y] = frames[i].getImage().getRGB(x, y);
                    }
                }
                encod.addImage(pixels, options);
            }
            encod.finishEncoding();

            outputStream.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    */

    
}
