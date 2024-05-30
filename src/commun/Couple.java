package commun;

/*
 * La classe Couple représente une paire d'objets génériques.
 * @param <X> Le type du premier élément.
 * @param <Y> Le type du deuxième élément.
 * @version 1.0
 * @since 1.0
 * @see commun
 * @see commun.Couple
 * @see commun.Couple#equals
 * @see commun.Couple#copy
 * @see commun.Couple#getX
 * @see commun.Couple#getY
 * @see commun.Couple#setX
 * @see commun.Couple#setY
 * @see commun.Couple#toString 
 */
public class Couple<X,Y> {
    private X x;
    private Y y;

    /**
     * Constructeur de la classe Couple.
     * @param x Le premier élément.
     * @param y Le deuxième élément.
     */
    public Couple(X x, Y y){
        this.x=x;
        this.y=y;
    }

    /**
     * Retourne le premier élément du couple.
     * @return Le premier élément.
     */
    public X getX() {
        return x;
    }

    /**
     * Retourne le deuxième élément du couple.
     * @return Le deuxième élément.
     */
    public Y getY() {
        return y;
    }

    /**
     * Modifie le premier élément du couple.
     * @param x Le nouveau premier élément.
     */
    public void setX(X x) {
        this.x = x;
    }

    /**
     * Modifie le deuxième élément du couple.
     * @param y Le nouveau deuxième élément.
     */
    public void setY(Y y) {
        this.y = y;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères du couple.
     * @return Une représentation du couple sous forme de chaîne de caractères.
     */
    @Override
    public String toString() {
        return "Couple{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    /**
     * Compare si ce couple est égal à un autre couple.
     * @param c Le couple à comparer.
     * @return true si les couples sont égaux, false sinon.
     */
    public boolean equals(Couple<X,Y> c){
        return this.x.equals(c.x) && this.y.equals(c.y);
    }

    /**
     * Effectue une copie de ce couple.
     * @return Une copie du couple.
     */
    public Couple<X,Y> copy(){
        return new Couple<>(this.x,this.y);
    }
}
