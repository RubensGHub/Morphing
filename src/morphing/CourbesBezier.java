package morphing;

import java.util.ArrayList;
import java.util.List;

public class CourbesBezier {
    private final List<Point> pointsControle;

    public CourbesBezier() 
    {
        this.pointsControle = new ArrayList<>();
    }

    public void ajouterPointControle(int x, int y) 
    {
        this.pointsControle.add(new Point(x, y));
    }

    public List<Point> getpointsControle() 
    {
        return pointsControle;
    }

    /**
     * Calcule un point sur la courbe à un paramètre t (0 <= t <= 1)
     * @param t Le paramètre de la courbe
     * @return Le point calculé sur la courbe
     */
    public Point calculerPoint(double t) 
    {
        int n = pointsControle.size() - 1;
        double x = 0;
        double y = 0;
        double coeffBinomial;
        double powT;
        double pow1SurT;
        for (int i = 0; i <= n; i++) 
        {
            coeffBinomial = factorielle(n) / (factorielle(i) * factorielle(n - i));
            powT = Math.pow(t, i);
            pow1SurT = Math.pow(1 - t, n - i);
            x += coeffBinomial * powT * pow1SurT * pointsControle.get(i).getPoint().getX();
            y += coeffBinomial * powT * pow1SurT * pointsControle.get(i).getPoint().getY();
        }
        return new Point((int)x, (int)y);
    }

    private double factorielle(int number) 
    {
        double result = 1;
        for (int factor = 2; factor <= number; factor++) 
        {
            result *= factor;
        }
        return result;
    }
}
}