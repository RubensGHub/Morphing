package morphingFormesArrondies.abstraction;

import commun.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BSpline {
    int deg;
    ArrayList<Point> controlPolygon;
    ArrayList<Double> nodeVector;
    ArrayList<Couple<Double, Double>> tVectors;
    protected double[][] matrice;
    protected Point[] courbe;

    public BSpline(int deg, ArrayList<Point> controlPolygon, ArrayList<Double> nodeVector) {
        this.deg = deg;
        this.controlPolygon = controlPolygon;
        this.nodeVector = nodeVector;
        this.tVectors = new ArrayList<Couple<Double, Double>>();
        for (int i = 0; i < controlPolygon.size(); i++) {
            tVectors.add(new Couple<Double, Double>(0.0, 0.0));
        }
        this.matrice = new double[controlPolygon.size()][(int)((nodeVector.get(nodeVector.size() - 1) - nodeVector.get(0)) * 100) + 1];
        this.courbe = new Point[(int)((nodeVector.get(nodeVector.size() - 1) - nodeVector.get(0))*100) + 1];;
    }

    public BSpline() {
        this.deg = -1;
        this.controlPolygon = new ArrayList<Point>();
        this.nodeVector = new ArrayList<Double>();
        this.tVectors = new ArrayList<Couple<Double, Double>>();
        for (int i = 0; i < controlPolygon.size(); i++) {
            tVectors.add(new Couple<Double, Double>(0.0, 0.0));
        }
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    public ArrayList<Point> getcontrolPolygon() {
        return controlPolygon;
    }

    public void setcontrolPolygon(ArrayList<Point> controlPolygon) {
        this.controlPolygon = controlPolygon;
    }

    public ArrayList<Double> getNodeVector() {
        return nodeVector;
    }

    public void setNodeVector(ArrayList<Double> nodeVector) {
        this.nodeVector = nodeVector;
    }

    public void addControlPoint(Point p) {
        controlPolygon.add(p);
    }

    public void addNode(double d) {
        nodeVector.add(d);
    }

    public void removeControlPoint(Point p) {
        controlPolygon.remove(p);
    }

    public void removeNode(double d) {
        nodeVector.remove(d);
    }

    public void removeControlPoint(int i) {
        controlPolygon.remove(i);
    }

    public Point getcontrolPoint(int i) {
        return controlPolygon.get(i);
    }

    public double getNode(int i) {
        return nodeVector.get(i);
    }

    public void close() {
        controlPolygon.add(controlPolygon.get(0));
    }

    public boolean isClosed() {
        return controlPolygon.get(0).equals(controlPolygon.get(controlPolygon.size() - 1));
    }

    @Override
    public String toString() {
        return "BSpline{" +
                "deg=" + deg +
                ", controlPolygon=" + controlPolygon +
                ", nodeVector=" + nodeVector +
                '}';
    }


    /**
     * Calcule un point sur la courbe B-Spline à la valeur de paramètre t en utilisant l'algorithme de De Boor.
     *
     * @param t La valeur du paramètre pour évaluer la courbe, où 0 <= t <= 1
     * @return Le point calculé sur la courbe B-Spline
     * @autor : Paul Usieto
     */

    public Point calculerPoint(double t, boolean b)
    {
        int n = controlPolygon.size() - 1;
        int k = trouverIntervalle(t);

        ArrayList<Point> d = new ArrayList<>(controlPolygon);

        for (int r = 1; r <= deg; r++)
        {
            for (int j = k; j > k - r; j--)
            {
                int i = j - k + deg - r;
                double a = (t - nodeVector.get(j)) / (nodeVector.get(j + deg - r + 1) - nodeVector.get(j));
                Point p1 = d.get(i);
                Point p2 = d.get(i + 1);
                double x = (1 - a) * p1.getX() + a * p2.getX();
                double y = (1 - a) * p1.getY() + a * p2.getY();
                d.set(i, new Point((int) x, (int) y));

                if (b && matrice != null && i < matrice.length)
                {
                    matrice[j][r] = a;
                }
            }
        }
        return d.get(k - deg);
    }

    /**
     * Trouve l'intervalle dans le vecteur de noeuds qui contient le paramètre t.
     *
     * @param t La valeur du paramètre, où 0 <= t <= 1
     * @return L'indice de l'intervalle
     * @autor : Paul Usieto
     */
    private int trouverIntervalle(double t)
    {
        if (t == nodeVector.get(nodeVector.size() - 1))
        {
            return nodeVector.size() - deg - 2;
        }

        int low = 0;
        int high = nodeVector.size() - 1;

        while (low <= high)
        {
            int mid = (low + high) / 2;
            if (t >= nodeVector.get(mid) && t < nodeVector.get(mid + 1))
            {
                return mid;
            }
            else if (t < nodeVector.get(mid))
            {
                high = mid - 1;
            }
            else
            {
                low = mid + 1;
            }
        }
        return 0;
    }

    /**
     * Calcule le vecteur de noeuds uniforme pour la courbe B-Spline.
     *
     * @autor : Ryan Bouchou
     */
    public void computeUniformVector() {
        nodeVector.clear();
        int n = controlPolygon.size();
        int m = n + deg;
        for (int i = 0; i < m + 1; i++) {
            if (i < deg + 1) {
                nodeVector.add(0.0);
            } else if (i >= m - deg) {
                nodeVector.add(1.0);
            } else {
                nodeVector.add((double) (i - deg) / (double) (n - deg + 1));
            }

        }

    }


    public ArrayList<Couple<Double, Double>> gettVectors() {
        return tVectors;
    }

    public void settVectors(ArrayList<Couple<Double, Double>> tVectors) {
        this.tVectors = tVectors;
    }

    // Modifier un vecteur t ou récupérer un vecteur t
    public void settVector(int i, Couple<Double, Double> t) {
        tVectors.set(i, t);
    }

    public Couple<Double, Double> gettVector(int i) {
        return tVectors.get(i);
    }

    /*public void initMatrice()
    {
        double tStep = 0.01;
        for (int i = 0; i < courbe.length; i++)
        {
            double t = tStep * i;
            courbe[i] = calculerPoint(t, false);
            for (int j = 0; j < controlPolygon.size(); j++)
            {
                matrice[j][i] = calculN(j, deg, t);
            }
        }
    }*/


    /*public double calculN(int i, int p, double t)
    {
        if (p == 0)
        {
            return (t >= nodeVector.get(i) && t < nodeVector.get(i + 1)) ? 1.0 : 0.0;
        }
        double leftTerm = 0.0, rightTerm = 0.0;

        if (nodeVector.get(i + p) != nodeVector.get(i))
        {
            leftTerm = ((t - nodeVector.get(i)) / (nodeVector.get(i + p) - nodeVector.get(i))) * calculN(i, p - 1, t);
        }
        if (nodeVector.get(i + p + 1) != nodeVector.get(i + 1))
        {
            rightTerm = ((nodeVector.get(i + p + 1) - t) / (nodeVector.get(i + p + 1) - nodeVector.get(i + 1))) * calculN(i + 1, p - 1, t);
        }

        return leftTerm + rightTerm;
    }*/
}