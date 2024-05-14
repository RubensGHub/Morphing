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

    public double dist(Point p)
    {
        Point p1 = new Point(p.getX()-this.getStart().getX(), p.getY()-this.getStart().getY());
        Point p2 = new Point(this.getEnd().getX()-this.getStart().getX(), this.getEnd().getY()-this.getStart().getY());
        Point p3 = perpendicular(p2);
        double numerator = produitScalaire(p1, p3);
        double denominateur = norme(p3);
        if (denominateur == 0)
        {
            return 0;
        }
        else
        {
            return Math.abs(numerator/denominateur);
        }
    }

    public double hauteur(Point p)
    {
        Point p1 = new Point(p.getX()-this.getStart().getX(), p.getY()-this.getStart().getY());
        Point p2 = new Point(this.getEnd().getX()-this.getStart().getX(), this.getEnd().getY()-this.getStart().getY());
        double numerator = produitScalaire(p1, p2);
        Point p3 = new Point(this.getEnd().getX()-this.getStart().getX(), this.getEnd().getY()-this.getStart().getY());
        double denominateur = Math.pow(norme(p3),2);
        if (denominateur == 0)
        {
            return 0;
        }
        else
        {
            return Math.abs(numerator/denominateur);
        }
    }

    public Point perpendicular(Point p)
    {
        return new Point(-p.getY(), p.getX()); 
    }

    public double produitScalaire(Point p1, Point p2)
    {
        return p1.getX()*p2.getX()+p1.getY()*p2.getY();
    }

    public double norme(Point p)
    {
        return Math.sqrt(Math.pow(p.getX(),2)+Math.pow(p.getY(),2));
    }
    
}
