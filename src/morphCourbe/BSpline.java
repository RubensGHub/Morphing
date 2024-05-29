package morphCourbe;
import morphing.*;

import java.util.ArrayList;

public class BSpline {
    int deg;
    ArrayList<Point> controlPolygon;
    ArrayList<Double> nodeVector;

    public BSpline(int deg, ArrayList<Point> controlPolygon, ArrayList<Double> nodeVector) {
        this.deg = deg;
        this.controlPolygon = controlPolygon;
        this.nodeVector = nodeVector;
    }

    public BSpline() {
        this.deg = -1;
        this.controlPolygon = new ArrayList<Point>();
        this.nodeVector = new ArrayList<Double>();
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
     * @param t La valeur du paramètre pour évaluer la courbe, où 0 <= t <= 1
     * @return Le point calculé sur la courbe B-Spline
     * @autor : Paul Usieto
     */
    public Point calculerPoint(double t)
    {
        int k = trouverIntervalle(t);
        ArrayList<Point> d = new ArrayList<>(controlPolygon);

        // Copie de points de controle
        for (int i = 0; i <= deg; i++)
        {
            Point original = controlPolygon.get(k - deg + i);
            d.add(new Point(original.getX(), original.getY()));
        }

        // Algorithme de De Boor
        for (int r = 1; r <= deg; r++)
        {
            for (int j = k; j > k - r; j--)
            {
                int i = j - k + deg;
                double a = (t - nodeVector.get(j)) / (nodeVector.get(j + deg - r + 1) - nodeVector.get(j));
                Point interpolated = d.get(i - 1).nextPoint(d.get(i), a);
                d.set(i, interpolated);
            }
        }
        return d.get(deg);
    }

    /**
     * Trouve l'intervalle dans le vecteur de noeuds qui contient le paramètre t.
     * @param t La valeur du paramètre, où 0 <= t <= 1
     * @return L'indice de l'intervalle
     * @autor : Paul Usieto
     */
    private int trouverIntervalle(double t) {
        int n = controlPolygon.size() - 1;
        if (t >= nodeVector.get(n + 1)) {
            return n;
        }
        int low = deg;
        int high = n + 1;
        int mid = (low + high) / 2;
        while (t < nodeVector.get(mid) || t >= nodeVector.get(mid + 1))
        {
            if (t < nodeVector.get(mid))
            {
                high = mid;
            }
            else
            {
                low = mid;
            }
            mid = (low + high) / 2;
        }
        return mid;
    }

    /*public static void main(String[] args) {
        // Create the BSpline instance with scaled control points
        ArrayList<Point> controlPolygon = new ArrayList<>();
        controlPolygon.add(new Point(0, 0));    // Consider multiplying these by 100
        controlPolygon.add(new Point(100, 200));
        controlPolygon.add(new Point(300, -100));
        controlPolygon.add(new Point(400, 300));
        controlPolygon.add(new Point(700, 0));

        ArrayList<Double> knotVector = new ArrayList<>();
        knotVector.add(0.0);   // n + deg + 1 knots for n = 5, deg = 2 -> total 8 knots
        knotVector.add(0.0);
        knotVector.add(0.0);
        knotVector.add(0.33);
        knotVector.add(0.66);
        knotVector.add(1.0);
        knotVector.add(1.0);
        knotVector.add(1.0);

        BSpline spline = new BSpline(2, controlPolygon, knotVector);

        // Output the points for t from 0 to 1
        System.out.println("Points on the B-Spline with scaled coordinates:");
        for (double t = 0.0; t <= 1.0; t += 0.1) {
            Point p = spline.calculerPoint(t);
            System.out.printf("t = %.1f -> Point(%d, %d)\n", t, p.getX(), p.getY());
        }
    }*/
}


