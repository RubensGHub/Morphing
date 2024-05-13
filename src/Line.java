public class Line {
    private Couple<Point,Point> vector;

    public Line(Couple<Point,Point> vector) {
        this.vector = vector;
    }

    public Line(Point start, Point end) {
        this.vector = new Couple<Point,Point>(start, end);
    }

    public Couple<Point,Point> getVector() {
        return this.vector;
    }

    public void setVector(Couple<Point,Point> vector) {
        this.vector = vector;
    }

    public Point getStart() {
        return this.vector.getX();
    }

    public Point getEnd() {
        return this.vector.getY();
    }

    public void setStart(Point start) {
        this.vector.setX(start);
    }

    public void setEnd(Point end) {
        this.vector.setY(end);
    }




    


}
