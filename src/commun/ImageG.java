package commun;

/*
 * La classe ImageG représente une image générique.
 * @version 1.0
 * @since 1.0
 * @see commun
 * @see commun.ImageG
 * @see commun.ImageG#getPixel
 * @see commun.ImageG#setPixel
 * @see commun.ImageG#save
 * @see commun.ImageG#getImage
 * @see commun.ImageG#ImageG 
 */
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageG {
    private BufferedImage image;
    private int width;
    private int height;
    private String format;

    /**
     * Constructeur de la classe ImageG.
     * @param bImg L'image à utiliser.
     * @param w La largeur de l'image.
     * @param h La hauteur de l'image.
     * @param ext Le format de l'image.
     */
    public ImageG(BufferedImage bImg, int w, int h, String ext) {
        image = bImg;
        width = w;
        height = h;
        format = ext;
    }

    /**
     * Constructeur de la classe ImageG.
     * @param path Le chemin vers le fichier image.
     */
    public ImageG(String path) {
        try {
            image = ImageIO.read(new File(path));
            width = image.getWidth();
            height = image.getHeight();
            format = path.substring(path.lastIndexOf('.') + 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructeur de la classe ImageG.
     * @param width La largeur de l'image.
     * @param height La hauteur de l'image.
     * @param format Le format de l'image.
     */
    public ImageG(int width, int height, String format) {
        this.width = width;
        this.height = height;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.format = format;
    }

    /**
     * Récupère la coordonnée minimale en abscisse de l'image.
     * @return La coordonnée minimale en abscisse.
     */
    public int getMinX() {
        return this.image.getMinX();
    }

    /**
     * Récupère la coordonnée maximale en abscisse de l'image.
     * @return La coordonnée maximale en abscisse.
     */
    public int getMaxX() {
        return this.image.getWidth();
    }

    /**
     * Récupère la coordonnée minimale en ordonnée de l'image.
     * @return La coordonnée minimale en ordonnée.
     */
    public int getMinY() {
        return this.image.getMinY();
    }

    /**
     * Récupère la coordonnée maximale en ordonnée de l'image.
     * @return La coordonnée maximale en ordonnée.
     */
    public int getMaxY() {
        return this.image.getHeight();
    }

    /**
     * Définit la couleur d'un pixel de l'image.
     * @param x La coordonnée en abscisse du pixel.
     * @param y La coordonnée en ordonnée du pixel.
     * @param color La couleur du pixel.
     */
    public void setPixel(int x, int y, Color color) {
        image.setRGB(x, y, color.getRGB());
    }

    /**
     * Récupère la couleur d'un pixel de l'image.
     * @param x La coordonnée en abscisse du pixel.
     * @param y La coordonnée en ordonnée du pixel.
     * @return La couleur du pixel.
     */
    public Color getPixel(int x, int y) {
        return new Color(image.getRGB(x, y));
    }

    /**
     * Récupère la hauteur de l'image.
     * @return La hauteur de l'image.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Définit la hauteur de l'image.
     * @param height La nouvelle hauteur de l'image.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Récupère la largeur de l'image.
     * @return La largeur de l'image.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Définit la largeur de l'image.
     * @param width La nouvelle largeur de l'image.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Enregistre l'image dans un fichier.
     * @param path Le chemin vers le fichier de destination.
     */
    public void save(String path) {
        try {
            ImageIO.write(image, format, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Récupère l'image sous forme de BufferedImage.
     * @return L'image sous forme de BufferedImage.
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Récupère le format de l'image.
     * @return Le format de l'image.
     */
    public String getFormat() {
        return this.format;
    }
}
