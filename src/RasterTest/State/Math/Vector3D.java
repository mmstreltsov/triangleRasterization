package RasterTest.State.Math;

public class Vector3D extends HomogeneousCoord {
    public Vector3D(double x, double y, double z) {
        super(x, y, z, 0);
    }

    public static Vector3D normalized(Vector3D a) {
        double val = Math.pow(a.getX(), 2) + Math.pow(a.getY(), 2) + Math.pow(a.getZ(), 2);
        val = Math.sqrt(val);
        return new Vector3D(a.getX() / val, a.getY() / val, a.getZ() / val);
    }

    public static Vector3D crossProduct(Vector3D a, Vector3D b) {
        double s1 = a.getY() * b.getZ() - a.getZ() * b.getY();
        double s2 = a.getZ() * b.getX() - a.getX() * b.getZ();
        double s3 = a.getX() * b.getY() - a.getY() * b.getX();
        return new Vector3D(s1, s2, s3);
    }

    public static double dotProduct(Vector3D a, Vector3D b) {
        return a.getX() * b.getX() + a.getY() * b.getY() + a.getZ() * b.getZ();
    }
}
