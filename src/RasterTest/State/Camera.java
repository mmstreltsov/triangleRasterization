package RasterTest.State;

import RasterTest.State.Animation.Translation;
import RasterTest.State.Math.BasisChange;
import RasterTest.State.Math.Matrix4x4;
import RasterTest.State.Math.Vector3D;



//GluLookAt
public class Camera implements Transformation {

    private static Camera camera;
    private Translation offset;
    private Vector3D center;
    private Vector3D up;

    public static Camera fabric() {
        if (camera == null) {
            camera = new Camera();
        }
        return camera;
    }

    private Camera() {
        offset = new Translation(new Vector3D(0, 0, -15), true);
        makeCenterDefault();
        makeUpDefault();
    }

    private void makeCenterDefault() {
        this.center = this.offset.getTransl().additional(new Vector3D(0, 0, 1)).toVector();
    }

    private void makeUpDefault(){
        this.up = new Vector3D(0, 1, 0).normalized();
    }

    @Override
    public Matrix4x4 transformation() {
        makeCenterDefault();
        makeUpDefault();

        Vector3D eye = offset.getTransl();
        Matrix4x4 matrix;
        Vector3D z = center.subtracting(eye).toVector().normalized();
        if (Vector3D.crossProduct(z, this.up).magnitude() < 1e-7) {
            matrix = new Matrix4x4(1);
        }
        else {
            Vector3D x = Vector3D.crossProduct(this.up, z).normalized();
            Vector3D y = Vector3D.crossProduct(z, x).normalized();
            matrix = BasisChange.matrix(x, y, z);
        }

        matrix = matrix.multiplyOnMatrix(offset.translating());
        return matrix;
    }

    public Translation getOffset() {
        return offset;
    }

    public void setOffset(Translation offset) {
        this.offset = new Translation(offset, true);
    }

    public Vector3D getCenter() {
        return center;
    }

    public void setCenter(Vector3D center) {
        this.center = center;
    }
}
