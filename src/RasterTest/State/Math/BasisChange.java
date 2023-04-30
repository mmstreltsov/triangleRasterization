package RasterTest.State.Math;

public class BasisChange {
    public static Matrix4x4 matrix(Vector3D x, Vector3D y, Vector3D z) {
        Matrix4x4 matrix = new Matrix4x4();

        matrix.setM11(x.getX());
        matrix.setM12(y.getX());
        matrix.setM13(y.getX());

        matrix.setM21(x.getY());
        matrix.setM22(y.getY());
        matrix.setM23(z.getY());

        matrix.setM31(x.getZ());
        matrix.setM32(y.getZ());
        matrix.setM33(z.getZ());

        matrix.setM44(1.);
        return matrix;
    }
}
