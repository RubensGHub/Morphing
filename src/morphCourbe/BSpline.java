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





    



}
