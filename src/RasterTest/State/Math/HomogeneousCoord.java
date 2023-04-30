package RasterTest.State.Math;

public class HomogeneousCoord {
    private double x;
    private double y;
    private double z;
    private double w;

    public HomogeneousCoord(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vector3D toVector() {
        return new Vector3D(x, y, z);
    }

    public Coord3D toPoint() {
        return new Coord3D(x, y, z);
    }
    @Override
    public String toString() {
        return "(" + x + ") + (" + y + ") + (" + z + ")";
    }

    public HomogeneousCoord additional(HomogeneousCoord other) {
        return new HomogeneousCoord(x + other.x, y + other.y, z + other.z, w + other.w);
    }

    public HomogeneousCoord subtracting(HomogeneousCoord other) {
        return new HomogeneousCoord(x - other.x, y - other.y, z - other.z, w - other.w);
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

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }
}
