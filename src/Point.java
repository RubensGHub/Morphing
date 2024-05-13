public class Point {
    private Couple<Integer, Integer> point;

    public Point(int x, int y) {
        point = new Couple<>(x, y);
    }

    public Couple<Integer, Integer> getPoint() {
        return this.point;
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
