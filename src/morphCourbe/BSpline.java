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

    
}
