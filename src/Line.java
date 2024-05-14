public class Line 
{
    private Couple<Point, Point> line;
    private Couple<Point,Point> vector;

    public Line(Couple<Point,Point> vector) 
    {
        this.vector = vector;
    }

    public Line(Point start, Point end) 
    {
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

    public void setBase(Point p) 
    {
        line.setX(p);
    }

    public void setHead(Point p) 
    {
        line.setY(p);
    }

    public double getLength()
    {
        return Math.sqrt(Math.pow(this.getEnd().getX()-this.getStart().getX(),2)+Math.pow(this.getEnd().getY()-this.getStart().getY(),2));
    }

    public double dist(Point p) {
        Point p1 = new Point(p.getX() - this.getStart().getX(), p.getY() - this.getStart().getY());
        Line perpendicularLine = this.perpendicular();
        Point p2 = new Point(perpendicularLine.getEnd().getX() - perpendicularLine.getStart().getX(), 
                             perpendicularLine.getEnd().getY() - perpendicularLine.getStart().getY());
        Line l1 = new Line(p1, new Point(p2.getX(), p2.getY()));
        double numerator = this.produitScalaire(l1);
        double denominator = this.norme();
        
        if (denominator == 0) {
            return Double.POSITIVE_INFINITY;
        } else {
            return Math.abs(numerator / denominator);
        }
    }
    
    public double hauteur(Point p) {
        Point p1 = new Point(p.getX() - this.getStart().getX(), p.getY() - this.getStart().getY());
        Point p2 = new Point(this.getEnd().getX() - this.getStart().getX(), this.getEnd().getY() - this.getStart().getY());
        Line l1 = new Line(p1, p2);
        double numerator = this.produitScalaire(l1);
        double denominator = Math.pow(this.norme(), 2);
        
        if (denominator == 0) {
            return Double.POSITIVE_INFINITY;
        } else {
            return Math.abs(numerator / denominator);
        }
    }
    

    public Line perpendicular() 
    {
        Point start = this.getStart();
        Point end = this.getEnd();
        int dx = end.getY() - start.getY();
        int dy = start.getX() - end.getX();
        Point newEnd = new Point(start.getX() + dx, start.getY() + dy);
        return new Line(start, newEnd);
    }

    public double produitScalaire(Line l) 
    {
        Point start1 = this.getStart();
        Point end1 = this.getEnd();
        Point start2 = l.getStart();
        Point end2 = l.getEnd();

        int x1 = end1.getX() - start1.getX();
        int y1 = end1.getY() - start1.getY();
        int x2 = end2.getX() - start2.getX();
        int y2 = end2.getY() - start2.getY();

        return x1 * x2 + y1 * y2;
    }

    public double norme() 
    {
        Point start = this.getStart();
        Point end = this.getEnd();
        return Math.sqrt(Math.pow(end.getX() - start.getX(), 2) + Math.pow(end.getY() - start.getY(), 2));
    }
    
}
