package commun;

import java.util.Observable;

/**
 * Représente une ligne avec deux points de départ et d'arrivée.
 */
@SuppressWarnings("deprecation")
public class Line extends Observable {
    private Couple<Point,Point> line;
    private Couple<Double,Double> vector;

    /**
     * Constructeur de la classe Line à partir d'un couple de points.
     * @param line Le couple de points représentant la ligne.
     */
    public Line(Couple<Point,Point> line) {
        this.line = line;
        double x = line.getY().getPoint().getX()-line.getX().getPoint().getX();
        double y = line.getX().getPoint().getY()-line.getX().getPoint().getY();
        vector = new Couple<Double,Double>(x, y);
    }

    /**
     * Constructeur de la classe Line à partir de deux points de départ et d'arrivée.
     * @param start Le point de départ de la ligne.
     * @param end Le point d'arrivée de la ligne.
     */
    public Line(Point start, Point end) {
        this.line = new Couple<Point,Point>(start, end);
        double x = end.getPoint().getX()-start.getPoint().getX();
        double y = end.getPoint().getY()-start.getPoint().getY();
        vector = new Couple<Double,Double>(x, y);
    }

    /**
     * Obtient le couple de points représentant la ligne.
     * @return Le couple de points.
     */
    public Couple<Point,Point> getLine() {
        return this.line;
    }

    /**
     * Définit le couple de points représentant la ligne.
     * @param line Le couple de points à définir.
     */
    public void setLine(Couple<Point,Point> line) {
        this.line = line;
    }

    /**
     * Obtient le point de départ de la ligne.
     * @return Le point de départ.
     */
    public Point getStart() {
        return this.line.getX();
    }

    /**
     * Obtient le point d'arrivée de la ligne.
     * @return Le point d'arrivée.
     */
    public Point getEnd() {
        return this.line.getY();
    }

    /**
     * Définit le point de départ de la ligne.
     * @param start Le point de départ à définir.
     */
    public void setStart(Point start) {
        this.line.setX(start);
        // PAC
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Définit le point d'arrivée de la ligne.
     * @param end Le point d'arrivée à définir.
     */
    public void setEnd(Point end) {
        this.line.setY(end);
        // PAC
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Définit le point d'arrivée de la ligne.
     * @param end Le point d'arrivée à définir.
     */
    public Couple<Double,Double> getVector() {
        return this.vector;
    }

    /**
    * Calcule la norme du vecteur représentant la ligne.
    * @return La norme du vecteur.
    */
    public double norme() {
        return Math.sqrt(Math.pow(this.vector.getX(), 2) + Math.pow(this.vector.getY(), 2));
    }

    /**
    * Calcule le vecteur normal à la ligne, c'est-à-dire le vecteur résultant de la rotation de 90 degrés du vecteur de la ligne.
    * @return Le vecteur normal.
    */
    public Couple<Double,Double> vectorNormal(){
        // Rotation du vecteur de pi/2
        double x = -this.vector.getY();
        double y = this.vector.getX();
        return new Couple<Double,Double>(x, y);
    }

    /**
     * Calcule le produit scalaire entre le vecteur de cette ligne et le vecteur d'une autre ligne.
     * @param y L'autre ligne.
     * @return Le produit scalaire.
    */
    public double scalaire(Line y){
        return this.vector.getX()*y.getVector().getX()+this.vector.getY()*y.getVector().getY();
    }

    /**
     * Calcule le vecteur normalisé, c'est-à-dire le vecteur résultant de la rotation de 90 degrés du vecteur de la ligne, et dont la longueur est égale à 1.
     * @return Le vecteur normalisé.
    */
    public Couple<Double,Double> vectorNormalUnitaire(){
        // Rotation du vecteur de pi/2
        double x = -this.vector.getY();
        double y = this.vector.getX();
        double norme = Math.sqrt(x*x+y*y);
        return new Couple<Double,Double>(x/norme, y/norme);
    }

    
    /**
     * Obtient la hauteur relative par rapport à un point.
     * @param x Le point par rapport auquel calculer la hauteur relative.
     * @return La hauteur relative.
     */
    public double hauteurRelative(Point x){

        // Calcul de [PX] et [PQ]
        Couple<Double,Double> s1 = new Couple<Double,Double>((double)x.getPoint().getX()-this.getStart().getPoint().getX(), (double)x.getPoint().getY()-this.getStart().getPoint().getY());
        Couple<Double,Double> s2 = getVector();

        // Numerateur
        Double num = s1.getX()*s2.getX()+s1.getY()*s2.getY();

        // Dénominateur
        Double den = this.norme() * this.norme();

        return num/den;

    }

    /**
     * Calcule la distance entre la ligne et un point.
     * @param x Le point par rapport auquel calculer la distance.
     * @return La distance entre la ligne et le point.
     */
    public double dist(Point x){
        // Calcul de [XP] et perpendiculaire
        Couple<Double,Double> s1 = new Couple<Double,Double>((double)x.getPoint().getX()-this.getStart().getPoint().getX(), (double)x.getPoint().getY()-this.getStart().getPoint().getY());
        Couple<Double,Double> s2 = this.vectorNormal();

        // Numerateur
        double num = s1.getX()*s2.getX()+s1.getY()*s2.getY();

        // Dénominateur
        double den = this.norme();

        return num/den;
    }

    @Override
    public String toString() {
        return "[ " + getStart() + ", " + getEnd() + " ]  ||  ";
    }
}
