package morphing;

public class FormesUniesPolygonales {

    /**
     * Méthode qui vérifie si un point est dans un polygone
     * @param tab
     * @param P
     * @return true si le point est dans le polygone, false sinon
     */
    boolean Collision(Point tab[],Point P){
    int nbp = tab.length;
    int i;
    for(i=0;i<nbp;i++){
        Point A = tab[i];
        Point B;
        if (i==nbp-1){ 
            B = tab[0];     // si c'est le dernier point, on relie au premier
        }
        else{ 
            B = tab[i+1];   // sinon on relie au suivant.
        }

        Couple<Integer,Integer> D = new Couple<>(B.getX() - A.getX(),B.getY() - A.getY());
        Couple<Integer,Integer> T = new Couple<>(P.getX() - A.getX(),P.getY() - A.getY());

        double d = D.getX()*T.getY() - D.getY()*T.getX();

        if (d<0){
            return false;  // un point à droite et on arrête tout.
        }
    }
    return true;  // si on sort du for, c'est qu'aucun point n'est à gauche, donc c'est bon.
    }
}
