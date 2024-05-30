package commun;

/*
 * La classe Point représente un point dans un plan 2D.
 * @version 1.0
 * @since 1.0
 * @see commun
 * @see commun.Point
 * @see commun.Point#div
 * @see commun.Point#add
 * @see commun.Point#sub
 * @see commun.Point#mul
 * @see commun.Point#nextPoint
 */
public class Point {
    private Couple<Integer, Integer> point;

    /**
     * Constructeur de la classe Point.
     * @param x La coordonnée en abscisse du point.
     * @param y La coordonnée en ordonnée du point.
     */
    public Point(int x, int y) {
        point = new Couple<>(x, y);
    }

    /**
     * Retourne le point sous forme d'un couple d'entiers.
     * @return Le point sous forme d'un couple.
     */
    public Couple<Integer, Integer> getPoint() {
        return this.point;
    }

    /**
     * Retourne la coordonnée en abscisse du point.
     * @return La coordonnée en abscisse.
     */
    public int getX() {
        return this.point.getX();
    }

    /**
     * Retourne la coordonnée en ordonnée du point.
     * @return La coordonnée en ordonnée.
     */
    public int getY() {
        return this.point.getY();
    }

    /**
     * Modifie la coordonnée en abscisse du point.
     * @param x La nouvelle coordonnée en abscisse.
     */
    public void setX(int x) {
        this.point.setX(x);
    }

    /**
     * Modifie la coordonnée en ordonnée du point.
     * @param y La nouvelle coordonnée en ordonnée.
     */
    public void setY(int y) {
        this.point.setY(y);
    }

    /**
     * Modifie les coordonnées du point.
     * @param point Le nouveau point.
     */
    public void setPoint(Couple<Integer, Integer> point) {
        this.point = point;
    }

    /**
     * Modifie les coordonnées du point.
     * @param x La nouvelle coordonnée en abscisse.
     * @param y La nouvelle coordonnée en ordonnée.
     */
    public void setPoint(int x, int y) {
        this.point = new Couple<>(x, y);
    }

    /**
     * Divise les coordonnées du point par un scalaire.
     * @param d Le scalaire de division.
     * @return Le point résultant de la division.
     */
    public Point div(double d) {
        return new Point((int) (this.point.getX() / d), (int) (this.point.getY() / d));
    }

    /**
     * Additionne les coordonnées du point avec celles d'un autre point.
     * @param p Le point à ajouter.
     * @return Le point résultant de l'addition.
     */
    public Point add(Point p) {
        return new Point(this.point.getX() + p.getPoint().getX(), this.point.getY() + p.getPoint().getY());
    }

    /**
     * Soustrait les coordonnées du point avec celles d'un autre point.
     * @param p Le point à soustraire.
     * @return Le point résultant de la soustraction.
     */
    public Point sub(Point p) {
        return new Point(this.point.getX() - p.getPoint().getX(), this.point.getY() - p.getPoint().getY());
    }

    /**
     * Multiplie les coordonnées du point par un scalaire.
     * @param d Le scalaire de multiplication.
     * @return Le point résultant de la multiplication.
     */
    public Point mul(int d) {
        return new Point(this.point.getX() * d, this.point.getY() * d);
    }

    /**
     * Calcule le point suivant le long de la ligne entre ce point et un autre point à une distance proportionnelle à t.
     * @param p L'autre point.
     * @param t Le paramètre de distance.
     * @return Le point suivant.
     */
    public Point nextPoint(Point p, double t)
    {
        int x = (int)(this.getPoint().getX() * (1-t) + p.getPoint().getX() * t);
        int y = (int)(this.getPoint().getY() * (1-t) + p.getPoint().getY() * t);

        return new Point(x, y);
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères du point.
     * @return Une représentation du point sous forme de chaîne de caractères.
     */
    @Override
    public String toString() {
        return "(" + point.getX() + ", " + point.getY() + ")";
    }
}
