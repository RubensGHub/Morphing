package morphCourbe;
import morphing.*;

import java.util.ArrayList;

public class BSpline {
    int deg;
    ArrayList<Point> controlPoints;
    ArrayList<Double> nodeVector;

    public BSpline(int deg, ArrayList<Point> controlPoints, ArrayList<Double> nodeVector) {
        this.deg = deg;
        this.controlPoints = controlPoints;
        this.nodeVector = nodeVector;
    }

    public int getDeg() {
        return deg;
    }
    public void setDeg(int deg) {
        this.deg = deg;
    }
    public ArrayList<Point> getControlPoints() {
        return controlPoints;
    }
    public void setControlPoints(ArrayList<Point> controlPoints) {
        this.controlPoints = controlPoints;
    }
    public ArrayList<Double> getNodeVector() {
        return nodeVector;
    }
    public void setNodeVector(ArrayList<Double> nodeVector) {
        this.nodeVector = nodeVector;
    }





    



}
