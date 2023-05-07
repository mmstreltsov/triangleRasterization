package RasterTest.State.Math;

public class Plane {
    private final double A;
    private final double B;
    private final double C;
    private final double D;
    private final Vector3D normal;

    public Plane(Vector3D v1, Vector3D v2, Coord3D point) {
        v1 = v1.normalized();
        v2 = v2.normalized();

        this.normal = Vector3D.crossProduct(v1, v2).normalized();

        this.A = normal.getX();
        this.B = normal.getY();
        this.C = normal.getZ();

        this.D = -(this.A * point.getX() + this.B * point.getY() + this.C * point.getZ());
    }

    public Plane(Coord3D point1, Coord3D point2, Coord3D point3) {
        this(point1.subtracting(point3).toVector(), point2.subtracting(point3).toVector(), point3);
    }

    private final static double EPS = 1e-7;
    public Coord3D intersectionWithLine(Vector3D vector, Coord3D point) {
        vector = vector.normalized();
        double rightPart = - (this.D + (this.A * point.getX() + this.B * point.getY() + this.C * point.getZ()));
        double leftPart = this.A * vector.getX() + this.B * vector.getY() + this.C * vector.getZ();
        if (Math.abs(leftPart) < EPS) {
            return point;
        }
        double t = rightPart / leftPart;
        Coord3D intersect = new Coord3D(point.getX() + vector.getX() * t, point.getY() + vector.getY() * t, point.getZ() + vector.getZ() * t);
        return intersect;
    }

    public Coord3D findPointProjection(Coord3D point) {
       return intersectionWithLine(this.normal, point);
    }

    @Override
    public String toString() {
        return "A: " + A + " B: " + B + " C: " + C + " D: " + D;
    }

    public double getA() {
        return A;
    }

    public double getB() {
        return B;
    }

    public double getC() {
        return C;
    }

    public double getD() {
        return D;
    }

    public Vector3D getNormal() {
        return normal;
    }

}
