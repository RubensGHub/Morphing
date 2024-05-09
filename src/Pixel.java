public class Pixel extends Point {

    private int r;
    private int g;
    private int b;

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }

    public void setR(int c) {
        this.r = c;
    }

    public void setG(int c) {
        this.g = c;
    }

    public void setB(int c) {
        this.b = c;
    }

    public Pixel(double x, double y, int r, int g, int b) {
        setX(x);
        setY(y);
        setR(r);
        setG(g);
        setB(b);
    }
}
