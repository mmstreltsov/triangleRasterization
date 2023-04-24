package RasterTest.State.Math;

import java.util.Objects;

public class Coord3D extends Coord2D {
    private double z;


    public Coord3D(double x, double y, double z) {
        super(x, y);
        this.z = z;
    }
    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coord3D vertex = (Coord3D) o;
        return this.getX() == vertex.getX() && this.getY() == vertex.getY() && this.getZ() == vertex.getZ();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getX(), this.getY(), this.getZ());
    }
}
