package morphCourbe;
import morphing.*;
import java.awt.image.BufferedImage;

public class ImageS extends ImageG {
    private BSpline spline;
    
    public ImageS(int w, int h, String format) {
        super(w, h, format);
        spline = new BSpline();
    }

    public ImageS(String img) {
        super(img);
    }

    public ImageS(BufferedImage bImg, int w, int h, String ext) {
        super(bImg, w, h, ext);
    }

    public BSpline getSpline() {
        return spline;
    }

    public void setSpline(BSpline spline) {
        this.spline = spline;
    }    
}