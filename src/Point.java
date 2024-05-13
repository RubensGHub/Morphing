public class Point {
    private Couple<Integer, Integer> point;

    public Point(int x, int y) {
        point = new Couple<>(x, y);
    }

    public Couple<Integer, Integer> getPoint() {
        return point;
    }

    public void setPoint(Couple<Integer, Integer> point) {
        this.point = point;
    }

    public int getX() {
        return point.getY();
    }

    public int getY() {
        return point.getY();
    }

    public void setX(int x) {
        point.setX(x);
    }

    public void setY(int y) {
        point.setY(y);
    }


}
