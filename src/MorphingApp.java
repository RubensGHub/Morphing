
public class MorphingApp {

    private ImageT imgSrc;
    private ImageT imgDest;
    private ImageT[] frames;
    private int nbFrames;
    private int nbLines;

    public ImageT getImgSrc() {
        return this.imgSrc;
    }

    public void setImgSrc(ImageT imgSrc) {
        this.imgSrc = imgSrc;
    }

    public ImageT getImgDest() {
        return this.imgDest;
    }

    public void setImgDest(ImageT imgDest) {
        this.imgDest = imgDest;
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
        this.imgSrc = null;
        this.imgDest = null;
        this.frames = null;
        this.nbFrames = 0;
        this.nbLines = 0;
    }

    /**
     * Créer une image intermédiaire à partir de l'image de départ et de l'image d'arrivée
     * @param t : ordre de l'image intermédiaire
     * @return ImageT
     */
    public ImageT newFrame(int t){
        ImageT frame = new ImageT(imgSrc.getWidth(), imgSrc.getHeight(), imgSrc.getFormat());

        for (int i = 0 ; i < this.getNbLines() ; i++)
        {
            Line v1 = imgSrc.getLine(i);
            Line v2 = imgDest.getLine(i);

            // Calcul des nouveaux points pour définir les nouvelles lignes de contrainte
            Point p = v1.getVector().getX().nextPoint(v2.getVector().getX(), t);
            Point q = v1.getVector().getY().nextPoint(v2.getVector().getY(), t);

            frame.addLine(new Line(p, q));
        } 

        return frame;
    }


    public void wrap(ImageT imgSrc, ImageT imgDest){
        double a = 0.5f;
        double b = 0.5f;
        double length;
        for (int x = 0 ; x < imgDest.getWidth() ; x++)
        {
            for (int y = 0 ; y < imgDest.getHeight() ; y++)
            {
                int dsum = 0;
                int weightsum = 0;
                double dist = imgDest.getWidth() * imgDest.getHeight();

                for (int k = 0 ; k < this.getNbLines() ; k++){

                    length = imgDest.getLine(k).length();
                    double u, v;
                    Line l = imgDest.getLine(k);
                    Point p = l.getVector().getX();
                    Point q = l.getVector().getY();
                    u = l.hauteur(new Point(x, y));
                    v = l.dist(new Point(x, y));

                    Line l2 = imgSrc.getLine(k);
                    Point p2 = l2.getVector().getX();
                    Point q2 = l2.getVector().getY();
                    Point transPoint = new Point((int) v * l2.perdendicular().getX(), (int) v * l2.perdendicular().getY());
                    Point xP = new Point((int) (p2.getPoint().getX() + u * (q2.getPoint().getX() - p2.getPoint().getX())) + transPoint.getPoint().getX(), (int) (p2.getPoint().getY() + u * (q2.getPoint().getY() - p2.getPoint().getY())) + transPoint.getPoint().getY());

                    // Calcul de la distance
                    double d = Math.sqrt(Math.pow(x - xP.getPoint().getX(), 2) + Math.pow(y - xP.getPoint().getY(), 2));
                    
                    // Calcul de la distance minimale
                    if(v < dist){
                        dist = v;
                    }
            
                    // Calcul du poids
                    double weight = Math.pow(length / (d + a),b);

                    // Calcul de la somme des distances et des poids
                    dsum += d * weight;
                    weightsum += weight;
                }

                // Calcul de la nouvelle position du pixel
                int xnew = (int) (x + dsum / weightsum);
                int ynew = (int) (y + dsum / weightsum);

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
        for (int x = 0 ; x < wrapSrc.getWidth() ; x++)
        {
            for (int y = 0 ; y < wrapSrc.getHeight() ; y++)
            {
                int pixSrc = wrapSrc.getImage().getRGB(x, y);
                int pixDest = wrapDest.getImage().getRGB(x, y);
                int pix = (int) (pixSrc * (1 - k) + pixDest * k);
                img.getImage().setRGB(x, y, pix);
            }
        }
        return img;

    }



    /**
     * Beier--Neely Field Warping AlgorithmNeely Field Warping Algor
     */
    public void generate()
    {
        for (int f = 0 ; f < this.getNbFrames() ; f++)
        {
            int t = f/this.getNbFrames();

            ImageT img = newFrame(t);
        }






        // Pour chaque pixel de notre image de départ
        for (int x = 0 ; x < imgSrc.getWidth() ; x++)
        {
            for (int y = 0 ; y < imgSrc.getHeight() ; y++)
            {
                int pix = imgSrc.getImage().getRGB(x, y);
                int dsum = 0;
                int weightsum = 0;

                // Pour chaque vecteur
                for (int v = 0 ; v < this.getNbLines() ; v++)
                {
                    // a finir ...
                }

            }
        }
    }




}
