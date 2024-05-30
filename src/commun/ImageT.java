package commun;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Cette classe représente une image avec des formes.
 */
public class ImageT extends ImageG {

    private ArrayList<Line> lines = new ArrayList<>();
    private ArrayList<Point> points = new ArrayList<>();
    private Point tempPoint;
    private int compteur = 1;
    private final static Couple<Integer, Integer> DEFAULT_ORIENTATION = new Couple<Integer,Integer>(-1,0);
    ArrayList<Double> lengthVector;
    ArrayList<Double> angleVector;

    /**
     * Constructeur de la classe ImageT à partir d'une BufferedImage.
     * @param bImg L'image BufferedImage.
     * @param w La largeur de l'image.
     * @param h La hauteur de l'image.
     * @param ext L'extension de l'image.
     */
    public ImageT(BufferedImage bImg, int w, int h, String ext) {
        super(bImg, w, h, ext);
    }

    /**
     * Constructeur de la classe ImageT à partir du chemin d'une image.
     * @param path Le chemin de l'image.
     */
    public ImageT(String path) {
        super(path);
    }

    /**
     * Constructeur de la classe ImageT avec largeur, hauteur et format spécifiés.
     * @param width La largeur de l'image.
     * @param height La hauteur de l'image.
     * @param format Le format de l'image.
     */
    public ImageT(int width, int height, String format) {
        super(width, height, format);
    }

    /**
     * Ajoute une ligne à l'image.
     * @param l La ligne à ajouter.
     */
    public void addLine(Line l){
        lines.add(l);
    }

    /**
     * Récupère une ligne de l'image par son index.
     * @param i L'index de la ligne.
     * @return La ligne correspondante.
     */
    public Line getLine(int i){
        return lines.get(i);
    }

    /**
     * Supprime une ligne de l'image par son index.
     * @param i L'index de la ligne à supprimer.
     */
    public void removeLine(int i){
        lines.remove(i);
    }

    /**
     * Récupère la liste de lignes de l'image.
     * @return La liste de lignes.
     */
    public ArrayList<Line> getLines() {
        return lines;
    }

    /**
     * Définit la liste de lignes de l'image.
     * @param lines La liste de lignes à définir.
     */
    public void setLines(ArrayList<Line> lines) {
        this.lines = lines;
    }

    /**
     * Récupère le point temporaire de l'image.
     * @return Le point temporaire.
     */
    public Point getTempPoint() {
        return this.tempPoint;
    }

    /**
     * Définit le point temporaire de l'image.
     * @param tempPoint Le point temporaire à définir.
     */
    public void setTempPoint(Point tempPoint) {
        this.tempPoint = tempPoint;
    }

    /**
     * Affiche les lignes de l'image.
     */
    public void printLines() {
        if (lines == null) {
            System.out.println("La liste de lignes est null.");
        } else if (lines.isEmpty()) {
            System.out.println("La liste de lignes est vide.");
        } else {
            System.out.println("\nTableau à " + compteur + " lignes :");
            for (Line line : lines) {
                System.out.print(line);
            }
            System.out.println("");
            compteur++;
        }
    }

    /**
     * Ajoute un point à l'image.
     * @param p Le point à ajouter.
     */
    public void addPoint(Point p){
        points.add(p);
    }

    /**
     * Récupère un point de l'image par son index.
     * @param i L'index du point.
     * @return Le point correspondant.
     */
    public Point getPoint(int i){
        return points.get(i);
    }

    /**
     * Supprime un point de l'image par son index.
     * @param i L'index du point à supprimer.
     */
    public void removePoint(int i){
        points.remove(i);
    }

    /**
     * Récupère la liste de points de l'image.
     * @return La liste de points.
     */
    public ArrayList<Point> getPoints() {
        return points;
    }

    /**
     * Définit la liste de points de l'image.
     * @param points La liste de points à définir.
     */
    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }
}
