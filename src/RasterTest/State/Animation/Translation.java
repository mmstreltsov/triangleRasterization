package RasterTest.State.Animation;

import RasterTest.State.Math.Matrix4x4;
import RasterTest.State.Math.Vector3D;

public class Translation {
    private Vector3D transl;
    private boolean isReversed;
    public Vector3D getTransl() {
        return transl;
    }

    public void setTransl(Vector3D transl) {
        this.transl = transl;
    }
    public void setTranslation(double x, double y, double z) {
        this.transl = new Vector3D(x, y, z);
    }


    public Translation(double x, double y, double z) {
        this.transl = new Vector3D(x, y, z);
    }
    public Translation(Vector3D transl) {
        this.transl = transl;
    }
    public Translation(Translation other, boolean isReversed) {
        this.isReversed = isReversed;
        this.transl = other.getTransl();
    }

    public Translation(Vector3D other, boolean isReversed) {
        this.isReversed = isReversed;
        this.transl = other;
    }

    public Translation() {
        this.transl = new Vector3D(0, 0, 0);
    }

    public Matrix4x4 translating() {
        Matrix4x4 matrix = new Matrix4x4();
        matrix.setM11(1.);
        matrix.setM22(1.);
        matrix.setM33(1.);
        matrix.setM44(1.);

        matrix.setM14(transl.getX());
        // because of implementation. (0, 0) in other angle
        matrix.setM24(-1 * transl.getY());
        matrix.setM34(transl.getZ());

        if (isReversed) {
            matrix.setM14(matrix.getM14() * -1);
            matrix.setM24(matrix.getM24() * -1);
            matrix.setM34(matrix.getM34() * -1);
        }
        return matrix;
    }
}
