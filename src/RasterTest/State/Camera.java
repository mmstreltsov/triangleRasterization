package RasterTest.State;

import RasterTest.State.Animation.Rotation;
import RasterTest.State.Animation.Translation;
import RasterTest.State.Math.EulerAngles;
import RasterTest.State.Math.Matrix4x4;
import RasterTest.State.Math.Vector3D;

public class Camera implements Transformation {
    private Translation offset;
    private Rotation rotation;

    public Camera(Translation offset, Rotation rotation) {
        this.offset = new Translation(offset, true);
        this.rotation = rotation;
    }

    public Camera() {
        offset = new Translation(new Vector3D(0, 0, -10), true);
        rotation = new Rotation(new EulerAngles(0., 0., 0.), true);
    }

    @Override
    public Matrix4x4 transformation() {
        Matrix4x4 matrix = new Matrix4x4(1.);
        matrix = matrix.multiplyOnMatrix(offset.translating());
        matrix = matrix.multiplyOnMatrix(rotation.rotating());
        return matrix;
    }

    public Translation getOffset() {
        return offset;
    }

    public void setOffset(Translation offset) {
        this.offset = offset;
    }

    public Rotation getRotation() {
        return rotation;
    }

    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }
}
