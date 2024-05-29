package morphing;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import com.squareup.gifencoder.GifEncoder;
import com.squareup.gifencoder.ImageOptions;
import java.util.Observable;




@SuppressWarnings("deprecation")
/**
 * Classe MorphingApp
 */
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
     * @autor : Ryan Bouchou
     * @date : 2024-05-27
     * @version : 1.0
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



    /**
     * Calcul de la transformation de l'image de départ vers l'image d'arrivée
     * @param imgSrc : image de départ
     * @param imgDest : image d'arrivée
     * @return void
     * @autor : Ryan Bouchou
     * @date : 2024-05-27
     * @version : 3.0
     */
    public void wrap(ImageT imgSrc, ImageT imgDest){
        double a = 0.5f;
        double b = 1f;
        double p = 0.75f;
        double length;
        for (int x = imgDest.getMinX() ; x < imgDest.getMaxX(); x++){
            for (int y = imgDest.getMinY() ; y < imgDest.getMaxY() ; y++){
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

                    // Déduction de (x',y') dans imgSrc d'après (u,v)
                    Line lp = imgSrc.getLine(k);
                    Point pp = lp.getStart();
                    Point qp = lp.getEnd();
                    Point xpH = new Point((int)(pp.getPoint().getX() + u * lp.getVector().getX()), (int)(pp.getPoint().getY() + u * lp.getVector().getY()));
                    Point xp = new Point((int)(xpH.getPoint().getX() + v * lp.vectorNormalUnitaire().getX() ), (int)(xpH.getPoint().getY() + v * lp.vectorNormalUnitaire().getY()));

                    // Calcul du déplacement X'-X
                    Point d = new Point(xp.getPoint().getX() - x, xp.getPoint().getY() - y);

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
                    double weight = Math.pow(Math.pow(length,p) / (dist + a), b);

                    // Calcul de la somme des distances et des poids
                    dsum.setPoint((int) (dsum.getPoint().getX() + d.getPoint().getX() * weight),(int) (dsum.getPoint().getY() +  d.getPoint().getY() * weight));
                    weightsum += weight;
                }

                // Calcul de la nouvelle position du pixel
                int xnew = (int) (x + dsum.getX()/weightsum);
                int ynew = (int) (y + dsum.getY()/weightsum);

                // Vérification des bornes
                if (xnew >= imgSrc.getMinX() && xnew < imgSrc.getMaxX() && ynew >= imgSrc.getMinY() && ynew < imgSrc.getMaxY() && x >= imgDest.getMinX() && x < imgDest.getMaxX() && y >= imgDest.getMinY() && y < imgDest.getMaxY())
                {
                    int pix = imgSrc.getImage().getRGB(xnew, ynew);
                    imgDest.getImage().setRGB(x, y, pix);

                }
            }
        }
    }

    /**
     * Interpolation des couleurs entre deux images
     * @param k : ordre de l'image intermédiaire (entre 0 et 1)
     * @param wrapSrc : image de départ
     * @param wrapDest : image d'arrivée
     * @return ImageT
     * @autor : Ryan Bouchou
     * @date : 2024-05-27
     * @version : 2.0
     */
    public ImageT interpolateColor(int k, ImageT wrapSrc, ImageT wrapDest){
        ImageT img = new ImageT(wrapSrc.getWidth(), wrapSrc.getHeight(), wrapSrc.getFormat());
        double t = (double) k / this.getNbFrames();
        for (int x = wrapSrc.getMinX() ; x < wrapSrc.getWidth() ; x++)
        {
            for (int y = wrapSrc.getMinY() ; y < wrapSrc.getHeight() ; y++)
            {
                Color pixSrc = new Color(wrapSrc.getImage().getRGB(x, y), true);
                Color pixDest = new Color(wrapDest.getImage().getRGB(x, y), true);

                // Interpoler les composantes colorimétriques
                double red = pixSrc.getRed() * (1 - t) + pixDest.getRed() * t;
                double green = pixSrc.getGreen() * (1 - t) + pixDest.getGreen() * t;
                double blue = pixSrc.getBlue() * (1 - t) + pixDest.getBlue() * t;
                double alpha = pixSrc.getAlpha() * (1 - t) + pixDest.getAlpha() * t;
                Color interpolatedColor = new Color((int)red,(int)green,(int) blue, (int) alpha);

                // Affectation de la couleur à l'image intermédiaire
                img.getImage().setRGB(x, y, interpolatedColor.getRGB());
            }
        }
        return img;
    }



    /**
     * Calcul de la transformation de l'image de départ vers l'image d'arrivée
     * @param imgSrc : image de départ
     * @param imgDest : image d'arrivée
     * @return void
     * @autor : Ryan Bouchou
     * @date : 2024-05-27
     * @version : 2.0
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
        saveFrames("/home/cytech/projet/Morphing/test");
        //generateGif("./");
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

    /**
     * Génération d'un gif à partir des images intermédiaires
     * @param pathSrc
     * @param pathTarget
     * @return void
     * @autor : Ryan Bouchou
     */
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

    /**
     * Génération d'un gif à partir des images intermédiaires
     * @param pathTarget
     * @return void
     * @autor : Ryan Bouchou
     */
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




}
