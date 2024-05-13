public class Line 
{
    private Couple<Point, Point> line;

    public Line(Point p1, Point p2) 
    {
        line = new Couple<>(p1, p2);
    }

    public Point getBase() 
    {
        return line.getX();
    }

    public Point getHead() 
    {
        return line.getY();
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
        return Math.sqrt(Math.pow(this.getHead().getX()-this.getBase().getX(),2)+Math.pow(this.getHead().getY()-this.getBase().getY(),2));
    }

    public double dist(Point p)
    {
        Point p1 = new Point(p.getX()-this.getBase().getX(), p.getY()-this.getBase().getY());
        Point p2 = new Point(this.getHead().getX()-this.getBase().getX(), this.getHead().getY()-this.getBase().getY());
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
        Point p1 = new Point(p.getX()-this.getBase().getX(), p.getY()-this.getBase().getY());
        Point p2 = new Point(this.getHead().getX()-this.getBase().getX(), this.getHead().getY()-this.getBase().getY());
        double numerator = produitScalaire(p1, p2);
        Point p3 = new Point(this.getHead().getX()-this.getBase().getX(), this.getHead().getY()-this.getBase().getY());
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
