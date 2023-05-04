package RasterTest.State.Animation;

import RasterTest.State.Math.Angles;
import RasterTest.State.Math.Matrix4x4;

public class Rotation {

    private Angles rotation;
    private boolean isReversed;

    public Angles getRotation() {
        return rotation;
    }


    public void setRotation(Angles rotation) {
        this.rotation = rotation;
    }
    public void setRotation(double x, double y, double z) {
        this.rotation = new Angles(x, y, z);
    }

    public Rotation(double x, double y, double z) {
        this.rotation = new Angles(x, y, z);
    }

    public Rotation(Angles rotation) {
        this.rotation = rotation;
    }

    public Rotation(Angles rotation, boolean isReversed) {
        this.isReversed = isReversed;
        this.rotation = rotation;
    }
    public Rotation(Rotation rotation, boolean isReversed) {
        this.isReversed = isReversed;
        this.rotation = rotation.getRotation();
    }

    public Rotation() {
        this.rotation = new Angles(0., 0., 0.);
    }

    public Matrix4x4 rotating() {
        Matrix4x4 matrix = new Matrix4x4(1.);
        if (isReversed) {
            matrix = matrix.multiplyOnMatrix(rotation.RGammaNegative());
            matrix = matrix.multiplyOnMatrix(rotation.RBetaNegative());
            matrix = matrix.multiplyOnMatrix(rotation.RAlphaNegative());
        }
        else {
            matrix = matrix.multiplyOnMatrix(rotation.RAlpha());
            matrix = matrix.multiplyOnMatrix(rotation.RBeta());
            matrix = matrix.multiplyOnMatrix(rotation.RGamma());
        }
        return matrix;
    }

    @Override
    public String toString() {
        return "Rotation{" +
                "rotation=" + rotation +
                '}';
    }
}
