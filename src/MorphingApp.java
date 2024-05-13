
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


    public MorphingApp() {

    }



    public ImageT newFrame(int t)
    {
        ImageT frame = new ImageT(imgSrc.getWidth(), imgSrc.getHeight(), imgSrc.getFormat());

        for (int i = 0 ; i < this.getNbLines() ; i++)
        {
            Line vect = imgSrc.getLine(i);

            // Calcul des nouveaux points pour définir les nouvelles lignes de contrainte
            Point p = vect.getLine().getX().nextPoint(vect.getLine().getX(), t);
            Point q = vect.getLine().getY().nextPoint(vect.getLine().getY(), t);

            frame.addLine(new Line(p, q));
        } 

        return frame;
    }


    public void wrap(ImageT img)
    {

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
