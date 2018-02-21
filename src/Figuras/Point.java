package Figuras;

public class Point {
    public Point(){}
    public Point(int x, int y) {
        X = x;
        Y = y;
    }

    public int x() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int y() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    private int X;
    private int Y;

}
