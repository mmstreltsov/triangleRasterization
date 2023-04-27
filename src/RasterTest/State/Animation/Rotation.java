package RasterTest.State.Animation;

import RasterTest.State.Math.EulerAngles;
import RasterTest.State.Math.Matrix4x4;

public class Rotation {

    private EulerAngles rotation;
    private boolean isReversed;

    public EulerAngles getRotation() {
        return rotation;
    }


    public void setRotation(EulerAngles rotation) {
        this.rotation = rotation;
    }
    public void setRotation(double x, double y, double z) {
        this.rotation = new EulerAngles(x, y, z);
    }

    public Rotation(double x, double y, double z) {
        this.rotation = new EulerAngles(x, y, z);
    }

    public Rotation(EulerAngles rotation) {
        this.rotation = rotation;
    }

    public Rotation(EulerAngles rotation, boolean isReversed) {
        this.isReversed = isReversed;
        if (isReversed) {
            this.rotation = new EulerAngles(-rotation.getAlpha(), -rotation.getBeta(), -rotation.getGamma());
        }
        else {
            this.rotation = rotation;
        }
    }
    public Rotation(Rotation rotation, boolean isReversed) {
        this.isReversed = isReversed;
        if (isReversed) {
            this.rotation = new EulerAngles(-rotation.getRotation().getAlpha(), -rotation.getRotation().getBeta(), -rotation.getRotation().getGamma());
        }
        else {
            this.rotation = rotation.getRotation();
        }
    }

    public Rotation() {
        this.rotation = new EulerAngles(0., 0., 0.);
    }

    public Matrix4x4 rotating() {
        Matrix4x4 matrix = new Matrix4x4(1.);
        if (isReversed) {
            matrix = matrix.multiplyOnMatrix(rotation.RGamma());
            matrix = matrix.multiplyOnMatrix(rotation.RBeta());
            matrix = matrix.multiplyOnMatrix(rotation.RAlpha());
        }
        else {
            matrix = matrix.multiplyOnMatrix(rotation.RAlpha());
            matrix = matrix.multiplyOnMatrix(rotation.RBeta());
            matrix = matrix.multiplyOnMatrix(rotation.RGamma());
        }
        return matrix;
    }

}
