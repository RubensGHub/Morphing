package morphCourbe;

public class morphingSpline {
    private ImageS imgSrc;
    private ImageS imgDest;
    

    public morphingSpline(ImageS imgSrc, ImageS imgDest) {
        this.imgSrc = imgSrc;
        this.imgDest = imgDest;
    }


    public ImageS getImgSrc() {
        return imgSrc;
    }


    public void setImgSrc(ImageS imgSrc) {
        this.imgSrc = imgSrc;
    }


    public ImageS getImgDest() {
        return imgDest;
    }


    public void setImgDest(ImageS imgDest) {
        this.imgDest = imgDest;
    }

    
    
}
