package RasterTest.State.Math;

import java.util.Objects;

public class Coord2D {
    private double x;
    private double y;


    @Override
    public String toString() {
        return "(" + x  + ", " + + y + ')';
    }

    private double zCoord;

    public Coord2D(double x, double y) {
        this.x = x;
        this.y = y;
        this.zCoord = 0;
    }

    public Coord2D(double x, double y, double z_coord) {
        this.x = x;
        this.y = y;
        this.zCoord = z_coord;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coord2D coord2D = (Coord2D) o;
        return Double.compare(coord2D.x, x) == 0 && Double.compare(coord2D.y, y) == 0;
    }
    public double getzCoord() {
        return zCoord;
    }

    public void setzCoord(double zCoord) {
        this.zCoord = zCoord;
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}
