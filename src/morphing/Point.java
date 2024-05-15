package morphing;

public class Point {
    private Couple<Integer, Integer> point;

    public Point(int x, int y) {
        point = new Couple<>(x, y);
    }

    public Couple<Integer, Integer> getPoint() {
        return this.point;
    }

    public int getX() {
        return point.getX();
    }

    public int getY() {
        return point.getY();
    }

    public void setX(int x) {
        this.point.setX(x);
    }

    public void setY(int y) {
        this.point.setY(y);
    }

    

    public void setPoint(Couple<Integer, Integer> point) {
        this.point = point;
    }

    public Point nextPoint(Point p, int t)
    {
        int x = this.getPoint().getX() * (1-t) + p.getPoint().getX() * t;
        int y = this.getPoint().getY() * (1-t) + p.getPoint().getY() * t;

        return new Point(x, y);
    }
}
