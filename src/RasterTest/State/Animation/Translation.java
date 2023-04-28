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
        if (isReversed) {
            this.transl = new Vector3D(-other.transl.getX(), -other.transl.getY(), -other.transl.getZ());
        }
        else {
            this.transl = other.getTransl();
        }
    }

    public Translation(Vector3D other, boolean isReversed) {
        this.isReversed = isReversed;
        if (isReversed) {
            this.transl = new Vector3D(-other.getX(), -other.getY(), -other.getZ());
        }
        else {
            this.transl = other;
        }
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
        matrix.setM24(transl.getY());
        matrix.setM34(transl.getZ());
        return matrix;
    }
}
