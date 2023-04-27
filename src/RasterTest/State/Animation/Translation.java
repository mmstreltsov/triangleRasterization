package RasterTest.State.Animation;

import RasterTest.State.Math.Matrix4x4;
import RasterTest.State.Math.Vector3D;

public class Translation {
    private Vector3D translation;
    private boolean isReversed;
    public Vector3D getTranslation() {
        return translation;
    }

    public void setTranslation(Vector3D translation) {
        this.translation = translation;
    }


    public Translation(Vector3D translation) {
        this.translation = translation;
    }
    public Translation(Translation other, boolean isReversed) {
        this.isReversed = isReversed;
        if (isReversed) {
            this.translation = new Vector3D(-other.translation.getX(), -other.translation.getY(), -other.translation.getZ());
        }
        else {
            this.translation = other.getTranslation();
        }
    }

    public Translation(Vector3D other, boolean isReversed) {
        this.isReversed = isReversed;
        if (isReversed) {
            this.translation = new Vector3D(-other.getX(), -other.getY(), -other.getZ());
        }
        else {
            this.translation = other;
        }
    }

    public Translation() {
        this.translation = new Vector3D(0, 0, 0);
    }

    public Matrix4x4 translating() {
        Matrix4x4 matrix = new Matrix4x4();
        matrix.setM11(1.);
        matrix.setM22(1.);
        matrix.setM33(1.);
        matrix.setM44(1.);

        matrix.setM14(translation.getX());
        matrix.setM24(translation.getY());
        matrix.setM34(translation.getZ());
        return matrix;
    }
}
