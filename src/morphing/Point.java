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
        return this.point.getX();
    }

    public int getY() {
        return this.point.getY();
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

    public void setPoint(int x, int y) {
        this.point = new Couple<>(x, y);
    }

    public Point div(double d) {
        return new Point((int) (this.point.getX() / d), (int) (this.point.getY() / d));
    }

    public Point add(Point p) {
        return new Point(this.point.getX() + p.getPoint().getX(), this.point.getY() + p.getPoint().getY());
    }

    public Point sub(Point p) {
        return new Point(this.point.getX() - p.getPoint().getX(), this.point.getY() - p.getPoint().getY());
    }

    public Point mul(int d) {
        return new Point(this.point.getX() * d, this.point.getY() * d);
    }

    public Point nextPoint(Point p, double t)
    {
        int x = (int)(this.getPoint().getX() * (1-t) + p.getPoint().getX() * t);
        int y = (int)(this.getPoint().getY() * (1-t) + p.getPoint().getY() * t);

        return new Point(x, y);
    }

    @Override
    public String toString() {
        return "(" + point.getX() + ", " + point.getY() + ")";
    }

}
